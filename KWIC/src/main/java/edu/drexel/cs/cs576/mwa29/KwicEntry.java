/**
 * 
 */
package edu.drexel.cs.cs576.mwa29;

import java.util.List;

/**
 * @author mark
 *
 */
public class KwicEntry implements Comparable<KwicEntry> {

	private final String keyword;
	private final String beforeKeyword;
	private final String afterKeyword;

	public KwicEntry(final String keyword, final String beforeKeyword,
			final String afterKeyword) {
		super();
		this.keyword = keyword;
		this.beforeKeyword = beforeKeyword;
		this.afterKeyword = afterKeyword;
	}

	public KwicEntry(final String keyword,
			final List<String> beforeKeywordList,
			final List<String> afterKeywordList) {
		this(keyword, join(" ", beforeKeywordList), join(" ", afterKeywordList));
	}

	@Override
	public String toString() {
		return String
				.format("%s [%s] %s", beforeKeyword, keyword, afterKeyword);
	}

	public String toHtmlRow() {
		return String.format(
				"<TR><TD>%s</TD><TD>%s</TD><TD><BOLD>%s</BOLD></TD></TR>",
				beforeKeyword, keyword, afterKeyword);
	}

	private static String join(String separator, List<String> strings) {
		final StringBuffer sb = new StringBuffer();
		for (String s : strings) {
			sb.append(s);
			sb.append(separator);
		}
		if (strings.size() > 0) {
			// Remove trailing separator
			sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((afterKeyword == null) ? 0 : afterKeyword.hashCode());
		result = prime * result
				+ ((beforeKeyword == null) ? 0 : beforeKeyword.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KwicEntry other = (KwicEntry) obj;
		if (afterKeyword == null) {
			if (other.afterKeyword != null)
				return false;
		} else if (!afterKeyword.equals(other.afterKeyword))
			return false;
		if (beforeKeyword == null) {
			if (other.beforeKeyword != null)
				return false;
		} else if (!beforeKeyword.equals(other.beforeKeyword))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		return true;
	}

	@Override
	public int compareTo(KwicEntry o) {
		return keyword.toLowerCase().compareTo(o.keyword.toLowerCase());
	}
}
