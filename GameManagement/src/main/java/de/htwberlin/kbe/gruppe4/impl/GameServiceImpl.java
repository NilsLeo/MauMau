package de.htwberlin.kbe.gruppe4.impl;


import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.entity.Game;
import de.htwberlin.kbe.gruppe4.export.*;
import jakarta.persistence.EntityTransaction;
import org.apache.log4j.Logger;
import java.util.*;

public class GameServiceImpl implements GameService {
    private static final Logger logger =  Logger.getLogger(GameServiceImpl.class);

    private DeckService deckService;
    private RulesService rulesService;
    private PlayerService playerService;
    private VirtualPlayerService virtualPlayerService;


    /**
     * Constructor for {@link GameServiceImpl}
     *
     * @param deckService - instance of {@link DeckService}
     * @param rulesService - instance of {@link RulesService}
     * @param playerService - instance of {@link PlayerService}
     */
    private GameDao gameDao;
    private EntityTransaction entityTransaction;
    @Inject
    public GameServiceImpl(DeckService deckService, RulesService rulesService,
                           PlayerService playerService, VirtualPlayerService virtualPlayerService, EntityTransaction entityTransaction, GameDao gameDao){
        this.gameDao = gameDao;
        this.entityTransaction = entityTransaction;
        this.deckService = deckService;
        this.rulesService = rulesService;
        this.playerService = playerService;
        this.virtualPlayerService = virtualPlayerService;
        createGame();
    }
    @Override
    public Game createGame(){
        entityTransaction.begin();
        Game game = new Game();
        gameDao.createGame(game);
        entityTransaction.commit();
        return game;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public void setPlayers(int noOfPlayers, List<String> names) {

        try {
            Game game = getGame();

            for(int i =0; i<noOfPlayers;i++){
                game.getPlayers().add(new VirtualPlayer(virtualPlayerService.generateBotName(i)));


            }
            for (String name : names) {
                game.getPlayers().add(new Player(name));
            }
            updateGame(game);

        } catch (Exception e) {
            logger.error("Error adding players: " + e.getMessage());
            throw new RuntimeException("Error adding players", e);
        }
    }

    private void updateGame(Game game){
        entityTransaction.begin();
        gameDao.updateGame(game);
        entityTransaction.commit();
    }
    private Game getGame() {
        return gameDao.findGameById(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce) {
        Game game = getGame();
        game.setRules(rulesService.setupRules(drawTwoOnSeven, chooseSuitOnJack, reverseOnAce, game.getRules()));
        updateGame(game);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        try {
            Game game = getGame();
            game.setDeck(deckService.createDeck());
            Collections.shuffle(game.getDeck().getCards());
            game.getTable().add(deckService.deal(game.getDeck()));
            for (Player player : game.getPlayers()) {
                playerService.dealHand(player, game.getDeck());
                playerService.sortHand(player);
            }
            updateGame(game);
            logger.info("Game started");
        } catch (Exception e) {
            logger.error("Error starting the game: " + e.getMessage());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card getLeadCard() {
        return getGame().getTable().get(getGame().getTable().size() - 1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void refillDeckwithExcessCardsOnTable(){
        Game game = getGame();
        if(game.getTable().size()>1){
            Deck deck = game.getDeck();
            for(int i = 0; i<game.getTable().size()-2;i++){
                deck.getCards().add(game.getTable().get(i));
                game.getTable().remove(game.getTable().get(i));
            }
            game.setDeck(deck);
            Collections.shuffle(game.getDeck().getCards());
        }
        updateGame(game);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasCardsLeft()
    {
        Game game = getGame();

        return !game.getDeck().getCards().isEmpty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getNextPlayerDraws()
    {
        Game game = getGame();

        return game.getNextPlayerDraws();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextPlayerDraws(int nextPlayerDraws) {

        Game game = getGame();
        game.setNextPlayerDraws(nextPlayerDraws);
        updateGame(game);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getSpecialRules(){
        Game game = getGame();

        return rulesService.getSpecialRules(game.getRules());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void applySpecialRules(Card played) {

        Game game = getGame();

        rulesService.applySpecialRules(played, game.getRules());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSuitChoice(Suit choice) {
        Game game = getGame();
        game.getRules().setSuit(choice);
        updateGame(game);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirectionClockwise(boolean directionClockwise) {
        Game game = getGame();

        game.getRules().setDirectionClockwise(directionClockwise);
        updateGame(game);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDirectionClockwise() {
        Game game = getGame();
        return game.getRules().isDirectionClockwise();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setRememberedToSayMauMau(boolean rememberedToSayMauMau) {
        Game game = getGame();
        game.getRules().setRememberedToSayMauMau(rememberedToSayMauMau);
        updateGame(game);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getRememberedToSayMauMau() {
        Game game = getGame();
        return game.getRules().isRememberedToSayMauMau();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card cardToPlay(Player player, int index) {
        return playerService.cardToPlay(player, index, getLeadSuit(), getLeadValue());
    }

    @Override
    public Rules getRules() {
        Game game = getGame();

        return game.getRules();
    }

    @Override
    public Suit getVirtualPlayerSuitChoice(Player player) {
        return virtualPlayerService.getVirtualPlayerChoice(player.getHand());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVirtualPlayerMove(Player player, Card lead, Rules rules) {
        return virtualPlayerService.getVirtualMove(player, lead, rules);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCardToTable(Card card) {
        // adding a card to the table
        Game game = getGame();

        game.getTable().add(card);
        logger.info(card.getValue() + " of " + card.getSuit() + " was placed on table.");
        logger.debug("Top card is the " + getLeadValue() + " of " + getLeadSuit());
        updateGame(game);
    }
    /**
     * {@inheritDoc}
     */

    @Override
    public Card drawCard(Player player) {
        Game game = getGame();

        Card card = deckService.deal(game.getDeck());
        playerService.draw(player, card);
        player.setHand(playerService.sortHand(player));

        logger.info(player.getName() + " drew a " + card.getValue() + " of " + card.getSuit());
        updateGame(game);

        return card;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card playCard(Player player, int index) {
        return playerService.play(player, index, getLeadSuit(), getLeadValue());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentPlayer(int currentPlayer) {
        Game game = getGame();

        game.setCurrentPlayer(rulesService.setCurrentPlayer(game.getRules(), game.getCurrentPlayer(), game.getPlayers().size()-1));
        updateGame(game);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getPlayers() {

        Game game = getGame();
        return game.getPlayers();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentPlayer() {
        Game game = getGame();

        return game.getCurrentPlayer();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Suit getLeadSuit() {
        Game game = getGame();

        return game.getTable().get(game.getTable().size() - 1).getSuit();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Value getLeadValue() {
        Game game = getGame();

        return game.getTable().get(game.getTable().size() - 1).getValue();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        Game game = getGame();

        for (Player player : game.getPlayers()) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCardValid(Card card, Card lead) {
        Game game = getGame();

        return rulesService.isCardValid(card, getLeadSuit(), getLeadValue(), game.getRules());
    }
}