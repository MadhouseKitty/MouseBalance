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

	public MouseListModel(List<Mouse> _mouse) {
		super();
		this._mouse = _mouse;
	}

	
	@Override
	public Mouse getElementAt(int row) {
		return _mouse.get(row);
	}

	@Override
	public int getSize() {
		return _mouse.size();
	}

	public void setmouse(List<Mouse> _mouse) {
		int sizeDif = this._mouse.size() - _mouse.size();
		this._mouse = _mouse;
		if(sizeDif > 0)
		{
			this.fireIntervalRemoved(this,this._mouse.size() , this._mouse.size());
		}
		else if(sizeDif == 0)
		{
			this.fireContentsChanged(this, 0, getSize());
		}
		else
		{
			this.fireIntervalAdded(this,this._mouse.size() , this._mouse.size());
		}
		
	}
}
