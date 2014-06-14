package MouseBalance.materials;


public class Mouse
{
	private final String _name;
	private final Date _birthday;
	private final String _colour;
	
	public Mouse(String name, Date birthday, String colour)
	{
		_name = name;
		_birthday = birthday;
		_colour = colour;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public Date getBirthday()
	{
		return _birthday;
	}
	
	public String getColour()
	{
		return _colour;
	}
	
	
}
