import scala.sys.process._
import org.scalajs.linker.interface.ModuleSplitStyle

import sbt.Keys._
import sbt._

ThisBuild / organization  := "org.virtuslab"

ThisBuild / licenses := Seq(("Apache-2.0" -> url("https://opensource.org/licenses/apache-2-0")))
ThisBuild / description := "Type-safe Scala.js facade for writing k6 load testing scenarios in Scala."
ThisBuild / homepage := Some(url("https://github.com/VirtusLab/k6-scala"))
ThisBuild / developers := List(
  Developer("wiacekm", "Michał Wiącek", "", url("https://github.com/wiacekm"))
)
ThisBuild / scmInfo := Some(ScmInfo(url("https://github.com/VirtusLab/k6-scala"), "scm:git:git@github.com:VirtusLab/k6-scala.git"))
ThisBuild / publishMavenStyle := true

//settings for all the projects
lazy val commonSettings = Seq(
  scalaVersion := "3.5.0",
  crossScalaVersions := Seq("2.12.10", "2.13.16", "3.3.6"),
  name := "k6-scala",
  updateOptions := updateOptions.value.withCachedResolution(true),
  scalacOptions ++= Seq(
    "-deprecation"
  ),
  libraryDependencies ++= Seq(
    "org.scalatest" %%% "scalatest" % "3.2.19" % Test
  )
)

lazy val k6scala: Project =
  Project(id = "k6scala", base = file("k6scala"))
    .enablePlugins(ScalaJSBundlerPlugin)
    .settings(commonSettings: _*)

lazy val root = Project("root", file("."))
  .settings(commonSettings)
  .dependsOn(k6scala)
  .aggregate(k6scala)
