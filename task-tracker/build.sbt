scalaVersion := "3.8.4"
val circeVersion = "0.14.16"

lazy val root = rootProject
  .settings(
    name := "task-tracker",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core"    % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser"  % circeVersion,
      "io.circe" %% "circe-java8"   % circeVersion
    )
  )
