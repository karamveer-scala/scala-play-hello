name := """play-scala-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)


scalaVersion := "2.11.12"
val sparkVersion = "2.4.5"
autoScalaLibrary := false

herokuAppName in Compile := "play-scala-seed"
//crossScalaVersions := Seq("2.12.8", "2.11.12")

// libraryDependencies += guice
// libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test




libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test,
  "com.lihaoyi" %% "ujson" % "0.7.1",
  guice,
   "com.h2database" % "h2" % "1.3.168",
 "javax.persistence" % "javax.persistence-api" % "2.2",
 "org.apache.commons" % "commons-io" % "1.3.2",
  			"org.apache.spark" %% "spark-core" % sparkVersion,
  			"org.slf4j" % "slf4j-nop" % "1.6.4",
   			"org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
		    "tech.tablesaw" % "tablesaw-core" % "0.24.5",
		    "com.crealytics" % "spark-excel_2.11" % "0.9.8",
		    "org.apache.spark" %% "spark-sql" % sparkVersion,
			"org.apache.hadoop" % "hadoop-client" % "2.7.2",
		    "org.webjars" % "bootstrap" % "3.3.6",
			"com.heroku.sdk" % "heroku-deploy" % "0.1.5"
)

libraryDependencies ~= { _.map(_.exclude("org.slf4j", "slf4j-log4j12")) }

// https://mvnrepository.com/artifact/org.apache.tika/tika-core
libraryDependencies += "org.apache.tika" % "tika-core" % "1.22"

// https://mvnrepository.com/artifact/org.apache.tika/tika-parsers
libraryDependencies += "org.apache.tika" % "tika-parsers" % "1.10"

// https://mvnrepository.com/artifact/com.google.guava/guava
libraryDependencies += "com.google.guava" % "guava" % "24.1.1-jre"






dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.8"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.0"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8"
dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.8"
