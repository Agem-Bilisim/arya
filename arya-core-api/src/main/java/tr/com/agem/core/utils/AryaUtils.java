package tr.com.agem.core.utils;

public class AryaUtils {

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

}
