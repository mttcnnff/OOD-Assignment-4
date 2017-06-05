package cs3500.hw02.pile;

import java.util.ArrayList;

import cs3500.hw02.PileType;
import cs3500.hw02.card.Card;
import cs3500.hw02.card.CardValue;

/**
 * Concrete FoundationPile class.
 * Extends AbstractPile and ensures proper behavior when moving cards to a Foundation Pile.
 * Cards moving to a foundation pile must be of value one greater and the same suit than the
 * stack's last card.
 */
public class FoundationPile extends AbstractPile {


  /**
   * Constructor for Foundation Pile.
   */
  public FoundationPile() {
    super(PileType.FOUNDATION, 4);
  }

  /**
   * Tries to put card passed in as param at the end of specified pile.
   * Checks if move is legal for Foundation pile.
   * If move is legal, the card is moved and True is returned.
   * If the move is illegal, the card is not moved and False is returned.
   * Foundation pile Moves Rules:
   * "A card can be added to a foundation pile if and only if its suit matches that of the pile,
   * and its value is one more than that of the card currently on top of the pile. If a foundation
   * pile is currently empty, any ace can be added to it: there is no required ordering of suits
   * in the foundation piles."
   *
   * @return true if move is legal and made, false if it was not
   */
  @Override
  public boolean put(int pileNumber, ArrayList<Card> buffer) {
    Card card = buffer.get(0);
    ArrayList<Card> currentPile = this.pileContents.get(pileNumber);

    if (buffer.size() > 1) {
      return false;
    }


    if (card.isOfValue(CardValue.ace)) {

      if (currentPile.isEmpty()) {
        currentPile.add(card);
        return true;
      } else {
        return false;
      }

    } else {

      if (currentPile.isEmpty()) {
        return false;
      }
      Card topCard = currentPile.get(currentPile.size() - 1);
      if (topCard.sameSuitAs(card) && card.compare(topCard) == 1) {
        currentPile.add(card);
        return true;
      } else {
        return false;
      }

    }
  }
}
