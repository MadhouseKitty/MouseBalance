package MouseBalance.tools.mouse;

import java.util.List;

import javax.swing.AbstractListModel;

import MouseBalance.materials.Mouse;

public class MouseListModel extends AbstractListModel<Mouse>
{
	private List<Mouse> _mouse;
	
	@Override
	public Mouse getElementAt(int row) 
	{
		return _mouse.get(row);
	}

	@Override
	public int getSize() 
	{
		return _mouse.size();
	}

}
