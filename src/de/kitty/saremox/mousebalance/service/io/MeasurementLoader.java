package de.kitty.saremox.mousebalance.service.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.materials.Mouse;

public class MeasurementLoader {
	public static List<Measurement> loadMeasurements(Mouse mouse)
	{
		ArrayList<Measurement> measurements = new ArrayList<>();
		try(BufferedReader measurementReader = new BufferedReader(new FileReader(mouse.getFileName())))
		{
			String measurementString;
			while((measurementString = measurementReader.readLine()) != null)
			{
				if(measurementString.isEmpty())
				{
					continue;
				}
				Measurement mes;
				if((mes = Measurement.loadMeasurementString(measurementString)) != null)
				{
					measurements.add(mes);
				}
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return measurements;
	}
}
