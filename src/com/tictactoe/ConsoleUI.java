package com.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class ConsoleUI
{
	BufferedReader in;
	PrintStream out;


	public ConsoleUI()
	{
		in = new BufferedReader(new InputStreamReader(System.in));
		out = System.out;
	}


	public void onStart()
	{
		out.println("Игра крестики-нолики.");
	}

	public void onWin(char currentPlayer)
	{
		out.println("Игрок " + currentPlayer + ", вы победили!");
	}

	public void onDraw()
	{
		out.println("Ничья!");
	}

	public void onEnd()
	{
		out.println("Игра закончена.");
	}


	public int getFieldSize(int minSize, int maxSize) throws IOException
	{
		int fieldSize;
		while (true) {
			out.print("Установите размер игрового поля (одно целое число от " + minSize + " до " + maxSize + "): ");
			String input = in.readLine().trim();
			fieldSize = Game.checkInputNumber(input, minSize, maxSize);
			if (fieldSize == 0) {
				out.println("Неверно! Попробуйте ещё раз.");
			}
			else break;
		}
		return fieldSize;
	}

	public PlayerMove getMove(
			char currentPlayer,
			Function<String, PlayerMove> checkInputCell,
			Function<PlayerMove, Boolean> checkInputCellBusy) throws IOException
	{
		out.println("Игрок " + currentPlayer + ", ваш ход");
		out.print("Введите номер строки и букву столбца(например, 2b или 1C): ");

		PlayerMove move;
		while (true) {
			String cell = in.readLine().toUpperCase().trim();
			move = checkInputCell.apply(cell);
			if (move == null) {
				out.print("Игрок " + currentPlayer + ", такой ячейки не существует, введите другую: ");
			} else {
				if (checkInputCellBusy.apply(move)) {
					out.print("Игрок " + currentPlayer + ", эта ячейка уже занята, введите другую: ");
				}
				else break;
			}
		}
		return move;
	}


	public void showField(Field field)
	{
		showColumnIndex(field.fieldSize);
		for (int y = 0; y < field.fieldSize; y++) {
			showLineIndex(y + 1);
			for (int x = 0; x < field.fieldSize; x++) {
				out.print("[" + field.field[x][y] + "]");
			}
			out.println();
		}
		out.println();
	}

	private void showLineIndex(int lineIndex)
	{
		if (lineIndex < 10)
			out.print(" " + lineIndex + " ");
		else
			out.print(lineIndex + " ");
	}

	private void showColumnIndex(int fieldSize)
	{
		out.print("   ");
		for (int i = 0; i < fieldSize; i++) {
			char index = (char) (i + 65);
			out.print(" " + index + " ");
		}
		out.println();
	}


	public interface Function<T, R>
	{
		R apply(T t);
	}
}
