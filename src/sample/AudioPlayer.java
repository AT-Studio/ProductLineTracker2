/**
 * @author: Alexander Thieler
 * @description: This class represents an AudioPlayer
 * @Date: Nov 8 2019
 */

package sample;

/**
 * An AudioPlayer extends Product and implements MultimediaControl
 */
public class AudioPlayer extends Product implements MultimediaControl {

  private String audioForm;
  private String playForm;

  public AudioPlayer(String name, String manufacturer, String audioForm, String playForm) {
    super(name, manufacturer, ItemType.AUDIO.getLabel());
    this.audioForm = audioForm;
    this.playForm = playForm;
  }

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public String toString() {
    return super.toString()
        + "Supported Audio Formats: " + audioForm + "\n"
        + "Supported Playlist Formats: " + playForm;
  }

}