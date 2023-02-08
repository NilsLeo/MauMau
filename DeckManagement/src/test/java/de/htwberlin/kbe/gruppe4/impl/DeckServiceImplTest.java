package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Deck;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckServiceImplTest {
    private DeckServiceImpl deckService;
    private Deck deck;

    @BeforeEach
    public void setup() {
        deckService = new DeckServiceImpl();
        deck = new Deck();
    }

    @Test
    public void testDealHand() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.CLUBS, Value.SEVEN));
        cards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
        cards.add(new Card(Suit.HEARTS, Value.NINE));
        cards.add(new Card(Suit.SPADES, Value.TEN));
        cards.add(new Card(Suit.CLUBS, Value.QUEEN));
        deck.setCards(cards);
        ArrayList<Card> hand = deckService.dealHand(deck);

        Assertions.assertEquals(5, hand.size());

        Assertions.assertEquals(Value.SEVEN, hand.get(0).getValue());
        Assertions.assertEquals(Value.QUEEN, hand.get(4).getValue());

    }

    @Test
    void testDeal() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.CLUBS, Value.ACE));
        cards.add(new Card(Suit.DIAMONDS, Value.QUEEN));
        deck.setCards(cards);
        Card card = deckService.deal(deck);
        assertNotNull(card);
        assertEquals(Suit.CLUBS, card.getSuit());
        assertEquals(Value.ACE, card.getValue());
    }

    @Test
    void testCreateDeck() {
        Deck deck = deckService.createDeck();
        assertEquals(32, deck.getCards().size());
    }

}