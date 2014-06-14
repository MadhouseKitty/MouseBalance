package MouseBalance.tools.measurement;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class MeasurementUi implements Observer
{
	private MeasurementTableModel _tablemodel;
	private JPanel _panel;

	public MeasurementUi(MeasurementTableModel _tablemodel)
	{
		super();
		this._tablemodel = _tablemodel;
		JTable table = new JTable(_tablemodel);
		_panel = new JPanel();
		_panel.setLayout(new BorderLayout());
		_panel.add(new JScrollPane(table),BorderLayout.CENTER);
	}
	
	public JPanel get_panel()
	{
		return _panel;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		
	}
}
