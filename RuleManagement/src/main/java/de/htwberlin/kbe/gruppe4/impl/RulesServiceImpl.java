package de.htwberlin.kbe.gruppe4.impl;


import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Rules;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.RulesService;

import java.util.HashMap;
import java.util.Map;

public class RulesServiceImpl implements RulesService {

    @Override
    public boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules) {

        Suit suit = card.getSuit();
        Value value = card.getValue();
        boolean valid = false;
        if (rules.isChooseSuitOnJackEnabled()) {
            if (rules.getSuit() != null) {

                if(rules.getSuit() == suit){
                    valid = true;
                    rules.setSuit(null);
                }
                else {
                    valid = false;
                }
            } else {


                valid = (leadSuit == suit || leadValue == value) ? (true) : (false);
            }
        } else {

            valid = (leadSuit == suit || leadValue == value) ? (true) : (false);
        }
        return valid;

    }

    @Override
    public Map<String, Object> getSpecialRules(Rules rules) {
        Map<String, Object> specialRules = new HashMap<>();
        if (rules.getChooseSuitOnJackToggled()) {
            specialRules.put("chooseSuit", true);

        }
        else{
            specialRules.put("chooseSuit", false);
        }
        if (rules.isReverseOnAceEnabled()) {
            specialRules.put("reverseOnAce", true);

        }
        else{
            specialRules.put("reverseOnAce", false);
        }
        if (rules.isDrawTwoOnSevenToggled()) {
            specialRules.put("drawTwoOnSeven", true);
        }
        else{
            specialRules.put("drawTwoOnSeven", false);
        }
        if(rules.isDirectionClockwise()){
            specialRules.put("direction", "clockwise");
        }
        else{
            specialRules.put("direction", "counterclockwise");
        }
        return specialRules;
    }

    @Override
    public Rules applySpecialRules(Card played, Rules rules) {
        if (rules.isDrawTwoOnSevenEnabled() && played.getValue() == Value.SEVEN) {
            rules.setDrawTwoOnSevenToggled(true);
        }
        else{
            rules.setChooseSuitOnJackToggled(false);

        }

        if (rules.isChooseSuitOnJackEnabled() && played.getValue() == Value.JACK) {
            rules.setChooseSuitOnJackToggled(true);
        }
        else{
            rules.setChooseSuitOnJackToggled(false);
        }

        if (rules.isReverseOnAceEnabled() && played.getValue() == Value.ACE) {
            rules.setDirectionClockwise(!rules.isDirectionClockwise());
        }
        else{
            rules.setDirectionClockwise(false);

        }
        return rules;
    }

    @Override
    public Rules setupRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce, Rules rules) {
        rules.setDrawTwoOnSevenEnabled(drawTwoOnSeven);
        rules.setChooseSuitOnJackEnabled(chooseSuitOnJack);
        rules.setReverseOnAceEnabled(reverseOnAce);

        return rules;
    }

    @Override
    public int setCurrentPlayer(Rules rules, int currentPlayer, int maxPlayer) {
        int nextPlayer = currentPlayer;
        // in case of Clockwise direction
        if(rules.isDirectionClockwise()){

            nextPlayer++;
            // sets next player to first if current is last player in the order
            if(nextPlayer>maxPlayer){
                nextPlayer=0;
            }
        }

        // in case of Counter Clockwise direction
        
        if(!rules.isDirectionClockwise()){

            nextPlayer--;
            // sets next player to first if current is last player in the order
            if(nextPlayer<0){
                nextPlayer=maxPlayer;
            }
        }

        return nextPlayer;
    }
}