package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.export.CardDao;
import de.htwberlin.kbe.gruppe4.export.CardService;
import jakarta.persistence.EntityTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CardServiceImpl implements CardService {

    private CardDao cardDao;

    private EntityTransaction entityTransaction;

    public CardServiceImpl() {
        super();
    }
    @Inject
    public CardServiceImpl(CardDao cardDao, EntityTransaction entityTransaction) {
        this();
        this.cardDao = cardDao;
        this.entityTransaction = entityTransaction;
    }

    @Override
    public void createCard(){
        entityTransaction.begin();
        Card category = new Card();
        cardDao.create(category);
        entityTransaction.commit();
    }

}