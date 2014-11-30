package by.bsuir.iit.abramov.giis.GGE.main;

public class Config {
	public static boolean decScale() {
		if (CURRENT_SCALE - PIXELS_PER_SCALE >= 0) {
			PREV_SCALE = CURRENT_SCALE;
			CURRENT_SCALE -= PIXELS_PER_SCALE;
			if (CURRENT_SCALE < MIN_SCALE) {
				CURRENT_SCALE = MIN_SCALE;
			}
			System.out.println("new Scale = " + CURRENT_SCALE);
			return true;
		}
		return false;
	}

	public static boolean incScale() {
		if (CURRENT_SCALE + PIXELS_PER_SCALE <= MAX_SCALE) {
			PREV_SCALE = CURRENT_SCALE;
			CURRENT_SCALE += PIXELS_PER_SCALE;
			System.out.println("new Scale = " + CURRENT_SCALE);
			return true;
		}
		return false;
	}

	public static int getHalfScale() {
		return CURRENT_SCALE / 2;
	}

	public static int 		PREV_SCALE 			= 4;
	public static int		CURRENT_SCALE		= 4;
	public static final int	PIXELS_PER_SCALE	= 2;

	public static final int	MAX_SCALE			= PIXELS_PER_SCALE * 20;

	public static final int	MIN_SCALE			= 4;
}
