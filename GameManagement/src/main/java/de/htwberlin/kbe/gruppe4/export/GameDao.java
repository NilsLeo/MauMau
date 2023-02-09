package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.Game;

public interface GameDao {
    void createGame(Game game);

    Game findGameById(Long id);



    void updateGame(Game game);

    void deleteGame(Game game);
}
