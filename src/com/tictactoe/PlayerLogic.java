package com.tictactoe;

public class PlayerLogic
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
}
