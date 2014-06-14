package MouseBalance.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import MouseBalance.materials.Mouse;

public class MouseService extends Observable
{
	List<Mouse> _mouseList;
	
	public MouseService()
	{
		_mouseList = new LinkedList<Mouse>();
	}
	
	public void addMouse(Mouse sweetMouse)
	{
		_mouseList.add(sweetMouse);
		this.setChanged();
		this.notifyObservers();
	}
}
