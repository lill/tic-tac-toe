package com.tictactoe;

import com.tictactoe.Player;

public class Field
{
	private static int fieldSize = 3;
	private final  char[][] field     = new char[fieldSize][fieldSize];

	public static int getFieldSize()
	{
		return fieldSize;
	}

	public void showItems() {
		System.out.println("   A  B  C");
		for (int i = 0; i < fieldSize; i++) {
			System.out.print(i+1 + " ");
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

	public void eraseCell(int lineNumber, int columnNumber) {
		field[columnNumber][lineNumber] = Player.getCurrentPlayer();
	}

	public boolean checkWin() {
		for (int i = 0; i < fieldSize; i++) {
			if (checkLineWin(i) || checkColumnWin(i) || checkFirstDiagonalWin() || checkSecondDiagonalWin()) {
				return true;
			}
		}
		return false;
	}

	private boolean checkLineWin (int lineNumber) {
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
		} else
			return false;
	}

	public boolean checkColumnWin (int columnNumber) {
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
		} else
			return false;
	}

	private boolean checkFirstDiagonalWin () {
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
		} else
			return false;
	}

	private boolean checkSecondDiagonalWin () {
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
		} else
			return false;
	}



	// создаем поле, которое в каждую ячейку пишет пробел
	//	public void  eraseField() {
	//		for (int i = 0; i < fieldSize; i++) {
	//			eraseLine(i);
	//		}
	//	}

	//	public void eraseLine(int lineNumber) {
	//		for (int i = 0; i < fieldSize; i++) {
	//			field[i][lineNumber] = ' ';
	//		}
	//	}

}

/*
поля для игры Х-0
    0  1  2
0  [ ][ ][ ]
1  [ ][ ][ ]
2  [ ][ ][ ]
 */
