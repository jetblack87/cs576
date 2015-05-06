/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author mark
 *
 */
public class KwicGenerator {

	private static final String tokenDelimiter = "[\\s.,;:?!]+";

	private final Scanner inputFileScanner;
	private final List<String> stopWordList;

	public KwicGenerator(final String inputFile, final String stopWordFile)
			throws FileNotFoundException {
		inputFileScanner = getInputScanner(inputFile);

		stopWordList = getStopWordList(stopWordFile);
	}

	public KwicIndex generateKwicIndex() {
		final KwicIndex kwicIndex = new KwicIndex();

		while (inputFileScanner.hasNext()) {
			String line = inputFileScanner.nextLine();
			List<String> tokenizedLine = Arrays.asList(line
					.split(tokenDelimiter));
			for (int i = 0; i < tokenizedLine.size(); i++) {
				final String nextWord = tokenizedLine.get(i);
				if (!stopWordList.contains(nextWord.toLowerCase())) {
					final int beforeLength = i < Constants.MAX_BEFORE_ENTRY ? i
							: Constants.MAX_BEFORE_ENTRY;
					final int afterLength = (tokenizedLine.size() - i < Constants.MAX_AFTER_ENTRY) ? (tokenizedLine
							.size() - i) : Constants.MAX_AFTER_ENTRY;

					if (i == 0) {
						kwicIndex.add(new KwicEntry(nextWord,
								new ArrayList<String>(), tokenizedLine.subList(
										i + 1, i + afterLength)));
					} else if (i == tokenizedLine.size()) {
						kwicIndex.add(new KwicEntry(nextWord, tokenizedLine
								.subList(i - beforeLength, i),
								new ArrayList<String>()));
					} else {
						kwicIndex.add(new KwicEntry(nextWord, tokenizedLine
								.subList(i - beforeLength, i), tokenizedLine
								.subList(i + 1, i + afterLength)));
					}
				}
			}

		}

		return kwicIndex;
	}

	private Scanner getInputScanner(final String inputFile)
			throws FileNotFoundException {
		if (Constants.STDIN.equals(inputFile.toLowerCase())) {
			return new Scanner(System.in);
		} else {
			return new Scanner(new File(inputFile));
		}
	}

	private List<String> getStopWordList(final String stopWordFile)
			throws FileNotFoundException {
		final List<String> stopWordList = new ArrayList<String>();
		final File file = new File(stopWordFile);
		if (file.exists()) {
			final Scanner stopWordScanner = new Scanner(file);

			while (stopWordScanner.hasNext()) {
				stopWordList.add(stopWordScanner.next().toLowerCase());
			}

			stopWordScanner.close();
		}
		return stopWordList;
	}
}
