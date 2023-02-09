package de.htwberlin.kbe.gruppe4.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rules_id", referencedColumnName = "id")
    private Rules rules;

    private int currentPlayer;

    private int nextPlayerDraws;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deck_id", referencedColumnName = "id")
    private Deck deck;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "game_player",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<Player> players;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "game_card",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> table;
    public int getNextPlayerDraws() {
        return nextPlayerDraws;
    }

    public void setNextPlayerDraws(int nextPlayerDraws) {
        this.nextPlayerDraws = nextPlayerDraws;
    }




    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Game(){
        this.rules = new Rules();
        this.currentPlayer=0;
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.table = new ArrayList<>();
        this.nextPlayerDraws = 0;
    }


    public List<Card> getTable() {
        return table;
    }

    public void setTable(List<Card> table) {
        this.table = table;
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


    public Deck getDeckByDeck() {
        return deck;
    }

    public void setDeckByDeck(Deck deckByDeck) {
        this.deck = deck;
    }
}

