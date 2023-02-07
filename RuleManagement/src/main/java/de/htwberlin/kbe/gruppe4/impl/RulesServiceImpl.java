package de.htwberlin.kbe.gruppe4.impl;


import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Rules;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.RulesService;

import java.util.HashMap;
import java.util.Map;

public class RulesServiceImpl implements RulesService {
    private boolean isValidBasedOnLead(Card card, Suit leadSuit, Value leadValue) {
        return card.getSuit() == leadSuit || card.getValue() == leadValue;
    }
    @Override
    public boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules) {
        boolean valid = false;
        if (rules.isChooseSuitOnJackEnabled()) {
            if (rules.getSuit() != null) {

                if(rules.getSuit() == card.getSuit()){
                    valid = true;
                    rules.setSuit(null);
                }
                else {
                    valid = false;
                }
            } else {
               valid =  isValidBasedOnLead(card, leadSuit, leadValue);
            }
        } else {

            valid =  isValidBasedOnLead(card, leadSuit, leadValue);
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

    public int setCurrentPlayer(Rules rules, int currentPlayer, int maxPlayer) {
        int nextPlayer = currentPlayer;
        if (rules.isDirectionClockwise()) {
            nextPlayer = getNextClockwisePlayer(currentPlayer, maxPlayer);
        } else {
            nextPlayer = getNextCounterClockwisePlayer(currentPlayer, maxPlayer);
        }
        return nextPlayer;
    }

    private int getNextClockwisePlayer(int currentPlayer, int maxPlayer) {
        int nextPlayer = currentPlayer + 1;
        if (nextPlayer > maxPlayer) {
            nextPlayer = 0;
        }
        return nextPlayer;
    }

    private int getNextCounterClockwisePlayer(int currentPlayer, int maxPlayer) {
        int nextPlayer = currentPlayer - 1;
        if (nextPlayer < 0) {
            nextPlayer = maxPlayer;
        }
        return nextPlayer;
    }



}