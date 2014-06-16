package de.kitty.saremox.mousebalance.tools.mouse;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
														" Wirklich loeschen?", "Loesch bestatigung", JOptionPane.YES_NO_OPTION);
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

    private JMenu _mouseMenu;

    private JMenuItem _newMouseMT,_removeMouseMT;

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
		this.setupMenu();
		this.setupPopupMenu();
	}

    private void setupMenu()
    {
        _mouseMenu = new JMenu("Maus");
        _newMouseMT = new JMenuItem("Neue Maus");
        _newMouseMT.addActionListener(new NewMouseButtonListener());
        _removeMouseMT = new JMenuItem("Maus Entfernen");
        _removeMouseMT.addActionListener(new RemoveMouseButtonListener());
        _mouseMenu.add(_newMouseMT);
        _mouseMenu.add(_removeMouseMT);
        de.kitty.saremox.mousebalance.tools.MainUi.registerMenu(_mouseMenu);
    }
    
    private void setupPopupMenu()
    {
    	final JPopupMenu menu = new JPopupMenu();
    	JMenuItem newMouseMT = new JMenuItem("Neue Maus");
        newMouseMT.addActionListener(new NewMouseButtonListener());
        JMenuItem removeMouseMT = new JMenuItem("Maus Entfernen");
        removeMouseMT.addActionListener(new RemoveMouseButtonListener());
        menu.add(newMouseMT);
        menu.add(removeMouseMT);
        
    	_list.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                	_list.setSelectedIndex(getRow(e.getPoint()));
                    
                    menu.show(_list, 5, e.getY());
                }
            }

            private int getRow(Point point)
            {
               return _list.locationToIndex(point);
       }

        });
    }

	public JPanel get_panel() {
		return _panel;
	}

	public Mouse getSelectedMouse() {
		return _list.getSelectedValue();
	}
}
