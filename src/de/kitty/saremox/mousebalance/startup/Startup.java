package de.kitty.saremox.mousebalance.startup;

import de.kitty.saremox.mousebalance.tools.MainUi;
import de.kitty.saremox.mousebalance.tools.measurement.MeasurementTool;
import de.kitty.saremox.mousebalance.tools.mouse.MouseTool;

public class Startup {
	public static void main(String[] args) {
		MouseTool _mtool = new MouseTool();
		MeasurementTool _measureTool = new MeasurementTool(_mtool);
		new MainUi(_mtool.getUiPanel(), _measureTool.getUIComponent());
	}
}
