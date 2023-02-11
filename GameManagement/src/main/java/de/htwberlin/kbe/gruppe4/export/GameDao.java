package de.htwberlin.kbe.gruppe4.export;

import de.htwberlin.kbe.gruppe4.entity.Game;
/**
 * The `GameDao` interface defines the methods for creating, finding, and updating a `Game` entity.
 *
 * @author Group 4
 * @version 1.0
 */
public interface GameDao {
    /**
     * Creates a new `Game` entity in the database.
     *
     * @param game The `Game` object to be created.
     */
    void createGame(Game game);

    /**
     * Finds a `Game` entity in the database based on its ID.
     *
     * @param id The ID of the `Game` entity to be found.
     * @return The `Game` entity with the specified ID, or `null` if no such entity exists.
     */
    Game findGameById(int id);

    /**
     * Updates an existing `Game` entity in the database.
     *
     * @param game The updated `Game` object to be saved.
     */
    void updateGame(Game game);
}
