name := "akka-clustering-pubsub"

organization := "kykl"

version := "0.1"

homepage := Some(url("https://github.com/kykl/akka-clustering-pubsub"))

startYear := Some(2013)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/kykl/akka-clustering-pubsub"),
    "scm:git:https://github.com/kykl/akka-clustering-pubsub.git",
    Some("scm:git:git@github.com:kykl/akka-clustering-pubsub.git")
  )
)

/* scala versions and options */
scalaVersion := "2.11.6"

// These options will be used for *all* versions.
scalacOptions ++= Seq(
  "-deprecation"
  ,"-unchecked"
  ,"-encoding", "UTF-8"
  ,"-Xlint"
  ,"-Yclosure-elim"
  ,"-Yinline"
  ,"-Xverify"
  ,"-feature"
  ,"-language:postfixOps"
)

val akka = "2.4.0-RC1"

/* dependencies */
libraryDependencies ++= Seq (
  "com.github.nscala-time" %% "nscala-time" % "1.2.0"
  // -- testing --
  , "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"
  // -- Logging --
  ,"ch.qos.logback" % "logback-classic" % "1.1.1"
  // -- Akka --
  ,"com.typesafe.akka" %% "akka-testkit" % akka % "test"
  ,"com.typesafe.akka" %% "akka-actor" % akka
  ,"com.typesafe.akka" %% "akka-slf4j" % akka
  ,"com.typesafe.akka" %% "akka-cluster" % akka
  ,"com.typesafe.akka" %% "akka-contrib" % akka
  // -- json --
  ,"org.json4s" %% "json4s-jackson" % "3.2.10"
  // -- config --
  ,"com.typesafe" % "config" % "1.2.0"
)

maintainer := "Kenneth Lee <kennneth.lee@gmail.com"

dockerExposedPorts in Docker := Seq(1600)

dockerEntrypoint in Docker := Seq("sh", "-c", "CLUSTER_IP=`/sbin/ifconfig eth0 | grep 'inet addr:' | cut -d: -f2 | awk '{ print $1 }'` bin/clustering $*")

dockerRepository := Some("kykl")

dockerBaseImage := "java"
enablePlugins(JavaAppPackaging)
