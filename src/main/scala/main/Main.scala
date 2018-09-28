package main

import main.models.{Config, DBConfig, DBsConfig, Log}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.neo4j.driver.v1.{AuthTokens, Driver, GraphDatabase}

object Main extends App {
//  val config: Config = Config(
//    DBsConfig(
//      DBConfig(args(0), args(1), args(2), args(3)),
//      DBConfig(args(4), args(5), args(6), args(7))
//    )
//  )
//  val _target_driver: Driver = GraphDatabase.driver(
//    config.db.target.uri,
//    AuthTokens.basic(config.db.target.username, config.db.target.password)
//  )
  val spark = SparkSession
    .builder
    .appName("LogAnalyzer")
    .config("spark.master", "local")
    .getOrCreate
  import spark.implicits._

  val logs: Dataset[Log] = spark.sqlContext
    .read
    .format("jdbc")
    .option("url", "jdbc:postgresql://localhost:5432/test?user=shuning")
    .option("dbtable","log_view")
    .option("driver", "org.postgresql.Driver")
    .load()
    .as[Log]

  println(logs.count())
  spark.close()
}
