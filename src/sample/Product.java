/**
 * @author: Alexander Thieler
 * @description: This class is represents a Product
 * @Date: Sep 28 2019
 */

package sample;

/**
 * Product class containing name, type and manufacturer
 */
public class Product {

  private String name;
  private String type;
  private String manufacturer;

  public String getName() {
    return name;
  }

  /**
   * Public constructor used to instantiate a Product object
   *
   * @param name         name of the product
   * @param type         the type of product (Audio, Video, Audio/Video)
   * @param manufacturer the manufacturer of the product
   */
  public Product(String name, String type, String manufacturer) {
    this.name = name;
    this.type = type;
    this.manufacturer = manufacturer;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }
}
