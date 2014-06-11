package com.tictactoe;

import java.util.ArrayList;

public class Checks
{
	private static ArrayList<String> inputCellArray = new ArrayList<String>();

	public static boolean checkInputCellRight(String s)
	{
		if (!s.trim().isEmpty()) {
			if (s.charAt(0) == '1' || s.charAt(0) == '2' || s.charAt(0) == '3') {
				if (s.charAt(1) == 'A' || s.charAt(1) == 'B' || s.charAt(1) == 'C') {
					return true;
				} else
					return false;
			} else
				return false;
		} else
			return false;
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
