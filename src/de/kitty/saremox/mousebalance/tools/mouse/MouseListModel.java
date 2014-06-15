package de.kitty.saremox.mousebalance.tools.mouse;

import java.util.List;

import javax.swing.AbstractListModel;

import de.kitty.saremox.mousebalance.materials.Mouse;

public class MouseListModel extends AbstractListModel<Mouse> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3010751240524831350L;
	private List<Mouse> _mouse;

	@Override
	public Mouse getElementAt(int row) {
		return _mouse.get(row);
	}

	@Override
	public int getSize() {
		return _mouse.size();
	}

	public void setmouse(List<Mouse> _mouse) {
		this._mouse = _mouse;
		this.fireIntervalAdded(this, 0, getSize());
	}

}
