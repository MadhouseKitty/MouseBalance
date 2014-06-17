package de.kitty.saremox.mousebalance.tools.measurement;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

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

	private JTabbedPane _mainPane;
	private JPanel _tablePanel, _buttonPanel;
	private JButton _newMeasurementButton;

	private MeasurementGraph _graph1m, _graph3m, _graph6m, _graph12m;

	private JMenu _measuremtMenu;

	private JMenuItem _newMeasurementMT;

	public MeasurementUi(MeasurementTableModel _tablemodel,
			MeasurementTool _mtool)
	{
		super();
		this._mtool = _mtool;
		_mtool.addObserver(this);

		JTable table = new JTable(_tablemodel);

		_newMeasurementButton = new JButton("Neue Messung hinzufuegen");
		_newMeasurementButton
				.addActionListener(new newMeasurementActionListener());
		_newMeasurementButton.setEnabled(false);
		_buttonPanel = new JPanel();
		_buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		_buttonPanel.add(_newMeasurementButton);
		_tablePanel = new JPanel();
		_tablePanel.setLayout(new BorderLayout());
		_tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
		_tablePanel.add(_buttonPanel, BorderLayout.NORTH);
		this.setupGraph();
		_mainPane = new JTabbedPane();
		_mainPane.add(_tablePanel, "Messungs Tabelle");
		_mainPane.add(_graph1m, "1 Monat");
		_mainPane.add(_graph3m, "3 Monate");
		_mainPane.add(_graph6m, "6 Monate");
		_mainPane.add(_graph12m, "12 Monate");
		this.setupMenu();
	}

	public Component getComponent()
	{
		return _mainPane;
	}

	public void setGraphList(List<Measurement> list)
	{
		_graph1m.set_measurements(list);
		_graph3m.set_measurements(list);
		_graph6m.set_measurements(list);
		_graph12m.set_measurements(list);
	}

	private void setupGraph()
	{
		_graph1m = new MeasurementGraph(new ArrayList<Measurement>(), 1);
		_graph1m.setDoubleBuffered(true);
		_graph3m = new MeasurementGraph(new ArrayList<Measurement>(), 3);
		_graph3m.setDoubleBuffered(true);
		_graph6m = new MeasurementGraph(new ArrayList<Measurement>(), 6);
		_graph6m.setDoubleBuffered(true);
		_graph12m = new MeasurementGraph(new ArrayList<Measurement>(), 12);
		_graph12m.setDoubleBuffered(true);
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

	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof MeasurementTool && arg instanceof Boolean)
		{
			_newMeasurementMT.setEnabled(((Boolean) arg).booleanValue());
			_newMeasurementButton.setEnabled(((Boolean) arg).booleanValue());
		}
	}
}
