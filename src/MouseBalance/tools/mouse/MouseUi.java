package MouseBalance.tools.mouse;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MouseBalance.materials.Mouse;
import MouseBalance.tools.DialogTool;

public class MouseUi extends Observable {
	class MouseListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			setChanged();
			notifyObservers();
		}

	}

	class NewMouseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Mouse mouse = DialogTool.newMouseDialog();
			if (mouse != null) {
				_tool.addMouse(mouse);
			}
		}

	}

	private MouseTool _tool;
	private JList<Mouse> _list;

	private JPanel _panel;

	private JButton _newMouseButton;

	public MouseUi(MouseListModel _model, MouseTool tool) {
		super();
		_tool = tool;
		_list = new JList<>(_model);
		_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_list.addListSelectionListener(new MouseListSelectionListener());
		_newMouseButton = new JButton("Neue Maus");
		_newMouseButton.addActionListener(new NewMouseButtonListener());
		_panel = new JPanel();
		_panel.setLayout(new BorderLayout());
		_panel.add(_newMouseButton, BorderLayout.NORTH);
		_panel.add(new JScrollPane(_list), BorderLayout.CENTER);
	}

	public JPanel get_panel() {
		return _panel;
	}

	public Mouse getSelectedMouse() {
		return _list.getSelectedValue();
	}
}
