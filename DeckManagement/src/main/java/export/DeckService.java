package export;

import entity.Card;

import java.util.ArrayList;

public interface DeckService {
    void shuffle(Deck deck);

    ArrayList<Card> dealHand(Deck deck);

    Card deal(Deck deck);
}
