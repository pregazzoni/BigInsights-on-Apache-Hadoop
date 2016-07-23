## Overview

This example shows how to connect and execute SQL against a BigInsights cluster. It connects via [JDBC](https://en.wikipedia.org/wiki/Java_Database_Connectivity) to send request and process result sets.

Big SQL is a massively parallel processing ([MPP](https://en.wikipedia.org/wiki/Massively_parallel_\(computing\))) SQL engine that deploys directly on the physical Hadoop Distributed File System (HDFS) cluster. This SQL engine pushes processing down to the same nodes that hold the data. Big SQL uses a low-latency parallel execution infrastructure that accesses Hadoop data natively for reading and writing.

BigInsights for Apache Hadoop clusters are secured using [Apache Knox](https://knox.apache.org/).  Apache Knox is a REST API Gateway for interacting with Apache Hadoop clusters.  The Apache Knox project provides a [Java client library](https://cwiki.apache.org/confluence/display/KNOX/Client+Usage) and this can be used for interacting with the WebHDFS REST API.  Using a library is usally more productive because the library provides a higher level of abstraction than when working directly with the REST API.

See also:

- [BigInsights Apache Knox documentation](https://www.ibm.com/support/knowledgecenter/en/SSPT3X_4.2.0/com.ibm.swg.im.infosphere.biginsights.admin.doc/doc/knox_overview.html)
- [BigInsights Big SQL documentation](https://www.ibm.com/support/knowledgecenter/SSPT3X_4.2.0/com.ibm.swg.im.infosphere.biginsights.product.doc/doc/bi_sql_access.html)

## Developer experience

Developers will gain the most from these examples if they are:

- Comfortable using Windows, OS X or *nix command prompts
- Familiar with Database concepts and SQL language
- Able to read code written in a high level language such as [Groovy](http://www.groovy-lang.org/)
- Familiar with the [Gradle](https://gradle.org/) build tool
- Familiar with the [WebHdfsGroovy Example](../WebHdfsGroovy)

## Example Requirements

You have met the [pre-requisites](../../README.md#pre-requisites) and have followed the [setup instructions](../../README.md#setup-instructions) in the top level [README](../../README.md)

Specificaly, ensure you have run from top level repo directory the following tasks:

- `./gradlew DownloadCertificate` - to get SSL certificate
- `./gradlew DownloadLibs` - to get JDBC jar libraries

## Run the example

This example consists of the following sub examples:

- [Connect.groovy](./Connect.groovy) - example with simple jdbc connection to BigSQL
- [CreateCsv.groovy](./CreateCsv.groovy]) - example to export CSV file from a table
- [CreateExternal.groovy](./CreateExternal.groovy]) - example with external table in HDFS
- [Federation.groovy](./Federation.groovy]) - example with Federation to DASDB. Requires some manual setup. See [README_Federation](./README_Federation.md).
- [Hbase.groovy](./Hbase.groovy]) -  example creating and querying HBASE table from Big SQL
- [Insert.groovy](./Insert.groovy]) -  example to insert data into Big SQL table
- [Load.groovy](./Load.groovy]) - example to load data file into Big SQL table
- [build.gradle](./build.gradle) - Gradle script to compile and package the Map/Reduce code and execute the Example.groovy script 


To run the examples, open a command prompt window:

   - change into the directory containing this example and run gradle to execute the example
      - `./gradlew Example` (OS X / *nix)
      - `gradlew.bat Example` (Windows)
   - some output from running the command on my machine is shown below 

```bash
biginsight-bluemix-docs $ cd examples/BigSQLGroovy
biginsight-bluemix-docs/examples/BigSQLGroovy $ ./gradlew Example
:CreateTrustStore
Certificate was added to keystore
:SetupLibs
Attepmting to copy /Users/pierreregazzoni/biginsight-bluemix-docs/downloads/db2jcc.jar to /Users/pierreregazzoni/biginsight-bluemix-docs/examples/BigSQLGroovy/lib
Attepmting to copy /Users/pierreregazzoni/biginsight-bluemix-docs/downloads/db2jcc4.jar to /Users/pierreregazzoni/biginsight-bluemix-docs/examples/BigSQLGroovy/lib
Attepmting to copy /Users/pierreregazzoni/biginsight-bluemix-docs/downloads/db2jcc_license_cu.jar to /Users/pierreregazzoni/biginsight-bluemix-docs/examples/BigSQLGroovy/lib
:compileJava UP-TO-DATE
:compileGroovy UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:Connect

>> Connectivity test was successful.
:CreateCsv

Copy data to tmp
...

BUILD SUCCESSFUL

Total time: 39.642 secs
```

The output above shows:

- the steps performed by gradle (command names are prefixed by ':' such as :compileGroovy) 
- CreateTrustore step creates the keystore using your cluster SSL certificate
- SetupLibs step copies the JDBC jars from the downloads directory into the lib directory
- the steps performed by each groovy script (output is prefixed by '[Connect](./Connect.groovy)')
 
## Decomposition Instructions

The examples uses a gradle build file [build.gradle](./build.gradle) when you run `./gradlew` or `gradle.bat`.  The build.gradle for this example does the following:

- perform the dependency steps. Example depends on following two tasks:
    - CreateTrustStore - This will create the keystore with the SSL certificate. This is used as part of the JDBC connection.
    - SetupLibs - This task will copy the JDBC driver files under `lib/` directory. Build.gradle also set runtime dependency via following line: `runtime fileTree(dir: 'lib', include: '*.jar')`
- compile and execute the groovy code

All code is well commented and it is suggested that you browse the build.gradle and *.groovy scripts to understand in more detail how they work.
