package hw04;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.card.Card;
import cs3500.hw03.FreecellController;
import cs3500.hw03.IFreecellController;
import cs3500.hw04.FreecellModelCreator;
import cs3500.hw04.MultiFreecellModel;

import static org.junit.Assert.assertEquals;

/**
 * Tests class for MultiFreecell class using JUnit.
 */
public class MultiFreecellModelTest {
  FreecellOperations<Card> testModel = FreecellModelCreator.create(FreecellModelCreator.GameType
          .MULTIMOVE);
  FreecellOperations<Card> single = FreecellModelCreator.create(FreecellModelCreator.GameType
          .SINGLEMOVE);
  FreecellController testController;
  List<Card> deck = testModel.getDeck();

  //Tests factory for multi card game
  @Test
  public void testFactory1() {
    FreecellOperations<Card> multiModel = FreecellModelCreator.create(FreecellModelCreator.GameType
            .MULTIMOVE);
    assertEquals(true, multiModel instanceof MultiFreecellModel);
  }

  //Tests factory for single car game
  @Test
  public void testFactory2() {
    FreecellOperations<Card> singleModel = FreecellModelCreator
            .create(FreecellModelCreator.GameType.SINGLEMOVE);
    assertEquals(true, singleModel instanceof FreecellModel);
  }

  //Tests moving too many cards at same time
  @Test
  public void testMove1() {
    this.testModel = FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    StringReader input = new StringReader(
            "C1 7 O1\n" +
                    "C2 7 O2\n" +
                    "C3 7 O3\n" +
                    "C4 7 O4\n" +
                    "C5 6 C3\n" +
                    "C3 6 C5\n" +
                    "q\n");

    StringWriter output = new StringWriter();
    IFreecellController<Card> control = new FreecellController(input, output);
    String expected = "Invalid Move. Try Again.: Not enough space for move.\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: A♣\n" +
            "O2: A♠\n" +
            "O3: A♦\n" +
            "O4: A♥\n" +
            "C1: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, 2♣\n" +
            "C4: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C5: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "Game quit prematurely.";
    control.playGame(deck, this.testModel, 8, 4, false);
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }

  //Tests Illegal State Exception is thrown when trying to play game with null readable
  @Test(expected = IllegalStateException.class)
  public void testPlayGame1() {
    StringReader input = null;
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
  }

  //Tests Illegal State Exception is thrown when trying to play game with null appendable
  @Test(expected = IllegalStateException.class)
  public void testPlayGame2() {
    StringReader input = new StringReader("");
    StringWriter output = null;
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
  }

  //Tests that an Illegal Argument Exception is thrown when null deck is passed in
  @Test(expected = IllegalArgumentException.class)
  public void testPlayGame3() {
    StringReader input = new StringReader("");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(null, testModel, 8, 4, false);
  }

