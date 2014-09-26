package by.bsuir.iit.abramov.giis.GGE.utils;

import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.listeners.EdgeActionListener;
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
		case EDGE:
			return new EdgeActionListener(controller);
		case NONE:
			return new NoneActionListener(controller);
		default:
			return null;
		}
		
		
	}
}
