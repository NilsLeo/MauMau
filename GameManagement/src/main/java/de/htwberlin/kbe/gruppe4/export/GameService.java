package de.htwberlin.kbe.gruppe4.export;


import de.htwberlin.kbe.gruppe4.entity.*;

import java.util.List;
import java.util.Map;

public interface GameService {
    void setRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce);
    void startGame();
    Card getLeadCard();
    void addCardToTable(Card card);
    Card drawCard(Player player);
    void refillDeckwithExcessCardsOnTable();
    Card playCard(Player player, int index);
    void setCurrentPlayer(int noOfTurns);
    List<Player> getPlayers();
    int getCurrentPlayer();
    Suit getLeadSuit();
    Value getLeadValue();
    boolean isGameOver();
    boolean isCardValid(Card card, Card lead);
    Map<String, Object> getSpecialRules();
    void setPlayers(List<String> names);
    boolean hasCardsLeft();

    int getNextPlayerDraws();

    void setNextPlayerDraws(int nextPlayerDraws);
    void applySpecialRules(Card played);

    void setSuitChoice(Suit choice);

    void setDirectionClockwise(boolean directionClockwise);

    boolean isDirectionClockwise();

    void setRememberedToSayMauMau(boolean b);

    boolean getRememberedToSayMauMau();

    Card cardToPlay(Player player, int index);
}