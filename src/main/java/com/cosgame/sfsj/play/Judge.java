package com.cosgame.sfsj.play;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.google.common.annotations.VisibleForTesting;
import java.util.List;

public class Judge {

  CardRank dominantRank;
  CardSuit dominantSuit;
  boolean tractorsEnabled;

  public Judge(CardRank dominantRank, CardSuit dominantSuit, boolean tractorsEnabled) {
    this.dominantRank = dominantRank;
    this.dominantSuit = dominantSuit;
    this.tractorsEnabled = tractorsEnabled;
  }

  /**
   * Determine the result of a round according to the four hands, dominant rank and dominant suit.
   *
   * @param hands A list of exactly FOUR hands. The first hand is from the first player who played
   * in this round, which therefore determines the format of this round (a starter hand).
   * @return The index of player (in the input list) who won this round and the points gained.
   */
  public RoundRes compareRound(List<Hand> hands) {
    return new RoundRes(0, 0);
  }

  /**
   * Judge if the second {@code Hand} can beat the starter {@code Hand}.
   */
  @VisibleForTesting
  protected boolean canBeat(Hand starter, Hand other) {
    return false;
  }

  class RoundRes {

    int winner;
    int points;

    public RoundRes(int winner, int points) {
      this.winner = winner;
      this.points = points;
    }
  }
}
