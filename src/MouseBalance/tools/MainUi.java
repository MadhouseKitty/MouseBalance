package MouseBalance.tools;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUi {
	private JFrame _frame;

	public MainUi(JPanel _mouselistpanel, JPanel _measurementstablepanel) {
		super();
		_frame = new JFrame("Maus Gewichts Stuff");
		_frame.add(_mouselistpanel, BorderLayout.WEST);
		_frame.add(_measurementstablepanel, BorderLayout.CENTER);
		_frame.setSize(1280, 720);
		_frame.setVisible(true);
	}

}
