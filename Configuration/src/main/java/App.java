import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import entity.Card;
import entity.Suit;
import entity.Value;
import export.CardDao;
import export.CardService;
import impl.CardDaoImpl;
import impl.CardServiceImpl;
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
        controller.startGame();

        try {
            cardService.createCard();
//            transaction.begin();
//            Card card = new Card(Suit.CLUBS, Value.EIGHT);
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
        }
    }
}