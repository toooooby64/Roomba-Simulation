package controller;

import gui.RumbaGUI;

public class rumbaThread extends Thread {
    public volatile boolean flag;
    private RumbaGUI gui;
    private RumbaReaction action;
    private rumbaLoction rumba;

    public rumbaThread(RumbaGUI gui, rumbaLoction rumba, RumbaReaction action) {
        this.gui = gui;
        this.rumba = rumba;
        this.action = action;
    }

    public rumbaThread() {
    }

    public void setFlag(boolean clicked) {
        flag = clicked;
    }

    public boolean getFlag() {
        return flag;
    }

    public rumbaThread reset() {
        gui.restart();
        rumba.restart();
        action.restart();
        action.createObjectArray();
        return new rumbaThread(gui, rumba, action);
    }

    @Override
    public void run() {
        while (flag) {
            boolean moveRight = false;
            boolean moveLeft = false;
            boolean moveUp = false;
            boolean moveDown = false;

            int oldRow = rumba.getRow();
            int oldCol = rumba.getCol();
            int newRow = oldRow;
            int newCol = oldCol;

            double randomDirection = Math.random();

            if (randomDirection < .25)
                moveRight = true;
            if (randomDirection > .25 && randomDirection < .50)
                moveLeft = true;
            if (randomDirection > .50 && randomDirection < .75)
                moveDown = true;
            if (randomDirection > .75 && randomDirection < 1)
                moveUp = true;

            if (newCol < gui.getCols() - 1 && moveRight && action.rumbaActionRight(moveRight)) {
                newCol = oldCol + 1;
                rumba.setCol(newCol);

            }
            if (newRow < gui.getRows() - 1 && moveDown && action.rumbaActionDown(moveDown)) {
                newRow = oldRow + 1;
                rumba.setRow(newRow);

            }
            if (newCol > 0 && moveLeft && action.rumbaActionLeft(moveLeft)) {
                newCol = oldCol - 1;
                rumba.setCol(newCol);

            }
            if (newRow > 0 && moveUp && action.rumbaActionUp(moveUp)) {
                newRow = oldRow - 1;
                rumba.setRow(newRow);

            }
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
