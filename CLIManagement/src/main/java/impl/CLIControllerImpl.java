package impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Player;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.GameService;
import export.CLIController;
import export.CLIService;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CLIControllerImpl implements CLIController {
    private final CLIService cli;
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
public void runGame(){
        while (!gameService.isGameOver()) {
            cli.displayLead(gameService.getLeadCard().getSuit(), gameService.getLeadCard().getValue());
            Player player = gameService.getPlayers().get(gameService.getCurrentPlayer());
            if (gameService.getNextPlayerDraws() != 0) {
                penaltyDraw(player, gameService.getNextPlayerDraws());
                gameService.setNextPlayerDraws(0);

            }
            cli.displayHand(player.getName(), player.getHand());
            cli.displayPlayOrDraw();
            String input = cli.getPlayOrDraw();
            playTurn(player, gameService.getLeadCard(), input);
        }
        cli.announceWinner(gameService.getPlayers().get(gameService.getCurrentPlayer()).getName());
    }
    /**
     * {@inheritDoc}
     */
@Override
public void playTurn(Player player, Card lead, String input) {
        // cli.displayLead(lead.getSuit(), lead.getValue());
        int noOfTurns = 1;
            if (input.equals("d")) {
                drawCard(player);
                gameService.setCurrentPlayer(noOfTurns);
            } else {
                playCard(input, player, lead, noOfTurns);
            }
        gameService.refillDeckwithExcessCardsOnTable();
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
            index = Integer.valueOf(matcher.group())-1;
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
        }

        if (Boolean.TRUE.equals(specialRules.get("chooseSuit"))) {
            cli.displaySuitChoice();
            cli.displaySuits();
            Suit choice = cli.getSuitChoice();
            gameService.setSuitChoice(choice);
            cli.announceChosenSuit(choice);
        }
            if (specialRules.get("direction")=="clockwise") {
                gameService.setDirectionClockwise(true);
            }
            if (specialRules.get("direction")=="counterclockwise") {
                gameService.setDirectionClockwise(false);
            }

        if(played.getValue()==Value.ACE && Boolean.TRUE.equals(specialRules.get("reverseOnAce"))){
            cli.announceReversal();

        }



    }
    /**
     * {@inheritDoc}
     */

    @Override
    public void playCard(String input, Player player, Card lead, int noOfTurns){
        int index = 0;
        try {
            if (input.contains("m")) {
                confirmOrDenyMauMau(player, index, input);
            } else {

                index = Integer.parseInt(input) - 1;
            }
            Card cardToBePlayed = gameService.cardToPlay(player, index);

            if (cardToBePlayed == null) {
                System.out.println("Played=null");
            } else {
                if (gameService.isCardValid(cardToBePlayed, lead)) {
                    placeCard(player, index, noOfTurns);


                } else {
                    cli.announceInvalid();
                }
            }
        }
        catch (NumberFormatException e){
            cli.announceInvalid();

        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void placeCard(Player player, int index, int noOfTurns){
        Card played = gameService.playCard(player, index);


        cli.displayPlay(played.getSuit(), played.getValue());
        gameService.addCardToTable(played);
        applySpecialRules(played);
        // 2 Karten Strafziehen
        if (player.getHand().size() == 1 && !gameService.getRememberedToSayMauMau()) {
            cli.announceForgotToSayMauMau();
            penaltyDraw(player, 2);
        }
        if (player.getHand().size() == 1) {
            noOfTurns = -1;
        }
        gameService.setCurrentPlayer(noOfTurns);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void penaltyDraw(Player player, int noOfCardsToDraw){
        for (int i = 0; i<noOfCardsToDraw; i++){
            drawCard(player);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCard(Player player){
        if(!gameService.hasCardsLeft()){
            cli.announceNoCardsLeft();
        }
        else{

            Card drawnCard = gameService.drawCard(player);
            cli.displayDraw(player, drawnCard.getSuit(), drawnCard.getValue());
        }
    }
}