package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.export.DeckService;
import de.htwberlin.kbe.gruppe4.export.VirtualPlayerService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VirtualPlayerServiceImpl implements VirtualPlayerService {
    private static final Logger logger =  Logger.getLogger(PlayerServiceImpl.class);

    private DeckService deckService;
    /**
     * {@inheritDoc}
     */
    @Inject
    public VirtualPlayerServiceImpl(DeckService deckService){
        this.deckService = deckService;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void dealHand(Player player, Deck deck) {
        try {
            player.setHand(deckService.dealHand(deck));
        } catch (Exception e) {
            logger.error("Error while dealing hand to player: " + e.getMessage());
            throw e;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Player player, Card card) {
        try {
            player.getHand().add(card);
        } catch (Exception e) {
            logger.error("Error while drawing card for player: " + e.getMessage());
            throw e;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card play(Player player, int index, Suit leadSuit, Value leadValue) {
        Card card = null;
        try {
            card = player.getHand().get(index);
            player.getHand().remove(index);
        } catch (Exception e) {
            logger.error("Error while playing card for player: " + e.getMessage());
            throw e;
        }
        return card;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card cardToPlay(Player player, int index, Suit leadSuit, Value leadValue){
        Card card = null;
        try {
            card = player.getHand().get(index);
        } catch (Exception e) {
            logger.error("Error while retrieving card to play for player: " + e.getMessage());
            throw e;
        }
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

    @Override
    public String getVirtualMove(Player player, Card lead) {
        String index = getIndexOfFirstMatchingCard(player, lead);
        if(getIndexOfFirstMatchingCard(player, lead)!= null){
            return index;
        }
        else{
            return "d";
        }
    }

    public String getIndexOfFirstMatchingCard(Player player, Card lead) {
        String index = null;
        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i);
            if (card.getSuit() == lead.getSuit() || card.getValue() == lead.getValue()) {
                index = ""+i+1+"";
                break;
            }
        }
        return index;
    }

}
