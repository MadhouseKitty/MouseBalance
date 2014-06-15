package de.kitty.saremox.mousebalance.tools.mouse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.kitty.saremox.mousebalance.materials.Mouse;
import de.kitty.saremox.mousebalance.tools.DialogTool;

public class MouseUi extends Observable {
	class MouseListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			setChanged();
			notifyObservers();
			if(getSelectedMouse() != null)
			{
				_removeMouseButton.setEnabled(true);;
			}
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
	
	class RemoveMouseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int result = JOptionPane.showConfirmDialog(null, "Willst du "+getSelectedMouse().getName()+
														" Wirklich löschen?", "Lösch bestatigung", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.OK_OPTION)
			{
				_tool.removeMouse(getSelectedMouse());
			}
		}
		
	}

	private MouseTool _tool;
	private JList<Mouse> _list;

	private JPanel _panel,_buttonPanel;

	private JButton _newMouseButton;
	private JButton _removeMouseButton;

	public MouseUi(MouseListModel _model, MouseTool tool) {
		super();
		_tool = tool;
		_list = new JList<>(_model);
		_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		_list.addListSelectionListener(new MouseListSelectionListener());
		_newMouseButton = new JButton("Neue Maus");
		_newMouseButton.addActionListener(new NewMouseButtonListener());
		_removeMouseButton = new JButton("Maus entfernen");
		_removeMouseButton.addActionListener(new RemoveMouseButtonListener());
		_removeMouseButton.setEnabled(false);;
		_buttonPanel = new JPanel();
		_buttonPanel.setLayout(new FlowLayout());
		_buttonPanel.add(_newMouseButton);
		_buttonPanel.add(_removeMouseButton);
		_panel = new JPanel();
		_panel.setLayout(new BorderLayout());
		_panel.add(_buttonPanel, BorderLayout.NORTH);
		_panel.add(new JScrollPane(_list), BorderLayout.CENTER);
	}

	public JPanel get_panel() {
		return _panel;
	}

	public Mouse getSelectedMouse() {
		return _list.getSelectedValue();
	}
}
