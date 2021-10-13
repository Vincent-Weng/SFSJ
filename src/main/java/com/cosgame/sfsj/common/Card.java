package com.cosgame.sfsj.common;

import java.util.Objects;

public class Card {

  private final CardRank number;
  private final CardSuit suit;
  private static final Card RED_JOKER = new Card(CardRank.JOKER, CardSuit.RED_JOKER);
  private static final Card BLACK_JOKER = new Card(CardRank.JOKER, CardSuit.BLACK_JOKER);

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

  private Card(CardRank number, CardSuit suite) {
    this.number = number;
    this.suit = suite;
  }

  public CardRank getRank() {
    return number;
  }

  public CardSuit getSuit() {
    return suit;
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

  public enum CardSuit {
    SPADE("♠"),
    HEART("♥"),
    CLUB("♣"),
    DIAMOND("♦"),
    RED_JOKER("#"),
    BLACK_JOKER("+");

    private final String symbol;

    CardSuit(String symbol) {
      this.symbol = symbol;
    }

    @Override
    public String toString() {
      return symbol;
    }
  }

  public enum CardRank {
    JOKER("  "),
    ACE(" A"),
    TWO(" 2"),
    THREE(" 3"),
    FOUR(" 4"),
    FIVE(" 5"),
    SIX(" 6"),
    SEVEN(" 7"),
    EIGHT(" 8"),
    NINE(" 9"),
    TEN("10"),
    JACK(" J"),
    QUEEN(" Q"),
    KING(" K");

  private final String symbol;

  CardRank(String symbol) {
    this.symbol = symbol;
  }

  @Override
  public String toString() {
    return symbol;
  }
}
}