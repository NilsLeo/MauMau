package de.htwberlin.kbe.gruppe4.export;


import de.htwberlin.kbe.gruppe4.entity.*;

import java.util.List;

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
    boolean isDrawTwoOnSeven();
    boolean isChooseSuitOnJack();
    boolean isReverseOnAce();
    void setPlayers(List<String> names);
    void setReversed(boolean reversed);
    boolean isReversed();
    void setSuitChoice(Suit suit);
    boolean hasCardsLeft();
}