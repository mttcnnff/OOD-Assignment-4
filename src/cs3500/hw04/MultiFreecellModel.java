package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.PileType;

/**
 * Class responsible for multi-card moving freecell model implementation.
 * Implements FreecellOperations Interface.
 */
public class MultiFreecellModel extends FreecellModel {

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) throws IllegalArgumentException {
    if (!this.gameStarted) {
      throw new IllegalArgumentException("Cannot move, game is not started");
    }

    if (!this.piles.get(source).hasPile(pileNumber)) {
      throw new IllegalArgumentException("Invalid pile Number for Source.");
    }

    if (!this.piles.get(destination).hasPile(destPileNumber)) {
      throw new IllegalArgumentException("Invalid pile Number for Destination.");
    }

    int n = this.piles.get(PileType.OPEN).emptyPileCount();
    int k = this.piles.get(PileType.CASCADE).emptyPileCount();
    int pileSize = this.piles.get(source).getPileSize(pileNumber);

    if (pileSize - cardIndex > (n + 1) * (Math.pow(2, k))) {
      throw new IllegalArgumentException("Not enough space for move.");
    }

    if (!this.piles.get(source).move(pileNumber, cardIndex, this.piles.get(destination),
            destPileNumber)) {
      throw new IllegalArgumentException("Move not allowed.");
    }


  }


}
