package com.cosgame.sfsj.util;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import java.util.Comparator;
import java.util.Random;

public class CardUtils {

  private static final Random rand = new Random();

  /**
   * Compare two cards considering the dominants in the game.
   *
   * Check joker first, then dominant rank, then dominant suit.
   *
   * A stronger card has a smaller sort value.
   */
  public static Comparator<Card> cardComparator(CardRank dominantRank, CardSuit dominantSuit) {
    return (card1, card2) -> {
      if (card1.getRank() == CardRank.JOKER || card2.getRank() == CardRank.JOKER) {
        return card1.compareTo(card2);
      }
      if (card1.getRank() == dominantRank) {
        if (card2.getRank() == dominantRank) {
          if (card1.getSuit() == dominantSuit) {
            return card2.getSuit() == dominantSuit ? 0 : -1;
          } else {
            return card2.getSuit() == dominantSuit ? 1 : card1.compareTo(card2);
          }
        } else {
          return -1; // card1 stronger
        }
      } else if (card2.getRank() == dominantRank) {
        return 1; // card2 stronger
      } else {
        if (card1.getSuit() == dominantSuit) {
          if (card2.getSuit() == dominantSuit) {
            return card1.compareTo(card2);
          } else {
            return -1; // card1 stronger
          }
        } else if (card2.getSuit() == dominantSuit) {
          return 1; // card2 stronger
        } else {
          return card1.compareTo(card2);
        }
      }
    };
  }

  public static Card cardOfRank(int rank) {
    if (rank < 1 || rank > 13) {
      throw new IllegalArgumentException("Illegal rank integer. Must be in [1, 13]");
    }
    return Card.of(CardRank.values()[rank], CardSuit.values()[rand.nextInt(4)]);
  }

  public static Card cardOfRank(CardRank rank) {
    return Card.of(rank, CardSuit.values()[rand.nextInt(4)]);
  }

  public static Card cardOfSuit(CardSuit suit) {
    return Card.of(CardRank.values()[rand.nextInt(14) + 1], suit);
  }
}
