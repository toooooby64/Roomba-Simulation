package controller;

public class rumbaLocation {
	private int col;
	private int row;

	public rumbaLocation(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public void restart() {
		col = 1;
		row = 1;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
