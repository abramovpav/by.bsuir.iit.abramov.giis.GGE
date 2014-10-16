package by.bsuir.iit.abramov.giis.GGE.utils;

import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.listeners.ExitMenuListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.LineBrezenhemActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.LineDDAActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.LineWyActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NewMenuListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NoneActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.ShowLogListener;

public class ListenerFactory {

	public static ActionListener getActionListener(final EMenuItem item, final Controller controller) {

		switch (item) {
		case NEW:
			return new NewMenuListener(controller);
		case CLOSE:
			return null;
		case EXIT:
			return new ExitMenuListener(controller);
		case OPEN:
			return null;
		case LINE_DDA:
			return new LineDDAActionListener(controller);
		case LINE_BREZENHEM:
			return new LineBrezenhemActionListener(controller);
		case LINE_WY:
			return new LineWyActionListener(controller);
		case NONE:
			return new NoneActionListener(controller);
		case LOG:
			return new ShowLogListener(controller);
		default:
			return null;
		}
	}
}
