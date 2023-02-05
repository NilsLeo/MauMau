package impl;

import inter.Card;
import inter.CardService;

import java.util.ArrayList;
import java.util.List;

public class CardServiceImpl implements CardService {
    public List<Card> createDeck() {
        return new ArrayList<>();
    }

/*    private CardDAO cardDao;
    @Override
    public List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                Card card = new Card(suit, rank);
                cardDao.create(card);
                deck.add(card);
            }
        }
        return deck;
    }*/
}