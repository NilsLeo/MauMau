package inter;

public interface RulesService {
    boolean isCardValid(Card card, Suit leadSuit, Value leadValue, Rules rules);
}
