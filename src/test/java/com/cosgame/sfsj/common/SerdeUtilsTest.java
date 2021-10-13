package com.cosgame.sfsj.common;

import static org.junit.Assert.assertEquals;

import com.cosgame.sfsj.util.SerdeUtils;
import org.junit.Test;

public class SerdeUtilsTest {

  @Test
  public void TestPrettyPrint() {
    Deck deck = new Deck();
    String printRes = SerdeUtils.prettyPrintCards(deck.getDeck(), 13);
    // 5 card rows, times 5 text lines for each card row
    assertEquals(5 * 5, printRes.split("\n").length);
    System.out.println("=== This Test CANNOT be automated. Please visual check for pretty print ===");
    System.out.println(printRes);
  }
}