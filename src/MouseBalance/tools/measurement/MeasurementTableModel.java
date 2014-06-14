package MouseBalance.tools.measurement;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import MouseBalance.materials.Measurement;

public class MeasurementTableModel extends AbstractTableModel
{
	private List<Measurement> _measurements;
	
	public void setmeasurements(List<Measurement> measurements)
	{
		_measurements = measurements;
	}

	@Override
	public int getColumnCount()
	{
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
