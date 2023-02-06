package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Deck;
import de.htwberlin.kbe.gruppe4.export.DeckDao;
import de.htwberlin.kbe.gruppe4.export.DataAccessException;
import jakarta.persistence.*;

public class DeckDaoImpl implements DeckDao {

    private EntityManager entityManager;

    @Inject
    public DeckDaoImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @Override
    public void create(Deck deck) {
        try {
            entityManager.persist(deck);
        } catch (PersistenceException exp) {
            throw new DataAccessException(exp.getMessage());
        }
    }

    @Override
    public Deck update(Deck deck) {
        try {
            return entityManager.merge(deck);
        } catch (PersistenceException exp) {
            throw new DataAccessException(exp.getMessage());
        }
    }

    @Override
    public void delete(Deck deck) {
        try {
            entityManager.remove(deck);
        } catch (PersistenceException exp) {
            throw new DataAccessException(exp.getMessage());
        }
    }
}
