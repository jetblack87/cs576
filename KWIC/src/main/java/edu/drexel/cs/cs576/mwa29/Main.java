/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * @author mark
 *
 */
public class Main {

	/**
	 * The main entry into this application
	 * 
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(final String[] args) {
		String inputFile = Constants.STDIN;
		String outputFile = Constants.STDOUT;
		String stopWordFile = Constants.DEFAULT_STOPWORD_FILE;
		for (int i = 0; i < args.length; i++) {
			if (Constants.HELP_FLAG.equals(args[i].toLowerCase())) {
				printUsage();
				System.exit(0);
			} else if (i < args.length - 1) {
				if (Constants.INPUT_FILE_FLAG.equals(args[i].toLowerCase())) {
					inputFile = args[i + 1];
				} else if (Constants.OUTPUT_FILE_FLAG.equals(args[i]
						.toLowerCase())) {
					outputFile = args[i + 1];
				} else if (Constants.STOP_WORD_FILE_FLAG.equals(args[i]
						.toLowerCase())) {
					stopWordFile = args[i + 1];
				}
			}
		}

		try {
			final KwicGenerator kwicGenerator = new KwicGenerator(inputFile,
					stopWordFile);
			final KwicIndex kwicIndex = kwicGenerator.generateKwicIndex();
			kwicIndex.printHtml(outputFile);
		} catch (final FileNotFoundException | UnsupportedEncodingException e) {
			System.err.println("Failed to print KWIC: " + e.getMessage());
		}
	}

	private static void printUsage() {
		StringBuilder sb = new StringBuilder();
		sb.append(String
				.format("Usage: java -jar KWIC.jar [%s] [%s <inputFile>] [%s <outputFile>] [%s <stopWordFile>]%n",
						Constants.HELP_FLAG, Constants.INPUT_FILE_FLAG,
						Constants.OUTPUT_FILE_FLAG,
						Constants.STOP_WORD_FILE_FLAG));
		sb.append(String.format("\t%s : Print this help.%n",
				Constants.HELP_FLAG));
		sb.append(String
				.format("\t%s : The file that contains the text to process. '%s' indicates STDIN. Defaults to '%s'.%n",
						Constants.INPUT_FILE_FLAG, Constants.STDIN,
						Constants.STDIN));
		sb.append(String
				.format("\t%s : The file where the output will be printed. '%s' indicates STDOUT. Defaults to '%s'.%n",
						Constants.OUTPUT_FILE_FLAG, Constants.STDOUT,
						Constants.STDOUT));
		sb.append(String
				.format("\t%s : The file that contains the list of stop words. Defaults to '%s'.%n",
						Constants.STOP_WORD_FILE_FLAG,
						Constants.DEFAULT_STOPWORD_FILE));
		System.out.println(sb.toString());
	}
}
