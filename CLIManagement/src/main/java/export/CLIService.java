package export;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Player;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;

import java.util.List;
/**
 * Interface for a Command Line Interface (CLI) Service.
 * This interface defines the basic functionalities that a CLI service should provide.
 *
 * @author Group 4, HTW Berlin
 * @version 1.0
 */
public interface CLIService {
    /**
     * Gets the rule for the specified name.
     *
     * @param name the name of the rule
     * @return boolean indicating whether the rule is true or false
     */
    boolean getRule(String name) throws InvalidInputException;

    /**
     * Displays the hand of the player with the specified name.
     *
     * @param name the name of the player
     * @param hand the list of cards in the player's hand
     */
    void displayHand(String name, List<Card> hand);
    /**
     * Displays the hand of the player with the specified name.
     * Displays the lead card with the specified suit and value.
     *
     * @param suit the suit of the lead card
     * @param value the value of the lead card
     */

    void displayLead(Suit suit, Value value);

    String getPlay(String userInput, int handSize) throws InvalidInputException;
    /**
     * Announces that there are no more cards left.
     */
    void announceNoCardsLeft();
    /**
     * Gets the player's choice of playing or drawing a card.
     *
     * @return a string indicating the player's choice of "play" or "draw"
     */

    String getPlayOrDraw() throws InvalidInputException;
    /**
     * Announces the winner with the specified name.
     *
     * @param name the name of the winner
     */
    void announceWinner(String name);
    /**
     * Gets the list of player names.
     *
     * @return a list of strings representing the names of the players
     */
    List<String> getPlayerNames(int noOfVirtualPlayers) throws InvalidInputException;
    /**
     * Displays the available suits.
     */
    void displaySuits();
    /**
     * Gets the player's choice of suit.
     *
     * @return the suit that the player has chosen
     */
    Suit getSuitChoice() throws InvalidInputException;
    /**
     * Announces the chosen suit.
     *
     * @param suit the chosen suit
     */
    void announceChosenSuit(Suit suit);
    /**
     * Displays the choice of playing or drawing a card.
     */
    void displayPlayOrDraw();
    /**
     * Displays the draw action of the specified player with the specified suit and value.
     *
     * @param player the player who drew the card
     * @param suit the suit of the drawn card
     * @param value the value of the drawn card
     */
    void displayDraw(Player player, Suit suit, Value value);
    /**
     * Displays the available suit choices.
     */
    void displaySuitChoice();
    /**
     * Displays the play action with the specified suit and value.
     *
     * @param suit the suit of the played card
     * @param value the value of the played card
     */
    void displayPlay(Player player, Suit suit, Value value);
    /**
     * Announces an invalid Mau Mau call.
     */
    void announceInvalidMauMauCall();
    /**
     * Announces MauMau!
     */
    void announceMauMau();
    /**
     * Announces that the player forgot to say Mau Mau.
     */
    void announceForgotToSayMauMau();
    /**
     * Displays that the next player has to draw two cards.
     */

    void displayNextPlayer2Draws();
    /**
     * Announces a Direction reversal in the game.
     */

    void announceReversal();

    int getNoOfVirtualPlayers() throws InvalidInputException;
}