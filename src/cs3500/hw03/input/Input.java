package cs3500.hw03.input;

/**
 * Interface describing operations of an Input read from readable.
 */
public interface Input {

  /**
   * Abstract method which determines if this input is valid.
   *
   * @return true if valid, false if not.
   */
  boolean isValid();

  /**
   * Checks if input indicates that user wants to quit.
   *
   * @return true if quit is detected, false if not.
   */
  boolean quitCheck();

  /**
   * Sets this input as given string.
   *
   * @param input given input from scanner.
   */
  void read(String input);

  /**
   * Returns this Input represented as a string.
   *
   * @return string representation of this input.
   */
  String toString();
}
