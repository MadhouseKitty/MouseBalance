package de.kitty.saremox.mousebalance.service.io;

import java.io.FileWriter;
import java.io.IOException;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.materials.Mouse;

public class MeasurementSaver {
	
	public void saveMeasurement(Mouse mouse,Measurement measurement)
	{
		try(FileWriter measurementWriter = new FileWriter(mouse.getName()+".mice", true))
		{
			measurementWriter.write(measurement.toSaveString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
