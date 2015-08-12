package tr.com.agem.arya.metadata.util;

import java.util.Random;

public class RandomValueGenerator {

	private static final char[] symbols = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	private static final Random random = new Random();
	private char[] buf;

	public RandomValueGenerator(int length) {
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		buf = new char[length];
	}

	public String nextString() {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[random.nextInt(symbols.length)];
		return new String(buf);
	}

}
