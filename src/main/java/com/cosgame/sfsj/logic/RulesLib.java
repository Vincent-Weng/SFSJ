package com.cosgame.sfsj.logic;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.common.Hand;
import java.util.List;

public class RulesLib {

  class RoundRes {
    int beater;
    int points;

    public RoundRes(int beater, int points) {
      this.beater = beater;
      this.points = points;
    }
  }

  /**
   * Determine the result of a round according to the four hands, dominant rank and dominant suit.
   *
   * @param hands A list of exactly FOUR hands. The first hand is from the first player who played
   * in this round, which therefore determines the format of this round.
   * @return The index of player (in the input list) who won this round and the points gained.
   */
  public RoundRes compareRound(List<Hand> hands, CardRank dominantRank, CardSuit dominantSuit) {
    return new RoundRes(0, 0);
  }
}
