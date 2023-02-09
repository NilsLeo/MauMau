package de.htwberlin.kbe.gruppe4.entity;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "player_cards", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "card_id"))
    private List<Card> cards;

    public Player() {}

    public Player(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return cards;
    }

    public void setHand(List<Card> cards) {
        this.cards = cards;
    }
}