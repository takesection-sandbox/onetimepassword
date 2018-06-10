import Dependencies._

lazy val root = (project in file("."))
  .settings(
    name := "jp.pigumer.onetime",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.6",
    libraryDependencies ++= Seq(
      commonsCodec,
      scalatest
    )
  )
