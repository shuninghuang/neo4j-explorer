package main.models
case class DBConfig(uri: String, username: String, name: String, password: String)
case class DBsConfig(target: DBConfig, source: DBConfig)
case class Config(db: DBsConfig)
