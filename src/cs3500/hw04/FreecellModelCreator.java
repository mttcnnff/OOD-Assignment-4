package cs3500.hw04;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.card.Card;

/**
 * Factory class used for making Freecell Models.
 * This factory can make Multimove and Singlemove Models.
 */
public class FreecellModelCreator {

  /**
   * Factory method.
   *
   * @param type Type of game to create.
   * @return new Freecell Model for game.
   */
  public static FreecellOperations<Card> create(GameType type) {
    switch (type) {
      case MULTIMOVE:
        return new MultiFreecellModel();
      case SINGLEMOVE:
        return new FreecellModel();
      default:
        throw new IllegalArgumentException("Game type doesn't exist");
    }

  }

  /**
   * Type for the two types of models available in this Freecell Game. <br>
   * Singlemove<br>
   * Multimove<br>
   */
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }
}


