package de.kitty.saremox.mousebalance.materials;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.Adler32;

public class Mouse {
	private final String _name;
	private final Date _birthday;
	private final String _colour;
	private final long _createTime;
	private final long _myUID;
	private static long _nextUID = 1;

	public Mouse(String name, Date birth, String colour) {
		_name = name;
		_birthday = birth;
		_colour = colour;
		_createTime = System.nanoTime();
		_myUID = _nextUID;
		_nextUID++;
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
	
	public String saveString() {
		return Long.toHexString(_myUID)+"/"+_name + "/" + new SimpleDateFormat("dd.MM.yyyy").format(_birthday) + "/" + _colour + "/" + Long.toHexString(_createTime);
	}
	
	public int hashCode()
	{
		Adler32 adler = new Adler32();
		for(int i = 0; i<8;i++)
		{
			adler.update((int) _createTime>>(8*i));
			adler.update((int) _birthday.getTime()>>(8*i));
			adler.update((int) _myUID>>(8*i));
		}
		adler.update(_name.getBytes());
		adler.update(_colour.getBytes());
		return (int) adler.getValue();
	}
	
	public String getFileName()
	{
		return _name+"["+Integer.toHexString(hashCode())+"].mice";
	}
}
