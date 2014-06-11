package com.tictactoe;

public class Player
{
	private static char playerX = 'X';
	private static char playerO = 'O';
	private static int moveCount = 1;

	public static char getCurrentPlayer()
	{
		if (moveCount%2 == 0) {
			return playerO;
		} else {
			return playerX;
		}
	}

	public static void setCurrentMove()
	{
		moveCount++;
	}

	public static int inputColumnNumber (char c)
	{
		if (c == 'A') {
			return 0;
		}
		if (c == 'B') {
			return 1;
		} else
			return 2;
	}
}
