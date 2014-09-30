package by.bsuir.iit.abramov.giis.GGE.utils;

import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.listeners.ExitMenuListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NewMenuListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NoneActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.SegmentBrezenhemActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.SegmentDDAActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.SegmentVyActionListener;

public class ListenerFactory {

	public static ActionListener getActionListener(final EMenuItem item,
			final Controller controller) {

		switch (item) {
		case NEW:
			return new NewMenuListener(controller);
		case CLOSE:
			return null;
		case EXIT:
			return new ExitMenuListener(controller);
		case OPEN:
			return null;
		case SEGMENT_DDA:
			return new SegmentDDAActionListener(controller);
		case SEGMENT_BREZENHEM:
			return new SegmentBrezenhemActionListener(controller);
		case SEGMENT_VY:
			return new SegmentVyActionListener(controller);
		case NONE:
			return new NoneActionListener(controller);
		default:
			return null;
		}
	}
}
