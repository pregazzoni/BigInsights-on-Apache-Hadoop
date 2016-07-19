## Overview

This example shows how to programmatically interact with HDFS on the BigInsights cluster.  Some examples of programmatic interactions may be:

- An [ETL](https://en.wikipedia.org/wiki/Extract,_transform,_load) tool job that needs to exchange data with BigInsights
- A [Microservice](https://en.wikipedia.org/wiki/Microservices) that needs to access data in BigInsights

BigInsights for Apache Hadoop clusters are secured using [Apache Knox](https://knox.apache.org/).  Apache Knox is a REST API Gateway for interacting with Apache Hadoop clusters.  As such, the HDFS service is not available to external clients of the BigInsights cluster, instead external clients interact with HDFS through the [WebHDFS REST API](http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-hdfs/WebHDFS.html).

The Apache Knox project provides a [Java client library](https://cwiki.apache.org/confluence/display/KNOX/Client+Usage) and this can be used for interacting with the REST API.  Using a library is usally more productive because the library provides a higher level of abstraction than when working directly with the REST API.

There are three examples in this folder written in the [Groovy Language](http://www.groovy-lang.org/) and the Knox Java client library:

- [Ls.groovy](./Ls.groovy) - this example lists files and folders in the root HDFS folder
- [Mkdir.groovy](./Mkdir.groovy) - this example creates a folder in HDFS
- [Put.groovy](./Put.groovy) - this example uploads a file to HDFS

More information on WebHDFS / Apache Knox:

- [WebHDFS REST API](http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-hdfs/WebHDFS.html)
- [BigInsights Apache Knox documentation](https://www.ibm.com/support/knowledgecenter/en/SSPT3X_4.2.0/com.ibm.swg.im.infosphere.biginsights.admin.doc/doc/knox_overview.html)

See also:

- [WebHdfsCurl example](./WebHdfsCurl) this example will be useful for developers who want to use only REST to interact with the API and don't want to use the knox client library, e.g. non-JVM language developers.

## Devloper experience

- Be able to read code written in a high level language such as [Groovy](http://www.groovy-lang.org/)
- It is recommended that you understand the [Gradle](https://gradle.org/) build tool.  Some free training is available [here](https://www.udacity.com/course/gradle-for-android-and-java--ud867)

## Application Requirements

You have meet the Pre-Requisites and have followed the Setup Instructions in the top level [README](../README.md)

## Run the example

- In a command prompt window, change into the directly containing this README and run the following
   - `./gradlew Example` (OS X / *nix)
   - `gradlew.bat Example` (Windows)
- You can also run the example from the top level project folder using the gradle `-p` argument
   - `./gradlew -p examples/WebHdfsGroovy Example` (OS X / *nix)
   - `gradlew.bat -p examples/WebHdfsGroovy Example` (Windows)

## Decomposition Instructions

Instructions on how a developer/architect would take the sample application and extract the relevant code for reuse. For example, if the app is a "Hello World" to demonstrate integration of a specific service, explain what code is relevant to this integration and how one would reuse it.

The example uses a gradle build file [./build.gradle](./build.gradle) to retrieve the knox client libraries, set up a groovy session and execute the groovy scripts.

