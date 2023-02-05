package de.htwberlin.kbe.gruppe4.impl;


import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.export.CardDao;
import de.htwberlin.kbe.gruppe4.entity.DataAccessException;
import jakarta.persistence.*;

import java.util.List;

public class CardDaoImpl implements CardDao {



    private EntityManager entityManager;
@Inject
    public CardDaoImpl(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }


    @Override
    public void create(Card card) {
        try {
            entityManager.persist(card);
        } catch (PersistenceException exp) {

            throw new DataAccessException(exp.getMessage());
        }
    }

    @Override
    public Card find(long id) {
        try {
            Card c = entityManager.find(Card.class, id);
            return c;
        } catch (PersistenceException exp) {

            throw new DataAccessException(exp.getMessage());
        }
    }

    @Override
    public List<Card> getAll() {
        try {
            return entityManager.createQuery("SELECT c FROM Card c", Card.class).getResultList();
        } catch (PersistenceException exp) {

            throw new DataAccessException(exp.getMessage());
        }
    }

    @Override
    public void update(Card card) {
        try {
            entityManager.merge(card);
        } catch (PersistenceException exp) {

            throw new DataAccessException(exp.getMessage());
        }
    }


    @Override
    public void delete(long id) {
        try {
            Card c = entityManager.find(Card.class, id);
            entityManager.remove(c);
        } catch (PersistenceException exp) {

            throw new DataAccessException(exp.getMessage());
        }

    }
}