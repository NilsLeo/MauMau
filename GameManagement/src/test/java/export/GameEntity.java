package export;

import jakarta.persistence.*;

@Entity
@Table(name = "game", schema = "maumau", catalog = "")
public class GameEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "player_id")
    private int playerId;
    @Basic
    @Column(name = "deck_id")
    private int deckId;
    @Basic
    @Column(name = "rules")
    private int rules;
    @Basic
    @Column(name = "current_player")
    private int currentPlayer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public int getRules() {
        return rules;
    }

    public void setRules(int rules) {
        this.rules = rules;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameEntity that = (GameEntity) o;

        if (id != that.id) return false;
        if (playerId != that.playerId) return false;
        if (deckId != that.deckId) return false;
        if (rules != that.rules) return false;
        if (currentPlayer != that.currentPlayer) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + playerId;
        result = 31 * result + deckId;
        result = 31 * result + rules;
        result = 31 * result + currentPlayer;
        return result;
    }
}
