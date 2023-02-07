package de.htwberlin.kbe.gruppe4.impl;

import com.google.inject.Inject;
import de.htwberlin.kbe.gruppe4.entity.Card;
import de.htwberlin.kbe.gruppe4.export.CardDao;
import de.htwberlin.kbe.gruppe4.export.CardService;
import jakarta.persistence.EntityTransaction;

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

}