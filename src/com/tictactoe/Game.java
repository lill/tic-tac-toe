package com.tictactoe;

import com.tictactoe.ConsoleUI.Function;
import java.io.IOException;


public class Game
{
	public static final char PLAYER_X  = 'X';
	public static final char PLAYER_O  = 'O';
	public static final char SPACE     = ' ';

	private int moveCount = 1;
	private boolean win = false;
	private boolean draw = false;
	private int fieldSize;


	public void start() throws IOException
	{
		final ConsoleUI userUI = new ConsoleUI();
		userUI.onStart();
		fieldSize = userUI.getFieldSize(Field.MIN_SIZE, Field.MAX_SIZE);

		final Field field = new Field(fieldSize, this);
		userUI.showField(field);
		field.collectoinOfLines();

		for (int i = 0; i < fieldSize*fieldSize; i++) {

			PlayerMove move = userUI.getMove(
				getCurrentPlayer(),
				new Function<String, PlayerMove>() {
					@Override public PlayerMove apply(String s) {
						return checkInputCell(s);
					}
				},
				new Function<PlayerMove, Boolean>() {
					@Override public Boolean apply(PlayerMove move) {
						return field.checkInputCellBusy(move);
					}
				}
			);

			field.onCellChange(move.inputLineIndex, move.inputColumnIndex, getCurrentPlayer());

			userUI.showField(field);
			if (this.win) {
				userUI.onWin(getCurrentPlayer());
				break;
			}
			else if (this.draw) {
				userUI.onDraw();
				break;
			}
			moveCount++;
		}
		userUI.onEnd();
	}


	public void onWin() {
		win = true;
	}

	public void onDraw()
	{
		draw = true;
	}


	public char getCurrentPlayer()
	{
		if (moveCount%2 == 0) {
			return PLAYER_O;
		} else {
			return PLAYER_X;
		}
	}


	public static int checkInputNumber(String s, int minNumber, int maxNumber)
	{
		int number = 0;
		if (!s.isEmpty()) {

			try {
				number = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return 0;
			}

			if (number >= minNumber && number <= maxNumber) {
				return number;
			}
			else return 0;
		}
		else return number;
	}

	private PlayerMove checkInputCell(String s)
	{
		StringBuilder sb = new StringBuilder();
		int stringLength = s.length();
		int lineIndex;
		int columnIndex;
		if (!s.isEmpty() && stringLength > 1 && stringLength < 4) {

			for (int i = 0; i < stringLength; i++) {
				char currentChar = s.charAt(i);

				if (i < 2 && Character.isDigit(currentChar)) {
					sb.append(currentChar);
				} else if (sb.length() == 0 || !checkInputLineNumber(sb.toString())) {
					return null;
				} else {
					lineIndex = Integer.parseInt(sb.toString())-1;
					sb.setLength(0);
					if (Character.isLetter(currentChar)) {
						sb.append(currentChar);
						if (checkInputColumnLetter(sb.charAt(0))) {
							columnIndex = (int)sb.charAt(0) - 65;
							return new PlayerMove(lineIndex, columnIndex);
						}
						return null;
					}
					return null;
				}

			}

		}
		return null;
	}

	private boolean checkInputLineNumber(String s)
	{
		return checkInputNumber(s, 1, fieldSize) != 0;
	}

	private boolean checkInputColumnLetter(char c)
	{
		String s = String.valueOf((int) c);
		return checkInputNumber(s, 65, fieldSize+65) != 0;
	}

}
