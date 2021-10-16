package com.cosgame.sfsj.play;

import static com.cosgame.sfsj.util.TestUtils.handOf;
import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import org.junit.Test;

public class JudgeTest {

  @Test
  public void testJudgeRound1() {
    Judge judge = new Judge(CardRank.TWO, CardSuit.HEART);
    Hand[] hands1 = new Hand[]{handOf("AsAsKsKs"), handOf("AdAdKdKd"), handOf("5h5h5h5h"),
        handOf("JOJO4h4h")};
    assertEquals(3, judge.whoWins(hands1));
    Hand[] hands2 = new Hand[]{handOf("AsAsAsKsKsKs"), handOf("5h5h5h5h3h3h"),
        handOf("7h7h7hjojoJO"), handOf("JOJOJO2h2h3s")};
    assertEquals(0, judge.whoWins(hands2));
    Hand[] hands3 = new Hand[]{handOf("As"), handOf("As"), handOf("Ts"), handOf("3s")};
    assertEquals(0, judge.whoWins(hands3));
    Hand[] hands4 = new Hand[]{handOf("As"), handOf("3s"), handOf("Ks"), handOf("Kc")};
    assertEquals(0, judge.whoWins(hands4));
    Hand[] hands5 = new Hand[]{handOf("Ts"), handOf("Ad"), handOf("5h"), handOf("JO")};
    assertEquals(3, judge.whoWins(hands5));
    Hand[] hands6 = new Hand[]{handOf("KsKs"), handOf("5h6h"), handOf("2s2s"), handOf("2h2h")};
    assertEquals(3, judge.whoWins(hands6));
    Hand[] hands7 = new Hand[]{handOf("As"), handOf("Th"), handOf("3s"), handOf("5h")};
    assertEquals(1, judge.whoWins(hands7));
    Hand[] hands8 = new Hand[]{handOf("AsKsKs"), handOf("2d2dJO"), handOf("4s5c6c"),
        handOf("Th2h2h")};
    assertEquals(3, judge.whoWins(hands8));
  }
}