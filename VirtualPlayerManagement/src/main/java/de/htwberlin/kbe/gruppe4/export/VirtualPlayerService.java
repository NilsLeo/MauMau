package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.*;

import java.util.List;

public interface VirtualPlayerService {
    /**
     * Retrieves the card that the player wants to play
     * @param player the player
     * @param card the Card to be played
     */
    String getIndexOfCardToPlay(Player player, Card card);

    /**
     * Retrieves the card that the player wants to play
     * @param player the player
     * @param lead the lead Card
     * @return The user input of the Virtual Player
     */
    String getVirtualMove(Player player, Card lead, Rules rules);
    /**
     * determines the best move for the virtual player
     * @param validCards the valid Cards
     * @param rules the Rules
     * @param lead the lead Card
     * @return the Card that is returned
     */
    Card calculateBestMove(List<Card> validCards, Rules rules, Card lead);
    /**
     * determines the best move for the virtual player, based on the special rules
     * @param validCards the valid Cards
     * @param rules the Rules
     * @return the Card that is returned
     */
    Card calculateBestMoveBasedOnSpecialRules(List<Card> validCards, Rules rules);

    /**
     * Retrieves the valid cards
     * @param player the player
     * @param lead the lead Card
     * @param rules the rules
     * @return the list of valid cards
     */
    List<Card> getValidCards(Player player, Card lead, Rules rules);
    /**
     * Retrieves the players chosen Suit
     * @param cards the players Cards
     */
    Suit getVirtualPlayerChoice(List<Card> cards);
    /**
     * @param noOfExistingBots the number of Existing bots
     * @return the name Of the Bot
     */
    String generateBotName(int noOfExistingBots);
    /**
     * finds the most common Suit
     * @param validCards the valid cards
     * @return the Most common Suit
     */
    Suit findMostCommonSuit(List<Card> validCards);
}
