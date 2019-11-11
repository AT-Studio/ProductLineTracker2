/**
 * @author: Alexander Thieler
 * @description: Every product is based on this Item interface
 * @Date: Nov 8 2019
 */

package sample;

/**
 * An interface that has the key features of a product
 */
public interface Item {

  int getId();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturer);

  String getManufacturer();

}

