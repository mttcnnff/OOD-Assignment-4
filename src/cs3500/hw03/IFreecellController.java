package cs3500.hw03;

import java.util.List;

import cs3500.hw02.FreecellOperations;

/**
 * This is the interface of the Freecell Controller. It is parameterized over the
 * card type, i.e. when you implement it, you can substitute K with your
 * implementation of a card.
 */
public interface IFreecellController<K> {

  /**
   * This method should start a new game of Freecell using the provided model, number of cascade
   * and open piles and the provided deck. If "shuffle" is set to false the deck must be used as-is,
   * else the deck should be shuffled.<br><br>
   * Ask provided model to start a new game with given parameters then run game as follows.
   * <br>
   * <br>
   * 1. Transmit game state to the Appendable object exactly as the model provides it.
   * 2. If game is ongoing, wait for user input from the Readable object.
   * <br>
   * i. The source pile (e.g., "C1", as a single word). The pile number begins at 1, so that it is
   * more human-friendly.
   * ii. The card index, again with the index beginning at 1.
   * iii. The destination pile (e.g., "F2", as a single word). The pile number is again counted
   * from 1.
   * <br>
   * 3. If the game has been won, the method should transmit the final game state, and a message
   * "Game over." on a new line and return.
   *
   * @param deck        deck provided to make game with.
   * @param model       model provided to run game.
   * @param numCascades number of cascade piles in this game.
   * @param numOpens    number of open piles in this game.
   * @param shuffle     boolean indicating whether to shuffle deck or not.
   * @throws IllegalStateException thrown only if the controller has not been initialized properly
   *                               to receive input and transmit output.
   */
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascades, int numOpens,
                boolean shuffle) throws IllegalStateException, IllegalArgumentException;
}
