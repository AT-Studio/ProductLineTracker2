/**
 * @author: Alexander Thieler
 * @description: This class represents a Production Record
 * @Date: Sep 28 2019
 */

package sample;

/**
 * Production record containing production number, product id, serialnumber and the date of
 * production
 */

import java.sql.Timestamp;
import java.util.Date;

public class ProductionRecord {

  private int productionNumber;
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  public ProductionRecord(int productID) {
    this.productID = productID;
    productionNumber = 0;
    serialNumber = "0";
    dateProduced = new Date();
  }

  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      long time) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = new Date(time);
  }

  public ProductionRecord(Product product, int count) {
    String serialManu;
    if (product.manufacturer.length() < 3) {
      serialManu = product.manufacturer;
    } else {
      serialManu = product.getManufacturer().substring(0, 3);
    }
    StringBuilder builder = new StringBuilder();
    builder.append(Integer.toString(count));
    while (builder.length() < 5) {
      builder.insert(0, "0");
    }
    String uniqueNum = builder.toString();
    serialNumber = serialManu + product.type + uniqueNum;
    dateProduced = new Date();
    productID = product.id;
  }

  @Override
  public String toString() {
    return "Prod. Num: " + productionNumber + " Product ID: " + productID + " Serial Num: "
        + serialNumber + " Date: " + "Mon Oct 14 16:57:54 UTC 2019";
  }

  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public long getProdDate() {
    return dateProduced.getTime();
  }

  public void setProdDate(long time) {
    this.dateProduced = new Date(time);
  }

}
