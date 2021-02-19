package com.ovh.mostCommonValue.OVHMethods

import com.ovh.mostCommonValue.properties.OVHProperties
import com.ovh.mostCommonValue.vars.SparkVars.INPUTHQLFILE

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.io.BufferedInputStream
import scala.io.Source

class HiveApproach {

  val appProperties = new OVHProperties

  /** getting Most common value by Key
   *
   * @param inputData    : data to use
   * @param columnKey    : column name of the key
   * @param columnValue  : column value of the key
   */

  def excuteHqlFileToDF(hqlFile: String, hadoop: FileSystem)(implicit spark: SparkSession): DataFrame = {
    val inputHqlQuery = new BufferedInputStream(hadoop.open(new Path(appProperties.getProp(INPUTHQLFILE)+hqlFile)))
    val hqlQuery = Source.fromInputStream(inputHqlQuery).getLines().mkString
    spark.sql(hqlQuery).toDF()
  }

}
