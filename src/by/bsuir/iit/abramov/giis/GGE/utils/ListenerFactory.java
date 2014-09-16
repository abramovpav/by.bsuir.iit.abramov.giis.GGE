package by.bsuir.iit.abramov.giis.GGE.utils;

import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.listeners.ExitMenuListener;

public class ListenerFactory {

	
	public static ActionListener getActionListener(EMenuItem item , 
			Controller controller) {
		
		switch(item)  {
		case NEW:
			return null;
		case CLOSE:
			return null;
		case EXIT:
			return new ExitMenuListener(controller);
		case OPEN:
			return null;
		default:
			return null;
		}
		
		
	}
}
