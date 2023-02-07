package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Rules;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;

import java.util.Map;

public interface RulesService {
    boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules);
    Map<String, Object> getSpecialRules(Rules rules);

    Rules applySpecialRules(Card played, Rules rules);
    Rules setupRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce, Rules rules);

    int setCurrentPlayer(Rules rules, int currentPlayer, int maxPlayer);
}
