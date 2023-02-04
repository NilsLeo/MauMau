import jakarta.persistence.*;

@Entity
public class Card {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "suit")
    private Object suit;
    @Basic
    @Column(name = "value")
    private Object value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getSuit() {
        return suit;
    }

    public void setSuit(Object suit) {
        this.suit = suit;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (id != card.id) return false;
        if (suit != null ? !suit.equals(card.suit) : card.suit != null) return false;
        if (value != null ? !value.equals(card.value) : card.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (suit != null ? suit.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
