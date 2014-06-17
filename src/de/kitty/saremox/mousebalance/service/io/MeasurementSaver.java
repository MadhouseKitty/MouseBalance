package de.kitty.saremox.mousebalance.service.io;

import java.io.FileWriter;
import java.io.IOException;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.materials.Mouse;

public class MeasurementSaver
{

	public static void saveMeasurement(Mouse mouse, Measurement measurement)
	{
		try (FileWriter measurementWriter = new FileWriter(mouse.getFileName(),
				true))
		{
			measurementWriter.write(measurement.toSaveString() + "\n");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
