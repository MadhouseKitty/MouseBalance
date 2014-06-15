package de.kitty.saremox.mousebalance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.materials.Mouse;

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
		setChanged();
		notifyObservers();
	}

	public List<Measurement> getListForMouse(Mouse mice) {
		if (_mousemap.containsKey(mice)) {
			return new ArrayList<>(_mousemap.get(mice));
		}
		return new ArrayList<>();
	}
}
