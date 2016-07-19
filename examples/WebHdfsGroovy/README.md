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

- [WebHdfsCurl example](../WebHdfsCurl) this example will be useful for developers who want to use only REST to interact with the API and don't want to use the knox client library, e.g. non-JVM language developers. [[coming soon]]

## Devloper experience

Developers will gain the most from these examples if they are:

- Comfortable using Windows, OS X or *nix command prompts
- Familiar with compiling and running java applications
- Able to read code written in a high level language such as [Groovy](http://www.groovy-lang.org/)
- Familiar with the [Gradle](https://gradle.org/) build tool

## Application Requirements

You have met the [pre-requisites](../../README.md#pre-requisites) and have followed the [setup instructions](../../README.md#setup-instructions) in the top level [README](../README.md)

## Run the example

To run the example [Ls.groovy](./Ls.groovy), in a command prompt window:

   - change into the directly containing this example and run the following
      - `./gradlew Ls` (OS X / *nix)
      - `gradlew.bat Ls` (Windows)
   - You can also run the example by changing into the top level project folder, and then using the gradle `-p` argument to provide the path to this example
      - `./gradlew -p examples/WebHdfsGroovy Ls` (OS X / *nix)
      - `gradlew.bat -p examples/WebHdfsGroovy Ls` (Windows)

Replace `Ls` with `Mkdir` to run the example to create a directory in HDFS, or `Put` to upload a file to HDFS.

## Decomposition Instructions

The examples uses a gradle build file [./build.gradle](./build.gradle) when you run `./gradlew` or `gradle.bat`.  The build.gradle for this example does the following:

- download the knox java client library and make it available to Java
- download the groovy library and make it available to Java
- compile the groovy script so that it is executable by Java
- read the connection details in your connection.properties file
- run the compiled groovy script as a Java application, making the connection details available as environment variables

All code is well commented, and it is suggested that you browser the build.gradle and *.groovy scripts to understand in more detail how they work.

