package impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Player;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.GameService;
import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
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

    private List<String> setNames() {
        return cli.getPlayerNames();
    }

    public void startGame() {
        gameService.setPlayers(cli.getPlayerNames());
        gameService.setRules(cli.getRule("draw two on seven"), cli.getRule("choose suit on jack"), cli.getRule("reverse on ace"));
        gameService.startGame();
        runGame();
    }

    private void runGame(){
        while (!gameService.isGameOver()) {
            cli.displayLead(gameService.getLeadCard().getSuit(), gameService.getLeadCard().getValue());
            Player player = gameService.getPlayers().get(gameService.getCurrentPlayer());
            if (gameService.getNextPlayerDraws() != 0) {
                for (int i = 0; i < gameService.getNextPlayerDraws(); i++) {
                    drawCard(player);

                }
gameService.setNextPlayerDraws(0);
            }
            cli.displayHand(player.getName(), player.getHand());
            cli.displayPlayOrDraw();
            String input = cli.getPlayOrDraw();
            playTurn(player, gameService.getLeadCard(), input, 0);
        }
        cli.announceWinner(gameService.getPlayers().get(gameService.getCurrentPlayer()).getName());
    }

    private void playTurn(Player player, Card lead, String input, int turns) {
        // cli.displayLead(lead.getSuit(), lead.getValue());
        int noOfTurns = 1;
        try {
            if (input.equals("d")) {
                drawCard(player);
                gameService.setCurrentPlayer(noOfTurns);
            } else {
                playCard(input, player, lead, turns, noOfTurns);
            }
        } catch (Exception e) {
            cli.announceInvalid();
        }
        gameService.refillDeckwithExcessCardsOnTable();
    }

    private void confirmOrDenyMauMau(Player player, int index, String input){
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

    private void applySpecialRules(Card played){
        gameService.applySpecialRules(played);
        Map<String, Object> specialRules = gameService.getSpecialRules();

        if (Boolean.TRUE.equals(specialRules.get("drawTwoOnSeven"))) {
            gameService.setNextPlayerDraws(gameService.getNextPlayerDraws()+2);
            cli.displayNextPlayer2Draws();
        }

        if (specialRules.get("chosenSuit")!=null) {
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
        if(played.getValue()==Value.ACE){
            cli.announceReversal();

        }



    }



    private void playCard(String input, Player player, Card lead, int turns, int noOfTurns){
        int index = 0;
        if (input.contains("m")) {
            confirmOrDenyMauMau(player, index, input);
        } else {

            index = Integer.parseInt(input)-1;
        }
        Card played = gameService.playCard(player, index);
        if (played == null || !gameService.isCardValid(played, lead)) {
            cli.announceInvalid();
            playTurn(player, lead, getPlayOrDraw(), turns + 1);
        } else if(gameService.isCardValid(played, lead)) {
            cli.displayPlay(played.getSuit(), played.getValue());
            gameService.addCardToTable(played);
            applySpecialRules(played);
            // 2 Karten Strafziehen
            if (player.getHand().size() == 1 && !gameService.getRememberedToSayMauMau()) {
                penaltyDraw(player);
            }
            if (player.getHand().size() == 1) {
                noOfTurns = -1;
            }
            gameService.setCurrentPlayer(noOfTurns);
        }
    }

    private String getPlayOrDraw() {
        cli.displayPlayOrDraw();
        return cli.getPlayOrDraw();
    }

    private void penaltyDraw(Player player){
        cli.announceForgotToSayMauMau();
        drawCard(player);

        drawCard(player);
    }

    private void drawCard(Player player){
        if(!gameService.hasCardsLeft()){
            cli.announceNoCardsLeft();
        }
        else{

            Card drawnCard = gameService.drawCard(player);
            cli.displayDraw(player, drawnCard.getSuit(), drawnCard.getValue());
        }
    }
}