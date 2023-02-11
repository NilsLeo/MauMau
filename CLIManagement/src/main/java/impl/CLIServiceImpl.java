package impl;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Player;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import export.CLIService;
import export.InvalidInputException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CLIServiceImpl implements CLIService {
    private final Scanner scanner;
    private static final Logger logger =  Logger.getLogger(CLIServiceImpl.class);


    public CLIServiceImpl() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * {@inheritDoc}
     */
    public boolean getRule(String name) throws InvalidInputException {


            String errorMessage = "Invalid input. Please enter y for yes or n for no";
            System.out.print("Enable " + name + "? (y/n): ");
            String input = scanner.nextLine();
            boolean ruleEnabled = false;
            try {
                while (true) {
                if (input.equals("y")) {
                    ruleEnabled = true;
                } else if (input.equals("n")) {
                    ruleEnabled = false;

                } else {
                    throw new InvalidInputException(errorMessage);
                }
                return ruleEnabled;
                }


            } catch (InvalidInputException e) {
                System.out.println(errorMessage);
                return getRule(name);
            }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void displayHand(String name, List<Card> hand) {
        System.out.println(name + "'s hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i+1 + ": " + hand.get(i).getValue() + " of " + hand.get(i).getSuit());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void displayPlayOrDraw() {
        System.out.println("Enter a number to play a card, 'd' to draw a card or '#m'(number of your card followed by the letter m)  to place a number and say Mau:");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void displayDraw(Player player, Suit suit, Value value) {
        System.out.println(player.getName() + " drew the " + value + " of " + suit + ".");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayLead(Suit suit, Value value) {
        System.out.println("Top card on the table: " + value + " of " + suit);
    }

    @Override
    public String getPlay(String userInput, int handSize) throws InvalidInputException {
        String errorMessage = "Invalid input. Please give a Valid Card. 1 - " + handSize+", draw a Card or Call MauMau!";
        int input = 0;
        try {
            if(userInput.matches("[1-"+ handSize + "]+")){
                return "" + (input - 1) + "";
            }
            if (userInput.matches("^\\d+m$")) {
                int number = Integer.parseInt(userInput.substring(0, userInput.length() - 1));
                if (number >= 1 && number <= handSize) {
                    logger.info("valid number followed by m");
                } else {
                    throw new InvalidInputException("number not in range");

                }
            }
            if (userInput.contains("d")) {
                return  "d";
            }

            else{
                throw new InvalidInputException(errorMessage);
            }

        } catch (InvalidInputException e){
            System.out.println(errorMessage);
            return getPlay(scanner.nextLine(), handSize);
        }
    }

    private void announceInvalid() {
        System.out.println("Please Enter Valid Input!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayOrDraw(){
        String input = scanner.nextLine();
        return input;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void displayPlay(Player player, Suit suit, Value value) {
        System.out.println(player.getName() + " played the " + value + " of " + suit + ".");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void announceWinner(String name) {
        System.out.println(name + " won the game!");
    }
    /**
     * {@inheritDoc}
     */

    @Override
    public List<String> getPlayerNames(int noOfVirtualPlayers) throws InvalidInputException {
        int input = 0;
            int maxPlayers = 5-noOfVirtualPlayers;
            int totalPlayers = noOfVirtualPlayers;
            String possiblePlayerOptions = "";

            if (maxPlayers == 5) {
                possiblePlayerOptions = "(2-5): ";
            }
            if (maxPlayers >= 1 && 4 >= maxPlayers ) {
                possiblePlayerOptions = "(1-" + maxPlayers + "): ";
            }
            if (maxPlayers == 1) {
                possiblePlayerOptions = "(1): ";
            }
            String errorMessage="Invalid input. " + "Enter the number of real players " + possiblePlayerOptions;

            System.out.print("Enter the number of real players " + possiblePlayerOptions);
            try {
                String userInput = scanner.nextLine();
                if(userInput.matches("[1-" + maxPlayers + "]")){
                    input = Integer.parseInt(userInput);
                        List<String> names = new ArrayList<>();
                        for (int i = 1; i <= input; i++) {
                            System.out.print("Enter the name of player " + i + ": ");
                            names.add(scanner.nextLine());
                        }
                        return names;

                    }
                else {
                    throw new InvalidInputException(errorMessage);
                }
            } catch (InvalidInputException e) {
                System.out.println(errorMessage);
                return getPlayerNames(noOfVirtualPlayers);
            }


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displaySuits() {
        System.out.println("Choose a suit:");
        System.out.println("1. Clubs");
        System.out.println("2. Spades");
        System.out.println("3. Hearts");
        System.out.println("4. Diamonds");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void displaySuitChoice() {
        System.out.println("Please choose a suit: ");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Suit getSuitChoice() throws InvalidInputException {
        while (true) {

            String errorMessage="Invalid input. Please enter a number between 1 and 4.";
            System.out.print("Enter the number of the suit you want to choose: ");
        int input = 0;
            try {
                String userInput = scanner.nextLine();
                input = Integer.parseInt(scanner.nextLine());
                switch (input) {
                    case 1:
                        return Suit.CLUBS;
                    case 2:
                        return Suit.SPADES;
                    case 3:
                        return Suit.HEARTS;
                    case 4:
                        return Suit.DIAMONDS;
                    default:
                        throw new InvalidInputException(errorMessage);

                }

            } catch (InvalidInputException e) {
                System.out.println(errorMessage);
                return getSuitChoice();
            }
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void announceChosenSuit(Suit suit) {
        System.out.println("The next player's card must have the suit " + suit);

    }
    @Override
    public void announceInvalidMauMauCall() {
        System.out.println("MauMauCall was invalid.");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void announceMauMau() {
        System.out.println("MauMau!");

    }
    /**
     * {@inheritDoc}
     */
@Override
    public void announceForgotToSayMauMau(){
        System.out.println("You forgot to say MauMau when playing your Last Card. You must now draw 2 Cards as a penalty");

    }
    /**
     * {@inheritDoc}
     */

    @Override
    public void displayNextPlayer2Draws() {
        System.out.println("You played a SEVEN! The next player must draw 2 Cards!");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void announceReversal() {
        System.out.println("You played an ACE! The Direction will be reversed!");
    }

    @Override
    public int getNoOfVirtualPlayers() throws InvalidInputException {
        String errorMessage="Invalid input. Number of virtual players should be between 0 and 4.";
        System.out.print("Enter the number of virtual players (0-4): ");
        int input = 0;
        try {
            String userInput = scanner.nextLine();
            if(userInput.matches("[0-4]+")){
                input = Integer.parseInt(userInput);
            }
            else{
                throw new InvalidInputException(errorMessage);

            }

        } catch (InvalidInputException e) {
            System.out.println(errorMessage);
            return getNoOfVirtualPlayers();
        }
        return input;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void announceNoCardsLeft() {
        System.out.println("There are no Cards left to draw. You must play a card");


    }

}