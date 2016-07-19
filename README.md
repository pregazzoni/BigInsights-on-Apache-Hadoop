*********************************************************************
### README 

*********************************************************************

This repository contains example projects to help you quickly get started with BigInsights. Following the steps below on your client machine, it should take you less than 5 minutes to run any of the example projects against a BigInsights cluster. The projects are tested on BigInsights on Cloud (bluemix) but they should also work for BigInsights on-premise.

The core idea is that you can run an example project to see it working against your BigInsights cluster. You can then copy the project and adapt it to add your own custom logic.  Think of the example projects as working blueprints.  A design decision was taken that the build scripts should be as independent as possible to allow developers to take a standalone example script project and reuse it with minimal effort.

*********************************************************************

### Pre-requisites

#### Mandatory

- A client machine (e.g. laptop) connected to the internet
- A BigInsights cluster *([Bluemix](https://new-console.ng.bluemix.net/docs/services/BigInsights/index.html) setup instructions)*
- Java 8 JDK (not JRE) installed on your client machine *([OS X](https://docs.oracle.com/javase/8/docs/technotes/guides/install/mac_jdk.html#CHDBADCG) / [Linux](https://docs.oracle.com/javase/8/docs/technotes/guides/install/linux_jdk.html#BJFGGEFG) / [Windows](https://docs.oracle.com/javase/8/docs/technotes/guides/install/windows_jdk_install.html#CHDEBCCJ) installation instructions)*
- Git application installed on your client machine *([Linux / OS X / Windows](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) installation instructions)*

NOTE: You do NOT need to install gradle, the gradlew scripts mentioned below will install gradle for you

### Setup Instructions

Follow these steps on your client machine.  If you encounter an issue, see the [FAQ](./FAQ.md) for common issues and their resolution.

- Clone this repository `git clone https://github.com/snowch/biginsight-examples.git`
- Copy `connection.properties_template` to `connection.properties`
- Edit `connection.properties` to add your connection details for BigInsights
- In `connection.properties` uncomment the line `# known_hosts:allowAnyHosts`
- Download the ssl certificate
  - Run `./gradlew DownloadCertificate` (OS X / *nix) 
  - Run `gradlew.bat DownloadCertificate` (Windows)
- Download libraries from the cluster
  - Run `./gradlew DownloadLibs` (OS X / *nix)
  - Run `gradlew.bat DownloadLibs` (Windows)

Next, see the section below for details of running an example script.

*********************************************************************
### Running the scripts

After performing the setup steps, you can look at the README for the example you wish to run (see the list of [[examples](examples)] here) to understand more about the example. 

As a taster, here is how you can run the WebHdfsGroovy Ls example which lists the files and directories in the hdfs root directory:

```bash
biginsight-examples snowch$ ./gradlew -p examples/WebHdfsGroovy Ls
:compileJava UP-TO-DATE
...

>> [app-logs, apps, biginsights, ibmpacks, iop, mapred, mr-history, secureDir, securedir, tmp, user]

>> Ls test was successful.

BUILD SUCCESSFUL

Total time: 4.899 secs
```
The output shows the list of files and directories, e.g. `[app-logs, apps, biginsights, ibmpacks, iop, mapred, mr-history, secureDir, securedir, tmp, user]`

*********************************************************************

### List of examples

Each example project has a README.md file describing how to run the project.  All of the example projects are available in the [[examples](examples)] folder.


*********************************************************************

### Getting Help

Find us on [Stack Overflow](https://stackoverflow.com/questions/tagged/biginsight-examples)

*********************************************************************

### Contributing updates to the guidelines

To update the guidelines, please fork the repository, make changes, and create a pull request. When updating one of the sample READMEs, update the other sample versions where applicable.

