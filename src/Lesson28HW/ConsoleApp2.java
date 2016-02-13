package Lesson28HW;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

class ConsoleApp2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Please enter some text: ");
		String someText = sc.nextLine();
		String someTextToUpperCase = someText.toUpperCase();

		TreeMap<Character, Integer> sortedByLetter = new TreeMap<Character, Integer>();

		fillTreeMapSortedByLetter(sortedByLetter, someTextToUpperCase);

		TreeMap<Integer, TreeSet> sortedByLetterFrequency = new TreeMap<Integer, TreeSet>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return (i2.compareTo(i1));
			}
		});

		fillTreeMatSortedByLetterFrequency(sortedByLetterFrequency, sortedByLetter);

		System.out.println();

		printTreeMatSortedByLetterFrequency(sortedByLetterFrequency, someTextToUpperCase);

	}

	static void fillTreeMapSortedByLetter(TreeMap<Character, Integer> someTreeMap, String someTextToUpperCase) {
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

	static void fillTreeMatSortedByLetterFrequency(TreeMap<Integer, TreeSet> sortedByLetterFrequency,
			TreeMap<Character, Integer> sortedByLetter) {
		for (Map.Entry<Character, Integer> entry : sortedByLetter.entrySet()) {
			if (sortedByLetterFrequency.containsKey(entry.getValue())) {
				sortedByLetterFrequency.get(entry.getValue()).add(entry.getKey());
			} else {
				sortedByLetterFrequency.put(entry.getValue(), new TreeSet<>());
				sortedByLetterFrequency.get(entry.getValue()).add(entry.getKey());
			}
		}
	}

	static void printTreeMatSortedByLetterFrequency(TreeMap<Integer, TreeSet> sortedByLetterFrequency,
			String someTextToUpperCase) {
		for (Entry<Integer, TreeSet> entry : sortedByLetterFrequency.entrySet()) {
			System.out.print(entry.getKey() + ": " + entry.getValue().toString() + "\n");
			for (int i = 0; i < entry.getKey() * 100 / countLetters(someTextToUpperCase); i++) {
				System.out.print("#");
			}
			System.out.println("\n");
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
