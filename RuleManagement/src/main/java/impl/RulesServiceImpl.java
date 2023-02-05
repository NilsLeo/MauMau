package impl;


import entity.Card;
import entity.Rules;
import entity.Suit;
import entity.Value;
import export.*;

public class RulesServiceImpl implements RulesService {

    @Override
    public boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules) {
        Suit suit = card.getSuit();
        Value value = card.getValue();
        boolean valid = false;
        if (rules.isChooseSuitOnJack()) {
            if (rules.getSuit() != null) {
                valid = (rules.getSuit() == suit) ? (true) : (false);
                rules.setSuit(null);
            } else {

                valid = (leadSuit == suit || leadValue == value) ? (true) : (false);
            }
        } else {

            valid = (leadSuit == suit || leadValue == value) ? (true) : (false);
        }
        return valid;

    }
}