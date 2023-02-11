package de.htwberlin.kbe.gruppe4;

import com.google.inject.Guice;
import com.google.inject.Injector;
import export.CLIController;
import export.InvalidInputException;

/**
 * The App. Starts an instance of the MauMauGame
 *  @author Group 4, HTW Berlin
 *  @version 1.0
 */
public class App {
    /**E
     *
     * starts the Game
     */
    public static void main(String[] args) throws InvalidInputException {
        Injector injector = Guice.createInjector(new MauMauModule());
        CLIController controller = injector.getInstance(CLIController.class);
            controller.startGame();

        }
    }
