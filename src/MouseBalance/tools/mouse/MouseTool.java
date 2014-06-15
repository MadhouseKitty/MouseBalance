package MouseBalance.tools.mouse;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import MouseBalance.materials.Mouse;
import MouseBalance.service.MouseService;

public class MouseTool extends Observable implements Observer {
	private MouseService _service;
	private MouseUi _ui;
	private MouseListModel _model;

	public MouseTool() {
		_service = new MouseService();
		_service.addObserver(this);
		_model = new MouseListModel();
		_model.setmouse(_service.getMouseList());
		_ui = new MouseUi(_model, this);
		_ui.addObserver(this);
	}

	public void addMouse(Mouse mice) {
		_service.addMouse(mice);
	}

	public Mouse getSelectedMouse() {
		return _ui.getSelectedMouse();
	}

	public JPanel getUiPanel() {
		return _ui.get_panel();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof MouseUi) {
			setChanged();
			notifyObservers();
		} else {
			_model.setmouse(_service.getMouseList());
		}
	}
}
