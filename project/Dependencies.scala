import sbt._

object Dependencies {
  lazy val commonsCodec = "commons-codec" % "commons-codec" % "1.11"
  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.5" % Test
}