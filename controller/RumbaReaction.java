package controller;

import gui.RumbaGUI;

public class RumbaReaction {
    RumbaGUI testGui;
    rumbaLoction loction;
    boolean[][] objectArray;
    boolean moveRight = false;
    boolean moveLeft = false;
    boolean moveUp = false;
    boolean moveDown = false;

    public RumbaReaction(RumbaGUI gui, rumbaLoction location) {
        this.testGui = gui;
        this.loction = location;
    }

    public void createObjectArray() {
        objectArray = new boolean[testGui.getCols()][testGui.getRows()];
        for (int rowCounter = 0; rowCounter < testGui.getRows(); rowCounter++) {
            for (int colCounter = 0; colCounter < testGui.getCols(); colCounter++) {
                objectArray[colCounter][rowCounter] = testGui.getLocationLabels()[colCounter][rowCounter]
                        .getStyleClass().contains("object");
            }
        }
    }

    public boolean rumbaActionRight(boolean direction) {
        return !direction == objectArray[loction.getCol() + 1][loction.getRow()];
    }

    public boolean rumbaActionDown(boolean direction) {
        return !direction == objectArray[loction.getCol()][loction.getRow() + 1];
    }

    public boolean rumbaActionLeft(boolean direction) {
        return !direction == objectArray[loction.getCol() - 1][loction.getRow()];
    }

    public boolean rumbaActionUp(boolean direction) {
        return !direction == objectArray[loction.getCol()][loction.getRow() - 1];
    }

    public void restart() {
        testGui.restart();
    }
}
