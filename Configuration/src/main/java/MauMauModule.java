import com.google.inject.AbstractModule;

import jakarta.persistence.*;

public class MauMauModule extends AbstractModule {
    @Override
    protected void configure() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        bind(EntityManagerFactory.class).toInstance(emf);
        bind(EntityManager.class).toInstance(em);
        bind(EntityTransaction.class).toInstance(et);

    }
}
