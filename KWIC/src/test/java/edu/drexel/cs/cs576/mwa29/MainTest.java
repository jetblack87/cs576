/**
 * JUnit tests used to test Main class
 */
package edu.drexel.cs.cs576.mwa29;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mark
 *
 */
public class MainTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	private static final String TEST_RESOURCE_BASE_PATH = "src/test/resources/";

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void main_getHelp_successfully() {
		Main.main(new String[] { "-help" });
		assertThat(outContent.toString(), containsString("Usage"));
	}

	@Test
	public void main_generateIndex_successfully() {
		final String inputFile = TEST_RESOURCE_BASE_PATH + "inputfile.txt";
		final String stopWordFile = TEST_RESOURCE_BASE_PATH + "stopwords.txt";
		final String outputFile = System.getProperty("java.io.tmpdir")
				+ "/kwic_index.html";
		final File output = new File(outputFile);
		if (output.exists()) {
			output.delete();
		}
		
		Main.main(new String[] { "-input_file", inputFile, "-stop_word_file",
				stopWordFile, "-output_file", outputFile });

		assertTrue(output.exists());
		
		output.delete();
	}
}
