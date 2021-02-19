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
    def windowSpec = Window.partitionBy(columnKey, columnValue) //defining a window for the aggregation

    inputData.withColumn("countedValue", count(columnValue).over(windowSpec)) // counting repertition of value for each group of key, value and assigning that value to new column called as countedValue
      .orderBy(desc("countedValue"),asc(columnValue)) // order dataframe with countedValue in descending order
      .groupBy(columnKey) // group by columnKey
      .agg(first(columnValue).as(columnValue)) //taking the first row of each key with count column as the highest
      .orderBy(asc(columnKey)) // order dataframe with columnKey in ascending order
  }
}
