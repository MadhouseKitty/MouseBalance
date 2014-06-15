package de.kitty.saremox.mousebalance.tools.measurement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.tools.DialogTool;

public class MeasurementUi implements Observer {
	private JPanel _panel;
	private JButton _newMeasurementButton;

	public MeasurementUi(MeasurementTableModel _tablemodel,
			final MeasurementTool _mtool) {
		super();
		JTable table = new JTable(_tablemodel);

		_newMeasurementButton = new JButton("Neue Messung hinzufuegen");
		_newMeasurementButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Measurement measurement = DialogTool.newMeasurementDialog();
				if (measurement != null) {
					_mtool.addMeasurement(measurement);
				}
			}
		});

		_panel = new JPanel();
		_panel.setLayout(new BorderLayout());
		_panel.add(new JScrollPane(table), BorderLayout.CENTER);
		_panel.add(_newMeasurementButton, BorderLayout.NORTH);
	}

	public JPanel get_panel() {
		return _panel;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
