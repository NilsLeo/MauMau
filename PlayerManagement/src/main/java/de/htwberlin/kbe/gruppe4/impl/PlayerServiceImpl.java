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
    /**
     * {@inheritDoc}
     */
    @Inject
    public PlayerServiceImpl(DeckService deckService){
        this.deckService = deckService;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void dealHand(Player player, Deck deck) {
        player.setHand(deckService.dealHand(deck));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Player player, Card card) {
        player.getHand().add(card);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card play(Player player, int index, Suit leadSuit, Value leadValue) {
        Card card = player.getHand().get(index);
        player.getHand().remove(index);
        return card;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card cardToPlay(Player player, int index, Suit leadSuit, Value leadValue){
        Card card = player.getHand().get(index);
        return card;
    }
    /**
     * {@inheritDoc}
     */
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