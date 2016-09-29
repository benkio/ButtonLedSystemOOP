name := """buttonLedSystem"""

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "org.scalacheck" % "scalacheck_2.11" % "1.13.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  "org.specs2" %% "specs2-scalacheck" % "3.8.5" % "test",
  "org.specs2" %% "specs2-core" % "3.8.5" % "test")

scalacOptions in Test ++= Seq("-Yrangepos")
