## Overview

This example shows how to programmatically interact with HDFS on the BigInsights cluster.  Some examples of programmatic interactions may be:

- An [ETL](https://en.wikipedia.org/wiki/Extract,_transform,_load) tool job that needs to exchange data with BigInsights
- A [Microservice](https://en.wikipedia.org/wiki/Microservices) that needs to access data in BigInsights

BigInsights for Apache Hadoop clusters are secured using [Apache Knox](https://knox.apache.org/).  Apache Knox is a REST API Gateway for interacting with Apache Hadoop clusters.  As such, the HDFS service is not available to external clients of the BigInsights cluster, instead external clients interact with HDFS through the [WebHDFS REST API](http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-hdfs/WebHDFS.html).

This example uses [cURL](https://curl.haxx.se/) to interact with the REST API. This is useful for developers to understand the REST API so they can emulate the REST calls using their programming language features.

See also:

- [WebHdfsGroovy Example](../WebHdfsGroovy/README.md) - similar to this example, but uses a Java based library for working with the REST API
- [BigInsights Apache Knox documentation](https://www.ibm.com/support/knowledgecenter/en/SSPT3X_4.2.0/com.ibm.swg.im.infosphere.biginsights.admin.doc/doc/knox_overview.html)


## Developer experience

Developers will gain the most from these examples if they are:

- Comfortable using Windows, OS X or *nix command prompts
- Familiar with compiling and running java applications
- Able to read code written in a high level language such as [Groovy](http://www.groovy-lang.org/)
- Familiar with the [Gradle](https://gradle.org/) build tool
- Familiar with  [cURL](https://curl.haxx.se/)

## Example Requirements

- You have met the [pre-requisites](../../README.md#pre-requisites) and have followed the [setup instructions](../../README.md#setup-instructions) in the top level [README](../../README.md)
- You must have [bash](https://www.gnu.org/software/bash/) and [cURL](https://curl.haxx.se/) applications installed and in the PATH.

## Run the example

There are three examples in this folder are:

- [Ls.sh](./Ls.sh) - this example lists files and folders in the root HDFS folder
- [Mkdir.sh](./Mkdir.sh) - this example creates a folder in HDFS
- [Put.sh](./Put.sh) - this example uploads a file to HDFS

To run the example [Ls.sh](./Ls.sh), in a command prompt window:

   - change into the directory containing this example and run gradle to execute the example
      - `./gradlew Ls` (OS X / *nix)
      - `gradlew.bat Ls` (Windows)
   - some output from running the command on my machine is shown below 

```bash
biginsight-bluemix-docs $ cd examples/WebHdfsCurl
biginsight-bluemix-docs/examples/WebHdfsCurl $ ./gradlew Ls
:compileJava UP-TO-DATE
...

>> [app-logs, apps, biginsights, ibmpacks, iop, mapred, mr-history, secureDir, securedir, tmp, user]

>> Ls test was successful.

BUILD SUCCESSFUL

Total time: 4.899 secs
```
The output above shows the list of files and directories on *my cluster*, e.g. `[app-logs, apps, biginsights, ibmpacks, iop, mapred, mr-history, secureDir, securedir, tmp, user]`.  You may have different files and directories on *your cluster* so your output may be different.
 
**NOTE:** Replace `Ls` with `Mkdir` to run the example to create a directory in HDFS, or `Put` to upload a file to HDFS.

## Decomposition Instructions

The examples uses a gradle build file [build.gradle](./build.gradle) when you run `./gradlew` or `gradle.bat`.  The build.gradle for this example does the following:

- download the knox java client library and make it available to Java
- download the groovy library and make it available to Java
- read the connection details in your connection.properties file
- execute the bash shell scripts,  making the connection details available to the scripts as environment variables

All code is well commented and it is suggested that you browse the build.gradle and *.sh scripts to understand in more detail how they work.

