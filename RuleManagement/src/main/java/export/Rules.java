package export;

import entity.Suit;
import jakarta.persistence.*;

@Entity
public class Rules {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "draw_two_on_seven")
    private boolean drawTwoOnSeven;
    @Basic
    @Column(name = "choose_suit_on_jack")
    private boolean chooseSuitOnJack;
    @Basic
    @Column(name = "reverse_on_ace")
    private boolean reverseOnAce;
    @Basic
    @Column(name = "reversed")
    private boolean reversed;
    @Basic
    @Column(name = "suit")
    private Suit suit;
//    @OneToMany(mappedBy = "rulesByRulesId")
//    private Collection<Game> gamesById;

    public boolean isDrawTwoOnSeven() {
        return drawTwoOnSeven;
    }

    public boolean isChooseSuitOnJack() {
        return chooseSuitOnJack;
    }

    public boolean isReverseOnAce() {
        return reverseOnAce;
    }

    public boolean isReversed() {
        return reversed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getDrawTwoOnSeven() {
        return drawTwoOnSeven;
    }

    public void setDrawTwoOnSeven(boolean drawTwoOnSeven) {
        this.drawTwoOnSeven = drawTwoOnSeven;
    }

    public boolean getChooseSuitOnJack() {
        return chooseSuitOnJack;
    }

    public void setChooseSuitOnJack(boolean chooseSuitOnJack) {
        this.chooseSuitOnJack = chooseSuitOnJack;
    }

    public boolean getReverseOnAce() {
        return reverseOnAce;
    }

    public void setReverseOnAce(boolean reverseOnAce) {
        this.reverseOnAce = reverseOnAce;
    }

    public boolean getReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
//    public Collection<Game> getGamesById() {
//        return gamesById;
//    }
//
//    public void setGamesById(Collection<Game> gamesById) {
//        this.gamesById = gamesById;
//    }
}
