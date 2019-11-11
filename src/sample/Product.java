/**
 * @author: Alexander Thieler
 * @description: Base class for every product that exists in this program
 * @Date: Nov 8 2019
 */

package sample;

/**
 * Product class implements all the core behavior that is shared among all products
 */
public class Product implements Item {

  protected int id;
  protected String type;
  protected String manufacturer;
  protected String name;

  Product(String name, String manufacturer, String type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  Product(int id, String name, String manufacturer, String type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  @Override
  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + type + "\n";
  }

  public int getId() {
    return id;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getName() {
    return name;
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
}