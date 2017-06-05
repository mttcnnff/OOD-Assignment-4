package hw04;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import cs3500.hw02.FreecellModel;
import cs3500.hw02.FreecellOperations;
import cs3500.hw02.card.Card;
import cs3500.hw03.FreecellController;
import cs3500.hw04.FreecellModelCreator;

/**
 * Created by Matt on 5/22/17.
 */
public class test {
  public static void main(String[] args) {
//    String gameplay = "";
//
//    for (int i = 0; i < 36; i++) {
//      int cascnum = (i % 8) + 1;
//      Double d = Math.ceil((52 - (i + 1)) / 8) + 1;
//      Integer indexnum = d.intValue();
//      int foundnum = (i % 4 + 1);
//      gameplay = gameplay + "C" + String.valueOf(cascnum) + " " + String.valueOf(indexnum) + " F"
//              + String.valueOf(foundnum) + "\n";
//    }
//
//    System.out.println(gameplay);

    FreecellOperations<Card> multi = FreecellModelCreator.create(FreecellModelCreator.GameType
            .MULTIMOVE);
    FreecellController testController = new FreecellController(new InputStreamReader(System.in),
            System.out);
    List<Card> deck = multi.getDeck();

    testController.playGame(deck, multi, 8, 4, false);


  }
}
