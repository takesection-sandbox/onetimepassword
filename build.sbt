import Dependencies._

lazy val root = (project in file("."))
  .settings(
    name := "jp.pigumer.onetime",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.6",
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    libraryDependencies ++= Seq(
      commonsCodec,
      scalatest
    ),
    mainClass := Some("jp.pigumer.onetime.OneTimePassword")
  )
