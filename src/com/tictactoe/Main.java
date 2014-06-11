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
		final int MAX_FIELD_SIZE = 9;
		int fieldSize;
		String input;

		while (true) {
			System.out.print("Установите размер игрового поля (одно целое число от " + MIN_FIELD_SIZE + " до " + MAX_FIELD_SIZE + "): ");
			input = reader.readLine().trim();
			fieldSize = Checks.checkInputNumber(input, MIN_FIELD_SIZE, MAX_FIELD_SIZE);
			if (fieldSize == 0) {
				System.out.println("Неверно! Попробуйте ещё раз.");
			} else {
				fieldSize += 1;
				break;
			}
		}

		Field field = new Field(fieldSize);
		field.eraseField();
		field.showField();
		System.out.println();

		for (int i = 0; i < fieldSize*fieldSize; i++) {

			int lineIndex;
			int columnIndex;

			System.out.println("Игрок " + Player.getCurrentPlayer() + ", ваш ход");

			while (true) {
				while (true) {
					System.out.print("Введите номер строки: ");
					input = reader.readLine().trim();
					lineIndex = Checks.checkInputNumber(input, 0, fieldSize);
					if (lineIndex == 0) {
						System.out.println("Неверно! Попробуйте ещё раз.");
					} else {
						break;
					}
				}

				while (true) {
					System.out.print("Введите номер столбца: ");
					input = reader.readLine().trim();
					columnIndex = Checks.checkInputNumber(input, 0, fieldSize);
					if (columnIndex == 0) {
						System.out.println("Неверно! Попробуйте ещё раз.");
					} else {
						break;
					}
				}

				if (Checks.checkInputCellBusy(lineIndex, columnIndex)) {
					System.out.println("Игрок " + Player.getCurrentPlayer() + ", эта ячейка уже занята, введите другую");
				} else
					break;
			}

			field.eraseCellByPlayer(lineIndex, columnIndex);
			field.showField();
			System.out.println();

			if (field.checkWin()) {
				System.out.println("Игрок " + Player.getCurrentPlayer() + ", вы победили!");
				break;
			} else
				Player.setCurrentMove();
		}
		System.out.println("Игра закончена.");

	}
}
