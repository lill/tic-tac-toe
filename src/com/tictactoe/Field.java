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
			if (checkLine(i) == 1 || checkColumn(i) == 1 || checkFirstDiagonal() == 1 || checkSecondDiagonal() == 1) {
				return true;
			}
		}
		return false;
	}

	public boolean checkExit()
	{
		int failCount = 0;
		int failField = fieldSize * 2 + 2;
		for (int i = 0; i < fieldSize; i++) {
			failCount += checkLine(i) + checkColumn(i);
		}
		failCount += checkFirstDiagonal() + checkSecondDiagonal();
		return failField + failCount == 0;
	}

	private int checkLine(int lineNumber)
	{
		boolean containsX = false;
		boolean containsO = false;
		int countX = 0;
		int countO = 0;
		for (int i = 0; i < fieldSize; i++) {
			if (field[i][lineNumber] == 'X') {
				containsX = true;
				countX++;
			}
			if (field[i][lineNumber] == 'O') {
				containsO = true;
				countO++;
			}
		}

		if (containsX && containsO) {
			return -1; // if line failed
		} else
		if (countX+countO != fieldSize) {
			return 0;
		} else return 1; // win
	}

	private int checkColumn(int columnNumber)
	{
		boolean containsX = false;
		boolean containsO = false;
		int countX = 0;
		int countO = 0;
		for (int i = 0; i < fieldSize; i++) {
			if (field[columnNumber][i] == 'X') {
				containsX = true;
				countX++;
			}
			if (field[columnNumber][i] == 'O') {
				containsO = true;
				countO++;
			}
		}

		if (containsX && containsO) {
			return -1; // if column failed
		} else
		if (countX+countO != fieldSize) {
			return 0;
		} else return 1; // win
	}

	private int checkFirstDiagonal()
	{
		boolean containsX = false;
		boolean containsO = false;
		int countX = 0;
		int countO = 0;
		for (int i = 0; i < fieldSize; i++) {
			if (field[i][i] == 'X') {
				containsX = true;
				countX++;
			}
			if (field[i][i] == 'O') {
				containsO = true;
				countO++;
			}
		}

		if (containsX && containsO) {
			return -1; // if diagonal failed
		} else
		if (countX+countO != fieldSize) {
			return 0;
		} else return 1; // win
	}

	private int checkSecondDiagonal()
	{
		boolean containsX = false;
		boolean containsO = false;
		int countX = 0;
		int countO = 0;
		for (int i = 0; i < fieldSize; i++) {
			if (field[fieldSize-1-i][i] == 'X') {
				containsX = true;
				countX++;
			}
			if (field[fieldSize-1-i][i] == 'O') {
				containsO = true;
				countO++;
			}
		}

		if (containsX && containsO) {
			return -1;// if diagonal failed
		} else
		if (countX+countO != fieldSize) {
			return 0;
		} else return 1; // win
	}

}
