package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.Deck;

public interface DeckDao {

    void create(Deck deck);
    Deck update(Deck deck);
    void delete(Deck deck);
}
