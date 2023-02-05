package entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Game {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "player")
    private int player;
    @OneToOne(mappedBy = "rules")
    private Rules rules;
    @Basic
    @Column(name = "current_player")
    private int currentPlayer;

    @OneToOne(mappedBy = "deck")
    private Deck deck;

    @OneToOne(mappedBy = "players")
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    private List<Card> table;

    public List<Card> getTable() {
        return table;
    }

    public void setTable(List<Card> table) {
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return player;
    }

    public void setPlayerId(int player) {
        this.player = player;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        if (player != game.player) return false;
        if (deck != game.deck) return false;
        if (rules != game.rules) return false;
        if (currentPlayer != game.currentPlayer) return false;

        return true;
    }

    public Deck getDeckByDeck() {
        return deck;
    }

    public void setDeckByDeck(Deck deckByDeck) {
        this.deck = deck;
    }
}
