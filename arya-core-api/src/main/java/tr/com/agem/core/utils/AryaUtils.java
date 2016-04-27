package tr.com.agem.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AryaUtils {

	@SafeVarargs
	public static <T> String join(String separator, T... objects) {
		if (objects != null) {
			StringBuilder sb = new StringBuilder();
			String sep = "";
			for (Object o : objects) {
				sb.append(sep).append(o.toString());
				sep = separator;
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * Returns the first value in the array which is not null. If all the values
	 * are null or the array is null or empty then null is returned.
	 * 
	 * @param params
	 * @return
	 */
	@SafeVarargs
	public static <T> T firstNonNull(T... params) {
		if (params != null) {
			for (T o : params) {
				if (o != null) {
					return o;
				}
			}
		}
		return null;
	}

	public static String capitalize(final String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;
		if (o instanceof String)
			return "".equals(o) ? true : false;
		if (o instanceof Collection<?>)
			return ((Collection<?>) o).size() < 1 ? true : false;
		return false;
	}

	public static boolean isNotEmpty(Object o) {
		if (isEmpty(o))
			return false;
		return true;
	}

	/**
	 * Splits the provided text into an array, separators specified. This is an
	 * alternative to using StringTokenizer. The separator is not included in
	 * the returned String array. Adjacent separators are treated as one
	 * separator. For more control over the split use the StrTokenizer class. A
	 * null input String returns null. A null separatorChars splits on
	 * whitespace.
	 * 
	 * @param str
	 * @param separatorChars
	 * @return
	 */
	public static String[] split(String str, String separatorChars) {
		int max = -1;
		boolean preserveAllTokens = false;
		if (str == null) {
			return null;
		}
		final int len = str.length();
		if (len == 0) {
			return new String[] {};
		}
		final List<String> list = new ArrayList<String>();
		int sizePlus1 = 1;
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (separatorChars == null) {
			// Null separator means use whitespace
			while (i < len) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		} else if (separatorChars.length() == 1) {
			// Optimize 1 character case
			final char sep = separatorChars.charAt(0);
			while (i < len) {
				if (str.charAt(i) == sep) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		} else {
			// standard case
			while (i < len) {
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		}
		if (match || preserveAllTokens && lastMatch) {
			list.add(str.substring(start, i));
		}
		return list.toArray(new String[list.size()]);
	}

	public static boolean isExtension(final String filename, final String[] extensions) {
		if (filename == null) {
			return false;
		}
		if (extensions == null || extensions.length == 0) {
			return (indexOfExtension(filename) == -1);
		}
		String fileExt = getExtension(filename);
		for (int i = 0; i < extensions.length; i++) {
			if (fileExt.equals(extensions[i])) {
				return true;
			}
		}
		return false;
	}

	public static String getExtension(final String filename) {
		if (filename == null) {
			return null;
		}
		int index = indexOfExtension(filename);
		if (index == -1) {
			return "";
		} else {
			return filename.substring(index + 1);
		}
	}

	private static final char EXTENSION_SEPARATOR = '.';
	private static final char UNIX_SEPARATOR = '/';
	private static final char WINDOWS_SEPARATOR = '\\';

	public static int indexOfExtension(final String filename) {
		if (filename == null) {
			return -1;
		}
		int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
		int lastSeparator = indexOfLastSeparator(filename);
		return (lastSeparator > extensionPos ? -1 : extensionPos);
	}

	public static int indexOfLastSeparator(final String filename) {
		if (filename == null) {
			return -1;
		}
		int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
		int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
		return Math.max(lastUnixPos, lastWindowsPos);
	}
	

	public static void logException(Logger logger, Exception e) 
	{
		e.printStackTrace();
		if (logger != null) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

}
