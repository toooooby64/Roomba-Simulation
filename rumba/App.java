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
    int rows = 15;
    int cols = 20;
    double chanceOfDirtyTile = .75;
    double chanceOfObject = .05;
    RumbaGUI gui;
    rumbaLoction rumba;
    RumbaReaction action;

    @Override
    public void start(Stage primaryStage) throws Exception {
        gui = new RumbaGUI(cols, rows, chanceOfDirtyTile, chanceOfObject);
        rumba = new rumbaLoction();
        action = new RumbaReaction(gui, rumba);

        Scene sc = new Scene(gui);

        sc.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        gui.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            boolean clicked = false;
            rumbaThread test = new rumbaThread(gui, rumba, action);

            @Override
            public void handle(ActionEvent event) {
                if (clicked) {
                    clicked = false;
                    test.flag = clicked;
                } else {
                    clicked = true;
                    gui.getStartButton().setText("Stop");
                    test.flag = clicked;
                    test.start();
                }
            }
        });
        action.createObjectArray();
        primaryStage.setTitle("Rumba Simulation");
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);

    }
}
