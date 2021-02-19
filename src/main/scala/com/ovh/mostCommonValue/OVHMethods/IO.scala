package com.ovh.mostCommonValue.OVHMethods

import com.ovh.mostCommonValue.vars.SparkVars._
import org.apache.spark.sql.{DataFrame, SparkSession}

class IO {
  /** Read csv data from given path and filename
   *
   * @param path     : filename path
   * @param filename : file name
   * @param spark    : Spark Session
   */
  def readData(path: String, filename: String, fileExtension:String = FILEEXTENTION, dot:String = DOT, header:Boolean = true)(implicit spark: SparkSession): DataFrame = {

    spark.read
      .option(HEADER, header)
      .csv(path + filename + dot + fileExtension)
      .toDF()
  }
}
