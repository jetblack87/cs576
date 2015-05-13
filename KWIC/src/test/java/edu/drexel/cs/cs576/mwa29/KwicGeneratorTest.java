/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author mark
 *
 */
public class KwicGeneratorTest {

	private static final String TEST_RESOURCE_BASE_PATH = "src/test/resources/";

	@Test
	public void generateKwicIndex_successfully() throws FileNotFoundException {
		final KwicEntry[] expectedResults = new KwicEntry[] {
				new KwicEntry("this", "", "is a good input file"),
				new KwicEntry("is", "this", "a good input file"),
				new KwicEntry("a", "this is", "good input file"),
				new KwicEntry("good", "this is a", "input file"),
				new KwicEntry("input", "this is a good", "file"),
				new KwicEntry("file", "this is a good input", "") };

		final String inputFile = TEST_RESOURCE_BASE_PATH + "inputfile.txt";
		final String stopWordFile = TEST_RESOURCE_BASE_PATH + "stopwords.txt";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final KwicIndex kwicIndex = kwicGenerator.generateKwicIndex();
		final List<KwicEntry> index = kwicIndex.getIndex();

		Assert.assertArrayEquals(expectedResults,
				index.toArray(new KwicEntry[] {}));
	}

	@Test
	public void generateKwicIndex_withStopwords_successfully()
			throws FileNotFoundException {
		final KwicEntry[] expectedResults = new KwicEntry[] {
				new KwicEntry("good", "this is a", "input file"),
				new KwicEntry("input", "this is a good", "file"),
				new KwicEntry("file", "this is a good input", "") };

		final String inputFile = TEST_RESOURCE_BASE_PATH + "inputfile.txt";
		final String stopWordFile = TEST_RESOURCE_BASE_PATH
				+ "valid_stopwords.txt";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final KwicIndex kwicIndex = kwicGenerator.generateKwicIndex();
		final List<KwicEntry> index = kwicIndex.getIndex();

		Assert.assertArrayEquals(expectedResults,
				index.toArray(new KwicEntry[] {}));
	}

	@Test
	public void getInputScanner_fromFile_successfully()
			throws FileNotFoundException {
		final String inputFile = TEST_RESOURCE_BASE_PATH + "inputfile.txt";
		final String stopWordFile = "";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final Scanner scanner = kwicGenerator.getInputScanner(inputFile);

		assertNotNull(scanner);
	}

	@Test
	public void getInputScanner_fromStdin_successfully()
			throws FileNotFoundException {
		final String inputFile = Constants.STDIN;
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile, "");
		final Scanner scanner = kwicGenerator.getInputScanner(inputFile);

		assertNotNull(scanner);
	}

	@Test(expected = FileNotFoundException.class)
	public void getInputScanner_badPath_throwsFileNotFoundException()
			throws FileNotFoundException {
		new KwicGenerator("", "");
	}

	@Test
	public void getStopWordList_successfully() throws FileNotFoundException {
		final String inputFile = Constants.STDIN;
		final String stopWordFile = TEST_RESOURCE_BASE_PATH + "stopwords.txt";
		final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
				stopWordFile);
		final List<String> stopWords = kwicGenerator
				.getStopWordList(stopWordFile);

		Assert.assertArrayEquals(
				new String[] { "these", "are", "stop", "words" },
				stopWords.toArray(new String[] {}));
	}
}
