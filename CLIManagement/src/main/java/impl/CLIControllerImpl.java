package impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.export.EmptyDeckException;
import de.htwberlin.kbe.gruppe4.export.GameService;
import de.htwberlin.kbe.gruppe4.export.InvalidMauMauCallException;
import export.CLIController;
import export.CLIService;
import export.InvalidInputException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLIControllerImpl implements CLIController {
    private final CLIService cli;
    private static final Logger logger =  Logger.getLogger(CLIControllerImpl.class);

    private final GameService gameService;
    @Inject
    public CLIControllerImpl(CLIService cli, GameService gameService) {
        this.cli = cli;
        this.gameService = gameService;
    }
    /**
     * {@inheritDoc}
     */
@Override
    public void startGame() throws InvalidInputException {
    int noOfVirtualPlayers = cli.getNoOfVirtualPlayers();
        gameService.setPlayers(noOfVirtualPlayers, cli.getPlayerNames(noOfVirtualPlayers));
        gameService.setRules(cli.getRule("draw two on seven"), cli.getRule("choose suit on jack"), cli.getRule("reverse on ace"));
        gameService.startGame();
        runGame();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void runGame() {
        while (!gameService.isGameOver()) {
            try {
                cli.displayLead(gameService.getLeadCard().getSuit(), gameService.getLeadCard().getValue());
                Player player = gameService.getPlayers().get(gameService.getCurrentPlayer());
                if (gameService.getNextPlayerDraws() != 0) {
                    penaltyDraw(player, gameService.getNextPlayerDraws());
                    gameService.setNextPlayerDraws(0);
                }
                cli.displayHand(player.getName(), player.getHand());
                if(!(player instanceof VirtualPlayer)){
                    cli.displayPlayOrDraw();
                }
                String input = null;
                if (player instanceof VirtualPlayer) {
                    input = gameService.getVirtualPlayerMove(player, gameService.getLeadCard(), gameService.getRules());
                }
                else{
                    input = cli.getPlayOrDraw();
                }
                playCard(input, player, gameService.getLeadCard(), gameService.getCurrentPlayer());
            } catch (Exception e) {
                logger.error("An error occurred: " + e.getMessage(), e);

            }
        }
        cli.announceWinner(gameService.getPlayers().get(gameService.getCurrentPlayer()).getName());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void confirmOrDenyMauMau(Player player, int index, String input) throws InvalidMauMauCallException {
        if ((gameService.getMauMauCallValidity(player))) {
            cli.announceMauMau();
            gameService.setRememberedToSayMauMau(true);
            Matcher matcher = Pattern.compile("\\d+").matcher(input);
            matcher.find();
            try {
                index = Integer.valueOf(matcher.group())-1;
            } catch (NumberFormatException e) {
                logger.error("An error occurred while playing the turn: " + e.getMessage());
            }
        } else {
            cli.announceInvalidMauMauCall();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applySpecialRules(Card played, Player player) throws InvalidInputException {
        gameService.applySpecialRules(played);
        Map<String, Object> specialRules = gameService.getSpecialRules();

        if (Boolean.TRUE.equals(specialRules.get("drawTwoOnSeven"))) {
            gameService.setNextPlayerDraws(gameService.getNextPlayerDraws()+2);
            cli.displayNextPlayer2Draws();
            logger.info("Next player must draw 2 cards");
        }

        if (Boolean.TRUE.equals(specialRules.get("chooseSuit"))) {
            cli.displaySuitChoice();
            cli.displaySuits();
            Suit choice = null;
            if (player instanceof VirtualPlayer) {
                choice = gameService.getVirtualPlayerSuitChoice(player);
            }
            else{
                choice = cli.getSuitChoice();

            }
            gameService.setSuitChoice(choice);
            cli.announceChosenSuit(choice);
            logger.info("Suit has been chosen: " + choice);
        }
        if (specialRules.get("direction")=="clockwise") {
            gameService.setDirectionClockwise(true);
            logger.info("Direction set to clockwise");
        }
        if (specialRules.get("direction")=="counterclockwise") {
            gameService.setDirectionClockwise(false);
            logger.info("Direction set to counterclockwise");
        }

        if(played.getValue()==Value.ACE && Boolean.TRUE.equals(specialRules.get("reverseOnAce"))){
            cli.announceReversal();
            logger.info("Direction has been reversed");

        }
    }





    /**
     * {@inheritDoc}
     */

    @Override
    public void playCard(String input, Player player, Card lead, int i) throws InvalidInputException, InvalidMauMauCallException, EmptyDeckException {
        int index = 0;
            if (input.contains("m")) {
                confirmOrDenyMauMau(player, index, input);
            }
        if (input.equals("d")) {
            drawCard(player);
            gameService.setCurrentPlayer(gameService.getCurrentPlayer()+1);
        }

        else{
            index = Integer.parseInt(input);
        Card cardToBePlayed = gameService.cardToPlay(player, index);
                placeCard(player, index, gameService.getCurrentPlayer()+1);
                gameService.refillDeckwithExcessCardsOnTable();


    }
    }

    /**
     * {@inheritDoc}
     */
    public void placeCard(Player player, int index, int i) throws InvalidInputException, EmptyDeckException {
        System.out.println("reached placeCard");
        Card played = gameService.playCard(player, index);
        cli.displayPlay(player, played.getSuit(), played.getValue());
        gameService.addCardToTable(played);
        applySpecialRules(played, player);
        if (player.getHand().size() == 1 && !gameService.getRememberedToSayMauMau()) {
            cli.announceForgotToSayMauMau();
            penaltyDraw(player, 2);
        }
        if (player.getHand().size() == 1) {
            i = -1;
        }
        gameService.setCurrentPlayer(i);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void penaltyDraw(Player player, int noOfCardsToDraw) throws EmptyDeckException {
        for (int i = 0; i<noOfCardsToDraw; i++){
            drawCard(player);
            logger.info("Player " + player.getName() + " drew a card.");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCard(Player player) throws EmptyDeckException {
        if(!gameService.hasCardsLeft()){
            cli.announceNoCardsLeft();
            logger.debug("There are no cards left to draw");
        }
        else{

            Card drawnCard = gameService.drawCard(player);
            cli.displayDraw(player, drawnCard.getSuit(), drawnCard.getValue());

        }
    }
}