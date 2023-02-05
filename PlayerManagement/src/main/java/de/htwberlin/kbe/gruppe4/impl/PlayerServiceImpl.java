package impl;

import com.google.inject.Inject;
import entity.*;
import export.*;

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
}