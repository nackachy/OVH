package com.ovh.mostCommonValue.mainApproaches

import com.ovh.mostCommonValue.OVHMethods.{HiveApproach, IO, WindowApproach}
import com.ovh.mostCommonValue.properties.OVHProperties
import com.ovh.mostCommonValue.vars.SparkVars.{APPNAME, DATANAME, INPUTDATAPATH, MASTER}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.sql.SparkSession

object MostCommonValueHiveApproach {

  def main(args: Array[String]): Unit = {

    //create instance from classes
    val appProperties = new OVHProperties
    val IO = new IO
    val HiveApproach = new HiveApproach
    
    //spark configiration
    val spark = SparkSession.builder
      .master(appProperties.getProperty(MASTER))
      .appName(appProperties.getProperty(APPNAME))
      .getOrCreate()

    val hadoop: FileSystem = {
      val conf = new Configuration()
      FileSystem.get(conf)
    }

    //reading the data
    val inputData = IO.readData(appProperties.getProperty(INPUTDATAPATH),appProperties.getProperty(DATANAME))(spark)
    
    //creating a view to use in the hql file
    inputData.createTempView("inputData")

    //excuting the hql file
    val finalData = HiveApproach.excuteHqlFileToDF("mostCommonValue.hql",hadoop)(spark)
    
    finalData.show()
  }

}
