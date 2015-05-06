/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author mark
 *
 */
public class KwicIndex {

	private final List<KwicEntry> index;

	public KwicIndex() {
		index = new ArrayList<KwicEntry>();
	}

	public void add(KwicEntry kwicEntry) {
		index.add(kwicEntry);
	}

	public void printHtml(final String outputFile) throws FileNotFoundException {
		final PrintStream outputStream = getOutputStream(outputFile);
		List<KwicEntry> sortedIndex = new ArrayList<KwicEntry>(index);
		Collections.sort(sortedIndex);
		outputStream.println("<HTML><HEAD/>");
		outputStream.println("<BODY>");
		outputStream.println("<TABLE border='1'>");
		for (KwicEntry entry : sortedIndex) {
			outputStream.println(entry.toHtmlRow());
		}
		outputStream.println("</TABLE>");
		outputStream.println("</BODY>");
		outputStream.println("</HTML>");
		if (System.out != outputStream) {
			outputStream.close();
		}
	}

	private PrintStream getOutputStream(final String outputFile)
			throws FileNotFoundException {
		if (Constants.STDOUT.equals(outputFile)) {
			return System.out;
		} else {
			return new PrintStream(outputFile);
		}
	}
}
