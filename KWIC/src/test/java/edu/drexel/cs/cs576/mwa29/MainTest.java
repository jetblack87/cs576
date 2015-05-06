/**
 * JUnit tests used to test Main class
 */
package edu.drexel.cs.cs576.mwa29;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

import java.io.ByteArrayOutputStream;
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
}
