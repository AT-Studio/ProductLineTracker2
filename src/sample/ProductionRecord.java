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
public class ProductionRecord {

  private String productionNumber;
  private String productId;
  private String serialNumber;
  private String dateProduced;

  public String getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(String productionNumber) {
    this.productionNumber = productionNumber;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(String dateProduced) {
    this.dateProduced = dateProduced;
  }
}
