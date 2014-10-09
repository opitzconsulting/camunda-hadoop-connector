# __Camunda-Hadoop-Connector__

## Installation

### System requirements

* Linux OS
* Java Eclipse (JDK 6 or newer)

### Install required software

* Hadoop 2.4.x or better (tested with Hortonworks Sandbox 2.1 virtual machine http://hortonworks.com/hdp/downloads)
* Camunda BPM (tested on JBoss, http://docs.camunda.org/latest/guides/installation-guide/jboss/)

### Load project
* Import project as Maven project into Eclipse
* Run "importlibs.sh" to install pig-librarys (without Hadoop)

## Running Samples
* Import "sample" as Maven project into Eclipse
* To use PIG: Change all addresses ("sandbox.hortonworks.com") in src/main/ressources/core-site.xml to the ip-address of the virtual machine or to "localhost" if Camunda is installed on the Hadoop VM / node
* To run the JUnit-Tests, change the host in hive-Test and uncomment the code in Pig and HDFS

### Running sample: Predictive Maintenance
* Deploy the process
* Start process "Log Generator" with process variable 'num' and the number of logs you want to generated as value
* Start process "Predictive Maintenance" without process variables

## Three ways to use the connector

### Only with process variables
Run the process in Camunda and insert the required variables. Insert the name for the variable in format <task-name> <command>.

### With own Java-Delegate
Use a Service Task and write a delegate which implements the abstract hadoop-class (see samples). To use PIG insert the core-site.xml in your classpath and change the addresses (see Running Samples). 

### With own Java-Application and using config-functions
