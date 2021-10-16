package com.cosgame.sfsj.util;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.play.Hand;
import com.cosgame.sfsj.play.Hand.Slice;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Utility class to hold card serialization/deserialization functions.
 */
public class TestUtils {

  private static final Random rand = new Random();

  private TestUtils() {
  }

  /**
   * Generate a card from a string, represented by two characters:
   *
   * Red Joker = JO, Black Joker = jo, 2 Spade = 2s, 10 Heart = Th, J Club = Jc, A Diamond = Ad
   */
  public static Card cardOf(String card) {
    if (card.equals("JO")) {
      return Card.of(CardRank.JOKER, CardSuit.RED_JOKER);
    } else if (card.equals("jo")) {
      return Card.of(CardRank.JOKER, CardSuit.BLACK_JOKER);
    } else {
      char rankCh = card.charAt(0);
      char suitCh = card.charAt(1);
      CardRank rank;
      CardSuit suit;
      switch (rankCh) {
        case 'A':
          rank = CardRank.ACE;
          break;
        case 'K':
          rank = CardRank.KING;
          break;
        case 'Q':
          rank = CardRank.QUEEN;
          break;
        case 'J':
          rank = CardRank.JACK;
          break;
        case 'T':
          rank = CardRank.TEN;
          break;
        default:
          rank = CardRank.values()[rankCh - '0'];
      }
      switch (suitCh) {
        case 's':
          suit = CardSuit.SPADE;
          break;
        case 'h':
          suit = CardSuit.HEART;
          break;
        case 'c':
          suit = CardSuit.CLUB;
          break;
        case 'd':
          suit = CardSuit.DIAMOND;
          break;
        default:
          throw new IllegalArgumentException("Unexpected char for suit: " + suitCh);
      }
      return Card.of(rank, suit);
    }
  }

  /**
   * Generate list of cards from a string. Each card is represented with two characters:
   */
  public static Hand handOf(String cards) {
    Iterable<String> cardStr = Splitter.fixedLength(2).split(cards);
    List<Card> res = new ArrayList<>(cards.length() / 2);
    cardStr.forEach(cStr -> res.add(cardOf(cStr)));
    return new Hand(res);
  }

  public static Slice sliceOf(String card, int multiplex) {
    return new Slice(cardOf(card), multiplex);
  }

  public static String prettyPrintCards(Card... card) {
    return prettyPrintCards(Arrays.asList(card), Integer.MAX_VALUE);
  }

  public static String prettyPrintCards(List<Card> cards, int maxCardPerRow) {
    StringBuilder sb = new StringBuilder();

    for (List<Card> cardRow : Lists.partition(cards, maxCardPerRow)) {
      int size = cardRow.size();
      sb.append("┌─").append(String.join("", Collections.nCopies(size, "───┐"))).append("\n");

      sb.append("| ");
      cardRow.forEach(c -> sb.append(c.getRank() == CardRank.JOKER ? "  " : " ").append(c.getRank())
          .append("|"));
      sb.append("\n");

      sb.append("| ");
      cardRow.forEach(c -> sb.append(c.getRank() == CardRank.JOKER ? " " : "  ").append(c.getSuit())
          .append("|"));
      sb.append("\n");

      sb.append("| ");
      cardRow.forEach(c -> sb.append("   ").append("|"));
      sb.append("\n");

      sb.append("└─").append(String.join("", Collections.nCopies(size, "───╯"))).append("\n");
    }

    return sb.toString();
  }

  public static Card cardOfRank(CardRank rank) {
    return Card.of(rank, CardSuit.values()[rand.nextInt(4)]);
  }

  public static Card cardOfRank(int rank) {
    if (rank < 1 || rank > 13) {
      throw new IllegalArgumentException("Illegal rank integer. Must be in [1, 13]");
    }
    return Card.of(CardRank.values()[rank], CardSuit.values()[rand.nextInt(4)]);
  }

  public static Card cardOfSuit(CardSuit suit) {
    return Card.of(CardRank.values()[rand.nextInt(14) + 1], suit);
  }
}
