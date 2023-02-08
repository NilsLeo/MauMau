package de.htwberlin.kbe.gruppe4.impl;


import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Rules;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.RulesService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RulesServiceImpl implements RulesService {
    private static final Logger logger =  Logger.getLogger(RulesServiceImpl.class);
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidBasedOnLead(Card card, Suit leadSuit, Value leadValue) {
        boolean valid = card.getSuit() == leadSuit || card.getValue() == leadValue;
        if(valid){
            logger.info("Card is Valid");

        }
        else{
            logger.info("Card is invalid");
        }
        return valid;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules) {
        boolean valid = false;
        if (rules.isChooseSuitOnJackEnabled()) {
            if (rules.getSuit() != null) {
                logger.debug("Suit specified in Rules " + rules.getSuit());
                logger.debug("Cards Suit " + card.getSuit());

                if(rules.getSuit() == card.getSuit()){
                    valid = true;
                    rules.setSuit(null);
                    logger.info("Suits concurr");
                }
                else {
                    valid = false;
                    logger.error("Suits do not concurr");
                }
            } else {
               valid =  isValidBasedOnLead(card, leadSuit, leadValue);
            }
        } else {

            valid =  isValidBasedOnLead(card, leadSuit, leadValue);
        }
        return valid;

    }



    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Rules applySpecialRules(Card played, Rules rules) {
        try {
            if (rules.isDrawTwoOnSevenEnabled() && played.getValue() == Value.SEVEN) {
                rules.setDrawTwoOnSevenToggled(true);
                logger.debug("Draw two on seven rule toggled");
            } else {
                rules.setChooseSuitOnJackToggled(false);
            }

            if (rules.isChooseSuitOnJackEnabled() && played.getValue() == Value.JACK) {
                rules.setChooseSuitOnJackToggled(true);
                logger.debug("Choose suit on jack rule toggled");
            } else {
                rules.setChooseSuitOnJackToggled(false);
            }

            if (rules.isReverseOnAceEnabled() && played.getValue() == Value.ACE) {
                rules.setDirectionClockwise(!rules.isDirectionClockwise());
                logger.debug("Reverse on ace rule toggled");
            } else {
                rules.setDirectionClockwise(false);
            }
        } catch (Exception e) {
            logger.debug("Error: " + e.getMessage());
        }
        return rules;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Rules setupRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce, Rules rules) {
        rules.setDrawTwoOnSevenEnabled(drawTwoOnSeven);
        rules.setChooseSuitOnJackEnabled(chooseSuitOnJack);
        rules.setReverseOnAceEnabled(reverseOnAce);

        return rules;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int setCurrentPlayer(Rules rules, int currentPlayer, int maxPlayer) {
        int nextPlayer = currentPlayer;
        if (rules.isDirectionClockwise()) {
            nextPlayer = getNextClockwisePlayer(currentPlayer, maxPlayer);
        } else {
            nextPlayer = getNextCounterClockwisePlayer(currentPlayer, maxPlayer);
        }
        return nextPlayer;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getNextClockwisePlayer(int currentPlayer, int maxPlayer) {
        int nextPlayer = currentPlayer + 1;
        if (nextPlayer > maxPlayer) {
            nextPlayer = 0;
        }
        return nextPlayer;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getNextCounterClockwisePlayer(int currentPlayer, int maxPlayer) {
        int nextPlayer = currentPlayer - 1;
        if (nextPlayer < 0) {
            nextPlayer = maxPlayer;
        }
        return nextPlayer;
    }



}