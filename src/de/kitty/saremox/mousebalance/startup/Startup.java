package de.kitty.saremox.mousebalance.startup;

import java.util.Date;

import de.kitty.saremox.mousebalance.materials.Mouse;
import de.kitty.saremox.mousebalance.tools.MainUi;
import de.kitty.saremox.mousebalance.tools.measurement.MeasurementTool;
import de.kitty.saremox.mousebalance.tools.mouse.MouseTool;

public class Startup {
	public static void main(String[] args) {
		MouseTool _mtool = new MouseTool();
		MeasurementTool _measureTool = new MeasurementTool(_mtool);
		_mtool.addMouse(new Mouse("Mocca", new Date(10000000000000l), "Brown"));

		_mtool.addMouse(new Mouse("Mocca", new Date(10000000000000l), "Brown"));
		new MainUi(_mtool.getUiPanel(), _measureTool.getUiPanel());

	}
}
