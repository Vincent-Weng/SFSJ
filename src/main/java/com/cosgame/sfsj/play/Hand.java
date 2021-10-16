package com.cosgame.sfsj.play;

import static com.cosgame.sfsj.util.CardUtils.isDominant;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.util.CardUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Hand are split by suit first (see {@link HandSuitType}). Hands that are not {@code MIXED} can be
 * further split by rank (see {@link Slice}).
 */
public class Hand {

  private final List<Card> cards;
  private final int points;

  public Hand(List<Card> cards) {
    this.cards = cards;
    this.points = calculatePoints();
  }

  public List<Card> getCards() {
    return cards;
  }

  public HandSuitType getHandSuitType(CardRank dominantRank, CardSuit dominantSuit) {
    if (isHandMixed(cards, dominantRank, dominantSuit)) {
      return HandSuitType.MIXED;
    } else {
      return isDominant(cards.get(0), dominantRank, dominantSuit) ?
          HandSuitType.DOMINANTS : HandSuitType.NON_DOMINANTS;
    }
  }

  public int getPoints() {
    return points;
  }

  /**
   * Always choose the way to split to achieve the highest multiplex number. The first player in a
   * round would use this to generate a starterPattern.
   *
   * For example, 4♣︎4♣︎4♣︎5♣︎5♣︎ will give Pattern{3x 4♣︎, 2x 5♣︎}.
   */
  public Pattern maxSplit() {
    // count cards occurrence, construct slices, then sort by multiplex and card
    List<Slice> slices = cards.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet()
        .stream()
        .map(e -> new Slice(e.getKey(), e.getValue().intValue()))
        .sorted((s1, s2) -> s2.multiplex - s1.multiplex)
        .collect(Collectors.toList());
    return new Pattern(slices);

  }

  /**
   * Split a hand according to a starter pattern. If this hand can't match the input pattern (for
   * example, this hand is 445 and the input is 444), return {}
   *
   * For example, a hand of 5♣︎5♣︎5♣︎ will be considered as one pair of 5♣︎ + one single 5♣︎, if the
   * input pattern is Pattern[1x A♣︎, 2x K♣︎].
   *
   * NOTE: This method should only be called on a non-mixed hand. Therefore {@link
   * Hand#getHandSuitType(CardRank, CardSuit)} must be called to check this.
   *
   * @return the split result pattern. If not match, return {@code Pattern.NOT_MATCH}.
   */
  public Pattern splitAccordingTo(Pattern targetPattern) {
    List<Slice> handMaxSplit = maxSplit().slices;
    List<Slice> result = new ArrayList<>();
    /* Greedy match: always match the slice in this hand with same multiplex with target's slice
    first. If it can't find such, take target slice from a higher slice in hand. If no such slice
    can be found, return NOT_MATCH */
    for (Slice slice : targetPattern.slices) {
      Slice foundInHand = null;
      // First check if there is an exact match of slice (same multiplex) in hand.
      for (Slice sl : handMaxSplit) {
        if (sl.multiplex == slice.multiplex) {
          foundInHand = sl;
          break;
        }
      }
      // No exact slice match, can use a higher slice to match lower. (e.g. 3x 5♣︎ to match 2x 5♣︎).
      if (foundInHand == null) {
        for (Slice sl : handMaxSplit) {
          if (sl.multiplex > slice.multiplex) {
            foundInHand = sl;
          }
        }
      }
      // No match for current slice
      if (foundInHand == null) {
        return Pattern.NOT_MATCH;
      } else {
        result.add(new Slice(foundInHand.card, slice.multiplex));
        foundInHand.multiplex -= slice.multiplex;
      }
    }
    return new Pattern(result);
  }

  private boolean isHandMixed(List<Card> cards, CardRank dominantRank, CardSuit dominantSuit) {
    Card c1 = cards.get(0);
    for (Card c : cards) {
      if (isDominant(c1, dominantRank, dominantSuit)) {
        if (!isDominant(c, dominantRank, dominantSuit)) {
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

  @Override
  public String toString() {
    return "Hand{" + cards + '}';
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
  public enum HandSuitType {
    MIXED, // Not all dominants and have more than one suit. Worst hand. Not allowed as starter.
    DOMINANTS, // All dominants. Can be used to override.
    NON_DOMINANTS
  }

  /**
   * An atomic unit of card in a hand, like a single card, a pair, a triplet.
   *
   * The multiplex means the number of duplicates of a card in a slice.
   * <li> 1 - Single card </li>
   * <li> 2 - Pair </li>
   * <li> 3 - Triplet </li>
   * <li> 4 - Quadruplet </li>
   */
  public static class Slice {

    final Card card;
    int multiplex;

    public Slice(Card card, int multiplex) {
      this.card = card;
      this.multiplex = multiplex;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Slice)) {
        return false;
      }
      Slice slice = (Slice) o;
      return multiplex == slice.multiplex && card.equals(slice.card);
    }

    @Override
    public int hashCode() {
      return Objects.hash(card, multiplex);
    }

    @Override
    public String toString() {
      return "{" + multiplex + "x" + card + "}";
    }
  }

  /**
   * A way to "understand" a hand. A pattern is a list of slices
   *
   * NOTE: There could be more than one way of split a hand into slices. For example 555 as dominant
   * can be split as a triplet + one single card, in order to override a non-dominant hand of AKK.
   *
   * See {@link Hand#maxSplit()} and {@link Hand#splitAccordingTo(Pattern)} split according to the
   * starter's pattern.
   */
  public static class Pattern {

    public static final Pattern NOT_MATCH = Pattern.of(); // Pattern don't match, so not comparable
    final List<Slice> slices;

    public Pattern(List<Slice> slices) {
      this.slices = new ArrayList<>(slices);
      // Natural order regardless of dominants
      this.slices.sort((s1, s2) -> {
        int diffMul = s2.multiplex - s1.multiplex;
        return diffMul == 0 ? s1.card.compareTo(s2.card) : diffMul;
      });
    }

    public static Pattern of(Slice... slices) {
      return new Pattern(Arrays.asList(slices));
    }

    public boolean isCombo() {
      return slices.size() > 1;
    }

    /**
     * Highest slice in a pattern. This is used to compare with other pattern.
     */
    public Slice getHighestSlice(CardRank dominantRank, CardSuit dominantSuit) {
      slices.sort((s1, s2) -> {
        int diffMul = s2.multiplex - s1.multiplex;
        return diffMul == 0 ? CardUtils.cardComparator(dominantRank, dominantSuit)
            .compare(s1.card, s2.card) : diffMul;
      });
      return slices.get(0);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Pattern)) {
        return false;
      }
      Pattern pattern = (Pattern) o;
      return slices.equals(pattern.slices);
    }

    @Override
    public int hashCode() {
      return Objects.hash(slices);
    }

    @Override
    public String toString() {
      return "Pattern" + slices;
    }
  }
}
