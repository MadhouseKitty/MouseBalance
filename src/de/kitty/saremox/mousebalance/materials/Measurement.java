package de.kitty.saremox.mousebalance.materials;

import java.util.Date;

public class Measurement {
	private final Weight _weight;
	private final Date _date;

	public Measurement(Weight _weight, Date _date) {
		this._weight = _weight;
		this._date = _date;
	}

	public Date getDate() {
		return _date;
	}

	public Weight getWeight() {
		return _weight;
	}

	public String toSaveString() {
		return Long.toHexString(_date.getTime())+"/"+Integer.toHexString(_weight.getWeight());
	}
	
	public static Measurement loadMeasurementString(String measurement)
	{
		try
		{
			String[] measurementString = measurement.split("/");
			long datetime 	= Long.decode("0x"+measurementString[0]);
			int weight 		= Integer.decode("0x"+measurementString[1]);
			return new Measurement(new Weight(weight), new Date(datetime));
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
