package controller;

import java.util.Stack;

import gui.RumbaGUI;

public class rumbaSmartThread extends Thread {
    private volatile boolean flag;
    private RumbaGUI gui;
    private RumbaReaction action;
    private rumbaLocation rumba;
    private Stack<Integer> pastColLocation = new Stack<Integer>();
    private Stack<Integer> pastRowLocation = new Stack<Integer>();

    public rumbaSmartThread(RumbaGUI gui, rumbaLocation rumba, RumbaReaction action) {
        this.gui = gui;
        this.rumba = rumba;
        this.action = action;
    }

    public Stack<Integer> getPastColLocation() {
        return pastColLocation;
    }

    public Stack<Integer> getPastRowLocation() {
        return pastRowLocation;
    }

    public void setFlag(boolean clicked) {
        flag = clicked;
    }

    public boolean getFlag() {
        return flag;
    }

    public rumbaSmartThread reset() {
        gui.restart();
        rumba.restart();
        action.restart();
        action.createObjectArray();
        return new rumbaSmartThread(gui, rumba, action);
    }

    @Override
    public void run() {
        while (flag) {
            action.createCleanArray();

            int oldRow = rumba.getRow();
            int oldCol = rumba.getCol();
            int newRow = oldRow;
            int newCol = oldCol;

            if (newCol < gui.getCols() - 1 && !action.cleanArray[newCol + 1][newRow]
                    && !action.objectArray[newCol + 1][newRow]) {
                newCol = oldCol + 1;
                rumba.setCol(newCol);
                pastColLocation.push(oldCol);
                pastRowLocation.push(oldRow);
            } else if (newRow < gui.getRows() - 1 && !action.cleanArray[newCol][newRow + 1]
                    && !action.objectArray[newCol][newRow + 1]) {
                newRow = oldRow + 1;
                rumba.setRow(newRow);
                pastColLocation.push(oldCol);
                pastRowLocation.push(oldRow);
            } else if (newCol > 0 && !action.cleanArray[newCol - 1][newRow]
                    && !action.objectArray[newCol - 1][newRow]) {
                newCol = oldCol - 1;
                rumba.setCol(newCol);
                pastColLocation.push(oldCol);
                pastRowLocation.push(oldRow);
            } else if (newRow > 0 && !action.cleanArray[newCol][newRow - 1]
                    && !action.objectArray[newCol][newRow - 1]) {
                newRow = oldRow - 1;
                rumba.setRow(newRow);
                pastColLocation.push(oldCol);
                pastRowLocation.push(oldRow);
            } else {
                newRow = pastRowLocation.pop();
                newCol = pastColLocation.pop();
                rumba.setRow(newRow);
                rumba.setCol(newCol);
            }
            ;
            gui.moveRumba(oldRow, oldCol, newRow, newCol);

            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
        }
    }
}
