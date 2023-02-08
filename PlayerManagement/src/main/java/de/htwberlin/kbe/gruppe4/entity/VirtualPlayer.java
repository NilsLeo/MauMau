package de.htwberlin.kbe.gruppe4.entity;


import java.util.List;

public class VirtualPlayer extends Player {

    private int id;
    private String name;

    List<Card> cards;

    public VirtualPlayer(String name) {
        super(name);
    }

    public List<Card> getHand() {

        return cards;
    }

    public void setHand(List<Card> cards) {
        this.cards = cards;
    }
}
