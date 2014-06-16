package de.kitty.saremox.mousebalance.tools.measurement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.tools.DialogTool;

public class MeasurementUi implements Observer
{
	private class newMeasurementActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Measurement measurement = DialogTool.newMeasurementDialog();
			if (measurement != null)
			{
				_mtool.addMeasurement(measurement);
			}
		}

	}

	private MeasurementTool _mtool;
	
	private JPanel _panel, _buttonPanel;
	private JButton _newMeasurementButton;

	private JMenu _measuremtMenu;

	private JMenuItem _newMeasurementMT;

	public MeasurementUi(MeasurementTableModel _tablemodel, MeasurementTool _mtool)
	{
		super();
		this._mtool = _mtool;
		_mtool.addObserver(this);
		
		JTable table = new JTable(_tablemodel);
		
		_newMeasurementButton = new JButton("Neue Messung hinzufuegen");
		_newMeasurementButton.addActionListener(new newMeasurementActionListener());
		_newMeasurementButton.setEnabled(false);
		_buttonPanel = new JPanel();
		_buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		_buttonPanel.add(_newMeasurementButton);
		_panel = new JPanel();
		_panel.setLayout(new BorderLayout());
		_panel.add(new JScrollPane(table), BorderLayout.CENTER);
		_panel.add(_buttonPanel, BorderLayout.NORTH);
		
		this.setupMenu();
	}

	private void setupMenu()
	{
		_measuremtMenu = new JMenu("Messung");
		_newMeasurementMT = new JMenuItem("Neue Messung");
		_newMeasurementMT.addActionListener(new newMeasurementActionListener());
		_newMeasurementMT.setEnabled(false);
		_measuremtMenu.add(_newMeasurementMT);
		de.kitty.saremox.mousebalance.tools.MainUi.registerMenu(_measuremtMenu);
	}

	public JPanel get_panel()
	{
		return _panel;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof MeasurementTool && arg instanceof Boolean)
		{
			_newMeasurementMT.setEnabled(((Boolean) arg).booleanValue() );
			_newMeasurementButton.setEnabled(((Boolean) arg).booleanValue() );
		}
	}
}
