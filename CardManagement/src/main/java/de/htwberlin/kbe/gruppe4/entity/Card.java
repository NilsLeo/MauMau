package de.htwberlin.kbe.gruppe4.entity;

import jakarta.persistence.*;
@Entity
public class Card {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "suit")
    private Suit suit;
    @Enumerated(EnumType.STRING)
    @Column(name = "value")
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }


    public Card() {

    }

    public int getId() {
        return id;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}