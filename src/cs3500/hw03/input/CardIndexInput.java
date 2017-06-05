package cs3500.hw03.input;

/**
 * Concrete CardIndexInput class.
 * Extends Abstract Input
 */
public class CardIndexInput extends AbstractInput {

  /**
   * Constructor for CardIndexInput.
   *
   * @param name string representation of this input.
   */
  public CardIndexInput(String name) {
    super(name);
  }

  @Override
  public boolean isValid() {
    String pattern = "\\d+";
    return this.move.matches(pattern) && Integer.valueOf(this.move) != 0;
  }

  /**
   * Gets integer value of cardIndex input if input is valid.
   *
   * @return integer representation of this move.
   */
  public Integer getCardIndex() {
    if (this.isValid()) {
      return Integer.valueOf(this.move);
    } else {
      return 0;
    }
  }
}
