package com.cosgame.sfsj.play;

import static com.cosgame.sfsj.util.TestUtils.cardsOf;
import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.play.Game.RoundResult;
import java.util.List;
import org.junit.Test;

public class GameTest {

  @Test
  public void testPlayRound1() {
    Game game = new Game(CardRank.TWO, 2);
    game.claimDominantSuit(CardSuit.DIAMOND, 0);

    // start from banker
    RoundResult res1 = game.judgeRound(2, List.of(
        cardsOf("As"),
        cardsOf("3s"),
        cardsOf("Ts"),
        cardsOf("3s")));
    assertEquals(2, res1.winner);
    assertEquals(10, res1.pointsGained);
    assertEquals(0, game.getAttackerPointsGained());

    // start from banker's previous position
    RoundResult res2 = game.judgeRound(1, List.of(
        cardsOf("As"),
        cardsOf("3s"),
        cardsOf("Ts"),
        cardsOf("3s")));
    assertEquals(1, res2.winner);
    assertEquals(10, res2.pointsGained);
    assertEquals(10, game.getAttackerPointsGained());
  }

  @Test
  public void testRob() {
    Game game = new Game(CardRank.TWO, 2);
    game.claimDominantSuit(CardSuit.DIAMOND, 0);
    game.setTreasureCards(cardsOf("JsJsTs9s8s7s6s6s5s5s4s3s"));

    RoundResult lastRound = game.judgeRound(2, List.of(
        cardsOf("As"),
        cardsOf("3s"),
        cardsOf("Ts"),
        cardsOf("5d")));
    assertEquals(1, lastRound.winner);
    assertEquals(15, lastRound.pointsGained);
    assertEquals(40, game.robTreasure(lastRound));
  }
}