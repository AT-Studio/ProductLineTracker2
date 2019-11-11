/**
 * @author: Alexander Thieler
 * @description: This class represents an MoviePlayer
 * @Date: Nov 8 2019
 */

package sample;

/**
 * A MoviePlayer extends Product and implements MultimediaControl
 */
public class MoviePlayer extends Product implements MultimediaControl {

  private Screen screen;
  private MonitorType monitorType;

  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL.getLabel());
    this.screen = screen;
    this.monitorType = monitorType;
  }

  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  @Override
  public void next() {
    System.out.println("Next movie");
  }

  @Override
  public String toString() {
    return super.toString()
        + screen.toString()
        + "Monitor Type: " + monitorType.getType();
  }

}