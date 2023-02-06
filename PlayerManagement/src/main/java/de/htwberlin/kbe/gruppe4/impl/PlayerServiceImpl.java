package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.entity.Deck;
import de.htwberlin.kbe.gruppe4.export.DeckService;
import de.htwberlin.kbe.gruppe4.export.PlayerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private DeckService deckService;

    @Inject
    public PlayerServiceImpl(DeckService deckService){
        this.deckService = deckService;
    }

    @Override
    public void dealHand(Player player, Deck deck) {
        player.setHand(deckService.dealHand(deck));
    }

    @Override
    public void draw(Player player, Card card) {
        player.getHand().add(card);
    }

    @Override
    public Card play(Player player, int index, Suit leadSuit, Value leadValue) {
        if (index < 0 || index >= player.getHand().size()) {
            return null;
        }
        Card card = player.getHand().get(index);
        if (card.getSuit() != leadSuit && card.getValue() != leadValue) {
            return null;
        }
        player.getHand().remove(index);
        return card;
    }

    @Override
    public List<Card> sortHand(Player currentPlayer) {
        List<Card> cards = currentPlayer.getHand();
        List<Card> sortedCards = new ArrayList<>(cards);
        Collections.sort(sortedCards, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.getValue().ordinal() - c2.getValue().ordinal();
            }
        });
        return sortedCards;

    }
}