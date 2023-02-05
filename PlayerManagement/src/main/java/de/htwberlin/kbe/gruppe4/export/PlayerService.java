package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.*;
import de.htwberlin.kbe.gruppe4.entity.Deck;

public interface PlayerService {
    void dealHand(Player player, Deck deck);

    void draw(Player player, Card card);

    Card play(Player player, int index, Suit leadSuit, Value leadValue);
}