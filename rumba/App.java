package rumba;

import controller.RumbaReaction;
import controller.rumbaLocation;
import controller.rumbaThread;
import controller.rumbaSmartThread;
import gui.RumbaGUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    int rows = 10;
    int cols = 20;
    int rumbaInitalRowLocation = 1;
    int rumbaInitalColLocation = 1;
    double chanceOfDirtyTile = .85;
    double chanceOfObject = .15;
    RumbaGUI gui;
    rumbaLocation rumba;
    RumbaReaction action;
    rumbaThread startThread;
    rumbaSmartThread startSmartThread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui = new RumbaGUI(cols, rows, chanceOfDirtyTile, chanceOfObject);
        rumba = new rumbaLocation(rumbaInitalRowLocation, rumbaInitalColLocation);
        action = new RumbaReaction(gui, rumba);
        action.createObjectArray();
        startSmartThread = new rumbaSmartThread(gui, rumba, action);
        startThread = new rumbaThread(gui, rumba, action);
        startSmartThread.setFlag(true);
        startThread.setFlag(false);
        Scene sc = new Scene(gui);

        sc.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        startuttonEventHandler();
        newRoomButtonEventHandler();
        efficiencyButtonEventHandler();

        primaryStage.setTitle("Roomba Simulation");
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public void startuttonEventHandler() {
        gui.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            boolean clicked = false;

            @Override
            public void handle(ActionEvent event) {
                if (clicked) {
                    clicked = false;
                    startSmartThread.setFlag(false);
                    startThread.setFlag(false);
                    gui.getStartButton().setText("Start");
                } else {
                    clicked = true;
                    gui.getStartButton().setText("Stop");
                    if (startSmartThread.getFlag())
                        startSmartThread.start();
                    if (startThread.getFlag())
                        startThread.start();

                }
            }
        });
    }

    public void newRoomButtonEventHandler() {
        gui.getRestartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startSmartThread.getPastColLocation().clear();
                startSmartThread.getPastRowLocation().clear();
                gui.restart();
                gui.getStartButton().setText("Start");
                rumba = new rumbaLocation(rumbaInitalRowLocation, rumbaInitalColLocation);
                action.restart();
                action.createObjectArray();
                startSmartThread = startSmartThread.reset();
                startThread = startThread.reset();
            }
        });
    }

    public void efficiencyButtonEventHandler() {
        gui.getEfficiencyButton().setOnAction(new EventHandler<ActionEvent>() {
            boolean clicked = false;

            @Override
            public void handle(ActionEvent event) {
                if (clicked) {
                    clicked = false;
                    gui.getEfficiencyButton().setText("Smart");
                    startSmartThread.setFlag(true);
                    startThread.setFlag(false);
                } else {
                    clicked = true;
                    gui.getEfficiencyButton().setText("Random");
                    startSmartThread.setFlag(false);
                    startThread.setFlag(true);
                }
            }
        });
    }

    public static void main(String[] args) {

        Application.launch(args);

    }
}
