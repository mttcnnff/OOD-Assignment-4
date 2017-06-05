package cs3500.hw02.pile;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.PileType;
import cs3500.hw02.card.Card;

/**
 * Concrete CascadePile class.
 * Extends AbstractPile and ensures proper behavior when moving cards to a Cascade Pile.
 * Cards moving to a cascade pile must be of value one less and a different color than the
 * stack's last card.
 */
public class CascadePile extends AbstractPile {

  /**
   * Constructor for Cascade Pile.
   *
   * @param numOfPiles number of piles to be bade in this.
   * @param deck       deck of card to be dealt.
   */
  public CascadePile(int numOfPiles, List<Card> deck) {

    super(PileType.CASCADE, numOfPiles);

    //Deal cards to cascade pile
    int dealCount = 0;
    for (Card c : deck) {
      this.pileContents.get(dealCount).add(c);
      dealCount = (dealCount + 1) % numOfPiles;
    }
  }

  /**
   * Tries to put card passed in as param at the end of specified pile.
   * Checks if move is legal for Cascade pile.
   * If move is legal, the card is moved and True is returned.
   * If the move is illegal, the card is not moved and False is returned.
   * Cascade pile Moves Rules:
   * "However, a card from some pile can be moved to the end of a cascade pile if and only if its
   * color is different from that of the currently last card, and its value is exactly one less
   * than that of the currently last card".
   *
   * @return true if move is legal and made, false if it was not.
   */
  @Override
  public boolean put(int pileNumber, ArrayList<Card> buffer) {
    ArrayList<Card> currentPile = this.pileContents.get(pileNumber);

    if (currentPile.isEmpty()) {
      this.pileContents.get(pileNumber).addAll(buffer);
      return true;
    } else {
      Card currentPileLastCard = currentPile.get(currentPile.size() - 1);
      Card bufferFirstCard = buffer.get(0);
      if (!currentPileLastCard.sameColorAs(bufferFirstCard)
              && currentPileLastCard.compare(bufferFirstCard) == 1) {
        this.pileContents.get(pileNumber).addAll(buffer);
        return true;
      } else {
        return false;
      }
    }

  }
}
