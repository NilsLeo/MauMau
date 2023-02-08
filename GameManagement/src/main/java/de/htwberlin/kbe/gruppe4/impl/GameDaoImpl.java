package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Game;
import de.htwberlin.kbe.gruppe4.export.GameDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

public class GameDaoImpl implements GameDao {

    private EntityManager entityManager;

    @Inject
    public GameDaoImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @Override
    public void create(Game game) {
        try {
            entityManager.persist(game);
        } catch (PersistenceException exp) {
            throw new DataAccessException(exp.getMessage());
        }
    }
}
