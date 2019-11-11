/**
 * @author: Alexander Thieler
 * @description: An interface used to define important features of a screen
 * @Date: Nov 8 2019
 */

package sample;

/**
 * The inferface used by the Screen class to implement its functionality
 */
public interface ScreenSpec {

  String getResolution();

  int getRefreshRate();

  int getResponseTime();

}
