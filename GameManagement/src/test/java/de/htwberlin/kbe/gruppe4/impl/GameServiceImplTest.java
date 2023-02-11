package de.htwberlin.kbe.gruppe4.impl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.export.*;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameServiceImplTest {

    @Mock
    private PlayerService playerService;
    @Mock
    private DeckService deckService;
    @Mock
    private RulesService rulesService;
    @Mock
    private VirtualPlayerService virtualPlayerService;
    @Mock
    private EntityTransaction entityTransaction;
    @Mock
    private GameDao gameDao;
    private Game game;

    private GameServiceImpl gameServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        game = new Game();
        gameServiceImpl = new GameServiceImpl(deckService, rulesService, playerService, virtualPlayerService, entityTransaction, gameDao, game);

    }


    @Test
    public void testCreateGame() {
        Game gameToBeCreated = gameServiceImpl.createGame();
        verify(gameDao).createGame(gameToBeCreated);

    }
    @Test
    public void testGetNextPlayer() {
        int currentPlayer = 2;
        int maxPlayer = 3;
        Rules rules = mock(Rules.class);
        when(rulesService.getNextPlayer(rules, currentPlayer, maxPlayer)).thenReturn(3);
        int result = gameServiceImpl.getNextPlayer(rules, currentPlayer, maxPlayer);
        assertEquals(3, result);
        verify(rulesService).getNextPlayer(rules, currentPlayer, maxPlayer);
    }


    @Test
    public void testUpdateGame() {
        EntityTransaction entityTransactionMock = mock(EntityTransaction.class);
        gameServiceImpl.updateGame(game);
        verify(gameDao).updateGame(game);
    }


    @Test
    public void testStartGame() throws EmptyDeckException {
        // Arrange
        gameDao = mock(GameDao.class);
        deckService = mock(DeckService.class);
        rulesService = mock(RulesService.class);
        playerService = mock(PlayerService.class);
        virtualPlayerService = mock(VirtualPlayerService.class);
        entityTransaction = mock(EntityTransaction.class);
        GameServiceImpl gameService = new GameServiceImpl(deckService, rulesService, playerService, virtualPlayerService, entityTransaction, gameDao, game);

        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Suit.SPADES, Value.EIGHT));
        deck.setCards(cards);
        when(deckService.createDeck()).thenReturn(deck);

        Player player = new Player("player1");
        List<Player> players = new ArrayList<>();
        players.add(player);
        game.setPlayers(players);

        when(deckService.deal(deck)).thenReturn(new Card(Suit.CLUBS, Value.SEVEN));
        doNothing().when(playerService).dealHand(player, deck);

        // Act
        gameService.startGame();

        // Assert
        verify(deckService, times(1)).createDeck();
    }

    



}