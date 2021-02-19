# DataClusteringHDFS

## Description

The purpose of this project is to to create a deterministic code to generate a new DataFrame with exactly one row for each distinct "key" and the most common "value" for the corresponding key.
</n> I developped thisnproject as if it's a big one wich is going to be developped more and more in the future. That's why I implimented an architecture that helps me using 
classes, methods and vars to attend my goal.

## Requirements

* [Java 8](https://www.java.com/fr/download/faq/java8.xml)
* [Scala 2.11.12](https://www.scala-lang.org/download/2.11.12.html)
* [SBT 1.4.7](https://piccolo.link/sbt-1.2.8.zip)
* [Spark 2.4.4](https://spark.apache.org/releases/spark-release-2-4-4.html)

## Steps

* Initializing Spark Session
* Reading data.
* Showing the final data whith the key and the most common value.


## Configuration

Before executing the code, you have to change the parameters existing in application.properties.

          inputDataPath =
          inputHqlFiles = 
          dataName = 
          master = 
          appName = 

* **inputDataPath** path of the data to use.
* **inputHqlFiles** path of the hql file to execute.
* **dataName** name of the data.

## Running project on Spark

To build the project, run : 

    sbt> clean 
    sbt> assembly
    
This will produce a jar containing the compiled project.

Then you can submit the job using **spark-submit** : line commands are explained in scripts/shell/spark-submit.sh

## Results

The result is a final data that contains each key and each most common value.

***Input data***

![input data](https://github.com/nackachy/OVH/blob/master/inputData.PNG)

***Final datat***

![final data](https://github.com/nackachy/OVH/blob/master/finalData.PNG)




