package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.entity.Deck;

import java.util.List;

/**
 * PlayerService interface provides methods for dealing hands to players, drawing cards, playing cards,
 * determining the card to play, and sorting the player's hand.
 *
 * @author HTW Berlin Group 4
 * @version 1.0
 */
public interface PlayerService {

    /**
     * Deals a hand of cards to the player from the deck.
     *
     * @param player The player to deal the hand to.
     * @param deck The deck of cards to deal from.
     */
    void dealHand(Player player, Deck deck);

    /**
     * Draws a card for the player.
     *
     * @param player The player to draw the card for.
     * @param card The card to be drawn.
     */
    void draw(Player player, Card card);

    /**
     * Plays a card from the player's hand.
     *
     * @param player The player to play the card from.
     * @param index The index of the card to be played.
     * @param leadSuit The suit that the player must follow
     * @param leadValue The value that the player must follow
     *
     * @return The card that was played.
     */
    Card play(Player player, int index, Suit leadSuit, Value leadValue);

    /**
     * Retrieves the card that the player wants to play
     *
     * @param player the player
     * @param index The index of the card to be played.
     * @param leadSuit The suit that the player must follow
     * @param leadValue The value that the player must follow
     *
     * @return The best card to play.
     */
    Card cardToPlay(Player player, int index, Suit leadSuit, Value leadValue);

    /**
     * Sorts the player's hand in ascending order
     *
     * @param player The player to sort the hand for.
     *
     * @return A sorted list of the player's cards.
     */
    List<Card> sortHand(Player player);
}
