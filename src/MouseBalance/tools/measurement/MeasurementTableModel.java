package MouseBalance.tools.measurement;

import java.util.List;
import java.util.Date;

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
		return 2;
	}

	@Override
	public String getColumnName(int index)
	{
		switch(index)
		{
			case 0:
				return "Datum";
			case 1:
				return "Gewicht";
			default:
				return "";
		}
	}

	@Override
	public int getRowCount()
	{
		return _measurements.size();
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		switch (column)
		{
			case 0:
				return _measurements.get(row).getDate();
			case 1:
				return _measurements.get(row).getWeight();
			default:
				return null;
		}
	}

}
