package de.kitty.saremox.mousebalance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.materials.Mouse;
import de.kitty.saremox.mousebalance.service.io.MeasurementLoader;
import de.kitty.saremox.mousebalance.service.io.MeasurementSaver;

public class MeasurementService extends Observable {
	Map<Mouse, List<Measurement>> _mousemap;

	public MeasurementService() {
		_mousemap = new HashMap<>();
	}

	public void addMeasurement(Mouse sweetMouse, Measurement measurement) {
		if (!_mousemap.containsKey(sweetMouse)) {
			_mousemap.put(sweetMouse, new ArrayList<Measurement>());
		}
		_mousemap.get(sweetMouse).add(measurement);
		MeasurementSaver.saveMeasurement(sweetMouse, measurement);
		setChanged();
		notifyObservers();
	}

	public List<Measurement> getListForMouse(Mouse mice) {
		if (_mousemap.containsKey(mice)) {
			return new ArrayList<>(_mousemap.get(mice));
		}
		return new ArrayList<>();
	}
	
	final void mouseLoadedEvent(Mouse mouse)
	{
		// A Mouse have been added due to a load
		// We should now add Every measurement of the mice file
		_mousemap.put(mouse, MeasurementLoader.loadMeasurements(mouse));
	}
}
