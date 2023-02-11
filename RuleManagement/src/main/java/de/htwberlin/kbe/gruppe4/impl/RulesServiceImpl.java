package de.htwberlin.kbe.gruppe4.impl;


import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Rules;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.InvalidCardException;
import de.htwberlin.kbe.gruppe4.export.InvalidMauMauCallException;
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
    public boolean isValidBasedOnLead(Card card, Suit leadSuit, Value leadValue) throws InvalidCardException {
        boolean valid = card.getSuit() == leadSuit || card.getValue() == leadValue;
        try{
            if(valid){
                logger.info("Card is Valid");

            }
            else{
                throw new InvalidCardException("Card "  +  card.getValue() + " of "+ card.getSuit() + "Is Invalid");
            }
        }
        catch (InvalidCardException e){
            valid = false;
        }
        return valid;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules) throws InvalidCardException {
        boolean valid = false;
        try {

            if (rules.isChooseSuitOnJackEnabled()) {
                if (rules.getSuit() != null) {
                    logger.debug("Suit specified in Rules " + rules.getSuit());
                    logger.debug("Cards Suit " + card.getSuit());

                    if (rules.getSuit() == card.getSuit()) {
                        valid = true;
                        rules.setSuit(null);
                        logger.info("Suits concurr");
                    } else{
                        throw new InvalidCardException("Suits do not concurr");
                    }
                } else {
                    valid = isValidBasedOnLead(card, leadSuit, leadValue);
                }
            } else {

                valid = isValidBasedOnLead(card, leadSuit, leadValue);
            }

        } catch (InvalidCardException e) {
            valid = false;
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
            logger.info("ChooseSuitonJack activated");
            specialRules.put("chooseSuit", true);

        }
        else{
            logger.info("ChooseSuitonJack disabled");

            specialRules.put("chooseSuit", false);
        }
        if (rules.isReverseOnAceEnabled()) {
            logger.info("ReverseOnAce activated");

            specialRules.put("reverseOnAce", true);

        }
        else{
            specialRules.put("reverseOnAce", false);
            logger.info("reverseOnAce disabled");

        }
        if (rules.isDrawTwoOnSevenToggled()) {
            logger.info("drawTwoOnSeven activated");

            specialRules.put("drawTwoOnSeven", true);
        }
        else{
            specialRules.put("drawTwoOnSeven", false);
            logger.info("drawTwoOnSeven deactivated");

        }
        if(rules.isDirectionClockwise()){
            logger.info("direction: clockwise");

            specialRules.put("direction", "clockwise");
        }
        else{
            specialRules.put("direction", "counterclockwise");
            logger.info("direction: ccounterclockwise");

        }
        return specialRules;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rules applySpecialRules(Card played, Rules rules) {

            if (rules.isDrawTwoOnSevenEnabled() && played.getValue().equals(Value.SEVEN)) {
                rules.setDrawTwoOnSevenToggled(true);
                logger.debug("Draw two on seven rule toggled");

            } else {
                rules.setDrawTwoOnSevenToggled(false);
                logger.debug("Draw two on seven rule untoggled");
            }

            if (rules.isChooseSuitOnJackEnabled() && played.getValue() == Value.JACK) {
                rules.setChooseSuitOnJackToggled(true);
                logger.debug("Choose suit on jack rule toggled");
            } else {
                rules.setChooseSuitOnJackToggled(false);
                logger.debug("Choose suit on jack rule untoggled");

            }

            if (rules.isReverseOnAceEnabled() && played.getValue() == Value.ACE) {
                rules.setDirectionClockwise(!rules.isDirectionClockwise());
                logger.debug("Reverse on ace rule toggled");
            } else {
                rules.setDirectionClockwise(false);
                logger.debug("Reverse on ace rule untoggled");
            }
        return rules;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Rules setupRules(boolean drawTwoOnSeven, boolean chooseSuitOnJack, boolean reverseOnAce, Rules rules) {
        rules.setDrawTwoOnSevenEnabled(drawTwoOnSeven);
        logger.info("TwoOnSevenEnabled is set to: " + rules.isDrawTwoOnSevenEnabled());

        rules.setChooseSuitOnJackEnabled(chooseSuitOnJack);
        logger.info("ChooseSuitOnJackEnabled is set to: " + rules.isChooseSuitOnJackEnabled());


        rules.setReverseOnAceEnabled(reverseOnAce);
        logger.info("ReverseOnAceEnabled is set to: " + rules.isReverseOnAceEnabled());

        return rules;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getNextPlayer(Rules rules, int currentPlayer, int maxPlayer) {
        int nextPlayer = currentPlayer;
        if (rules.isDirectionClockwise()) {
            nextPlayer = getNextClockwisePlayer(currentPlayer, maxPlayer) + 1;
        } else {
            nextPlayer = getNextCounterClockwisePlayer(currentPlayer, maxPlayer) -1;
        }
        return nextPlayer;
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
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getMauMauCallValidity(int size) throws InvalidMauMauCallException {
        try{
            if ((size == 2)) {
                return true;
            }
            else{
                throw new InvalidMauMauCallException("More than 2 Cards. Invalid MauMauCall.");
            }
        }

        catch(InvalidMauMauCallException e){
            return false;
        }
    }


}