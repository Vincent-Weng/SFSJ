package com.cosgame.sfsj.common;

import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Card implements Comparable<Card> {

  private static final Card RED_JOKER = new Card(CardRank.JOKER, CardSuit.RED_JOKER);
  private static final Card BLACK_JOKER = new Card(CardRank.JOKER, CardSuit.BLACK_JOKER);
  private final CardRank number;
  private final CardSuit suit;

  private Card(CardRank number, CardSuit suite) {
    this.number = number;
    this.suit = suite;
  }

  /**
   * Get an immutable card instance.
   *
   * @param rank rank of the card. Can be arbitrary if suit is a joker.
   * @param suit suit of the card
   * @return the card instance
   */
  public static Card of(CardRank rank, CardSuit suit) {
    if (suit == CardSuit.RED_JOKER) {
      return RED_JOKER;
    }
    if (suit == CardSuit.BLACK_JOKER) {
      return BLACK_JOKER;
    }
    return new Card(rank, suit);
  }

  public CardRank getRank() {
    return number;
  }

  public CardSuit getSuit() {
    return suit;
  }

  /**
   * Natural card sorting without considering dominants:
   *
   * RED_JOKER (0), BLACK_JOKER (1), SPADE:ACE (2), SPADE:KING (3), ..., SPADE:TWO, HEART:ACE,
   * HEART:KING, ..., HEART:TWO, CLUB:ACE, CLUB:KING, ..., CLUB:TWO, DIAMOND:ACE, DIAMOND:KING, ...,
   * DIAMOND:TWO (53),
   */
  private int cardSortBase() {
    if (getSuit() == CardSuit.RED_JOKER) {
      return 0;
    } else if (getSuit() == CardSuit.BLACK_JOKER) {
      return 1;
    } else if (getRank() == CardRank.ACE) {
      return getSuit().ordinal() * 13 + 2;
    } else {
      return getSuit().ordinal() * 13 + (13 - getRank().ordinal()) + 3;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Card)) {
      return false;
    }
    Card card = (Card) o;
    return number == card.number && suit == card.suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, suit);
  }

  @Override
  public String toString() {
    return number.toString() + suit.toString();
  }

  @Override
  public int compareTo(@NonNull Card that) {
    return this.cardSortBase() - that.cardSortBase();
  }

  public enum CardSuit {
    SPADE("♠", "s"),
    HEART("♥", "h"),
    CLUB("♣", "c"),
    DIAMOND("♦", "d"),
    RED_JOKER("JO", "JO"),
    BLACK_JOKER("jo", "jo");

    private final String symbol;
    public final String serializeSymbol;

    CardSuit(String symbol, String serializeSymbol) {
      this.symbol = symbol;
      this.serializeSymbol = serializeSymbol;
    }

    @Override
    public String toString() {
      return symbol;
    }
  }

  public enum CardRank {
    JOKER(" ", ""),
    ACE(" A", "A"),
    TWO(" 2", "2"),
    THREE(" 3", "3"),
    FOUR(" 4", "4"),
    FIVE(" 5", "5"),
    SIX(" 6", "6"),
    SEVEN(" 7", "7"),
    EIGHT(" 8", "8"),
    NINE(" 9", "9"),
    TEN("10", "T"),
    JACK(" J", "J"),
    QUEEN(" Q", "Q"),
    KING(" K", "K");

    private final String symbol;
    public String serializeSymbol;

    CardRank(String symbol, String serializeSymbol) {
      this.symbol = symbol;
      this.serializeSymbol = serializeSymbol;
    }

    @Override
    public String toString() {
      return symbol;
    }
  }
}
