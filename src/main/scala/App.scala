import org.neo4j.driver.v1.{AuthTokens, Driver, GraphDatabase, Session}
import java.sql.{Connection, DriverManager, ResultSet}

import com.google.gson.stream.JsonReader
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.InputStreamReader

import models._

case class Connector(config: Config) {
  private val _target_driver: Driver = GraphDatabase.Driver(
    config.db.target.uri,
    AuthTokens.Basic(config.db.target.username, config.db.target.password)
  )
  val sqlContext = SparkSession()
    .builder()
    .appName("Data Injector")
    .config("spark.sql.warehouse")
  private val logs: DataFrame[Log] = sqlContext
    .read
    .format("jdbc")
    .option("url", config.db.source.uri)
    .option("dbtable","log")
    .load()

  def target = this._target_driver
  def source = this._source_conn

  def close() {
    this._target_driver.close()
    this._source_conn.close()
  }
  def read(sql: String): Unit = {
    val statement = _source_conn.createStatement()
    val resultSet = statement.executeQuery(sql)
    while ( resultSet.next() ) {
      val host = resultSet.getString("host")
      val user = resultSet.getString("user")
      println("host, user = " + host + ", " + user)
    }
  }
}
