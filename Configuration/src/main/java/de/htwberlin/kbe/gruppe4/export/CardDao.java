package export;

import entity.Card;

import java.util.List;

public interface CardDao {

    void create(Card card);
    Card find(long id);
    List<Card> getAll();
    void update(Card card);
    void delete(long id);
}