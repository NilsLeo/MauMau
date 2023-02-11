package de.htwberlin.kbe.gruppe4.impl;

import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.export.InvalidCardException;
import de.htwberlin.kbe.gruppe4.export.RulesService;
import de.htwberlin.kbe.gruppe4.export.VirtualPlayerService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VirtualPlayerServiceImplTest {

    VirtualPlayerService virtualPlayerService;


    @Mock
    private RulesService rulesService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getIndexOfCardToPlayTest() {
        VirtualPlayerService virtualPlayerService = new VirtualPlayerServiceImpl();
        Player player = new Player();
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Suit.HEARTS, Value.EIGHT));
        hand.add(new Card(Suit.SPADES, Value.SEVEN));
        player.setHand(hand);
        Card card = new Card(Suit.HEARTS, Value.EIGHT);

        String result = virtualPlayerService.getIndexOfCardToPlay(player, card);

        assertEquals("1", result);
    }


    @Test
    void getVirtualMoveTest() throws InvalidCardException {
        virtualPlayerService = new VirtualPlayerServiceImpl();

        Player player = new Player();
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Suit.DIAMONDS, Value.ACE));
        hand.add(new Card(Suit.HEARTS, Value.SEVEN));
        hand.add(new Card(Suit.SPADES, Value.JACK));
        player.setHand(hand);

        Card lead = new Card(Suit.HEARTS, Value.SEVEN);
        Rules rules = new Rules();
        String expectedMove = "3";
        String actualMove = virtualPlayerService.getVirtualMove(player, lead, rules);

        assertEquals(expectedMove, actualMove);
    }

    @Test
    void calculateBestMoveBasedOnSpecialRulesTest() {

        VirtualPlayerService virtualPlayerService = new VirtualPlayerServiceImpl();
        Rules rules = new Rules();

        List<Card> validCards = new ArrayList<>();
        validCards.add(new Card(Suit.DIAMONDS, Value.ACE));
        validCards.add(new Card(Suit.HEARTS, Value.SEVEN));
        validCards.add(new Card(Suit.SPADES, Value.JACK));

        // Test Reverse on Ace
        rules.setReverseOnAceEnabled(true);
        Card bestCard = virtualPlayerService.calculateBestMoveBasedOnSpecialRules(validCards, rules);
        assertEquals(Value.ACE, bestCard.getValue());

        // Test Choose Suit on Jack
        rules.setReverseOnAceEnabled(false);
        rules.setChooseSuitOnJackEnabled(true);
        bestCard = virtualPlayerService.calculateBestMoveBasedOnSpecialRules(validCards, rules);
        assertEquals(Value.JACK, bestCard.getValue());

        // Test Draw two on Seven
        rules.setChooseSuitOnJackEnabled(false);
        rules.setDrawTwoOnSevenEnabled(true);
        bestCard = virtualPlayerService.calculateBestMoveBasedOnSpecialRules(validCards, rules);
        assertEquals(Value.SEVEN, bestCard.getValue());
    }


    @Test
    void getValidCardsTest() throws InvalidCardException {
        RulesService rulesService = mock(RulesService.class); // initialize the rulesService object
        Player player = new Player();
        // Add cards to the player's hand
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Suit.HEARTS, Value.EIGHT));
        hand.add(new Card(Suit.SPADES, Value.SEVEN));
        player.setHand(hand);

        Card lead = new Card(Suit.DIAMONDS, Value.ACE);
        Rules rules = new Rules();

        when(rulesService.isCardValid(hand.get(0), lead.getSuit(), lead.getValue(), rules)).thenReturn(true);
        when(rulesService.isCardValid(hand.get(1), lead.getSuit(), lead.getValue(), rules)).thenReturn(false);

        VirtualPlayerService virtualPlayerService = new VirtualPlayerServiceImpl();

        List<Card> validCards = virtualPlayerService.getValidCards(player, lead, rules);

        assertEquals(1, validCards.size());
        assertEquals(hand.get(0), validCards.get(0));
    }

    @Test
    void getVirtualPlayerChoiceTest() {
        VirtualPlayerService virtualPlayerService = new VirtualPlayerServiceImpl();

        List<Card> validCards = new ArrayList<>();
        validCards.add(new Card(Suit.CLUBS, Value.ACE));
        validCards.add(new Card(Suit.DIAMONDS, Value.QUEEN));
        validCards.add(new Card(Suit.HEARTS, Value.JACK));
        validCards.add(new Card(Suit.SPADES, Value.TEN));
        validCards.add(new Card(Suit.HEARTS, Value.EIGHT));

        Suit mostCommonSuit = virtualPlayerService.getVirtualPlayerChoice(validCards);
        assertEquals(Suit.HEARTS, mostCommonSuit);
    }


    @Test
    void generateBotNameTest() {
        VirtualPlayerServiceImpl virtualPlayerService = new VirtualPlayerServiceImpl();

        // Test for first BOT
        int noOfExistingBots = 0;
        String botName = virtualPlayerService.generateBotName(noOfExistingBots);
        assertEquals("Bob the BOT", botName);

        // Test for second BOT
        noOfExistingBots = 1;
        botName = virtualPlayerService.generateBotName(noOfExistingBots);
        assertEquals("Frank the BOT", botName);

        // Test for third BOT
        noOfExistingBots = 2;
        botName = virtualPlayerService.generateBotName(noOfExistingBots);
        assertEquals("Jim the BOT", botName);

        // Test for fourth BOT
        noOfExistingBots = 3;
        botName = virtualPlayerService.generateBotName(noOfExistingBots);
        assertEquals("Tom the BOT", botName);
    }

    @Test
    void findMostCommonSuitTest() {
        VirtualPlayerService virtualPlayerService = new VirtualPlayerServiceImpl();
        List<Card> validCards = new ArrayList<>();
        validCards.add(new Card(Suit.HEARTS, Value.SEVEN));
        validCards.add(new Card(Suit.HEARTS, Value.EIGHT));
        validCards.add(new Card(Suit.SPADES, Value.NINE));

        Suit mostCommonSuit = virtualPlayerService.findMostCommonSuit(validCards);
        assertEquals(Suit.HEARTS, mostCommonSuit);
    }

}