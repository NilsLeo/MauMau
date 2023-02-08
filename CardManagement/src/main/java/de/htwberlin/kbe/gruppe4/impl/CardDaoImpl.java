package de.htwberlin.kbe.gruppe4.impl;


import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.export.CardDao;
import de.htwberlin.kbe.gruppe4.export.DataAccessException;
import jakarta.persistence.*;

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
}