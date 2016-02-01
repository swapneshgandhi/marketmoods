import AssemblyKeys._

assemblySettings

name := "MarketMood"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "Job Server Bintray" at "https://dl.bintray.com/spark-jobserver/maven"

libraryDependencies ++= Seq(
  "edu.stanford.nlp" % "stanford-corenlp" % "3.4.1",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.4.1" classifier "models",
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "org.apache.spark" % "spark-core_2.10" % "1.5.1" % "provided",
  "spark.jobserver" %% "job-server-api" % "0.6.0" % "provided",
  "org.xerial.snappy" % "snappy-java" % "1.1.1.7"
)

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

excludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter {_.data.getName == "stanford-corenlp-3.4.1-models.jar"}
}

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) => {
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.last
  case PathList("org", "apache", xs@_*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs@_*) => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case x => old(x)
}
}