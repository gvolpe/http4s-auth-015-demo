name := """http4s-auth-015-demo"""

version := "1.0"

scalaVersion := "2.11.8"

lazy val http4sVersion = "0.15.1"

libraryDependencies ++= Seq(
    "org.scalatest"   	%% "scalatest"            % "2.2.4"       % "test",
    "com.github.gvolpe"	%% "http4s-auth"	  % "0.1" excludeAll ExclusionRule(organization = "org.http4s"),
    "org.http4s"      	%% "http4s-dsl"           % http4sVersion,
    "org.http4s"      	%% "http4s-blaze-server"  % http4sVersion,
    "org.http4s"      	%% "http4s-blaze-client"  % http4sVersion,
    "org.http4s"      	%% "http4s-circe"         % http4sVersion,
    "ch.qos.logback"  	%  "logback-classic" % "1.0.6" % "runtime"
)
