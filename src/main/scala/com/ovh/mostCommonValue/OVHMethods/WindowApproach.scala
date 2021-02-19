package com.ovh.mostCommonValue.OVHMethods

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{asc, count, desc, first}

class WindowApproach {

  /** getting Most common value by Key
   *
   * @param inputData    : data to use
   * @param columnKey    : column name of the key
   * @param columnValue  : column value of the key
   */

  def getMostCommonValue(inputData : DataFrame,columnKey : String, columnValue : String): DataFrame ={

    /** defining a window for the aggregation
     *
     * @param columnKey    : column name of the key to use for the partition
     * @param columnValue  : column value of the key to use for the partition
     */
    def windowSpec = Window.partitionBy(columnKey, columnValue)

    inputData.withColumn("count", count(columnValue).over(windowSpec))
      .orderBy(desc("count"),asc(columnValue))
      .groupBy(columnKey)
      .agg(first(columnValue).as(columnValue))
      .orderBy(asc(columnKey))
  }
}
