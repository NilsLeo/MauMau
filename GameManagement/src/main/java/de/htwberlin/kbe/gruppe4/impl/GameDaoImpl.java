package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Game;
import de.htwberlin.kbe.gruppe4.export.DataAccessException;
import de.htwberlin.kbe.gruppe4.export.GameDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.apache.log4j.Logger;


public class GameDaoImpl implements GameDao {

    private EntityManager entityManager;
    private static final Logger logger =  Logger.getLogger(GameDao.class);
    @Inject
    public GameDaoImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game findGameById(int id) {
        try {
            Game game = entityManager.find(Game.class, id);
            return game;
        } catch (PersistenceException exp) {
            logger.error(exp);
            throw new DataAccessException(exp.getMessage());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void createGame(Game game) {
        try {
            entityManager.persist(game);
        } catch (PersistenceException exp) {
            logger.error(exp);
            throw new DataAccessException(exp.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame(Game game) {
        try {
            entityManager.merge(game);
        } catch (PersistenceException exp) {
            logger.error(exp);
            throw new DataAccessException(exp.getMessage());
        }
    }
}
