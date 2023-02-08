package de.htwberlin.kbe.gruppe4;

import com.google.inject.Guice;
import com.google.inject.Injector;
import export.CLIController;
import de.htwberlin.kbe.gruppe4.export.CardDao;
import de.htwberlin.kbe.gruppe4.impl.CardDaoImpl;
import jakarta.persistence.*;
/**
 * The App. Starts an instance of the MauMauGame
 *  @author Group 4, HTW Berlin
 *  @version 1.0
 */
public class App {
    /**
     *
     * starts the Game
     */
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MauMauModule());
        CLIController controller = injector.getInstance(CLIController.class);
            controller.startGame();

        }
    }
