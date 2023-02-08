package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.*;

import java.util.List;

public interface VirtualPlayerService {
    /**
     * Retrieves the card that the player wants to play
     * @param player the player
     * @param lead the lead Card
     * @return The user input of the Virtual Player
     */
    String getVirtualMove(Player player, Card lead, Rules rules);

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

    String generateBotName(int noOfExistingBots);
}
