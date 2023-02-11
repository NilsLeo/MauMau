package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Deck;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.DeckService;
import de.htwberlin.kbe.gruppe4.export.EmptyDeckException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DeckServiceImpl implements DeckService {
    private static final Logger logger =  Logger.getLogger(DeckServiceImpl.class);

    public DeckServiceImpl(){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Card> dealHand(Deck deck) throws EmptyDeckException {
        try{
            String errorMessage = "The deck is empty";
            if (deck == null) {
                throw new EmptyDeckException(errorMessage);
            }

            ArrayList<Card> hand = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Card card = deal(deck);
                if (card == null) {
                    throw new EmptyDeckException(errorMessage);
                }
                hand.add(card);
            }

            return hand;
        }
        catch(EmptyDeckException e){
            return dealHand(deck);
        }

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Card deal(Deck deck) throws EmptyDeckException {
        try{
            String errorMessage = "The deck is empty";
            if (deck == null) {
                throw new EmptyDeckException(errorMessage);
            }
        List<Card> cards = deck.getCards();
        if (cards.isEmpty()) {
            return null;
        }

        Card card = cards.get(0);
        cards.remove(0);
        deck.setCards(cards);
        return card;
    }
        catch(EmptyDeckException e){
            return deal(deck);
        }
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

        Deck deck = new Deck();
        deck.setCards(cards);
        return deck;
    }
}
