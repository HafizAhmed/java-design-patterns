package main;//package com.iluwatar.indextable;

import java.util.Objects;

/**
 * Basic data structure for each tuple stored in data IndexTable.
 */
public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           Data {

  private String key;
  private String value;

  /**
   * Constructor of main.Data class.
   * @param key data key
   * @param value data value
   */
  public Data(final String key, final String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public void setKey(final String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(final String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o == null || getClass() != o.getClass()) {return false;}
    Data data = (Data) o;
    return key.equals(data.getKey()) && value.equals(data.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public String toString(){
    return "Key: " + this.key + ", Value: " + this.value;
  }
}