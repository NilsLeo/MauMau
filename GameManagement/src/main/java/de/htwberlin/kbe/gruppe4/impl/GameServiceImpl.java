package de.htwberlin.kbe.gruppe4.impl;


import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.entity.Game;
import de.htwberlin.kbe.gruppe4.export.DeckService;
import de.htwberlin.kbe.gruppe4.export.GameService;
import de.htwberlin.kbe.gruppe4.export.PlayerService;
import de.htwberlin.kbe.gruppe4.export.RulesService;
import org.apache.log4j.Logger;
import java.util.*;

public class GameServiceImpl implements GameService {
    private static final Logger logger =  Logger.getLogger(CardDaoImpl.class);

    DeckService deckService;
    RulesService rulesService;
    PlayerService playerService;
    Game game;

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
        game.setRules(rulesService.setupRules(drawTwoOnSeven, chooseSuitOnJack, reverseOnAce, game.getRules()));
    }

    @Override
    public void startGame() {
        game.setDeck(deckService.createDeck());
        Collections.shuffle(game.getDeck().getCards());
        game.getTable().add(deckService.deal(game.getDeck()));
        for (Player player : game.getPlayers()) {
            playerService.dealHand(player, game.getDeck());
            playerService.sortHand(player);
        }
        logger.info("Game started");
    }

    @Override
    public Card getLeadCard() {
        return game.getTable().get(game.getTable().size() - 1);
    }
    @Override
    public void refillDeckwithExcessCardsOnTable(){
        if(game.getTable().size()>1){
            Deck deck = game.getDeck();
            for(int i = 0; i<game.getTable().size()-2;i++){
                deck.getCards().add(game.getTable().get(i));
                game.getTable().remove(game.getTable().get(i));
            }
            game.setDeck(deck);
            Collections.shuffle(game.getDeck().getCards());
        }
    }

    @Override
    public boolean hasCardsLeft() {
        return !game.getDeck().getCards().isEmpty();
    }

    @Override
    public int getNextPlayerDraws() {
        return game.getNextPlayerDraws();
    }

    @Override
    public void setNextPlayerDraws(int nextPlayerDraws) {
       game.setNextPlayerDraws(nextPlayerDraws);
    }

    @Override
public Map<String, Object> getSpecialRules(){
        return rulesService.getSpecialRules(game.getRules());
    }

    @Override
    public void applySpecialRules(Card played) {
        rulesService.applySpecialRules(played, game.getRules());
    }

    @Override
    public void setSuitChoice(Suit choice) {
        game.getRules().setSuit(choice);
    }

    @Override
    public void setDirectionClockwise(boolean directionClockwise) {
        game.getRules().setDirectionClockwise(directionClockwise);
    }

    @Override
    public boolean isDirectionClockwise() {
        return game.getRules().isDirectionClockwise();
    }

    @Override
    public void setRememberedToSayMauMau(boolean rememberedToSayMauMau) {
        game.getRules().setRememberedToSayMauMau(rememberedToSayMauMau);
    }

    @Override
    public boolean getRememberedToSayMauMau() {
        return game.getRules().isRememberedToSayMauMau();
    }


    @Override
    public void addCardToTable(Card card) {
        // adding a card to the table
        game.getTable().add(card);
        logger.info(card.getValue() + " of " + card.getSuit() + " was placed on table.");
        logger.debug("Top card is the " + getLeadValue() + " of " + getLeadSuit());
    }


    @Override
    public Card drawCard(Player player) {
        Card card = deckService.deal(game.getDeck());
        playerService.draw(player, card);
        player.setHand(playerService.sortHand(player));

        logger.info(player + " drew a " + card.getValue() + " of " + card.getSuit());

        return card;
    }

    @Override
    public Card playCard(Player player, int index) {
        return playerService.play(player, index, getLeadSuit(), getLeadValue());
    }



    @Override
    public void setCurrentPlayer(int currentPlayer) {
        game.setCurrentPlayer(rulesService.setCurrentPlayer(game.getRules(), game.getCurrentPlayer(), game.getPlayers().size()-1));

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
}