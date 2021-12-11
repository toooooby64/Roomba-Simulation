package rumba;

import controller.RumbaReaction;
import controller.rumbaLoction;
import controller.rumbaThread;
import gui.RumbaGUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    int rows = 20;
    int cols = 20;
    double chanceOfDirtyTile = .75;
    double chanceOfObject = .15;
    RumbaGUI gui;
    rumbaLoction rumba;
    RumbaReaction action;
    rumbaThread startThread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui = new RumbaGUI(cols, rows, chanceOfDirtyTile, chanceOfObject);
        rumba = new rumbaLoction();
        action = new RumbaReaction(gui, rumba);
        action.createObjectArray();
        startThread = new rumbaThread(gui, rumba, action);
        Scene sc = new Scene(gui);

        sc.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        gui.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            boolean clicked = false;

            @Override
            public void handle(ActionEvent event) {
                if (clicked) {
                    clicked = false;
                    gui.getStartButton().setText("Start");
                    startThread.flag = clicked;
                } else {
                    clicked = true;
                    gui.getStartButton().setText("Stop");
                    startThread.flag = clicked;
                    startThread.start();

                }
            }
        });
        gui.getRestartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startThread.flag = true;
                gui.restart();
                gui.getStartButton().setText("Start");
                rumba = new rumbaLoction();
                action.restart();
                action.createObjectArray();
                startThread = startThread.reset();
            }
        });

        primaryStage.setTitle("Roomba Simulation");
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);

    }
}
