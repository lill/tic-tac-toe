package com.tictactoe;

import java.util.ArrayList;

public class Checks
{
	private static ArrayList<String> inputCellArray = new ArrayList<String>();

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

	public static PlayerMove checkInputCellRight(String s)
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
						else return null;
					}
					else return null;
				}

			}

		}
		return null;
	}

	private static boolean checkInputLineNumber(String s)
	{
		return checkInputNumber(s, 1, Field.fieldSize) != 0;
	}

	private static boolean checkInputColumnLetter(char c)
	{
		String s = String.valueOf((int) c);
		return checkInputNumber(s, 65, Field.fieldSize+65) != 0;
	}

	public static boolean checkInputCellBusy (int lineIndex, int columnIndex)
	{
		String inputIndex = String.valueOf(lineIndex) + columnIndex;

		if (inputCellArray.contains(inputIndex)) {
			return true;
		} else {
			inputCellArray.add(inputIndex);
			return false;
		}
	}
}
