package de.kitty.saremox.mousebalance.materials;

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
	
	private Mouse(long uid, String name, long birthtime, String colour, long creatime)
	{
		_myUID = uid;
		_name = name;
		_birthday = new Date(birthtime);
		_colour = colour;
		_createTime = creatime;
		if(_myUID > _nextUID)
		{
			_nextUID = _myUID+1;
		}
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
	
	public String getSaveString() {
		return 		Long.toHexString(_myUID)+"/"
					+_name + "/"
					+ Long.toHexString(_birthday.getTime()) + "/" 
					+ _colour + "/" 
					+ Long.toHexString(_createTime);
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
	
	public static Mouse loadMouseString(String mouse)
	{
		try {
			String [] mouseStringArray = mouse.split("/");
			long uid 			= Long.decode("0x"+mouseStringArray[0]);
			long birthdayTime 	= Long.decode("0x"+mouseStringArray[2]);
			long createtime 	= Long.decode("0x"+mouseStringArray[4]);
			String name 		= mouseStringArray[1];
			String colour 		= mouseStringArray[3];
			return new Mouse(uid,name,birthdayTime,colour,createtime);
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
