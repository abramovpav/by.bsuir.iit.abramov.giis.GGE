package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class AboutDialog extends JDialog {
	class CloseButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent arg0) {
			setVisible(false);
		}
	}

	private static final long	serialVersionUID	= 1L;

	private JTextArea			info;

	public AboutDialog(final JFrame parent) {
		super();
		init();
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void append(final String msg) {
		info.append(msg + System.lineSeparator());
	}

	private void init() {
		getContentPane().setLayout(new BorderLayout());
		info = new JTextArea();
		info.setEditable(false);
		add(info, BorderLayout.CENTER);
		append("Authors:");
		append("Pavel Abramov <abramovpav@gmail.com>");
		append("Christina Drevnitskaya <drevnitskayachris@gmail.com>");
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new CloseButtonActionListener());
		add(closeButton, BorderLayout.SOUTH);
		pack();
	}
}
