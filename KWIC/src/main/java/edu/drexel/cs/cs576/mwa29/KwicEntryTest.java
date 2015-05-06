/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author mark
 *
 */
public class KwicEntryTest {

	private static final List<String> EMPTY_STRING_LIST = Arrays
			.asList(new String[] {});

	@Test
	public void KwicEntry_equals_successfully() {
		KwicEntry one = new KwicEntry("keyword", "before text", "after text");
		KwicEntry two = new KwicEntry("keyword", Arrays.asList(new String[] {
				"before", "text" }), Arrays.asList(new String[] { "after",
				"text" }));
		assertEquals(one, two);
	}

	@Test
	public void KwicEntry_compareLessThan_successfully() {
		KwicEntry one = new KwicEntry("aaa", EMPTY_STRING_LIST,
				EMPTY_STRING_LIST);
		KwicEntry two = new KwicEntry("bbb", EMPTY_STRING_LIST,
				EMPTY_STRING_LIST);
		assertEquals(-1, one.compareTo(two));
	}

	@Test
	public void KwicEntry_compareGreaterThan_successfully() {
		KwicEntry one = new KwicEntry("bbb", EMPTY_STRING_LIST,
				EMPTY_STRING_LIST);
		KwicEntry two = new KwicEntry("aaa", EMPTY_STRING_LIST,
				EMPTY_STRING_LIST);
		assertEquals(1, one.compareTo(two));
	}

	@Test
	public void toString_successfully() {
		KwicEntry kwicEntry = new KwicEntry("keyword", "before", "after");
		assertEquals("before [keyword] after", kwicEntry.toString());
	}

	@Test
	public void join_beforeIsEmptyString_sucessfully() {
		KwicEntry kwicEntry = new KwicEntry("keyword", EMPTY_STRING_LIST,
				Arrays.asList(new String[] { "after" }));
		assertEquals("[keyword] after", kwicEntry.toString());
	}

	@Test
	public void join_afterIsEmptyString_sucessfully() {
		KwicEntry kwicEntry = new KwicEntry("keyword",
				Arrays.asList(new String[] { "before" }), EMPTY_STRING_LIST);
		assertEquals("before [keyword]", kwicEntry.toString());
	}

	@Test
	public void hasCode_sucessfully() {
		KwicEntry kwicEntry = new KwicEntry("keyword", "before", "after");
		assertNotEquals(0, kwicEntry.hashCode());
	}
}
