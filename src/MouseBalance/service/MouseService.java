package MouseBalance.service;

import java.util.LinkedList;
import java.util.List;

import MouseBalance.materials.Mouse;

public class MouseService
{
	List<Mouse> _mouseList;
	
	public MouseService()
	{
		_mouseList = new LinkedList<Mouse>();
	}
	
	public void addMouse(Mouse sweetMouse)
	{
		_mouseList.add(sweetMouse);
	}
}
