/**
 * @author: Alexander Thieler
 * @description: Screen helps manage the different types of screens that can exist among products
 * @Date: Nov 8 2019
 */

package sample;

/**
 * Screen implements the methods defined in ScreenSpec that are to be used for products
 */
public class Screen implements ScreenSpec {

  String resolution;
  int refreshRate;
  int responseTime;

  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  @Override
  public int getResponseTime() {
    return responseTime;
  }

  @Override
  public String toString() {
    return "Screen:\n"
        + "Resolution: " + resolution + "\n"
        + "Refresh rate: " + refreshRate + "\n"
        + "Response time: " + responseTime + "\n";
  }

}