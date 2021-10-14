package com.cosgame.sfsj.util;

import com.cosgame.sfsj.common.Card;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Utility class to hold card serialization/deserialization functions.
 *
 */
public class SerdeUtils {

  private SerdeUtils() {
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
      cardRow.forEach(c -> sb.append(" ").append(c.getRank()).append("|"));
      sb.append("\n");

      sb.append("| ");
      cardRow.forEach(c -> sb.append("  ").append(c.getSuit()).append("|"));
      sb.append("\n");

      sb.append("| ");
      cardRow.forEach(c -> sb.append("   ").append("|"));
      sb.append("\n");

      sb.append("└─").append(String.join("", Collections.nCopies(size, "───╯"))).append("\n");
    }

    return sb.toString();
  }
}
