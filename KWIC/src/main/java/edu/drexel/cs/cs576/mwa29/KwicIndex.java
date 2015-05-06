/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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
		final PrintWriter outputStream = getOutputStream(outputFile);
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
	}

	private PrintWriter getOutputStream(final String outputFile)
			throws FileNotFoundException {
		if (Constants.STDOUT.equals(outputFile)) {
			return new PrintWriter(System.out);
		} else {
			final File file = new File(outputFile);
			Writer w = null;
			try {
				w = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				System.err.println("UTF-8 is not supported - exiting");
				System.exit(1);
			}
			return new PrintWriter(w);
		}
	}
}
