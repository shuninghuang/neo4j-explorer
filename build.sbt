name := "neo4j-explorer"

version := "0.1"

scalaVersion := "2.11.12"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.1",
  "org.neo4j.driver" % "neo4j-java-driver" % "1.6.3",
  "com.github.nscala-time" %% "nscala-time" % "2.20.0",
  "com.google.code.gson" % "gson" % "2.8.5"
)