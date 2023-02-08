package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Deck;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.DeckService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckServiceImpl implements DeckService {

    public DeckServiceImpl(){
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Card> dealHand(Deck deck) {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hand.add(deal(deck));
        }
        
        return hand;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card deal(Deck deck) {
        List<Card> cards = deck.getCards();
        if (cards.isEmpty()) {
            return null;
        }
        Card card = cards.get(0);
        cards.remove(0);
        deck.setCards(cards);
        return card;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createDeck() {
        List<Card> cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(suit, value));
            }
        }
        Deck deck  = new Deck();
        deck.setCards(cards);
        return deck;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeck(Deck deck) {

    }

}