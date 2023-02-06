package de.htwberlin.kbe.gruppe4;

import com.google.inject.AbstractModule;
import de.htwberlin.kbe.gruppe4.export.*;
import de.htwberlin.kbe.gruppe4.export.DeckService;
import de.htwberlin.kbe.gruppe4.export.GameService;
import de.htwberlin.kbe.gruppe4.impl.*;
import de.htwberlin.kbe.gruppe4.impl.DeckServiceImpl;
import de.htwberlin.kbe.gruppe4.impl.GameServiceImpl;
import export.CLIController;
import export.CLIService;
import impl.CLIControllerImpl;
import impl.CLIServiceImpl;
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

        bind(CardService.class).to(CardServiceImpl.class);
        bind(CLIService.class).to(CLIServiceImpl.class);
        bind(DeckService.class).to(DeckServiceImpl.class);
        bind(GameService.class).to(GameServiceImpl.class);
        bind(PlayerService.class).to(PlayerServiceImpl.class);
        bind(RulesService.class).to(RulesServiceImpl.class);

        bind(CardDao.class).to(CardDaoImpl.class);
//        bind(DeckDao.class).to(DeckDaoImpl.class);
//        bind(PlayerDao.class).to(PlayerDaoImpl.class);
//        bind(RulesDao.class).to(RulesDaoImpl.class);


        bind(CLIController.class).to(CLIControllerImpl.class);
    }
}