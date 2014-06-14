package MouseBalance.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import MouseBalance.materials.Measurement;
import MouseBalance.materials.Mouse;

public class MeasurementService extends Observable
{
	Map<Mouse,List<Measurement>> _mousemap;
	
	public MeasurementService()
	{
		_mousemap = new HashMap<>();
	}
	
	public void addMeasurement(Mouse sweetMouse, Measurement measurement)
	{
		_mousemap.get(sweetMouse).add(measurement);
		this.notifyObservers();
	}
	
	public List<Measurement> getListForMouse(Mouse mice)
	{
		return new ArrayList();
	}
}
