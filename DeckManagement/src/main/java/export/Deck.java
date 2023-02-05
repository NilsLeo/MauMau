package export;

import entity.Card;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Deck {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @OneToMany
    @JoinTable(name = "CardDeck",
            joinColumns = {@JoinColumn(name = "player_id")},
            inverseJoinColumns = {@JoinColumn(name = "deck_id")}
    )
    private List<Card> cards;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void addAll(List<Card> newCards) {
        // Adding all new cards to the existing cards list
        cards.addAll(newCards);
    }

}
