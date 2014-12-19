package by.bsuir.iit.abramov.giis.GGE.utils;

import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.listeners.AboutListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.BSplainActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.BezierFormActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.CircleActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.ExitMenuListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.FormActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.LastActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.LineBrezenhemActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.LineDDAActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.LineWyActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NewMenuListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NextActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NoneActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.PrevActionListener;
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
		case ABOUT:
			return new AboutListener(controller);
		case NEXT:
			return new NextActionListener(controller);
		case PREV:
			return new PrevActionListener(controller);
		case LAST:
			return new LastActionListener(controller);
		case ERMIT_FORM:
			return new FormActionListener(controller);
		case BSPLAIN:
			return new BSplainActionListener(controller);
		case BEZIER_FORM:
			return new BezierFormActionListener(controller);
		case CIRCLE:
			return new CircleActionListener(controller);
		default:
			return null;
		}
	}
}
