package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;

public class Log {
	private static final int DEFAULT_HEIGHT = 300;
	private static final int DEFAULT_WIDTH = 400;
	private final JDialog dialog;
	private final String TITLE = "Log";
	private final String BUTTON_CAPTION = "Close";
	private JPanel contentPane;
	private JTextArea textArea;
	
	public Log() {
		dialog = new JDialog();
		dialogInit();
	}
	
	private void dialogInit() {
		dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		dialog.setTitle(TITLE);
		dialog.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		contentPane = new JPanel();
		dialog.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea);
		contentPane.add(sp, BorderLayout.CENTER);
		JButton button = new JButton(BUTTON_CAPTION);
		button.addActionListener(new CloseButtonActionListener());
		contentPane.add(button, BorderLayout.SOUTH);
	}
	
	public void setVisible(final boolean visible) {
		dialog.setVisible(visible);
	}
	
	public void log(final String message) {
		textArea.append(message + System.lineSeparator());
		textArea.setCaretPosition(textArea.getText().length());
	}
	
	class CloseButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dialog.setVisible(false);
		}
	}

}
