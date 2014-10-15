package by.bsuir.iit.abramov.giis.GGE.utils;

import by.bsuir.iit.abramov.giis.GGE.graphic.Point;
import by.bsuir.iit.abramov.giis.GGE.view.Log;

public class Logger {
	public static void log(final Point point) {
		String message = pointOffset + "Point: x = " + point.getX() + ", y = " + point.getY();
		System.out.println(message);
		log.log(message);
	}

	public static void log(final String message) {
		System.out.println(message);
		log.log(message);
	}

	public static void showLog() {
		log.setVisible(true);
	}

	private static final String	pointOffset	= "    ";

	private static final Log	log			= new Log();
}
