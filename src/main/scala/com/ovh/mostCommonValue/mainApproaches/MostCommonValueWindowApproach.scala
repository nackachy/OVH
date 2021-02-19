package com.ovh.mostCommonValue.mainApproaches

import com.ovh.mostCommonValue.properties.OVHProperties
import com.ovh.mostCommonValue.vars.SparkVars._
import com.ovh.mostCommonValue.OVHMethods.IO
import com.ovh.mostCommonValue.OVHMethods.WindowApproach
import org.apache.spark.sql.SparkSession

object MostCommonValueWindowApproach{

  def main(args: Array[String]): Unit = {

    //create instance from classes
    val appProperties = new OVHProperties
    val IO = new IO
    val WindowApproach  = new WindowApproach

    //spark configiration
    val spark = SparkSession.builder
      .master(appProperties.getProperty(MASTER))
      .appName(appProperties.getProperty(APPNAME))
      .getOrCreate()

    //reading the data
    val inputData = IO.readData(appProperties.getProperty(INPUTDATAPATH),appProperties.getProperty(DATANAME))(spark)
    
    //getting the final result using the method created in WindowApproach
    val finalData = WindowApproach.getMostCommonValue(inputData,"key","value")
    
    finalData.show()

  }

}