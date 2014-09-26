package by.bsuir.iit.abramov.giis.GGE.utils;

import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.listeners.SegmentActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.SegmentBrezenhemActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.SegmentDDAActionListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.ExitMenuListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NewMenuListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.NoneActionListener;

public class ListenerFactory {

	
	public static ActionListener getActionListener(EMenuItem item , 
			Controller controller) {
		
		switch(item)  {
		case NEW:
			return new NewMenuListener(controller);
		case CLOSE:
			return null;
		case EXIT:
			return new ExitMenuListener(controller);
		case OPEN:
			return null;
		case SEGMENT:
			return new SegmentActionListener(controller);
		case SEGMENT_DDA:
			return new SegmentDDAActionListener(controller);
		case SEGMENT_BREZENHEM:
			return new SegmentBrezenhemActionListener(controller);
		case NONE:
			return new NoneActionListener(controller);
		default:
			return null;
		}
		
		
	}
}
