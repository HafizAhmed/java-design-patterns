package main;

import java.util.Objects;

/**
 * Basic data structure for each tuple stored in Index Table.
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

  /**
   * getter method of key
   * @return key of Data
   */
  public String getKey() {
    return key;
  }

  /**
   * setter method of key
   * @param key of Data
   */
  public void setKey(final String key) {
    this.key = key;
  }

  /**
   * Getter method to get value of data
   * @return value of data
   */
  public String getValue() {
    return value;
  }

  /**
   * setter method to set value of data
   * @param value
   */
  public void setValue(final String value) {
    this.value = value;
  }

  /**
   * method to compare two objects for equality
   * @param o other object for comparison
   * @return result of comparison
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {return true;}
    if (o == null || getClass() != o.getClass()) {return false;}
    Data data = (Data) o;
    return key.equals(data.getKey()) && value.equals(data.getValue());
  }
  /**
   * method to create hashcode of the object
   * @return hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  /**
   * method to convert an object to useful string message
   * @return a string representing object
   */
  @Override
  public String toString(){
    return "Key: " + this.key + ", Value: " + this.value;
  }
}