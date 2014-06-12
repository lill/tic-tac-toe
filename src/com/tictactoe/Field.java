package com.tictactoe;

public class Field
{
	public static int fieldSize;
	private char[][] field;

	public Field(int size)
	{
		fieldSize = size;
		field = new char[fieldSize][fieldSize];
	}

	public void showField()
	{
		showColumnIndex(fieldSize);
		for (int i = 0; i < fieldSize; i++) {
			showLineIndex(i+1);
			showLine(i);
			System.out.println();
		}
	}

	private void showLineIndex(int lineIndex)
	{
		if (lineIndex < 10) {
			System.out.print(" " + lineIndex + " ");
		} else {
			System.out.print(lineIndex + " ");
		}
	}

	private void showColumnIndex(int fieldSize)
	{
		System.out.print("   ");
		for (int i = 0; i < fieldSize; i++) {
			char index = (char) (i + 65);
			System.out.print(" " + index + " ");
		}
		System.out.println();
	}

	private void showLine(int lineNumber)
	{
		for (int i = 0; i < fieldSize; i++) {
			showCell(i, lineNumber);
		}
	}

	private void showCell(int x, int y)
	{
		System.out.print("[" + field[x][y] + "]");
	}

	public void eraseField()
	{
		for (int i = 0; i < fieldSize; i++) {
			eraseLine(i);
		}
	}

	private void eraseLine(int lineNumber)
	{
		for (int i = 0; i < fieldSize; i++) {
			field[i][lineNumber] = ' ';
		}
	}

	public void eraseCellByPlayer(int lineNumber, int columnNumber)
	{
		field[columnNumber][lineNumber] = PlayerLogic.getCurrentPlayer();
	}

	public boolean checkWin()
	{
		for (int i = 0; i < fieldSize; i++) {
			if (checkLineWin(i) || checkColumnWin(i) || checkFirstDiagonalWin() || checkSecondDiagonalWin()) {
				return true;
			}
		}
		return false;
	}

	private boolean checkLineWin(int lineNumber)
	{
		int firstCellIndex = 0;
		int trueCount = 0;
		char firstCellLine = field[firstCellIndex][lineNumber];
		if (firstCellLine == 'X' || firstCellLine == 'O') {
			trueCount++;
			for (int i = 1; i < fieldSize; i++) {
				if (firstCellLine == field[i][lineNumber]) {
					trueCount++;
				} else {
					trueCount = 0;
				}
			}
			return trueCount == fieldSize;
		}
		else return false;
	}

	private boolean checkColumnWin(int columnNumber)
	{
		int firstCellIndex = 0;
		int trueCount = 0;
		char firstCellLine = field[columnNumber][firstCellIndex];
		if (firstCellLine == 'X' || firstCellLine == 'O') {
			trueCount++;
			for (int i = 1; i < fieldSize; i++) {
				if (firstCellLine == field[columnNumber][i]) {
					trueCount++;
				} else {
					trueCount = 0;
				}
			}
			return trueCount == fieldSize;
		}
		else return false;
	}

	private boolean checkFirstDiagonalWin()
	{
		int firstCellIndex = 0;
		int trueCount = 0;
		char firstCellLine = field[firstCellIndex][firstCellIndex];
		if (firstCellLine == 'X' || firstCellLine == 'O') {
			trueCount++;
			for (int i = 1; i < fieldSize; i++) {
				if (firstCellLine == field[i][i]) {
					trueCount++;
				} else {
					trueCount = 0;
				}
			}
			return trueCount == fieldSize;
		}
		else return false;
	}

	private boolean checkSecondDiagonalWin()
	{
		int firstCellIndex = 0;
		int trueCount = 0;
		char firstCellLine = field[fieldSize-1][firstCellIndex];
		if (firstCellLine == 'X' || firstCellLine == 'O') {
			trueCount++;
			for (int i = 1; i < fieldSize; i++) {
				if (firstCellLine == field[fieldSize-1-i][i]) {
					trueCount++;
				} else {
					trueCount = 0;
				}
			}
			return trueCount == fieldSize;
		}
		else return false;
	}

}
