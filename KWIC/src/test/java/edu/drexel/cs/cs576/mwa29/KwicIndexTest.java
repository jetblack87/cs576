/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @author mark
 *
 */
public class KwicIndexTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void add_addEntry_successfully() {
		final KwicIndex index = new KwicIndex();
		final KwicEntry entry = new KwicEntry("keyword", "before", "after");
		index.add(entry);
		final List<KwicEntry> entries = index.getIndex();

		assertEquals(1, entries.size());
		assertEquals(entry, entries.get(0));
	}

	@Test
	public void printHtml_print_successfully() throws FileNotFoundException,
			UnsupportedEncodingException {
		final KwicIndex index = new KwicIndex();
		final KwicEntry entry = new KwicEntry("keyword", "before", "after");
		index.add(entry);

		index.printHtml(Constants.STDOUT);

		assertThat(
				outContent.toString(),
				containsString("<TR><TD ALIGN='right'>before</TD><TD><B>keyword</B></TD><TD>after</TD></TR>"));
	}

	@Test
	public void printHtml_toFile_successfully() throws IOException {
		final KwicIndex index = new KwicIndex();
		final KwicEntry entry = new KwicEntry("keyword", "before", "after");
		index.add(entry);

		final File tempFile = tempFolder.newFile("tempFile.txt");
		index.printHtml(tempFile.getPath());

		final Scanner scanner = new Scanner(tempFile, "UTF-8");

		scanner.useDelimiter("\\z");

		assertThat(
				scanner.next(),
				containsString("<TR><TD ALIGN='right'>before</TD><TD><B>keyword</B></TD><TD>after</TD></TR>"));

		scanner.close();
	}

	@Test(expected = FileNotFoundException.class)
	public void getOutputStream_badFile_throwsFileNotFoundException()
			throws FileNotFoundException, UnsupportedEncodingException {
		final KwicIndex index = new KwicIndex();
		index.printHtml("\0");
	}
}
