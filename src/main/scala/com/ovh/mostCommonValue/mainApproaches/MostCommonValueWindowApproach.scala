package com.ovh.mostCommonValue.mainApproaches

import com.ovh.mostCommonValue.properties.OVHProperties
import com.ovh.mostCommonValue.vars.SparkVars._
import com.ovh.mostCommonValue.OVHMethods.IO
import com.ovh.mostCommonValue.OVHMethods.WindowApproach
import org.apache.spark.sql.SparkSession

object MostCommonValueWindowApproach{

  def main(args: Array[String]): Unit = {

    val appProperties = new OVHProperties
    val IO = new IO
    val WindowApproach  = new WindowApproach

    val spark = SparkSession.builder
      .master(appProperties.getProp(MASTER))
      .appName(appProperties.getProp(APPNAME))
      .getOrCreate()

    val inputData = IO.readData(appProperties.getProp(INPUTDATAPATH),appProperties.getProp(DATANAME))(spark)

    val finalData = WindowApproach.getMostCommonValue(inputData,"key","value")
    finalData.show()

  }

}