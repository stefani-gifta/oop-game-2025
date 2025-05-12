package Util;

import java.util.Random;

public class Randomizer {
	
	static Random r = new Random();
	
	public static int randomInt(int min, int max) {
		return min + r.nextInt(max-min);
	}
	
	public static double random(int min, int max) {
		return Double.valueOf(randomInt(min, max));
	}

	public static char getRandomCharFrom(String optionString) {
		int index = r.nextInt(optionString.length());
		return optionString.charAt(index);
	}

	public static boolean randomBoolean() {
		return r.nextBoolean();
	}
	
}