  //Tests that an Illegal Argument Exception is thrown when null model is passed in
  @Test(expected = IllegalArgumentException.class)
  public void testPlayGame4() {
    StringReader input = new StringReader("");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, null, 8, 4, false);
  }

  //Tests that "Could not start game." is printed if invalid number of cascade piles is entered
  @Test
  public void testPlayGame5() {
    StringReader input = new StringReader("");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, -1, 4, false);
    assertEquals("Could not start game.", output.toString());
  }

  //Tests that "Could not start game." is printed if invalid number of open piles is entered
  @Test
  public void testPlayGame6() {
    StringReader input = new StringReader("");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, -1, false);
    assertEquals("Could not start game.", output.toString());
  }

  //Tests quit game function with lowercase q
  @Test
  public void testPlayGame7() {
    StringReader input = new StringReader("q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    assertEquals("\nGame quit prematurely.", output.toString().substring(output.toString()
            .length() - 23));
  }

  //Tests quit game function with lowercase Q
  @Test
  public void testPlayGame8() {
    StringReader input = new StringReader("Q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    assertEquals("\nGame quit prematurely.", output.toString().substring(output.toString()
            .length() - 23));
  }

  //Tests quit mid move quitting second in order
  @Test
  public void testPlayGame9() {
    StringReader input = new StringReader("C1 Q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    assertEquals("\nGame quit prematurely.", output.toString().substring(output.toString()
            .length() - 23));
  }

  //Tests quit mid move quitting third in order
  @Test
  public void testPlayGame10() {
    StringReader input = new StringReader("C1 1 Q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    assertEquals("\nGame quit prematurely.", output.toString().substring(output.toString()
            .length() - 23));
  }

  //Tests that quit still works after entering invalid source pile
  @Test
  public void testPlayGame11() {
    StringReader input = new StringReader("xx q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    assertEquals("\nGame quit prematurely.", output.toString().substring(output.toString()
            .length() - 23));
  }

  //Tests that quit still works after entering invalid card index
  @Test
  public void testPlayGame12() {
    StringReader input = new StringReader("C1 xx q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    assertEquals("\nGame quit prematurely.", output.toString().substring(output.toString()
            .length() - 23));
  }

  //Tests that quit still works after entering invalid dest pile
  @Test
  public void testPlayGame13() {
    StringReader input = new StringReader("C1 7 O1 q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    assertEquals("\nGame quit prematurely.", output.toString().substring(output.toString()
            .length() - 23));
  }

  //Tests that user is prompted for new source pile if invalid entry is given
  @Test
  public void testPlayGame15() {
    StringReader input = new StringReader("xx q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    String expected = "Enter Source Pile Again: \n" +
            "Game quit prematurely.";
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }

  //Tests that user is prompted for new card index if invalid entry is given
  @Test
  public void testPlayGame16() {
    StringReader input = new StringReader("C1 xx q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    String expected = "Enter Card Index Again: \n" +
            "Game quit prematurely.";
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }

  //Tests that user is prompted for new dest pile if invalid entry is given
  @Test
  public void testPlayGame17() {
    StringReader input = new StringReader("C1 1 xx q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    String expected = "Enter Destination Pile Again: \n" +
            "Game quit prematurely.";
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }

  //Tests that user is told "Invalid Move. Try Again." if invalid move is given
  @Test
  public void testPlayGame18() {
    StringReader input = new StringReader("C5 6 F1 q");
    StringWriter output = new StringWriter();
    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, 8, 4, false);
    String expected = "\n" +
            "Invalid Move. Try Again.: Move not allowed.\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♣, J♣, 9♣, 7♣, 5♣, 3♣, A♣\n" +
            "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠, A♠\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, A♦\n" +
            "C4: K♥, J♥, 9♥, 7♥, 5♥, 3♥, A♥\n" +
            "C5: Q♣, 10♣, 8♣, 6♣, 4♣, 2♣\n" +
            "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "Game quit prematurely.";
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }

  //Tests game is over through controller
  @Test
  public void testGameOver() {
    String gameplay = "";
    int numOfCascade = 8;

    for (int i = 0; i < 52; i++) {
      int cascnum = (i % numOfCascade) + 1;
      Double d = Math.ceil((52 - (i + 1)) / numOfCascade) + 1;
      Integer indexnum = d.intValue();
      int foundnum = (i % 4 + 1);
      gameplay = gameplay + "C" + String.valueOf(cascnum) + " " + String.valueOf(indexnum) + " F"
              + String.valueOf(foundnum) + "\n";
    }

    StringReader input = new StringReader(gameplay);
    StringWriter output = new StringWriter();

    this.testController = new FreecellController(input, output);
    this.testController.playGame(deck, testModel, numOfCascade, 4, false);
    String expected = "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
            "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
            "F4: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1:\n" +
            "C2:\n" +
            "C3:\n" +
            "C4:\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:\n" +
            "Game over.";
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }

  //Tests trying to move multiple cards to foundation
  @Test
  public void testMove2() {
    StringReader input = new StringReader(
            "C1 7 F1\n" +
                    "C2 7 F2\n" +
                    "C3 7 F3\n" +
                    "C4 7 F4\n" +
                    "C5 6 C3\n" +
                    "C3 6 F1\n" +
                    "q\n");

    StringWriter output = new StringWriter();
    IFreecellController<Card> control = new FreecellController(input, output);
    String expected = "Invalid Move. Try Again.: Move not allowed.\n" +
            "F1: A♣\n" +
            "F2: A♠\n" +
            "F3: A♦\n" +
            "F4: A♥\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, 2♣\n" +
            "C4: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C5: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "Game quit prematurely.";
    control.playGame(deck, this.testModel, 8, 4, false);
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }

  //Tests trying to move multiple cards to open
  @Test
  public void testMove3() {
    StringReader input = new StringReader(
            "C1 7 F1\n" +
                    "C2 7 F2\n" +
                    "C3 7 F3\n" +
                    "C4 7 F4\n" +
                    "C5 6 C3\n" +
                    "C3 6 O1\n" +
                    "q\n");

    StringWriter output = new StringWriter();
    IFreecellController<Card> control = new FreecellController(input, output);
    String expected = "Invalid Move. Try Again.: Move not allowed.\n" +
            "F1: A♣\n" +
            "F2: A♠\n" +
            "F3: A♦\n" +
            "F4: A♥\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♣, J♣, 9♣, 7♣, 5♣, 3♣\n" +
            "C2: K♠, J♠, 9♠, 7♠, 5♠, 3♠\n" +
            "C3: K♦, J♦, 9♦, 7♦, 5♦, 3♦, 2♣\n" +
            "C4: K♥, J♥, 9♥, 7♥, 5♥, 3♥\n" +
            "C5: Q♣, 10♣, 8♣, 6♣, 4♣\n" +
            "C6: Q♠, 10♠, 8♠, 6♠, 4♠, 2♠\n" +
            "C7: Q♦, 10♦, 8♦, 6♦, 4♦, 2♦\n" +
            "C8: Q♥, 10♥, 8♥, 6♥, 4♥, 2♥\n" +
            "Game quit prematurely.";
    control.playGame(deck, this.testModel, 8, 4, false);
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }

  //Tests moving to empty cascade pile
  @Test
  public void testMove4() {
    StringReader input = new StringReader(
            "C1 7 F1\n" +
                    "C2 7 F2\n" +
                    "C3 7 F3\n" +
                    "C4 7 F4\n" +
                    "C5 6 F1\n" +
                    "C6 6 F2\n" +
                    "C7 6 F3\n" +
                    "C8 6 F4\n" +
                    "C1 6 F1\n" +
                    "C2 6 F2\n" +
                    "C3 6 F3\n" +
                    "C4 6 F4\n" +
                    "C5 5 F1\n" +
                    "C6 5 F2\n" +
                    "C7 5 F3\n" +
                    "C8 5 F4\n" +
                    "C1 5 F1\n" +
                    "C2 5 F2\n" +
                    "C3 5 F3\n" +
                    "C4 5 F4\n" +
                    "C5 4 F1\n" +
                    "C6 4 F2\n" +
                    "C7 4 F3\n" +
                    "C8 4 F4\n" +
                    "C1 4 F1\n" +
                    "C2 4 F2\n" +
                    "C3 4 F3\n" +
                    "C4 4 F4\n" +
                    "C5 3 F1\n" +
                    "C6 3 F2\n" +
                    "C7 3 F3\n" +
                    "C8 3 F4\n" +
                    "C1 3 F1\n" +
                    "C2 3 F2\n" +
                    "C3 3 F3\n" +
                    "C4 3 F4\n" +
                    "C6 2 C3\n" +
                    "C3 2 C6\n" +
                    "C6 1 C3\n" +
                    "C3 3 C6\n" +
                    "q\n");

    StringWriter output = new StringWriter();
    IFreecellController<Card> control = new FreecellController(input, output);
    String expected = "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
            "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
            "F3: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
            "F4: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♣, J♣\n" +
            "C2: K♠, J♠\n" +
            "C3: K♦, Q♠\n" +
            "C4: K♥, J♥\n" +
            "C5: Q♣, 10♣\n" +
            "C6: J♦, 10♠\n" +
            "C7: Q♦, 10♦\n" +
            "C8: Q♥, 10♥\n" +
            "Game quit prematurely.";
    control.playGame(deck, this.testModel, 8, 4, false);
    assertEquals(expected, output.toString().substring(output.toString()
            .length() - expected.length()));
  }


}
