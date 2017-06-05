package cs3500.hw02.card;

/**
 * Type for the four types of suits in a deck of Cards. <br>
 * Hearts: ♥ <br>
 * Diamonds: ♦ <br>
 * Spades: ♠ <br>
 * Clubs: ♣ <br>
 */
public enum CardSuit {
  hearts("red"),
  diamonds("red"),
  spades("black"),
  clubs("black");

  private String color;

  /**
   * Constructor for CardSuit.
   *
   * @param color given color this suit should be.
   */
  CardSuit(String color) {
    this.color = color;
  }

  /**
   * Determines if this suit is the same as the given suit.
   *
   * @param suit given suit to compare colors with.
   * @return true if the same color, false if not.
   */
  public boolean sameColorAs(CardSuit suit) {
    return this.color == suit.color;
  }

  /**
   * Gets a string representation of this suit.
   *
   * @return suit representation of suit.
   */
  @Override
  public String toString() {
    switch (this) {
      case hearts:
        return "♥";
      case diamonds:
        return "♦";
      case spades:
        return "♠";
      case clubs:
        return "♣";
      default:
        throw new IllegalArgumentException("card suit does not exist.");
    }
  }
}
