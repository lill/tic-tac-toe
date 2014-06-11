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
			} else return 0;
		} else
			return number;
	}

	public static boolean checkInputCellBusy (int lineIndex, int columnIndex)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(lineIndex);
		sb.append(columnIndex);
		String inputIndex = sb.toString();
//		или
//		String inputIndex = String.valueOf(lineIndex) + columnIndex;

		if (inputCellArray.contains(inputIndex)) {
			return true;
		} else {
			inputCellArray.add(inputIndex);
			return false;
		}
	}
}
