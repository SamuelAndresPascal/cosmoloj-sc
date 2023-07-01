ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.9.3" % Test

lazy val root = (project in file("."))
  .settings(
    name := "cosmoloj-sc",
    idePackagePrefix := Some("com.cosmoloj.sc")
  )
  .aggregate(unitSimpleApi, unitSimpleImpl)

lazy val unitSimpleApi = (project in file("unit-simple-api"))
  .settings(
    name := "unit-simple-api",
    idePackagePrefix := Some("com.cosmoloj.sc.unit.simple.api")
  )

lazy val unitSimpleImpl = (project in file("unit-simple-impl"))
  .settings(
    name := "unit-simple-impl",
    idePackagePrefix := Some("com.cosmoloj.sc.unit.simple.impl"),
    libraryDependencies += "org.junit.jupiter" % "junit-jupiter-api" % "5.9.3" % Test,
    libraryDependencies += "net.aichler" % "jupiter-interface" % "0.11.1" % Test
  )
  .dependsOn(unitSimpleApi)