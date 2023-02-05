package de.htwberlin.kbe.gruppe4.impl;


import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Rules;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.RulesService;

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