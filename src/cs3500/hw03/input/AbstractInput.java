package cs3500.hw03.input;

/**
 * Abstract class used to process inputs efficiently.
 * Implements Input Interface.
 */
public abstract class AbstractInput implements Input {

  private String name;
  protected String move;

  /**
   * Constructor which initializes move as an empty string.
   */
  public AbstractInput(String name) {
    this.name = name;
    this.move = "";
  }

  public abstract boolean isValid();

  public boolean quitCheck() {
    return move.equals("q") || move.equals("Q");
  }

  public void read(String input) {
    this.move = input;
  }

  public String toString() {
    return this.name;
  }
}
