package com.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;

public class Field
{
	final static int MIN_SIZE = 3;
	final static int MAX_SIZE = 26;

	private static ArrayList<Line> linesCollection, columnsCollection, diagonals1Collection, diagonals2Collection;

	public int fieldSize;
	public Cell[][] field;
	private int cellsForWin;
	private Game game;


	public Field(int size, Game gameIn)
	{
		game = gameIn;
		fieldSize = size;
		if (fieldSize < 5) {
			cellsForWin = fieldSize;
		} else
			cellsForWin = 5;

		field = new Cell[fieldSize][fieldSize];
		for (int y = 0; y < fieldSize; y++) {
			for (int x = 0; x < fieldSize; x++) {
				field[x][y] = new Cell();
			}
		}
	}

	public boolean checkInputCellBusy(PlayerMove move) {
		return field[move.inputColumnIndex][move.inputLineIndex].value != ' ';
	}

	public void collectoinOfLines()
	{
		linesCollection = new ArrayList<Line>();
		for (int i = 0; i < fieldSize; i++) {
			Cell[] lineArray = new Cell[fieldSize];
			for (int j = 0; j < fieldSize; j++) {
				lineArray[j] = field[j][i];
			}
			linesCollection.add(new Line(lineArray, i, LineType.GORIZONTAL));
		}

		columnsCollection = new ArrayList<Line>();
		for (int i = 0; i < fieldSize; i++) {
			columnsCollection.add(new Line(field[i], i, LineType.VERTICAL));
		}

		diagonals1Collection = new ArrayList<Line>();
		for (int i = 0; i <= fieldSize - cellsForWin; i++) {
			ArrayList<Cell> diagonal1ArrayList = new ArrayList<Cell>();
			int j = 0;
			while (true) {
				if (j + i < fieldSize) {
					diagonal1ArrayList.add(field[j][j + i]);
					j++;
				}
				else break;
			}
			Cell[] diagonal1Array = diagonal1ArrayList.toArray(new Cell[diagonal1ArrayList.size()]);
			diagonals1Collection.add(new Line(diagonal1Array, i, LineType.DIAGONAL1));
		}
		for (int i = 1; i <= fieldSize - cellsForWin; i++) {
			ArrayList<Cell> diagonal1ArrayList = new ArrayList<Cell>();
			int j = 0;
			while (true) {
				if (j + i < fieldSize) {
					diagonal1ArrayList.add(field[j + i][j]);
					j++;
				}
				else break;
			}
			Cell[] diagonal1Array = diagonal1ArrayList.toArray(new Cell[diagonal1ArrayList.size()]);
			diagonals1Collection.add(new Line(diagonal1Array, fieldSize - cellsForWin + i, LineType.DIAGONAL1));
		}

		diagonals2Collection = new ArrayList<Line>();
		for (int i = 0; i <= fieldSize - cellsForWin; i++) {
			ArrayList<Cell> diagonal2ArrayList = new ArrayList<Cell>();
			int j = 0;
			while (true) {
				if (j + i < fieldSize) {
					diagonal2ArrayList.add(field[fieldSize-1 - j][j + i]);
					j++;
				}
				else break;
			}
			Cell[] diagonal2Array = diagonal2ArrayList.toArray(new Cell[diagonal2ArrayList.size()]);
			diagonals2Collection.add(new Line(diagonal2Array, i, LineType.DIAGONAL2));
		}
		for (int i = 1; i <= fieldSize - cellsForWin; i++) {
			ArrayList<Cell> diagonal2ArrayList = new ArrayList<Cell>();
			int j = 0;
			while (true) {
				if (j + i < fieldSize) {
					diagonal2ArrayList.add(field[fieldSize-1 - j - i][j]);
					j++;
				}
				else break;
			}
			Cell[] diagonal2Array = diagonal2ArrayList.toArray(new Cell[diagonal2ArrayList.size()]);
			diagonals2Collection.add(new Line(diagonal2Array, fieldSize - cellsForWin + i, LineType.DIAGONAL2));
		}
	}

	public void onCellChange(int lineNumber, int columnNumber, char player) {
		field[columnNumber][lineNumber].value = player;
		linesCollection.get(lineNumber).onCellChange(player);
		columnsCollection.get(columnNumber).onCellChange(player);

		int diagonal1Number = lineNumber - columnNumber;
		try {
			if (diagonal1Number < 0) {
				diagonals1Collection.get(fieldSize - cellsForWin + Math.abs(diagonal1Number)).onCellChange(player);
			} else {
				diagonals1Collection.get(diagonal1Number).onCellChange(player);
			}
		} catch (Exception ignored) {}

		int diagonal2Number = lineNumber + columnNumber - (fieldSize-1);
		try {
			if (diagonal2Number < 0) {
				diagonals2Collection.get(fieldSize - cellsForWin + Math.abs(diagonal2Number)).onCellChange(player);
			} else {
				diagonals2Collection.get(diagonal2Number).onCellChange(player);
			}
		} catch (Exception ignored) {}
	}


	public enum LineType {GORIZONTAL, VERTICAL, DIAGONAL1, DIAGONAL2}

	public class Line
	{
		private Cell[] chars;
		private int lineNumber;
		LineType lineType;
		int countX = 0;
		int countO = 0;

		private Line(Cell[] cells, int lineNumber, LineType lineType) {
			this.chars = cells;
			this.lineNumber = lineNumber;
			this.lineType = lineType;
		}

		private void onCellChange(char player) {
			char currentChar;
			int count;

			if (player == 'X') {
				countX++;
				count = countX;
			} else {
				countO++;
				count = countO;
			}

			if (count >= cellsForWin) {
				int countInRow = 0;
				for (Cell charArray : chars) {
					currentChar = charArray.value;
					if (currentChar == player) {
						if (++countInRow >= cellsForWin) {
							assert countInRow == cellsForWin;
							game.onWin();
							break;
						}
					}
					else {
						countInRow = 0;
					}
				}
			}
		}

		@Override
		public String toString()
		{
			return Arrays.toString(chars);
		}
	}
}
