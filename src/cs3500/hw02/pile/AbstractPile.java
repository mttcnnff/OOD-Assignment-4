package cs3500.hw02.pile;

import java.util.ArrayList;
import cs3500.hw02.PileType;
import cs3500.hw02.card.Card;

/**
 * Abstract class used to handle common behaviors between piles.
 * Sub-classes are CascadePile, FoundationPile, and OpenPile.
 */
public abstract class AbstractPile {
  protected ArrayList<ArrayList<Card>> pileContents;
  private PileType pileType;

  /**
   * Constructor.
   *
   * @param pileType   type of pile to construct.
   * @param numOfPiles number of stacks in pile.
   */
  AbstractPile(PileType pileType, int numOfPiles) {
    this.pileType = pileType;
    this.pileContents = new ArrayList<>();

    for (int i = 0; i < numOfPiles; i++) {
      this.pileContents.add(new ArrayList<Card>());
    }
  }

  /**
   * Removes card at index from this pile's stack indexed at pileNumber
   *
   * @param pileNumber stack to remove card from.
   * @param index      card index in stack to remove.
   */
  private void remove(int pileNumber, int index) {
    this.pileContents.get(pileNumber).remove(index);
  }

  protected abstract boolean put(int pileNumber, ArrayList<Card> buffer);

  /**
   * Moves card from this pile to dest Pile according to
   * Freecell game rules. Refer to assignment for those rules.
   *
   * @param sourcePileNumber pile number of source.
   * @param sourceCardIndex  index number of card stack.
   * @param dest             destination pile for card.
   * @param destPileNumber   number of destination pile.
   * @return true if move is made.
   */
  public boolean move(int sourcePileNumber, int sourceCardIndex, AbstractPile dest, int
          destPileNumber) {
    ArrayList<Card> buffer = new ArrayList<>();
    int sourcePileSize = this.pileContents.get(sourcePileNumber).size();
    for (int i = sourceCardIndex; i < sourcePileSize; i++) {
      buffer.add(this.pileContents.get(sourcePileNumber).get(i));
    }

    if (!this.validBuild(buffer)) {
      return false;
    }

    if (dest.put(destPileNumber, buffer)) {
      for (int i = sourceCardIndex; i < sourcePileSize; i++) {
        this.remove(sourcePileNumber, sourceCardIndex);
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns string representation of single pile
   * to be used in building full representation of
   * game state.
   *
   * @return string rep of this pile.
   */
  public String getPileState() {
    String state = "";
    int index = 1;
    for (ArrayList<Card> pile : pileContents) {
      state = state + this.pileType.toString() + String.valueOf(index) + ":";
      for (Card card : pile) {
        state = state + " " + card.toString();
        if (card != pile.get(pile.size() - 1)) {
          state = state + ",";
        }
      }
      if (pile != pileContents.get(pileContents.size() - 1)) {
        state = state + "\n";
      }
      index++;
    }
    return state;
  }

  /**
   * Determines if this pile has stack indexed at pileNumber.
   *
   * @param pileNumber given pileNumber to check.
   * @return true if pileNumber is less than the number of piles, false if not.
   */
  public boolean hasPile(int pileNumber) {
    return pileNumber >= 0 && pileNumber < this.pileContents.size();
  }

  /**
   * @return card count of this pile.
   */
  public int cardCount() {
    int count = 0;
    for (ArrayList<Card> pile : this.pileContents) {
      count = count + pile.size();
    }
    return count;
  }

  /**
   * Gets number of card in given pileNumber of this pile.
   *
   * @param pileNumber given pileNumber.
   * @return number of cards in the zero-indexed
   */
  public int getPileSize(int pileNumber) {
    if (this.hasPile(pileNumber)) {
      return this.pileContents.get(pileNumber).size();
    } else {
      throw new IllegalArgumentException("Trying to access pile which does not exist.");
    }
  }

  /**
   * Gets number of empty stacks in this pile.
   *
   * @return the number of stacks in this pile with no cards as an int.
   */
  public int emptyPileCount() {
    int count = 0;
    for (ArrayList<Card> pile : this.pileContents) {
      if (pile.isEmpty()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Determines whether the set of cards in at the stack indexed by pileIndex at cardIndex and
   * all carda after it form a valid build. (Alternating colors and value decreasing by one).
   *
   * @param buffer buffer of cards to query.
   * @return true if the set does create a valid build, false if not.
   */
  private boolean validBuild(ArrayList<Card> buffer) {
    //if last element in stack
    int size = buffer.size();

    if (size == 1) {
      return true;
    }

    for (int i = 0; i < size - 1; i++) {
      Card currentCard = buffer.get(i);
      Card nextCard = buffer.get(i + 1);

      if (currentCard.sameColorAs(nextCard) || currentCard.compare(nextCard) != 1) {
        return false;
      }
    }

    return true;
  }

  /**
   * @return PileType of this pile.
   */
  public PileType getPileType() {
    return this.pileType;
  }
}
