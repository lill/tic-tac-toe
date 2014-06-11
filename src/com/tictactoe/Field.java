package com.tictactoe;

public class Field
{
	private static int fieldSize;
	private char[][] field;

	public Field(int size) {
		fieldSize = size;
		field = new char[fieldSize][fieldSize];
	}

//	public static int getFieldSize() {
//		return fieldSize;
//	}

	public void showField() {
		setLineIndex(0);
		setColumnIndex(0);
		for (int i = 0; i < fieldSize; i++) {
			showLine(i);
			System.out.println();
		}
	}

	public void showLine(int lineNumber) {
		for (int i = 0; i < fieldSize; i++) {
			showCell(i, lineNumber);
		}
	}

	public void showCell(int x, int y) {
		System.out.print("[" + field[x][y] + "]");
	}

	public void eraseField() {
		for (int i = 1; i < fieldSize; i++) {
			eraseLine(i);
		}
	}

	public void eraseLine(int lineNumber) {
		for (int i = 1; i < fieldSize; i++) {
			field[i][lineNumber] = ' ';
		}
	}

	public void eraseCellByPlayer(int lineNumber, int columnNumber) {
		field[columnNumber][lineNumber] = Player.getCurrentPlayer();
	}

	public void setLineIndex(int lineNumber) {
		for (int i = 1; i < fieldSize; i++) {
			field[i][lineNumber] = Character.forDigit(i, 10);
		}
	}

	public void setColumnIndex(int columnNumber) {
		for (int i = 1; i < fieldSize; i++) {
			field[columnNumber][i] = Character.forDigit(i, 10);
		}
	}

	public boolean checkWin() {
		for (int i = 1; i < fieldSize; i++) {
			if (checkLineWin(i) || checkColumnWin(i) || checkFirstDiagonalWin() || checkSecondDiagonalWin()) {
				return true;
			}
		}
		return false;
	}

	private boolean checkLineWin(int lineNumber) {
		int firstCellIndex = 1;
		int trueCount = 1;
		char firstCellLine = field[firstCellIndex][lineNumber];
		if (firstCellLine == 'X' || firstCellLine == 'O') {
			trueCount++;
			for (int i = 2; i < fieldSize; i++) {
				if (firstCellLine == field[i][lineNumber]) {
					trueCount++;
				} else {
					trueCount = 0;
				}
			}
			return trueCount == fieldSize;
		} else
			return false;
	}

	public boolean checkColumnWin(int columnNumber) {
		int firstCellIndex = 1;
		int trueCount = 1;
		char firstCellLine = field[columnNumber][firstCellIndex];
		if (firstCellLine == 'X' || firstCellLine == 'O') {
			trueCount++;
			for (int i = 2; i < fieldSize; i++) {
				if (firstCellLine == field[columnNumber][i]) {
					trueCount++;
				} else {
					trueCount = 0;
				}
			}
			return trueCount == fieldSize;
		} else
			return false;
	}

	public boolean checkFirstDiagonalWin() {
		int firstCellIndex = 1;
		int trueCount = 1;
		char firstCellLine = field[firstCellIndex][firstCellIndex];
		if (firstCellLine == 'X' || firstCellLine == 'O') {
			trueCount++;
			for (int i = 2; i < fieldSize; i++) {
				if (firstCellLine == field[i][i]) {
					trueCount++;
				} else {
					trueCount = 0;
				}
			}
			return trueCount == fieldSize;
		} else
			return false;
	}

	public boolean checkSecondDiagonalWin() {
		int firstCellIndex = 1;
		int trueCount = 1;
		char firstCellLine = field[fieldSize-1][firstCellIndex];
		if (firstCellLine == 'X' || firstCellLine == 'O') {
			trueCount++;
			for (int i = 2; i < fieldSize; i++) {
				if (firstCellLine == field[fieldSize-i][i]) {
					trueCount++;
				} else {
					trueCount = 0;
				}
			}
			return trueCount == fieldSize;
		} else
			return false;
	}

}
