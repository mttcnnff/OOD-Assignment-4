package cs3500.hw02.card;

/**
 * Card class used to represent a single card of a certain suit and value.
 */
public class Card {
  private CardSuit suit;
  private CardValue value;

  /**
   * Constructor for Card.
   *
   * @param value value for card being created.
   * @param suit  suit for card being created.
   */
  public Card(CardValue value, CardSuit suit) {
    this.value = value;
    this.suit = suit;
  }

  /**
   * Get a string representation of this card.
   *
   * @return String concatenation of value and suit
   */
  public String toString() {
    return this.value.toString() + this.suit.toString();
  }

  /**
   * Determines if card is of given suit.
   *
   * @param suit given suit to compare.
   * @return true if card is of given suit, false if card is not of given suit.
   */
  public boolean isOfSuit(CardSuit suit) {
    return this.suit == suit;
  }

  /**
   * Determines if card is of given value.
   *
   * @param value given value to compare.
   * @return true if card is of given value, false if card is not of given value.
   */
  public boolean isOfValue(CardValue value) {
    return this.value == value;
  }

  /**
   * Determines if card is of same value as given card.
   *
   * @param card given card to compare.
   * @return true if cards are same value, false if not.
   */
  public boolean sameSuitAs(Card card) {
    return this.suit == card.suit;
  }

  /**
   * Determines if card is the same color as given card.
   *
   * @param card given card to compare.
   * @return true if cards are same color, false if not.
   */
  public boolean sameColorAs(Card card) {
    return this.suit.sameColorAs(card.suit);
  }

  /**
   * Subtracts given card's value from this card to determine difference when comparing.
   *
   * @param card given card to compare.
   * @return positive if this card is greater, negative if this card is less than, zero if same.
   */
  public int compare(Card card) {
    return this.value.getCardValue() - card.value.getCardValue();
  }
}
