package impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.export.GameService;
import export.CLIController;
import export.CLIService;
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
    public void startGame() {
        gameService.setPlayers(cli.getPlayerNames());
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
                cli.displayPlayOrDraw();
                String input = null;
                if (player instanceof VirtualPlayer) {
                    input = gameService.getVirtualPlayerMove(player, gameService.getLeadCard());
                }
                else{
                    input = cli.getPlayOrDraw();
                }
                playTurn(player, gameService.getLeadCard(), input);
            } catch (Exception e) {
                logger.error("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
        cli.announceWinner(gameService.getPlayers().get(gameService.getCurrentPlayer()).getName());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void playTurn(Player player, Card lead, String input) {

        try {
            int i = 1;


            if (input.equals("d")) {
                drawCard(player);
                gameService.setCurrentPlayer(i);
            } else {
                playCard(input, player, lead, i);
            }
            gameService.refillDeckwithExcessCardsOnTable();
        } catch (Exception e) {
            logger.error("An error occurred while playing the turn: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void confirmOrDenyMauMau(Player player, int index, String input){
        if ((player.getHand().size() == 2)) {
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
    public void applySpecialRules(Card played){
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
            Suit choice = cli.getSuitChoice();
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
    public void playCard(String input, Player player, Card lead, int i){
        int index = 0;
        try {
            if (input.contains("m")) {
                confirmOrDenyMauMau(player, index, input);
            } else {

                index = Integer.parseInt(input) - 1;
            }
            Card cardToBePlayed = gameService.cardToPlay(player, index);
                if (gameService.isCardValid(cardToBePlayed, lead)) {
                    placeCard(player, index, i);


                } else {
                    cli.announceInvalid();
                }

        }
        catch (NumberFormatException e){
            cli.announceInvalid();
            logger.error("An error occurred while playing the turn: " + e.getMessage());


        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void placeCard(Player player, int index, int i){
        Card played = gameService.playCard(player, index);


        cli.displayPlay(player, played.getSuit(), played.getValue());
        gameService.addCardToTable(played);
        applySpecialRules(played);
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
    public void penaltyDraw(Player player, int noOfCardsToDraw){
        for (int i = 0; i<noOfCardsToDraw; i++){
            drawCard(player);
            logger.info("Player " + player.getName() + " drew a card.");
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCard(Player player){
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