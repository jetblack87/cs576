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

	/**
	 * Creates a {@link KwicEntry} with the given keyword, beforeKeyword and
	 * afterKeyword.
	 * 
	 * @param keyword
	 *            the keyword for this entry
	 * @param beforeKeyword
	 *            the text before the keyword
	 * @param afterKeyword
	 *            the text after the keyword
	 */
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
		String beforeKeywordString = "".equals(beforeKeyword) ? "" : beforeKeyword + " ";
		String afterKeywordString = "".equals(afterKeyword) ? "" : " " + afterKeyword;
		return String
				.format("%s[%s]%s", beforeKeywordString, keyword, afterKeywordString);
	}

	public String toHtmlRow() {
		return String
				.format("<TR><TD ALIGN='right'>%s</TD><TD><B>%s</B></TD><TD>%s</TD></TR>",
						beforeKeyword, keyword, afterKeyword);
	}

	private static String join(String separator, List<String> strings) {
		if (strings.isEmpty()) {
			return "";
		} else if (1 == strings.size()) {
			return strings.get(0);
		} else {
			final StringBuffer sb = new StringBuffer(strings.get(0));
			for (int i = 1; i < strings.size(); i++) {
				sb.append(separator);
				sb.append(strings.get(i));
			}
			return sb.toString();
		}
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
