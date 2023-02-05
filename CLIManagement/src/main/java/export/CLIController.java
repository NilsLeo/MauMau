package export;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.entity.Player;

import java.util.List;

public interface CLIController {
    public List<String> setNames();

    public void startGame();

    public void playTurn(Player player, Card lead, String input, int turns);
    public String getPlayOrDraw();

    public void drawCard(Player player);
}
