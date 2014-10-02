package by.bsuir.iit.abramov.giis.GGE.main;

public class Config {
	public static int DEFAULT_SCALE = 3;
	public static final int MAX_SCALE = DEFAULT_SCALE * 5;
	public static final int MIN_SCALE = 1;
	public static final int PIXELS_PER_SCALE = 3;
	
	public static void incScale() {
		if (DEFAULT_SCALE == MIN_SCALE) {
			DEFAULT_SCALE = PIXELS_PER_SCALE;
			return;
		}
		if (DEFAULT_SCALE + PIXELS_PER_SCALE <= MAX_SCALE) {
			DEFAULT_SCALE += PIXELS_PER_SCALE;
		}
	}
	
	public static void decScale() {
		if (DEFAULT_SCALE - PIXELS_PER_SCALE >= 0) {
			DEFAULT_SCALE -= PIXELS_PER_SCALE;
			if (DEFAULT_SCALE < MIN_SCALE) {
				DEFAULT_SCALE = MIN_SCALE;
			}
		}
	}
}
