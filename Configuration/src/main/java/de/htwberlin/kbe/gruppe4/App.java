package de.htwberlin.kbe.gruppe4;

import com.google.inject.Guice;
import com.google.inject.Injector;
import export.CLIController;
import de.htwberlin.kbe.gruppe4.export.CardDao;
import de.htwberlin.kbe.gruppe4.export.CardService;
import de.htwberlin.kbe.gruppe4.impl.CardDaoImpl;
import de.htwberlin.kbe.gruppe4.impl.CardServiceImpl;
import jakarta.persistence.*;
public class App {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MauMauModule());
        EntityManagerFactory entityManagerFactory = injector.getInstance(EntityManagerFactory.class);
        EntityManager entityManager = injector.getInstance(EntityManager.class);
        EntityTransaction transaction = injector.getInstance(EntityTransaction.class);

        CardDao cardDao = new CardDaoImpl(entityManager);
        CardService cardService = new CardServiceImpl(cardDao, transaction);
        CLIController controller = injector.getInstance(CLIController.class);

        try {
            cardService.createCard();
//            transaction.begin();
//            entity.Card card = new entity.Card(entity.Suit.CLUBS, entity.Value.EIGHT);
//            entityManager.persist(card);
//            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
            controller.startGame();

        }
    }
}