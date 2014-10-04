package by.bsuir.iit.abramov.giis.GGE.main;

public class Config {
	public static int CURRENT_SCALE = 1;
	public static final int PIXELS_PER_SCALE = 3;
	public static final int MAX_SCALE = PIXELS_PER_SCALE * 5;
	public static final int MIN_SCALE = 1;
	
	public static void incScale() {
		if (CURRENT_SCALE == MIN_SCALE) {
			CURRENT_SCALE = PIXELS_PER_SCALE;
			System.out.println("new Scale = " + CURRENT_SCALE);
			return;
		}
		if (CURRENT_SCALE + PIXELS_PER_SCALE <= MAX_SCALE) {
			CURRENT_SCALE += PIXELS_PER_SCALE;
			System.out.println("new Scale = " + CURRENT_SCALE);
		}
	}
	
	public static void decScale() {
		if (CURRENT_SCALE - PIXELS_PER_SCALE >= 0) {
			CURRENT_SCALE -= PIXELS_PER_SCALE;
			if (CURRENT_SCALE < MIN_SCALE) {
				CURRENT_SCALE = MIN_SCALE;
			}
			System.out.println("new Scale = " + CURRENT_SCALE);
		}
	}
}
