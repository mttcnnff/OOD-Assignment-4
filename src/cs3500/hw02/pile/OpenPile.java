package cs3500.hw02.pile;

import java.util.ArrayList;

import cs3500.hw02.PileType;
import cs3500.hw02.card.Card;

/**
 * Concrete OpenPile class.
 * Extends AbstractPile and ensures proper behavior when moving cards to a Open Pile.
 * Any card can be placed in an Open Pile as long as the stack of the Open Pile is empty.
 */
public class OpenPile extends AbstractPile {

  /**
   * Constructor for Open Pile.
   */
  public OpenPile(int numOfPiles) {
    super(PileType.OPEN, numOfPiles);
  }

  /**
   * Tries to put card passed in as param at the end of specified pile.
   * Checks if move is legal for Open pile.
   * If move is legal, the card is moved and True is returned.
   * If the move is illegal, the card is not moved and False is returned.
   * Open pile Moves Rules:
   * "An open pile may contain at most one card."
   *
   * @return true if move is legal and made, false if it was not
   */
  @Override
  public boolean put(int pileNumber, ArrayList<Card> buffer) {

    if (buffer.size() > 1) {
      return false;
    }

    ArrayList<Card> currentPile = this.pileContents.get(pileNumber);
    Card card = buffer.get(0);

    if (currentPile.isEmpty()) {
      currentPile.add(card);
      return true;
    } else {
      return false;
    }
  }
}
