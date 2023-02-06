package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Deck;

import java.util.ArrayList;

public interface DeckService {

    ArrayList<Card> dealHand(Deck deck);

    Card deal(Deck deck);

    Deck createDeck();
}
