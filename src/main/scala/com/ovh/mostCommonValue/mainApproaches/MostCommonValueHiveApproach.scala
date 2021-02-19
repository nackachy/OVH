package com.ovh.mostCommonValue.mainApproaches

import com.ovh.mostCommonValue.OVHMethods.{HiveApproach, IO, WindowApproach}
import com.ovh.mostCommonValue.properties.OVHProperties
import com.ovh.mostCommonValue.vars.SparkVars.{APPNAME, DATANAME, INPUTDATAPATH, MASTER}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.sql.SparkSession

object MostCommonValueHiveApproach {

  def main(args: Array[String]): Unit = {

    val appProperties = new OVHProperties
    val IO = new IO
    val HiveApproach = new HiveApproach

    val spark = SparkSession.builder
      .master(appProperties.getProp(MASTER))
      .appName(appProperties.getProp(APPNAME))
      .getOrCreate()

    val hadoop: FileSystem = {
      val conf = new Configuration()
      FileSystem.get(conf)
    }

    val inputData = IO.readData(appProperties.getProp(INPUTDATAPATH),appProperties.getProp(DATANAME))(spark)

    inputData.createTempView("inputData")

    val finalData = HiveApproach.excuteHqlFileToDF("mostCommonValue.hql",hadoop)(spark)
    finalData.show()
  }

}
