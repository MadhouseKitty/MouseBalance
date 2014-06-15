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
		return _date.toString()+"/"+_weight;
	}
}
