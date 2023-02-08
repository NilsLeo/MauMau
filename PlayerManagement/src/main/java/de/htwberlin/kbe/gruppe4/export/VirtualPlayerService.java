package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.*;

import java.util.List;

public interface VirtualPlayerService {
    void dealHand(Player player, Deck deck);

    void draw(Player player, Card card);

    Card play(Player player, int index, Suit leadSuit, Value leadValue);

    Card cardToPlay(Player player, int index, Suit leadSuit, Value leadValue);

    List<Card> sortHand(Player currentPlayer);

    String getVirtualMove(Player player, Card lead);
}
