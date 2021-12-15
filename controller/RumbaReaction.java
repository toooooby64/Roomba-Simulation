package controller;

import gui.RumbaGUI;

public class RumbaReaction {
    RumbaGUI testGui;
    rumbaLocation location;
    boolean[][] objectArray;
    boolean[][] cleanArray;
    boolean moveRight = false;
    boolean moveLeft = false;
    boolean moveUp = false;
    boolean moveDown = false;

    public RumbaReaction(RumbaGUI gui, rumbaLocation location) {
        this.testGui = gui;
        this.location = location;
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

    public void createCleanArray() {
        cleanArray = new boolean[testGui.getCols()][testGui.getRows()];
        for (int rowCounter = 0; rowCounter < testGui.getRows(); rowCounter++) {
            for (int colCounter = 0; colCounter < testGui.getCols(); colCounter++) {
                cleanArray[colCounter][rowCounter] = testGui.getLocationLabels()[colCounter][rowCounter]
                        .getStyleClass().contains("clean");
            }
        }

    }

    public boolean rumbaActionRight(boolean direction) {
        return !direction == objectArray[location.getCol() + 1][location.getRow()];
    }

    public boolean rumbaActionDown(boolean direction) {
        return !direction == objectArray[location.getCol()][location.getRow() + 1];
    }

    public boolean rumbaActionLeft(boolean direction) {
        return !direction == objectArray[location.getCol() - 1][location.getRow()];
    }

    public boolean rumbaActionUp(boolean direction) {
        return !direction == objectArray[location.getCol()][location.getRow() - 1];
    }

    public boolean rumbaCleanChecker(int row, int col) {
        if (cleanArray[col + 1][row] && cleanArray[col - 1][row] && cleanArray[col][row + 1]
                && cleanArray[col][row - 1]) {
            System.out.println("the rumba right and left and down is a clean tiles");
            return true;
        } else
            return false;
    }

    public void restart() {
        testGui.restart();
    }
}
