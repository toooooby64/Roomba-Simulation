package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class RumbaGUI extends BorderPane {
    private GridPane room;
    private Label[][] locationLabels;
    private Label tiles;
    private Button restartButton;
    private Button startButton;
    private int rows;
    private int cols;
    private double chanceOfDirtyTile;
    private double chanceOfObject;

    public RumbaGUI(int cols, int rows, double chanceOfDirtyTile, double chanceOfObject) {
        this.rows = rows;
        this.cols = cols;
        this.chanceOfDirtyTile = chanceOfDirtyTile;
        this.chanceOfObject = chanceOfObject;
        setUpGridPane(cols, rows);
        tileResize();
        setUpHeader();
    }

    public void setUpGridPane(int cols, int rows) {
        room = new GridPane();
        locationLabels = new Label[cols][rows];
        for (int rowCounter = 0; rowCounter < rows; rowCounter++) {
            for (int colCounter = 0; colCounter < cols; colCounter++) {
                tiles = new Label();
                tiles.setPrefSize(50, 50);
                tiles.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                double chance = Math.random();
                tiles.getStyleClass().add("clean");
                if (chance < chanceOfDirtyTile && chance > chanceOfObject) {
                    tiles.getStyleClass().clear();
                    tiles.getStyleClass().add("dirty");
                } else if (chance < chanceOfObject) {
                    tiles.getStyleClass().clear();
                    tiles.getStyleClass().add("object");
                }
                if (rowCounter == 0) {
                    tiles.getStyleClass().clear();
                    tiles.getStyleClass().add("object");
                }
                if (colCounter == 0) {
                    tiles.getStyleClass().clear();
                    tiles.getStyleClass().add("object");
                }
                if (rowCounter == rows - 1) {
                    tiles.getStyleClass().clear();
                    tiles.getStyleClass().add("object");
                }
                if (colCounter == cols - 1) {
                    tiles.getStyleClass().clear();
                    tiles.getStyleClass().add("object");
                }
                locationLabels[colCounter][rowCounter] = tiles;
                GridPane.setMargin(tiles, new Insets(.5));
                room.add(tiles, colCounter, rowCounter);
                setCenter(room);
            }
        }
    }

    public void tileResize() {
        for (int rowCounter = 0; rowCounter < rows; rowCounter++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            room.getRowConstraints().add(row);
        }
        for (int colCounter = 0; colCounter < cols; colCounter++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setHgrow(Priority.ALWAYS);
            room.getColumnConstraints().add(col);
        }
    }

    public void setUpHeader() {
        HBox header = new HBox();
        HBox.setMargin(tiles, new Insets(5));
        startButton = new Button("Start");
        restartButton = new Button("New Room");
        header.getChildren().add(startButton);
        header.getChildren().add(restartButton);
        setTop(header);
    }

    public void moveRumba(int oldRow, int oldCol, int newRow, int newCol) {
        Label oldLabel = locationLabels[oldCol][oldRow];
        oldLabel.getStyleClass().clear();
        oldLabel.getStyleClass().add("clean");
        Label newLabel = locationLabels[newCol][newRow];
        newLabel.getStyleClass().clear();
        newLabel.getStyleClass().add("rumba");
    }

    public void restart() {
        setUpGridPane(cols, rows);
        tileResize();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Label getTiles() {
        return tiles;
    }

    public Label[][] getLocationLabels() {
        return locationLabels;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getRestartButton() {
        return restartButton;
    }
}
