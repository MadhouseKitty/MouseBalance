package de.kitty.saremox.mousebalance.materials;

import java.util.Date;

public class Mouse {
	private final String _name;
	private final Date _birthday;
	private final String _colour;

	public Mouse(String name, Date birth, String colour) {
		_name = name;
		_birthday = birth;
		_colour = colour;
	}

	public Date getBirthday() {
		return _birthday;
	}

	public String getColour() {
		return _colour;
	}

	public String getName() {
		return _name;
	}

	@Override
	public String toString() {
		return _name;
	}
}
