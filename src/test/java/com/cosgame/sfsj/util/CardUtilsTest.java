package com.cosgame.sfsj.util;

import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class CardUtilsTest {

  @Test
  public void testCardComparator() {
    List<Card> cards1 = TestUtils.cards("JOjo9c9s9h9dAc5cJsQhKd");
    List<Card> cards2 = new ArrayList<>(cards1);
    cards2.sort(CardUtils.cardComparator(CardRank.NINE, CardSuit.CLUB));
    assertEquals(cards1, cards2);
  }
}
