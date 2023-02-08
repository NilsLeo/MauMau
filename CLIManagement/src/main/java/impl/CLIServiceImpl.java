package impl;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Player;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import export.CLIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CLIServiceImpl implements CLIService {
    private final Scanner scanner;

    public CLIServiceImpl() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getRule(String name) {
        System.out.print("Enable " + name + "? (y/n): ");
        String input = scanner.nextLine();
        return input.equals("y");
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
    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayOrDraw() {
        return scanner.nextLine();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void displayPlay(Suit suit, Value value) {
        System.out.println("You played the " + value + " of " + suit + ".");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void announceInvalid() {
        System.out.println("Invalid input. Try again.");
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
    public List<String> getPlayerNames() {
        System.out.print("Enter the number of players (2-5): ");
        int numPlayers = Integer.parseInt(scanner.nextLine());
        List<String> names = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter the name of player " + i + ": ");
            names.add(scanner.nextLine());
        }
        return names;
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
    public Suit getSuitChoice() {
        System.out.print("Enter the number of the suit you want to choose: ");
        int input = Integer.parseInt(scanner.nextLine());
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
                System.out.println("Invalid input. Try again.");
                return getSuitChoice();
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
    /**
     * {@inheritDoc}
     */
    @Override
    public void announceNoCardsLeft() {
        System.out.println("There are no Cards left to draw. You must play a card");


    }

}