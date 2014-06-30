package com.tictactoe;


public class Cell
{
	public char value = Game.SPACE;

	@Override
	public String toString()
	{
		return Character.toString(value);
	}
}
