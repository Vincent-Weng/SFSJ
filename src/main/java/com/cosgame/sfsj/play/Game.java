package com.cosgame.sfsj.play;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.common.GameDeck;
import com.cosgame.sfsj.play.Hand.Slice;
import com.cosgame.sfsj.play.Judge.Winner;
import com.cosgame.sfsj.util.CardUtils;
import com.google.common.annotations.VisibleForTesting;
import java.util.Iterator;
import java.util.List;

public class Game {

  public int getAttackerPointsGained() {
    return attackerPointsGained;
  }

  class RoundResult {

    int winner;
    int pointsGained;
    Slice winningSlice;

    public RoundResult(int winner, int pointsGained, Slice winningSlice) {
      this.winner = winner;
      this.pointsGained = pointsGained;
      this.winningSlice = winningSlice;
    }
  }

  // pre-set game properties
  public final GameDeck gameDeck;
  public final CardRank dominantRank; // pre-set for this game

  // properties set at claim/secretCreating phase
  private int bankerID; // usually set at constructor, except for first game
  private Judge judge;
  private CardSuit dominantSuit; // determined during dealing

  private List<Card> treasureCards;

  // moving-pieces
  private int attackerPointsGained;
  private List<List<Card>> playerCards;

  /**
   * Initialize a game.
   *
   * @param bankerID the absolute id of the banker (0, 1, 2, 3). If this is a new game, use -1.
   */
  public Game(CardRank dominantRank, int bankerID) {
    this.gameDeck = new GameDeck(4);
    this.playerCards = gameDeck.getPlayerCards();
    this.dominantRank = dominantRank;
    this.bankerID = bankerID;
    this.attackerPointsGained = 0;
  }

  public GameDeck getGameDeck() {
    return gameDeck;
  }

  public void setPlayerCards(List<List<Card>> playerCards) {
    this.playerCards = playerCards;
  }

  public List<Card> getTreasureCards() {
    return treasureCards;
  }

  public List<List<Card>> getPlayerCards() {
    return playerCards;
  }

  public void claimDominantSuit(CardSuit dominantSuit, int claimerID) {
    this.dominantSuit = dominantSuit;
    this.judge = new Judge(dominantRank, dominantSuit);
    if (bankerID == -1) {
      bankerID = claimerID;
    }
  }

  public void setTreasureCards(List<Card> treasureCardsFromBanker) {
    this.treasureCards = treasureCardsFromBanker;
  }

  public boolean allowPlay(int playerID, List<Card> cards) {
    return true;
  }

  public RoundResult playRound(int startPlayerID, List<List<Card>> hands) {
    // Split logic into two parts for easier testing
    removeCardFromPlayer(startPlayerID, hands);
    return judgeRound(startPlayerID, hands);
  }

  public int robTreasure(RoundResult lastRoundResult) {
    if (!isAttacker(lastRoundResult.winner)) {
      return 0;
    }

    int robMultiplex = lastRoundResult.winningSlice.multiplex;
    int treasurePoints = CardUtils.calculatePoints(treasureCards);
    return treasurePoints * (int) Math.pow(2, robMultiplex);
  }

  @VisibleForTesting
  protected RoundResult judgeRound(int startPlayerID, List<List<Card>> hands) {
    int[] absoluteIds = getAbsoluteIDs(startPlayerID);
    Winner winner = judge.whoWins(hands.stream().map(Hand::new).toArray(Hand[]::new));
    int points = hands.stream().mapToInt(CardUtils::calculatePoints).sum();
    if (isAttacker(absoluteIds[winner.id])) {
      attackerPointsGained += points;
    }
    return new RoundResult(absoluteIds[winner.id], points, winner.winningSlice);
  }

  @VisibleForTesting
  protected void removeCardFromPlayer(int startPlayerID, List<List<Card>> hands) {
    int[] absoluteIds = getAbsoluteIDs(startPlayerID);
    for (int i = 0; i < 4; i++) {
      removeCardFrom(absoluteIds[i], hands.get(i));
    }
  }

  private boolean isAttacker(int playerID) {
    return (playerID & 1) != (bankerID & 1);
  }

  private int[] getAbsoluteIDs(int roundStartPlayerID) {
    int[] absoluteIds = new int[4];
    for (int i = 0; i < 4; i++) {
      absoluteIds[i] = (i + roundStartPlayerID) % 4;
    }
    return absoluteIds;
  }

  private void removeCardFrom(int playerID, List<Card> cardsToRemove) {
    for (Card toRemove : cardsToRemove) {
      Iterator<Card> playerCard = playerCards.get(playerID).iterator();
      while (playerCard.hasNext()) {
        Card card = playerCard.next();
        if (toRemove.equals(card)) {
          playerCard.remove();
          break;
        }
      }
    }
  }
}
