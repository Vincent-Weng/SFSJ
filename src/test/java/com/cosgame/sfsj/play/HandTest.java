package com.cosgame.sfsj.play;

import static com.cosgame.sfsj.util.TestUtils.cardOfRank;
import static com.cosgame.sfsj.util.TestUtils.handOf;
import static com.cosgame.sfsj.util.TestUtils.sliceOf;
import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.play.Hand.Pattern;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

public class HandTest {

  @Test
  public void testStarterSplit1() {
    Hand hand1 = handOf("AsAsKsKs");
    assertEquals(hand1.maxSplit(),
        Pattern.of(sliceOf("As", 2), sliceOf("Ks", 2)));
  }

  @Test
  public void testStarterSplit2() {
    Hand hand1 = handOf("AsAsKsKsQsQsQs");
    assertEquals(hand1.maxSplit(),
        Pattern.of(sliceOf("Qs", 3), sliceOf("As", 2), sliceOf("Ks", 2)));
  }

  @Test
  public void testStarterSplit3() {
    Hand hand1 = handOf("AsKsTsTsTsTsKs");
    assertEquals(hand1.maxSplit(),
        Pattern.of(sliceOf("Ts", 4), sliceOf("Ks", 2), sliceOf("As", 1)));
  }

  @Test
  public void testFollowerSplit1() {
    Hand hand1 = handOf("AsAsKsKs");
    Hand hand2 = handOf("5d5d5d5d");
    assertEquals(hand2.splitAccordingTo(hand1.maxSplit()),
        Pattern.of(sliceOf("5d", 2), sliceOf("5d", 2)));
  }

  @Test
  public void testFollowerSplit2() {
    Hand hand1 = handOf("AsAsKsKsQsQsQs");
    Hand hand2 = handOf("5d5d5d5d4d4d4d");
    assertEquals(hand2.splitAccordingTo(hand1.maxSplit()),
        Pattern.of(sliceOf("4d", 3), sliceOf("5d", 2), sliceOf("5d", 2)));
  }

  @Test
  public void testFollowerSplit3() {
    Hand hand1 = handOf("AsKsKs");
    Hand hand2 = handOf("5d5d5d");
    assertEquals(hand2.splitAccordingTo(hand1.maxSplit()),
        Pattern.of(sliceOf("5d", 2), sliceOf("5d", 1)));
  }

  @Test
  public void testFollowerSplit4() {
    Hand hand1 = handOf("KsKs");
    Hand hand2 = handOf("5d5d");
    assertEquals(hand2.splitAccordingTo(hand1.maxSplit()),
        Pattern.of(sliceOf("5d", 2)));
  }

  @Test
  public void testFollowerSplitNotMatch1() {
    Hand hand1 = handOf("AsAsKsKsQsQs");
    Hand hand2 = handOf("5d5d5d6d6d6d");
    assertEquals(hand2.splitAccordingTo(hand1.maxSplit()), Pattern.NOT_MATCH);
  }

  @Test
  public void testFollowerSplitNotMatch2() {
    Hand hand1 = handOf("AsAs");
    Hand hand2 = handOf("5d6d");
    assertEquals(hand2.splitAccordingTo(hand1.maxSplit()), Pattern.NOT_MATCH);
  }

  @Test
  public void testGetPoints() {
    Hand hand1 = new Hand(ImmutableList.of(cardOfRank(5)));
    Hand hand2 = new Hand(ImmutableList.of(cardOfRank(10)));
    Hand hand3 = new Hand(ImmutableList.of(cardOfRank(13)));
    Hand hand4 = new Hand(ImmutableList.of(cardOfRank(6)));
    Hand hand5 = new Hand(ImmutableList.of(cardOfRank(5), cardOfRank(6))
    );
    Hand hand6 = new Hand(ImmutableList.of(cardOfRank(5), cardOfRank(5), cardOfRank(6))
    );
    Hand hand7 = new Hand(
        ImmutableList.of(cardOfRank(10), cardOfRank(11), cardOfRank(12), cardOfRank(13))
    );
    Hand hand8 = new Hand(ImmutableList.of(cardOfRank(6), cardOfRank(7), cardOfRank(8))
    );
    assertEquals(5, hand1.getPoints());
    assertEquals(10, hand2.getPoints());
    assertEquals(10, hand3.getPoints());
    assertEquals(0, hand4.getPoints());
    assertEquals(5, hand5.getPoints());
    assertEquals(10, hand6.getPoints());
    assertEquals(20, hand7.getPoints());
    assertEquals(0, hand8.getPoints());
  }
}