/**
 * @author: Alexander Thieler
 * @description: This class is meant to provide other classes with the table and column names for
 * easy interaction with the database
 * @Date: Sep 28 2019
 */

package sample;

/**
 * Utils class that holds static final strings that represent the table names and their column
 * names
 */
public class DBUtils {

  public static final String PRODUCT_TABLE_NAME = "Product";
  public static final String PRODUCT_ID = "id";
  public static final String PRODUCT_NAME = "name";
  public static final String PRODUCT_TYPE = "type";
  public static final String PRODUCT_MANUFACTURER = "manufacturer";

  public static final String PRODUCTIONRECORD_TABLE_NAME = "ProductionRecord";
  public static final String PRODUCTIONRECORD_PRODUCTION_NUM = "production_num";
  public static final String PRODUCTIONRECORD_PRODUCT_ID = "product_id";
  public static final String PRODUCTIONRECORD_SERIAL_NUM = "serial_num";
  public static final String PRODUCTIONRECORD_DATE_PRODUCED = "date_produced";

  public static final String ITEMTYPE_COUNTER_TABLE_NAME = "ItemType_Counter";
  public static final String ITEMTYPE_COUNTER_TYPE = "type";
  public static final String ITEMTYPE_COUNTER_COUNT = "count";

  /**
   * Private constructor as this class is not meant to be instantiated
   */
  private DBUtils() {
  }

}
