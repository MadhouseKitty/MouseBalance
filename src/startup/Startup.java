package startup;

import java.util.Date;

import MouseBalance.materials.Mouse;
import MouseBalance.tools.MainUi;
import MouseBalance.tools.measurement.MeasurementTool;
import MouseBalance.tools.mouse.MouseTool;

public class Startup {
	public static void main(String[] args) {
		MouseTool _mtool = new MouseTool();
		MeasurementTool _measureTool = new MeasurementTool(_mtool);
		_mtool.addMouse(new Mouse("Mocca", new Date(10000000000000l), "Brown"));

		_mtool.addMouse(new Mouse("Mocca", new Date(10000000000000l), "Brown"));
		new MainUi(_mtool.getUiPanel(), _measureTool.getUiPanel());

	}
}
