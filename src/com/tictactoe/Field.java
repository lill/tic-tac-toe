package com.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class Field
{
	final static int MIN_SIZE = 3;
	final static int MAX_SIZE = 26;

	public  int      fieldSize;
	public  Cell[][] field;

	private int      cellsForWin;
	private Game     game;

	private ArrayList<Line> linesCollection, columnsCollection, diagonals1Collection, diagonals2Collection;
	private HashSet<Line> nonFailedLines;


	public Field(int size, Game gameIn)
	{
		game = gameIn;
		fieldSize = size;
		cellsForWin = fieldSize < 5 ? fieldSize : 5;

		field = new Cell[fieldSize][fieldSize];
		for (int y = 0; y < fieldSize; y++) {
			for (int x = 0; x < fieldSize; x++) {
				field[x][y] = new Cell();
			}
		}
	}


	public boolean checkInputCellBusy(PlayerMove move)
	{
		return field[move.inputColumnIndex][move.inputLineIndex].value != Game.SPACE;
	}

	public void collectoinOfLines()
	{
		nonFailedLines = new HashSet<Line>();
		Line line;

		linesCollection = new ArrayList<Line>();
		for (int i = 0; i < fieldSize; i++) {
			Cell[] lineArray = new Cell[fieldSize];
			for (int j = 0; j < fieldSize; j++) {
				lineArray[j] = field[j][i];
			}
			line = new Line(lineArray, i, LineType.GORIZONTAL);
			linesCollection.add(line);
			nonFailedLines.add(line);
		}

		columnsCollection = new ArrayList<Line>();
		for (int i = 0; i < fieldSize; i++) {
			line = new Line(field[i], i, LineType.VERTICAL);
			columnsCollection.add(line);
			nonFailedLines.add(line);
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
			line = new Line(diagonal1Array, i, LineType.DIAGONAL1);
			diagonals1Collection.add(line);
			nonFailedLines.add(line);
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
			line = new Line(diagonal1Array, fieldSize - cellsForWin + i, LineType.DIAGONAL1);
			diagonals1Collection.add(line);
			nonFailedLines.add(line);
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
			line = new Line(diagonal2Array, i, LineType.DIAGONAL2);
			diagonals2Collection.add(line);
			nonFailedLines.add(line);
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
			line = new Line(diagonal2Array, fieldSize - cellsForWin + i, LineType.DIAGONAL2);
			diagonals2Collection.add(line);
			nonFailedLines.add(line);
		}
	}

	public void onCellChange(int lineNumber, int columnNumber, char player)
	{
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
		ArrayList<Group> arrayGroup;

		private Line(Cell[] cells, int lineNumber, LineType lineType) {
			this.chars = cells;
			this.lineNumber = lineNumber;
			this.lineType = lineType;
			arrayGroup = new ArrayList<Group>();
			arrayGroup.add(new Group(fieldSize, Game.SPACE));
		}

		@SuppressWarnings("ConstantConditions")
		private void onCellChange(char player)
		{
			char currentChar;
			int count;

			if (player == Game.PLAYER_X) {
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
					else countInRow = 0;
				}
			}

			arrayGroup.clear();
			int lastIdx = chars.length - 1;
			int commonCount = 1;
			char groupType = chars[0].value;
			for (int i = 1; i < chars.length; i++) {
				currentChar = chars[i].value;
				if (currentChar != groupType) {
					arrayGroup.add(new Group(commonCount, groupType));
					groupType = currentChar;
					commonCount = 1;
				} else {
					commonCount++;
				}
				if (i == lastIdx)
					arrayGroup.add(new Group(commonCount, currentChar));
			}

			int seqXSize = 0;
			int seqOSize = 0;
			boolean failedX = true;
			boolean failedO = true;
			for (Group group : arrayGroup) {
				int groupCount = group.count;
				switch (group.symbol) {
					case Game.PLAYER_X:
						seqXSize += groupCount;
						seqOSize = 0;
						break;
					case Game.PLAYER_O:
						seqOSize += groupCount;
						seqXSize = 0;
						break;
					case Game.SPACE:
						seqXSize += groupCount;
						seqOSize += groupCount;
						break;
				}

				if (seqXSize >= cellsForWin) failedX = false;
				if (seqOSize >= cellsForWin) failedO = false;

				if (!failedX && !failedO) break;
			}

			if (failedX && failedO) {
				nonFailedLines.remove(this);
				if (nonFailedLines.isEmpty())
					game.onDraw();
			}

		}

		@Override
		public String toString()
		{
			return Arrays.toString(chars);
		}
	}
}
