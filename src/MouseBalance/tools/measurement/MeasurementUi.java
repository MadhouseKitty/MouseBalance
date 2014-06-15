package MouseBalance.tools.measurement;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import MouseBalance.materials.Measurement;
import MouseBalance.tools.DialogTool;

public class MeasurementUi implements Observer
{
	private MeasurementTableModel _tablemodel;
	private JPanel _panel;
	private JButton _newMeasurementButton;
	private MeasurementTool _mtool;

	public MeasurementUi(MeasurementTableModel _tablemodel, final MeasurementTool _mtool)
	{
		super();
		this._tablemodel = _tablemodel;
		this._mtool = _mtool;
		JTable table = new JTable(_tablemodel);
		
		_newMeasurementButton = new JButton("Neue Messung hinzufuegen");
		_newMeasurementButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Measurement measurement = DialogTool.newMeasurementDialog();
				if(measurement != null)
					_mtool.addMeasurement(measurement);
			}
		});
		
		_panel = new JPanel();
		_panel.setLayout(new BorderLayout());
		_panel.add(new JScrollPane(table),BorderLayout.CENTER);
		_panel.add(_newMeasurementButton,BorderLayout.NORTH);
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
