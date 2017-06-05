package cs3500.hw03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs3500.hw02.FreecellOperations;
import cs3500.hw02.card.Card;
import cs3500.hw03.input.CardIndexInput;
import cs3500.hw03.input.Input;
import cs3500.hw03.input.PileInput;

/**
 * FreecellController class which implements the functionality described in the
 * IFreecellController interface.
 */
public class FreecellController implements IFreecellController<Card> {

  private Readable in;
  private Appendable out;
  private boolean quit;
  private ArrayList<Input> move;
  private PileInput sourcePileEntry;
  private CardIndexInput cardIndexEntry;
  private PileInput destPileEntry;
  private Scanner scan;

  /**
   * Constructor for FreecellController.
   *
   * @param rd Readable object used to read integers and Strings.
   * @param ap Appendable object used to transmit String.
   */
  public FreecellController(Readable rd, Appendable ap) throws IllegalStateException {
    //    if (rd == null) {
    //      throw new IllegalStateException("Readable not initialized properly!");
    //    }
    //    if (ap == null) {
    //      throw new IllegalStateException("Appendable not initialized properly!");
    //    }
    this.in = rd;
    this.out = ap;
    this.quit = false;
    this.sourcePileEntry = new PileInput("Source Pile");
    this.cardIndexEntry = new CardIndexInput("Card Index");
    this.destPileEntry = new PileInput("Destination Pile");
    this.move = new ArrayList<>();
    this.move.add(this.sourcePileEntry);
    this.move.add(this.cardIndexEntry);
    this.move.add(this.destPileEntry);


  }

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
  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascades,
                       int numOpens, boolean shuffle) throws IllegalStateException,
          IllegalArgumentException {

    if (this.in == null) {
      throw new IllegalStateException("Readable not initialized.");
    }

    if (this.out == null) {
      throw new IllegalStateException("Appendable not initialized.");
    }

    if (deck == null) {
      throw new IllegalArgumentException("Deck is null!");
    }

    if (model == null) {
      throw new IllegalArgumentException("Model is null!");
    }

    //Set up scanner for parsing
    this.scan = new Scanner(this.in);

    //start game
    try {
      model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      sendOutput(this.out, "Could not start game.");
      return;
    }

    //run game
    while (!this.quit && !model.isGameOver()) {
      sendOutput(this.out, model.getGameState());

      for (Input i : this.move) {
        if (!this.getInput(i, "Enter " + i.toString() + " Again: ")) {
          break;
        }
      }

      if (this.quit) {
        break;
      }

      try {
        model.move(this.sourcePileEntry.getPileType(),
                this.sourcePileEntry.getPileNumber() - 1,
                this.cardIndexEntry.getCardIndex() - 1,
                this.destPileEntry.getPileType(),
                this.destPileEntry.getPileNumber() - 1);
      } catch (IllegalArgumentException e) {
        sendOutput(this.out, "\nInvalid Move. Try Again.: " + e.getMessage() + "\n");
      }
    }
    if (this.quit) {
      sendOutput(this.out, "\nGame quit prematurely.");
      return;
    }
    if (model.isGameOver()) {
      sendOutput(this.out, model.getGameState());
      sendOutput(this.out, "\nGame over.");
      return;
    }

  }

  /**
   * Sends given msg to given appendable.
   *
   * @param ap  given appendable.
   * @param msg given message.
   */
  private void sendOutput(Appendable ap, String msg) {
    try {
      ap.append(msg);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Scans given input to resolve action to take.
   * Prints error if input is bad.
   *
   * @param input    input to parse into.
   * @param errorMsg message to print if input is bad.
   * @return true if input is good, false if user quit.
   */
  private boolean getInput(Input input, String errorMsg) {
    if (this.scan.hasNext()) {
      input.read(scan.next());
      if (input.quitCheck()) {
        this.quit = true;
        return false;
      }
      while (!input.isValid()) {
        sendOutput(this.out, errorMsg);
        input.read(scan.next());
        if (input.quitCheck()) {
          this.quit = true;
          return false;
        }
      }
    }
    return true;
  }

}

