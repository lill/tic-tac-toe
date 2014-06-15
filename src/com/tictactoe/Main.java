package com.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Игра крестики-нолики.");

		final int MIN_FIELD_SIZE = 3;
		final int MAX_FIELD_SIZE = 26;
		int fieldSize;
		String input;

		while (true) {
			System.out.print("Установите размер игрового поля (одно целое число от " + MIN_FIELD_SIZE + " до " + MAX_FIELD_SIZE + "): ");
			input = reader.readLine().trim();
			fieldSize = Checks.checkInputNumber(input, MIN_FIELD_SIZE, MAX_FIELD_SIZE);
			if (fieldSize == 0) {
				System.out.println("Неверно! Попробуйте ещё раз.");
			}
			else break;
		}

		Field field = new Field(fieldSize);
		field.eraseField();
		field.showField();
		System.out.println();

		for (int i = 0; i < fieldSize*fieldSize; i++) {

			String cell;
			int lineIndex;
			int columnIndex;

			System.out.println("Игрок " + PlayerLogic.getCurrentPlayer() + ", ваш ход");
			System.out.print("Введите номер строки и букву столбца(например, 2b или 1C): ");

			while (true) {
				cell = reader.readLine().toUpperCase().trim();
				PlayerMove move = Checks.checkInputCellRight(cell);

				if (move == null) {
					System.out.print("Игрок " + PlayerLogic.getCurrentPlayer() + ", такой ячейки не существует, введите другую: ");
				} else {
					lineIndex = move.inputLineIndex;
					columnIndex = move.inputColumnIndex;
					if (Checks.checkInputCellBusy(lineIndex, columnIndex)) {
						System.out.print("Игрок " + PlayerLogic.getCurrentPlayer() + ", эта ячейка уже занята, введите другую: ");
					}
					else break;
				}

			}

			field.eraseCellByPlayer(lineIndex, columnIndex);
			field.showField();
			System.out.println();

			if (field.checkWin()) {
				System.out.println("Игрок " + PlayerLogic.getCurrentPlayer() + ", вы победили!");
				break;
			} else
			if (field.checkExit()) {
				break;
			} else
				PlayerLogic.setCurrentMove();
		}
		System.out.println("Игра закончена.");

	}
}
