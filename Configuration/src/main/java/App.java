import com.google.inject.Guice;
import com.google.inject.Injector;

import inter.Card;
import inter.Suit;
import inter.Value;
import jakarta.persistence.*;
public class App {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new MauMauModule());
        EntityManagerFactory entityManagerFactory = injector.getInstance(EntityManagerFactory.class);
        EntityManager entityManager = injector.getInstance(EntityManager.class);
        EntityTransaction transaction = injector.getInstance(EntityTransaction.class);

        try {
            transaction.begin();
            Card card = new Card(Suit.CLUBS, Value.EIGHT);
            entityManager.persist(card);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}