package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.export.RulesService;
import de.htwberlin.kbe.gruppe4.export.VirtualPlayerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VirtualPlayerServiceImpl implements VirtualPlayerService {
    @Inject
    RulesService rulesService;
    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndexOfCardToPlay(Player player, Card card){
        int index = 0;
        for(int i=0; i<player.getHand().size(); i++){
            if(player.getHand().get(i).equals(card)){
                index = i;
                break;
            }
        }
        return Integer.toString(index + 1);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getVirtualMove(Player player, Card lead, Rules rules) {
        List<Card> validCards = getValidCards(player, lead, rules);
        String input = null;
        if(!validCards.isEmpty()){
            Card card = calculateBestMove(validCards, rules, lead);
            input = getIndexOfCardToPlay(player, card);

            if(player.getHand().size()==2){
                input += "m";
            }
        }

        else{
            input = "d";
        }
        return input;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card calculateBestMove(List<Card> validCards, Rules rules, Card lead){
        Card bestCard = new Card();
        for (Card card : validCards) {
            if (card.getValue() == lead.getValue() || card.getSuit() == lead.getSuit()) {
                bestCard =  card;

            }
        }
//        if (rules.isDrawTwoOnSevenToggled() || rules.isChooseSuitOnJackEnabled() || rules.isReverseOnAceEnabled()){
//            bestCard = calculateBestMoveBasedOnSpecialRules(validCards, rules);
//        }
        return bestCard;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Card calculateBestMoveBasedOnSpecialRules(List<Card> validCards, Rules rules) {
        Card bestCard = new Card();
//        Reverse on Ace (least helpful of the special Rules)
        if (rules.isReverseOnAceEnabled()) {
            for (Card card : validCards) {
                if (card.getValue() == Value.ACE) {
                    bestCard =  card;

                }
            }
        }
//        Choose Suit on Jack (second most helpful of the special Rules)
        if (rules.isChooseSuitOnJackEnabled()) {
            for (Card card : validCards) {
                if (card.getValue() == Value.JACK) {
                    bestCard =  card;

                }
            }



    }
        //       Draw two on Seven (most helpful of the special Rules)
        if (rules.isDrawTwoOnSevenEnabled()) {
            for (Card card : validCards) {
                if (card.getValue() == Value.SEVEN) {
                    bestCard =  card;
                }
            }
        }

        return bestCard;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Card> getValidCards(Player player, Card lead, Rules rules) {

        List<Card> validCards = new ArrayList<>();

        for (int i = 0; i < player.getHand().size(); i++) {
            Card card = player.getHand().get(i);
            if (rulesService.isCardValid(card, lead.getSuit(), lead.getValue(), rules)) {
                validCards.add(card);
            }
        }
        return validCards;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Suit getVirtualPlayerChoice(List<Card> validCards) {
        return findMostCommonSuit(validCards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateBotName(int noOfExistingBots) {
        String[] availableBotNames = {"Bob", "Frank", "Jim", "Tom"};
        return availableBotNames[noOfExistingBots] + " the BOT";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Suit findMostCommonSuit(List<Card> validCards) {
        Map<Suit, Integer> suitCounts = new HashMap<>();
        for (Card card : validCards) {
            Suit suit = card.getSuit();
            int count = suitCounts.getOrDefault(suit, 0) + 1;
            suitCounts.put(suit, count);
        }
        Suit mostCommonSuit = null;
        int maxCount = 0;
        for (Map.Entry<Suit, Integer> entry : suitCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonSuit = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostCommonSuit;
    }
}
