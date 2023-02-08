package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Rules;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;

import java.util.Map;

/**
 * The interface RulesService defines methods for checking if a card is valid, applying special rules,
 * setting up rules, determining the next player in clockwise or counter-clockwise order.
 *
 * @author Gruppe 4
 */
public interface RulesService {
    /**
     * Determines if a card is valid based on the lead suit, lead value and the game rules.
     *
     * @param card The card that is being played.
     * @param leadSuit The lead suit of the game.
     * @param leadValue The lead value of the game.
     * @param rules The rules of the game.
     *
     * @return true if the card is valid, false otherwise.
     */
    boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules);

    /**
     * Returns a map containing the special rules of the game.
     *
     * @param rules The rules of the game.
     *
     * @return A map containing the special rules of the game.
     */
    Map<String, Object> getSpecialRules(Rules rules);

    /**
     * Applies the special rules to the played card.
     *
     * @param played The card that was played.
     * @param rules The rules of the game.
     *
     * @return The updated rules of the game.
     */
    Rules applySpecialRules(Card played, Rules rules);

    /**
     * Sets up the rules of the game.
     *
     * @param drawTwoOnSeven A boolean indicating if the rule "Draw two cards on seven" is active.
     * @param chooseSuitOnJack A boolean indicating if the rule "Choose suit on Jack" is active.
     * @param reverseOnAce A boolean indicating if the rule "Reverse direction on Ace" is active.
     * @param rules The rules of the game.
     * @return The updated rules of the game.
     */
    Rules setupRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce, Rules rules);

    /**
     * Sets the current player of the game.
     *
     * @param rules The rules of the game.
     * @param currentPlayer The current player.
     * @param maxPlayer The maximum number of players.
     *
     * @return The updated current player.
     */
    int setCurrentPlayer(Rules rules, int currentPlayer, int maxPlayer);
    /**
     * Determines the next player in clockwise order.
     *
     * @param currentPlayer The current player.
     * @param maxPlayer The maximum number of players.
     *
     * @return The next player in clockwise order.
     */
    int getNextClockwisePlayer(int currentPlayer, int maxPlayer);

    /**
     * Determines the next player in counterclockwise order.
     *
     * @param currentPlayer The current player.
     * @param maxPlayer The maximum number of players.
     *
     * @return The next player in counterclockwise order.
     */
    int getNextCounterClockwisePlayer(int currentPlayer, int maxPlayer);
}
