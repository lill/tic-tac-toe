package com.tictactoe;

public class Cell
{
	public char value = ' ';

	@Override
	public String toString()
	{
		return Character.toString(value);
	}
}
