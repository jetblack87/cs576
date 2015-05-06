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

	public List<KwicEntry> getIndex() {
		return Collections.unmodifiableList(index);
	}

	public void add(KwicEntry kwicEntry) {
		index.add(kwicEntry);
	}

	public void printHtml(final String outputFile)
			throws FileNotFoundException, UnsupportedEncodingException {
		final PrintWriter outputStream = getOutputStream(outputFile);
		List<KwicEntry> sortedIndex = new ArrayList<KwicEntry>(index);
		Collections.sort(sortedIndex);
		outputStream.println("<HTML><HEAD/>");
		outputStream.println("<BODY>");
		outputStream.println("<TABLE>");
		for (KwicEntry entry : sortedIndex) {
			outputStream.println(entry.toHtmlRow());
		}
		outputStream.println("</TABLE>");
		outputStream.println("</BODY>");
		outputStream.println("</HTML>");
		outputStream.flush();
	}

	private PrintWriter getOutputStream(final String outputFile)
			throws FileNotFoundException, UnsupportedEncodingException {
		if (Constants.STDOUT.equals(outputFile)) {
			Writer w = new OutputStreamWriter(System.out, "UTF-8");
			return new PrintWriter(w);
		} else {
			File file = new File(outputFile);
			Writer w = new OutputStreamWriter(new FileOutputStream(file),
					"UTF-8");
			return new PrintWriter(w);
		}
	}
}
