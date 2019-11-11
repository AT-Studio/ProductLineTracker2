/**
 * @author: Alexander Thieler
 * @description: This enum class contains all the different types of screens a product can have
 * @Date: Nov 8 2019
 */

package sample;

/**
 * Enum class used by products to define their screen types
 */
public enum MonitorType {

  LCD("LCD"),
  LED("LED");

  private String type;

  MonitorType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}