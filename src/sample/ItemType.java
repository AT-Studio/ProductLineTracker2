/**
 * @author: Alexander Thieler
 * @description: This enum class contains all the different types of products that exist
 * @Date: Nov 8 2019
 */

package sample;

/**
 * Enum class used by products to define their types
 */
public enum ItemType {

  AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");

  private String label;

  ItemType(String c) {
    label = c;
  }

  public String getLabel() {
    return label;
  }
}
