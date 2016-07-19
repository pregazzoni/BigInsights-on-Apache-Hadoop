## Overview

This example shows how to programmatically interact with HDFS on the BigInsights cluster.  Some example interactions may be:

- An [ETL](https://en.wikipedia.org/wiki/Extract,_transform,_load) tool that needs to exchange data with BigInsights
- A [Microservice](https://en.wikipedia.org/wiki/Microservices) that needs to access data in BigInsights

BigInsights for Apache Hadoop clusters are secured using [Apache Knox](https://knox.apache.org/).  Apache Knox is a REST API Gateway for interacting with Apache Hadoop clusters.  As such, the HDFS service is not available to external clients of the BigInsights cluster, instead external clients interact with HDFS through the [WebHDFS REST API](http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-hdfs/WebHDFS.html).

The Apache Knox project provides a [Java client library](https://cwiki.apache.org/confluence/display/KNOX/Client+Usage) and this can be used for interacting with the REST API.  Using a library is usally more productive because the library provides a higher level of abstraction than when working directly with the REST API.

There are three examples ...

More information:

- [WebHDFS REST API](http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-hdfs/WebHDFS.html)
- [BigInsights Apache Knox documentation](https://www.ibm.com/support/knowledgecenter/en/SSPT3X_4.2.0/com.ibm.swg.im.infosphere.biginsights.admin.doc/doc/knox_overview.html)

See also:

- [WebHdfsCurl example](./WebHdfsCurl) this example will be useful for developers who want to use only REST to interact with the API and don't want to use the knox client library, e.g. non-JVM language developers.
- 
******


The example uses a gradle build file [./build.gradle](./build.gradle) to retrieve the knox client libraries, set up a groovy session and execute the groovy scripts.

For some other examples of Knox scripts, see the section [More Info](#more-info), below.
******

**Devloper experience**: List any requirements for using the example.

**Application Requirements** (if any): List any requirements for using the example, like browser and version. Include an architecture diagram if the app is anything more than a Demo.

**Run the example**: All samples should lay out step by step instructions on how to run it, including setting up dependencies.

    * **Running Instructions**: Instructions on how to run the example after cloning the repo. Assume little to no prior Bluemix experience.
      * Needs to have sanity checks at about every 20 steps
      * Think accessibility first. If any tools are used (i.e. the CF CLI), briefly explain what the tools are for and how they are used.
      * Avoid directly referencing Bluemix UI components here; doing this helps avoid ACE changes invalidating your README
      * This section tends to be large and/or subject to changes. In order to limit the changes  needed for your README, you can choose to:
        * Embed steps in another Instructions.md file and link there
        * Include a link to a tutorial hosted on a separate site (i.e. developerWorks or Bluemix Docs) instead

**Decomposition Instructions**: Instructions on how a developer/architect would take the sample application and extract the relevant code for reuse. For example, if the app is a "Hello World" to demonstrate integration of a specific service, explain what code is relevant to this integration and how one would reuse it.

**API Documentation**: If the sample app exposes an API, either include basic documentation, link to a Swagger/Apigee API, or something similar. Do not repeat API docs that are already published elsewhere.

*********************************************************************

### Instructions

Run this example by changing into the current directory then executing:

- on *nix using:

```
../../gradlew Example
```

- on Windows using:

```
../../gradlew.bat Example
```

*********************************************************************

Note: you can run this script from the top level project folder using the gradle `-p` argument:

```
./gradlew -p examples/WebHdfsGroovy Example
```

*********************************************************************

### More info

Here are some other examples of the knox API taken from the Knox [online documentation](http://knox.apache.org/books/knox-0-6-0/user-guide.html#WebHDFS):

```groovy
// Import the client DSL and a useful utilities for working with JSON.
import org.apache.hadoop.gateway.shell.Hadoop
import org.apache.hadoop.gateway.shell.hdfs.Hdfs
import groovy.json.JsonSlurper

// Setup some basic config.
gateway = "https://localhost:8443/gateway/sandbox"
username = "guest"
password = "guest-password"

// Start the session.
session = Hadoop.login( gateway, username, password )

// Cleanup anything leftover from a previous run.
Hdfs.rm( session ).file( "/user/guest/example" ).recursive().now()

// Upload the README to HDFS.
Hdfs.put( session ).file( "README" ).to( "/user/guest/example/README" ).now()

// Download the README from HDFS.
text = Hdfs.get( session ).from( "/user/guest/example/README" ).now().string
println text

// List the contents of the directory.
text = Hdfs.ls( session ).dir( "/user/guest/example" ).now().string
json = (new JsonSlurper()).parseText( text )
println json.FileStatuses.FileStatus.pathSuffix

// Cleanup the directory.
Hdfs.rm( session ).file( "/user/guest/example" ).recursive().now()

// Clean the session.
session.shutdown()
```
