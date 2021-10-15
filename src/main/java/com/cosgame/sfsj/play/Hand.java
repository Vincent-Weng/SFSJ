package com.cosgame.sfsj.play;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.util.CardUtils;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Hand are split by suit first (see {@link HandSuitType}). Hands that are not {@code MIXED} can be
 * further split by rank (see {@link Slice}).
 */
public class Hand {

  private final List<Card> cards;
  private final int points;
  private final HandSuitType suitType;
  private final boolean tractorEnabled;
  private final CardRank dominantRank;
  private final CardSuit dominantSuit;

  public Hand(List<Card> cards, CardRank dominantRank, CardSuit dominantSuit,
      boolean tractorEnabled) {
    this.cards = cards;
    this.dominantRank = dominantRank;
    this.dominantSuit = dominantSuit;
    this.tractorEnabled = tractorEnabled;
    this.points = calculatePoints();
    if (isHandMixed(cards)) {
      this.suitType = HandSuitType.MIXED;
    } else {
      this.suitType = isDominant(cards.get(0)) ?
          HandSuitType.DOMINANTS : HandSuitType.NON_DOMINANTS;
    }
  }

  public int getPoints() {
    return points;
  }

  public HandSuitType getHandSuitType() {
    return suitType;
  }

  public Pattern starterSplit() {
    if (tractorEnabled) {
      throw new UnsupportedOperationException("Tractor mode not implemented yet");
    } else {
      // count cards occurrence, construct slices, then sort by multiplex and card
      List<Slice> slices = cards.stream()
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
          .stream()
          .map(e -> new Slice(e.getKey(), e.getValue().intValue()))
          .sorted((s1, s2) -> {
            int diffMul = s2.multiplex - s1.multiplex;
            return diffMul == 0 ? CardUtils.cardComparator(dominantRank, dominantSuit)
                .compare(s1.card, s2.card) : diffMul;
          })
          .collect(Collectors.toList());
      return new Pattern(slices);
    }
  }

  /**
   * Split a hand according to a starter pattern.
   *
   * For example, a hand of 555 will be considered as one pair + one single card, if the input
   * pattern is Pattern[1x A♣︎, 2x K♣︎].
   *
   * NOTE: This method should only be called on a non-mixed hand. Therefore {@link
   * Hand#getHandSuitType()} must be called to check this.
   *
   * @return the split result pattern.
   */
  public Pattern followerSplit(Pattern starterPattern) {
    if (this.suitType == HandSuitType.MIXED) {
      throw new IllegalStateException(
          "This hand has mixed suit. Analyzing pattern makes no sense.");
    }
    if (tractorEnabled) {
      throw new UnsupportedOperationException("Tractor mode not implemented yet");
    } else {
      // TODO
      return null;
    }
  }

  private boolean isHandMixed(List<Card> cards) {
    Card c1 = cards.get(0);
    for (Card c : cards) {
      if (isDominant(c1)) {
        if (!isDominant(c)) {
          return true;
        }
      } else {
        if (c1.getSuit() != c.getSuit()) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isDominant(Card card) {
    return card.getRank() == CardRank.JOKER
        || card.getRank() == dominantRank
        || card.getSuit() == dominantSuit;
  }

  private int calculatePoints() {
    int pts = 0;
    for (Card c : cards) {
      switch (c.getRank()) {
        case TEN:
        case KING:
          pts += 10;
          break;
        case FIVE:
          pts += 5;
      }
    }
    return pts;
  }

  // Hand suite type. All dominants are considered the same suit
  enum HandSuitType {
    MIXED, // Not all dominants and have more than one suit. Worst hand. Not allowed as starter.
    DOMINANTS, // All dominants. Can be used to override.
    NON_DOMINANTS
  }

  /**
   * A unit of card in a hand, like a single card, a pair, or a tractor if enabled.
   *
   * The multiplex means the number of duplicates of a card in a slice.
   * <li> 1 - Single card </li>
   * <li> 2 - Pair </li>
   * <li> 3 - Triplet </li>
   * <li> 4 - Quadruplet </li>
   *
   * Specially, in tractor mode, tractors are considered single slices instead of combos. The
   * multiplex is then X * 10 + Y, where X is the X-plet and Y is the sequence length. For example,
   * 556677 is a pair of 3, X = 2 and Y = 3 so multiplex = 23. Similarly, 33445566 gives 24, 777888
   * gives 32 and JJJQQQKKK gives 33
   *
   * NOTE: There could be more than one way of split a hand into slices. For example 555 as dominant
   * can be split as a triplet + one single card, in order to override a non-dominant hand of AKK.
   *
   * {@link Hand#starterSplit()} always choose the way to split to achieve the highest multiplex
   * number.
   *
   * {@link Hand#followerSplit(Pattern)} split according to the starter's pattern.
   */
  public static class Slice {

    public final Card card;
    public final int multiplex;

    public Slice(Card card, int multiplex) {
      this.card = card;
      this.multiplex = multiplex;
    }

    @Override
    public String toString() {
      return "{" + multiplex + "x" + card + "}";
    }
  }

  public static class Pattern {

    List<Slice> slices;

    public Pattern(List<Slice> slices) {
      this.slices = slices;
    }

    public boolean isCombo() {
      return slices.size() > 1;
    }

    @Override
    public String toString() {
      return "Pattern" + slices;
    }
  }
}
