import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.export.DeckService;
import de.htwberlin.kbe.gruppe4.export.EmptyDeckException;
import de.htwberlin.kbe.gruppe4.export.PlayerService;
import de.htwberlin.kbe.gruppe4.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.mockito.Mockito.when;

public class PlayerServiceImplTest {
    @Mock
    private DeckService deckService;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void dealHandTest() throws EmptyDeckException {
        MockitoAnnotations.initMocks(this);
        Deck deck = new Deck();
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Suit.CLUBS, Value.ACE));
        when(deckService.dealHand(any(Deck.class))).thenReturn((ArrayList<Card>) hand);

        Player player = new Player();
        playerService.dealHand(player, deck);

        assertEquals(hand, player.getHand());
    }
    @Test
    void drawTest() {
        MockitoAnnotations.initMocks(this);
        Card card = new Card(Suit.CLUBS, Value.ACE);
        Player player = new Player();
        player.setHand(new ArrayList<>());

        playerService.draw(player, card);
        assertEquals(card, player.getHand().get(0));
    }


    @Test
    void playTest() {
        MockitoAnnotations.initMocks(this);
        Player player = new Player();
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Suit.CLUBS, Value.ACE));
        hand.add(new Card(Suit.DIAMONDS, Value.KING));
        player.setHand(hand);

        Card playedCard = playerService.play(player, 0, null, null);
        assertEquals(Suit.CLUBS, playedCard.getSuit());
        assertEquals(Value.ACE, playedCard.getValue());

        assertEquals(1, player.getHand().size());
    }
    @Test
    void cardToPlayTest() {
        MockitoAnnotations.initMocks(this);
        Player player = new Player();
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Suit.CLUBS, Value.ACE));
        hand.add(new Card(Suit.DIAMONDS, Value.KING));
        player.setHand(hand);
        Card card = playerService.cardToPlay(player, 1, null, null);
        assertEquals(Suit.DIAMONDS, card.getSuit());
        assertEquals(Value.KING, card.getValue());
    }
    @Test
    void sortHandTest() {
        MockitoAnnotations.initMocks(this);
        Player player = new Player();
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Suit.CLUBS, Value.KING));
        hand.add(new Card(Suit.DIAMONDS, Value.ACE));
        player.setHand(hand);

        List<Card> sortedHand = playerService.sortHand(player);

        assertEquals(Suit.DIAMONDS,sortedHand.get(1).getSuit());
        assertEquals(Value.ACE, sortedHand.get(1).getValue());

        assertEquals(Suit.CLUBS,sortedHand.get(0).getSuit());
        assertEquals(Value.KING, sortedHand.get(0).getValue());

    }
}