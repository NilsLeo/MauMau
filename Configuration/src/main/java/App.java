import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MauMauModule());
        EntityManagerFactory entityManagerFactory = injector.getInstance(EntityManagerFactory.class);
        EntityManager entityManager = injector.getInstance(EntityManager.class);
        EntityTransaction transaction = injector.getInstance(EntityTransaction.class);
        try {
            transaction.begin();

            List<Card> cards = new ArrayList<>();

            Card card = new Card();
            entityManager.persist(card);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
