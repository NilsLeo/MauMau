package de.htwberlin.kbe.gruppe4.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Rules;
import de.htwberlin.kbe.gruppe4.entity.Suit;
import de.htwberlin.kbe.gruppe4.entity.Value;
import de.htwberlin.kbe.gruppe4.export.InvalidCardException;
import de.htwberlin.kbe.gruppe4.export.InvalidMauMauCallException;
import de.htwberlin.kbe.gruppe4.export.RulesService;


import java.util.HashMap;
import java.util.Map;

class RulesServiceImplTest {
    private RulesServiceImpl rulesService;
    private Rules rules;
    private Card card;

    @BeforeEach
    void setUp() {
        rulesService = new RulesServiceImpl();
        rules = new Rules();
        card = new Card(Suit.CLUBS, Value.ACE);
    }

    @Test
    void isValidBasedOnLeadTest() {
        boolean valid = false;
        try {
            valid = rulesService.isValidBasedOnLead(card, Suit.CLUBS, Value.ACE);
        } catch (InvalidCardException e) {
            throw new RuntimeException(e);
        }
        assertTrue(valid);

        try {
            valid = rulesService.isValidBasedOnLead(card, Suit.HEARTS, Value.KING);
        } catch (InvalidCardException e) {
            throw new RuntimeException(e);
        }
        assertFalse(valid);
    }

    @Test
    void isCardValidTest() {
        rules.setChooseSuitOnJackEnabled(true);
        rules.setSuit(Suit.CLUBS);
        boolean valid = false;
        try {
            valid = rulesService.isCardValid(card, Suit.CLUBS, Value.ACE, rules);
        } catch (InvalidCardException e) {
            throw new RuntimeException(e);
        }
        assertTrue(valid);

        rules.setSuit(Suit.HEARTS);
        try {
            valid = rulesService.isCardValid(card, Suit.CLUBS, Value.ACE, rules);
        } catch (InvalidCardException e) {
            throw new RuntimeException(e);
        }
        assertFalse(valid);
    }

    @Test
    void getSpecialRulesTest() {
        Map<String, Object> specialRules = rulesService.getSpecialRules(rules);
        Map<String, Object> expectedSpecialRules = new HashMap<>();
        expectedSpecialRules.put("chooseSuit", false);
        expectedSpecialRules.put("reverseOnAce", false);
        expectedSpecialRules.put("drawTwoOnSeven", false);
        expectedSpecialRules.put("direction", "clockwise");

        assertEquals(expectedSpecialRules, specialRules);
    }

    @Test
    void applySpecialRules_SevenIsPlayed_DrawTwoOnSevenIsToggled() {
        Card sevenOfClubs = new Card(Suit.CLUBS, Value.SEVEN);
        Rules rules = new Rules();
        rules.setDrawTwoOnSevenEnabled(true);
        rules = rulesService.applySpecialRules(sevenOfClubs, rules);
        assertTrue(rules.isDrawTwoOnSevenToggled());
    }
    @Test
    void getMauMauCallValidityTest() {
        RulesService rulesService = new RulesServiceImpl();

        // Test Mau Mau call with 2 cards
        int size = 2;
        try {
            boolean isValid = rulesService.getMauMauCallValidity(size);
            assertTrue(isValid);
        } catch (InvalidMauMauCallException e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }


    @Test
    void applySpecialRules_SevenIsNotPlayed_DrawTwoOnSevenIsNotToggled() {
        Card aceOfClubs = new Card(Suit.CLUBS, Value.ACE);
        Rules rules = new Rules();
        rules.setDrawTwoOnSevenEnabled(true);
        rules = rulesService.applySpecialRules(aceOfClubs, rules);
        assertFalse(rules.isDrawTwoOnSevenToggled());
    }
    @Test
    void getNextClockwisePlayerTest() {
        int currentPlayer = 2;
        int maxPlayer = 4;
        int expectedResult = 3;

        int result = rulesService.getNextClockwisePlayer(currentPlayer, maxPlayer);

        assertEquals(expectedResult, result);
    }

    @Test
    void getNextCounterClockwisePlayerTest() {
        int currentPlayer = 2;
        int maxPlayer = 4;
        int expectedResult = 1;

        int result = rulesService.getNextCounterClockwisePlayer(currentPlayer, maxPlayer);

        assertEquals(expectedResult, result);
    }
    @Test
    void getNextPlayerTest() {
        Rules rules = new Rules();
        int currentPlayer = 2;
        int maxPlayer = 5;

        rules.setDirectionClockwise(true);
        int nextClockwisePlayer = rulesService.getNextPlayer(rules, currentPlayer, maxPlayer);
        assertEquals(4, nextClockwisePlayer);

        rules.setDirectionClockwise(false);
        int nextCounterClockwisePlayer = rulesService.getNextPlayer(rules, currentPlayer, maxPlayer);
        assertEquals(0, nextCounterClockwisePlayer);
    }

    @Test
    public void testSetCurrentPlayerClockwise() {
        Rules rules = new Rules();
        rules.setDirectionClockwise(true);
        int currentPlayer = 2;
        int maxPlayer = 5;

        int expected = 3;
        int result = rulesService.setCurrentPlayer(rules, currentPlayer, maxPlayer);

        assertEquals(expected, result);
    }

    @Test
    public void testSetCurrentPlayerCounterClockwise() {
        Rules rules = new Rules();
        rules.setDirectionClockwise(false);
        int currentPlayer = 2;
        int maxPlayer = 5;

        int expected = 1;
        int result = rulesService.setCurrentPlayer(rules, currentPlayer, maxPlayer);

        assertEquals(expected, result);
    }
    @Test
    void setupRulesTest() {
        Rules rules = new Rules();
        rules = rulesService.setupRules(true, true, true, rules);
        assertEquals(true, rules.isDrawTwoOnSevenEnabled());
        assertEquals(true, rules.isChooseSuitOnJackEnabled());
        assertEquals(true, rules.isReverseOnAceEnabled());
    }

}