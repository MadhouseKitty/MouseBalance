package MouseBalance.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import MouseBalance.materials.Measurement;
import MouseBalance.materials.Mouse;

public class MeasurementService
{
	Map<Mouse,List<Measurement>> _mousemap;
	
	public MeasurementService()
	{
		_mousemap = new HashMap<>();
	}
	
	public void addMeasurement(Mouse sweetMouse, Measurement measurement)
	{
		_mousemap.get(sweetMouse).add(measurement);
	}
}
