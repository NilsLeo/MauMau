package export;

import entity.Card;
import entity.Rules;
import entity.Suit;
import entity.Value;

public interface RulesService {
    boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules);
}
