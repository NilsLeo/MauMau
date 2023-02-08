package export;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Player;

import java.util.List;
/**
 * The `CLIController` interface represents a controller for a Command Line Interface (CLI) based game.
 *
 * It provides methods for managing the game flow and handling interactions with players.
 *  @author Group 4, HTW Berlin
 *  @version 1.0
 */
public interface CLIController {

    /**
     * Starts the game.
     */
    public void startGame();

    /**
     * Runs the game.
     */
    void runGame();

    /**
     * Plays a turn for a given player, with a given lead card and input.
     *
     * @param player the player who is taking their turn
     * @param lead the lead card for the turn
     * @param input the input provided by the player
     */
    void playTurn(Player player, Card lead, String input);

    /**
     * Confirms or denies a request to say MauMau from a player, based on their input.
     *
     * @param player the player who made the Mau Mau request
     * @param index the index
     * @param input the input provided by the player
     */
    void confirmOrDenyMauMau(Player player, int index, String input);

    /**
     * Applies any special rules for a played card.
     *
     * @param played the card that was played
     */
    void applySpecialRules(Card played);

    /**
     * Plays a card for a player, based on their input.
     *
     * @param input the input provided by the player
     * @param player the player who is playing the card
     * @param lead the lead card for the turn
     */
    void playCard(String input, Player player, Card lead, int noOfTurns);

    /**
     * Places a card for a player, based on their input.
     *
     * @param player the player who is placing the card
     * @param index the index of the card being placed
     */
    void placeCard(Player player, int index, int noOfTurns);

    /**
     * Applies a penalty draw for a player, forcing them to draw a specified number of cards.
     *
     * @param player the player who is receiving the penalty draw
     * @param noOfCardsToDraw the number of cards that the player must draw
     */
    void penaltyDraw(Player player, int noOfCardsToDraw);

    /**
     * Draws a card for a player.
     * @param player the player who is drawing the card
     */
    void drawCard(Player player);
}
