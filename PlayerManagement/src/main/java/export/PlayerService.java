package export;

import entity.Card;
import entity.Suit;
import entity.Value;

public interface PlayerService {
    void dealHand(Player player, Deck deck);

    void draw(Player player, Card card);

    Card play(Player player, int index, Suit leadSuit, Value leadValue);
}