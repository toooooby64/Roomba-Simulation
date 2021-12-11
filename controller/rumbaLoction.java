package controller;

public class rumbaLoction {
	private int col;
	private int row;

	public rumbaLoction() {
		col = 5;
		row = 5;
	}

	public void restart() {
		col = 5;
		row = 5;
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

	public void checkRumabLocation() {
		System.out.println("Colum: " + getCol() + " " + "Row: " + getRow());
	}

}
