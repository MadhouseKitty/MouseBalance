package MouseBalance.tools.mouse;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import MouseBalance.materials.Mouse;
import MouseBalance.service.MouseService;

public class MouseTool extends Observable implements Observer
{
	private MouseService _service;
	private MouseUi _ui;
	private MouseListModel _model;
	@Override
	public void update(Observable o, Object arg)
	{
		System.out.println("Blub");
		_model.setmouse(_service.getMouseList());
		_ui.get_panel().repaint();
	}
	public MouseTool()
	{
		_service = new MouseService();
		_service.addObserver(this);
		_model = new MouseListModel();
		_model.setmouse(_service.getMouseList());
		_ui = new MouseUi(_model,this);
	}
	
	public void addMouse(Mouse mice)
	{
		_service.addMouse(mice);
	}
	
	public void removeMouse(Mouse mice)
	{
		_service.removeMouse(mice);
	}
	
	public Mouse getSelectedMouse()
	{
		return _ui.getSelectedMouse();
	}
	
	public JPanel getUiPanel()
	{
		return _ui.get_panel();
	}
}
