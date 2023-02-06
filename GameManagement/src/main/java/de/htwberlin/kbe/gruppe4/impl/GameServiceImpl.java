package de.htwberlin.kbe.gruppe4.impl;


import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.entity.Game;
import de.htwberlin.kbe.gruppe4.export.DeckService;
import de.htwberlin.kbe.gruppe4.export.GameService;
import de.htwberlin.kbe.gruppe4.export.PlayerService;
import de.htwberlin.kbe.gruppe4.export.RulesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameServiceImpl implements GameService {
    private static final Logger logger = LogManager.getLogger(CardDaoImpl.class);

    private DeckService deckService;
    RulesService rulesService;
    PlayerService playerService;
    private Game game;

    @Inject
    public GameServiceImpl(DeckService deckService, RulesService rulesService,
                           PlayerService playerService) {
        this.deckService = deckService;
        this.rulesService = rulesService;
        this.playerService = playerService;
        this.game = new Game();
    }

    @Override
    public void setPlayers(List<String> names) {
        for (String name : names) {
            this.game.getPlayers().add(new Player(name));
        }
    }

    @Override
    public void setRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce) {
        game.getRules().setDrawTwoOnSeven(drawTwoOnSeven);
        game.getRules().setChooseSuitOnJack(chooseSuitOnJack);
        game.getRules().setReverseOnAce(reverseOnAce);
        logger.info("Rules for the game have been set: draw two on seven = " + drawTwoOnSeven +
                ", choose suit on jack = " + chooseSuitOnJack +
                ", reverse on ace = " + reverseOnAce);
    }

    @Override
    public void startGame() {
        deckService.shuffle(game.getDeck());
        game.getTable().add(deckService.deal(game.getDeck()));
        for (Player player : game.getPlayers()) {
            playerService.dealHand(player, game.getDeck());
        }
        logger.info("Game started");
    }

    @Override
    public Card getLeadCard() {
        return game.getTable().get(game.getTable().size() - 1);

    }

    @Override
    public boolean hasCardsLeft() {
        return !game.getDeck().getCards().isEmpty();
    }

    @Override
    public boolean hasDuplicateCards() {
        // list to store all the cards in the game
        List<Card> allCards = new ArrayList<>();
        // add all cards in the deck to the list
        allCards.addAll(game.getDeck().getCards());
        // add all cards on the table to the list
        allCards.addAll(game.getTable());
        // check all players' hands for duplicates
        for (Player player : game.getPlayers()) {
            // add all cards in the player's hand to the list
            allCards.addAll(player.getHand());
        }
        // check the combined list for duplicates
        return hasDuplicates(allCards);
    }
    @Override
    public boolean hasDuplicates(List<Card> cards) {
        // use a set to check for duplicates
        Set<Card> set = new HashSet<>();
        for (Card card : cards) {
            if (!set.add(card)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addCardToTable(Card card) {
        // adding a card to the table
        game.getTable().add(card);
        logger.info(card.getValue() + " of " + card.getSuit() + " was placed on table.");
        logger.debug("Top card is the " + getLeadValue() + " of " + getLeadSuit());
        // Check if the deck has less than 4 cards remaining
        if(game.getDeck().getCards().size() <= 3){
            // create a list of cards to remove
            List<Card> cardsToRemove = new ArrayList<>();
            //iterate over the cards in the table
            for (Card tableCard : game.getTable()) {
                //if the card is not the lead card, add it to the list of cards to remove
                if(!tableCard.equals(getLeadCard())){
                    cardsToRemove.add(tableCard);
                }
            }
            // remove the list of cards to remove from the table
            game.getTable().removeAll(cardsToRemove);
            // adds them back to deck
            game.getDeck().addAll(cardsToRemove);
            // clear the table
            game.getTable().clear();
            //shuffles the deck
            deckService.shuffle(game.getDeck());
        }
    }


    @Override
    public Card drawCard(Player player) {
        Card card = deckService.deal(game.getDeck());
        playerService.draw(player, card);

        logger.info(player + " drew a " + card.getValue() + " of " + card.getSuit());

        return card;
    }

    @Override
    public Card playCard(Player player, int index) {
        return playerService.play(player, index, getLeadSuit(), getLeadValue());
    }

    @Override
    public boolean isCardValid(Card card) {
        return rulesService.isCardValid(card, getLeadSuit(), getLeadValue(), game.getRules());
    }

    @Override
    public void setCurrentPlayer(int noOfTurns) {
        // check if game is in reverse order
        if (isReversed()) {
            // decrement current player by noOfTurns
            game.setCurrentPlayer(noOfTurns-1);
        } else {
            // increment current player by noOfTurns
            game.setCurrentPlayer(noOfTurns+1);


        }

        // check if current player is less than 0
        if (game.getCurrentPlayer() < 0) {
            // set current player to last player in the list
            game.setCurrentPlayer(game.getPlayers().size() - 1);

        } else if (game.getCurrentPlayer() == game.getPlayers().size()) {
            // set current player to first player in the list
            game.setCurrentPlayer(0);
        }
    }

    @Override
    public List<Player> getPlayers() {
        return game.getPlayers();
    }

    @Override
    public int getCurrentPlayer() {

        return game.getCurrentPlayer();
    }

    @Override
    public Suit getLeadSuit() {
        return game.getTable().get(game.getTable().size() - 1).getSuit();
    }

    @Override
    public Value getLeadValue() {
        return game.getTable().get(game.getTable().size() - 1).getValue();
    }

    @Override
    public boolean isGameOver() {
        for (Player player : game.getPlayers()) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isCardValid(Card card, Card lead) {
        return rulesService.isCardValid(card, getLeadSuit(), getLeadValue(), game.getRules());
    }

    @Override
    public boolean isDrawTwoOnSeven() {
        return game.getRules().isDrawTwoOnSeven();
    }

    @Override
    public boolean isChooseSuitOnJack() {
        return game.getRules().isChooseSuitOnJack();
    }

    @Override
    public boolean isReverseOnAce() {
        return game.getRules().isReverseOnAce();
    }

    @Override
    public boolean isReversed() {
        return game.getRules().isReversed();
    }

    @Override
    public void setReversed(boolean reversed) {
        game.getRules().setReversed(reversed);
        ;
    }

    @Override
    public Deck getDeck() {
        return game.getDeck();
    }

    @Override
    public Rules getRules() {
        return game.getRules();
    }

    @Override
    public void setDeckService(DeckService deckService) {
        this.deckService = deckService;

    }

    @Override
    public List<Card> getTable() {

        return game.getTable();
    }

    @Override
    public void setSuitChoice(Suit suit) {
        game.getRules().setSuit(suit);
    }
}