package com.cosgame.sfsj.play;


import static java.util.logging.Level.INFO;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.play.Hand.HandSuitType;
import com.cosgame.sfsj.play.Hand.Pattern;
import com.cosgame.sfsj.play.Hand.Slice;
import com.cosgame.sfsj.util.CardUtils;
import java.util.Comparator;
import java.util.logging.Logger;

public class Judge {

  static class Winner {

    final int id;
    final Slice winningSlice;

    public Winner(int id, Slice winningSlice) {
      this.id = id;
      this.winningSlice = winningSlice;
    }
  }

  public static final Logger LOG = Logger.getLogger(Judge.class.getName());
  CardRank dominantRank;
  CardSuit dominantSuit;

  public Judge(CardRank dominantRank, CardSuit dominantSuit) {
    this.dominantRank = dominantRank;
    this.dominantSuit = dominantSuit;
  }

  /**
   * Determine the result of a round according to the four hands, dominant rank and dominant suit.
   *
   * @param hands A list of exactly FOUR hands. The first hand is from the first player who played
   * in this round, which therefore determines the format of this round (a starter hand).
   * @return The index of player who won this round.
   */
  public Winner whoWins(Hand[] hands) {
    if (hands == null || hands.length != 4) {
      throw new IllegalArgumentException("Input hands must have a size of 4!");
    }
    Pattern starterPattern = hands[0].maxSplit(); // starterPattern. define the pattern of this round.

    int winnerID = 0;
    Slice winnerSlice = starterPattern.getHighestSlice(dominantRank, dominantSuit);
    Comparator<Slice> sliceComparator = (s1, s2) -> CardUtils.cardComparator(dominantRank,
        dominantSuit).compare(s1.card, s2.card);

    // Challenge winner
    for (int i = 1; i < 4; i++) {
      Hand curHand = hands[i];
      if (getHandSuitType(curHand) == HandSuitType.MIXED) {
        continue; // lose: a suit-mixed hand can never beat starterPattern
      }
      if (getHandSuitType(curHand) != HandSuitType.DOMINANTS
          && curHand.getCards().get(0).getSuit() != hands[0].getCards().get(0).getSuit()) {
        continue; // lose: current hand is not dominant, and not same suit as starter hand
      }
      if (curHand.splitAccordingTo(starterPattern) == Pattern.NOT_MATCH) {
        continue; // lose: current hand has different pattern than starter hand
      }
      Slice curHighest = curHand.splitAccordingTo(starterPattern)
          .getHighestSlice(dominantRank, dominantSuit);
      if (sliceComparator.compare(curHighest, winnerSlice) < 0) {
        LOG.log(INFO, curHand + " with highest slice " + curHighest + " beats " + winnerSlice);
        winnerID = i;
        winnerSlice = curHighest;
      }
    }
    return new Winner(winnerID, winnerSlice);
  }

  private HandSuitType getHandSuitType(Hand hand) {
    return hand.getHandSuitType(dominantRank, dominantSuit);
  }
}
