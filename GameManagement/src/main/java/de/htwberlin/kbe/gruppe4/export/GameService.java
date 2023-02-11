package de.htwberlin.kbe.gruppe4.export;


import de.htwberlin.kbe.gruppe4.entity.*;

import java.util.List;
import java.util.Map;

/**
 * The GameService interface provides methods for starting, playing and checking the status of a card game.
 *
 * @author Group 4, HTW Berlin
 */
public interface GameService {

    void updateGame(Game game);

    /**
     * Sets the rules of the game.
     *
     * @param drawTwoOnSeven  a boolean indicating whether two cards should be drawn on a seven
     * @param chooseSuitOnJack  a boolean indicating whether the player can choose the suit on a jack
     * @param reverseOnAce  a boolean indicating whether the direction of play should be reversed on an ace
     */
    void setRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce);

    /**
     * Starts the game.
     */
    void startGame();

    /**
     * Returns the lead card on the table.
     *
     * @return the lead card
     */
    Card getLeadCard();
    /**
     * Gets the card to be played by a player.
     *
     * @param player the player who is playing
     * @param lead the lead Card on the table
     * @return the card to be played
     */
    String getVirtualPlayerMove(Player player, Card lead, Rules rules) throws InvalidCardException;

    /**
     * Adds a card to the table.
     *
     * @param card the card to be added to the table
     */
    void addCardToTable(Card card);

    /**
     * Draws a card for the specified player.
     *
     * @param player the player who draws the card
     * @return the drawn card
     */
    Card drawCard(Player player) throws EmptyDeckException;

    /**
     * Refills the deck with the excess cards on the table.
     */
    void refillDeckwithExcessCardsOnTable();

    /**
     * Allows the specified player to play a card.
     *
     * @param player the player who plays the card
     * @param index the index of the card in the player's hand
     * @return the played card
     */
    Card playCard(Player player, int index);

    /**
     * Sets the current player.
     *
     * @param i the number of turns taken
     */
    void setCurrentPlayer(int i);

    /**
     * Returns a list of all players.
     *
     * @return a list of players
     */
    List<Player> getPlayers();

    /**
     * Returns the number of the current player.
     *
     * @return the number of the current player
     */
    int getCurrentPlayer();

    /**
     * Returns the lead suit.
     *
     * @return the lead suit
     */
    Suit getLeadSuit();

    /**
     * Returns the lead value.
     *
     * @return the lead value
     */
    Value getLeadValue();

    /**
     * Returns a boolean indicating whether the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    boolean isGameOver();

    /**
     * Returns a boolean indicating whether the specified card is valid to play.
     *
     * @param card the card to be checked
     * @param lead the lead card
     * @return true if the card is valid, false otherwise
     */
    boolean isCardValid(Card card, Card lead) throws InvalidCardException;
    /**
     * Gets the special rules for the game.
     *
     * @return a map of the special rules and their values as objects
     */
    Map<String, Object> getSpecialRules();
    /**
     * Gets the special rules for the game.
     *
     * @return the created Game
     */
    Game createGame();

    /**
     * Sets the players in the game.
     *
     * @param names a list of the names of the players
     */
    void setPlayers(int noOfPlayers, List<String> names) ;

    /**
     * Checks if the deck still has cards left.
     *
     * @return true if there are still cards in the deck, false otherwise
     */
    boolean hasCardsLeft();

    /**
     * Gets the number of cards the next player has to draw.
     *
     * @return the number of cards the next player has to draw
     */
    int getNextPlayerDraws();

    /**
     * Sets the number of cards the next player has to draw.
     *
     * @param nextPlayerDraws the number of cards the next player has to draw
     */
    void setNextPlayerDraws(int nextPlayerDraws);

    /**
     * Applies the special rules to the game based on the card played.
     *
     * @param played the card that was played
     */
    void applySpecialRules(Card played);

    /**
     * Sets the choice of suit in the game.
     *
     * @param choice the chosen suit
     */
    void setSuitChoice(Suit choice);

    /**
     * Sets the direction of play to be clockwise or counterclockwise.
     *
     * @param directionClockwise true for clockwise, false for counterclockwise
     */
    void setDirectionClockwise(boolean directionClockwise);

    /**
     * Sets whether or not the current player remembered to say "Mau Mau" before playing their card.
     *
     * @param b true if the player remembered to say "Mau Mau", false otherwise
     */
    void setRememberedToSayMauMau(boolean b);

    /**
     * Gets whether or not the current player remembered to say "Mau Mau" before playing their card.
     *
     * @return true if the player remembered to say "Mau Mau", false otherwise
     */
    boolean getRememberedToSayMauMau();

    /**
     * Gets the card to be played by a player.
     *
     * @param player the player who is playing
     * @param index the index of the card in the player's hand
     * @return the card to be played
     */
    Card cardToPlay(Player player, int index);
    /**
     * Gets the Rules of the game
     * @return the Rules
     *
     */
    Rules getRules();
    /**
     * Gets the Rules of the game
     * @return the Suit that the virtual player chooses
     */
    Suit getVirtualPlayerSuitChoice(Player player);
    /**
     * Checks the Validity of the MauMau Call
     * @return the Validity
     */
    boolean getMauMauCallValidity(Player player) throws InvalidMauMauCallException;
}