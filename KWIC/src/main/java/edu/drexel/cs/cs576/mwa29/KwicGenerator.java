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
					final int afterLength = (tokenizedLine.size() - i < Constants.MAX_AFTER_ENTRY + 1) ? (tokenizedLine
							.size() - i) : Constants.MAX_AFTER_ENTRY + 1;

					final List<String> beforeKeywordList = new ArrayList<String>(
							tokenizedLine.subList(i - beforeLength, i));
					if (i > Constants.MAX_BEFORE_ENTRY) {
						beforeKeywordList.add(0,"...");
					}
					final List<String> afterKeywordList = new ArrayList<String>(
							tokenizedLine.subList(i + 1, i + afterLength));
					if (tokenizedLine.size() - i > Constants.MAX_AFTER_ENTRY + 1) {
						afterKeywordList.add("...");
					}
					kwicIndex.add(new KwicEntry(nextWord, beforeKeywordList,
							afterKeywordList));
				}
			}

		}

		return kwicIndex;
	}

	protected Scanner getInputScanner(final String inputFile)
			throws FileNotFoundException {
		if (Constants.STDIN.equals(inputFile.toLowerCase())) {
			return new Scanner(System.in, "UTF-8");
		} else {
			return new Scanner(new File(inputFile), "UTF-8");
		}
	}

	protected List<String> getStopWordList(final String stopWordFile)
			throws FileNotFoundException {
		final List<String> stopWordList = new ArrayList<String>();
		final File file = new File(stopWordFile);
		if (file.exists()) {
			final Scanner stopWordScanner = new Scanner(file, "UTF-8");

			while (stopWordScanner.hasNext()) {
				stopWordList.add(stopWordScanner.next().toLowerCase());
			}

			stopWordScanner.close();
		}
		return stopWordList;
	}
}
