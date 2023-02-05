package export;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;

import java.util.List;

public interface CLIService {
    void displayRules();

    boolean getRule(String name);

    void displayHand(String name, List<Card> hand);

    void displayLead(Suit suit, Value value);
    void announceNoCardsLeft();
    void announceDuplicateCardError();

    String getPlayOrDraw();

    void announceInvalid();

    void announcePlay(String name, Card card);
    void announcePlayAgainOnAce(String name, Card card);

    void announceWinner(String name);

    List<String> getPlayerNames();
    void announceDrawTwoCards();
    void displaySuits();
    Suit getSuitChoice();
    void announceChosenSuit(Suit suit);
    void displayPlayOrDraw();
    void displayDraw(Suit suit, Value value);
    void displaySuitChoice();
    void displayPlay(Suit suit, Value value);
    void announceError();
    void announceInvalidMauMauCall();
    void announceMauMau();
    void announceForgotToSayMauMau();

}