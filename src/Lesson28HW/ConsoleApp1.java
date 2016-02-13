package Lesson28HW;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

class ConsoleApp1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Please enter some text: ");
		String someText = sc.nextLine();
		String someTextToUpperCase = someText.toUpperCase();

		TreeMap<Character, Integer> sortedByLetter = new TreeMap<Character, Integer>();

		fillTreeMap(sortedByLetter, someTextToUpperCase);

		TreeMap<Character, Integer> sortedByLetterFrequency = new TreeMap<Character, Integer>(
				new Comparator<Character>() {
					@Override
					public int compare(Character letter1, Character letter2) {
						if (sortedByLetter.get(letter1).compareTo(sortedByLetter.get(letter2)) == 0) {
							return letter1.toString().compareTo(letter2.toString());
						}
						return sortedByLetter.get(letter2).compareTo(sortedByLetter.get(letter1));
					}
				});

		fillTreeMap(sortedByLetterFrequency, someTextToUpperCase);

		System.out.println();
		for (Map.Entry<Character, Integer> entry : sortedByLetterFrequency.entrySet()) {
			System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
			for (int i = 0; i < entry.getValue() * 100 / countLetters(someTextToUpperCase); i++) {
				System.out.print("#");
			}
			System.out.println();
		}

	}

	static void fillTreeMap(TreeMap<Character, Integer> someTreeMap, String someTextToUpperCase) {
		for (char c : someTextToUpperCase.toCharArray()) {
			if (c >= 65 && c <= 90) {
				if (someTreeMap.containsKey(c)) {
					someTreeMap.put(c, someTreeMap.get(c) + 1);
				} else {
					someTreeMap.put(c, 1);
				}
			}
		}
	}

	static int countLetters(String someTextToUpperCase) {
		int counter = 0;
		for (int i = 0; i < someTextToUpperCase.length(); i++) {
			if (someTextToUpperCase.charAt(i) >= 65 && someTextToUpperCase.charAt(i) <= 90) {
				++counter;
			}
		}
		return counter;
	}
}
