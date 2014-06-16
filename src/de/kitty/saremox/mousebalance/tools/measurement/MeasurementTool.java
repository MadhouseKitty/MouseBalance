package de.kitty.saremox.mousebalance.tools.measurement;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.service.MeasurementService;
import de.kitty.saremox.mousebalance.tools.mouse.MouseTool;

public class MeasurementTool extends Observable implements Observer {
	private MeasurementService _service;
	private MeasurementTableModel _measuremodel;
	private MeasurementUi _ui;
	private MouseTool _mtool;

	public MeasurementTool(MouseTool mtool) {
		_mtool = mtool;
		_mtool.addObserver(this);
		_service = new MeasurementService(mtool);
		_service.addObserver(this);
		_measuremodel = new MeasurementTableModel();
		_measuremodel.setmeasurements(_service.getListForMouse(null));
		_ui = new MeasurementUi(_measuremodel, this);
	}

	public void addMeasurement(Measurement measurement) {
		_service.addMeasurement(_mtool.getSelectedMouse(), measurement);
	}

	public JPanel getUiPanel() {
		return _ui.get_panel();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		_measuremodel.setmeasurements(_service.getListForMouse(_mtool
				.getSelectedMouse()));
		setChanged();
		if(_mtool.getSelectedMouse() != null)
		{
			notifyObservers(new Boolean(true));
		}
		notifyObservers(new Boolean(false));
	}
}
