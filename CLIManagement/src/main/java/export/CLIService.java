package export;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Player;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;

import java.util.List;

public interface CLIService {
    boolean getRule(String name);

    void displayHand(String name, List<Card> hand);

    void displayLead(Suit suit, Value value);
    void announceNoCardsLeft();
    String getPlayOrDraw();

    void announceInvalid();
    void announceWinner(String name);

    List<String> getPlayerNames();
    void displaySuits();
    Suit getSuitChoice();
    void announceChosenSuit(Suit suit);
    void displayPlayOrDraw();
    void displayDraw(Player player, Suit suit, Value value);
    void displaySuitChoice();
    void displayPlay(Suit suit, Value value);
    void announceInvalidMauMauCall();
    void announceMauMau();
    void announceForgotToSayMauMau();

    void displayNextPlayer2Draws();

    void announceReversal();
}