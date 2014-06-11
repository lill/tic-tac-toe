package com.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Игра крестики-нолики.");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int fieldSize = Field.getFieldSize();
		Field field = new Field();
		field.showItems();
		System.out.println();

		for (int i = 0; i < fieldSize*fieldSize; i++) {
			String cell;
			int lineIndex;
			int columnIndex;

			System.out.println("Игрок " + Player.getCurrentPlayer() + ", ваш ход");
			System.out.println("Введите номер строки и букву столбца(например, 2B):");

			while (true) {
				cell = reader.readLine().toUpperCase();
				if (!Checks.checkInputCellRight(cell)) {
					System.out.println("Игрок " + Player.getCurrentPlayer() + ", такой ячейки не существует, введите другую:");
				} else {
					lineIndex = Character.getNumericValue(cell.charAt(0)) - 1;
					columnIndex = Player.inputColumnNumber(cell.charAt(1));
					if (Checks.checkInputCellBusy(lineIndex, columnIndex)) {
						System.out.println("Игрок " + Player.getCurrentPlayer() + ", эта ячейка уже занята, введите другую:");
					} else
					break;
				}
			}

			field.eraseCell(lineIndex, columnIndex);
			field.showItems();
			System.out.println();
			if (field.checkWin()) {
				System.out.println("Игрок " + Player.getCurrentPlayer() + ", вы победили! ПОЗДРАВЛЯЮ!");
				break;
			} else
				Player.setCurrentMove();
		}
		System.out.println("Игра закончена.");

	}
}
