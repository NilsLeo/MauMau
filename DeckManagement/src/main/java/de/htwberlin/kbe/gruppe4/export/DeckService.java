package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Deck;

import java.util.ArrayList;
/**
 * DeckService interface defines methods to perform operations on a deck of cards.
 *
 *  @author Group 4, HTW Berlin
 * @version 1.0
 */
public interface DeckService {

    /**
     * Deals a hand of cards from the deck.
     *
     * @param deck The deck to deal cards from.
     * @return An ArrayList of Card objects representing the hand.
     */
    ArrayList<Card> dealHand(Deck deck);

    /**
     * Deals a single card from the deck.
     *
     * @param deck The deck to deal a card from.
     * @return the dealt Card
     */
    Card deal(Deck deck);

    /**
     * Creates a new deck of cards.
     *
     * @return A new Deck
     */
    Deck createDeck();

    /**
     * Sets the deck of cards.
     *
     * @param deck The deck to set.
     */
    void setDeck(Deck deck);
}
