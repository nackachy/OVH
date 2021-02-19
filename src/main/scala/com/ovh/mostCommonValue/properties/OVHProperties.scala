package com.ovh.mostCommonValue.properties

import java.io.FileInputStream
import java.util.Properties

class OVHProperties {

  /** getting the resource
   *
   * @param propertyName    : based on the need, we can choose the appropriate property
   */
  def getProperty (propertyName: String) : String  = {

    val properties = new Properties()
    properties.load(new FileInputStream("config/ovhApplication.properties"))

    val property = properties.getProperty(propertyName)
    property.toString()

  }
}
