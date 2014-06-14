package MouseBalance.tools.measurement;

import java.util.Observable;
import java.util.Observer;

import MouseBalance.service.MeasurementService;

public class MeasurementTool extends Observable implements Observer
{
	private MeasurementService _service;
	private MeasurementTableModel _measuremodel;
}
