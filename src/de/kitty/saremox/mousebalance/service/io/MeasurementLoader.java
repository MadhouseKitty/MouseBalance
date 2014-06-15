package de.kitty.saremox.mousebalance.service.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.materials.Mouse;
import de.kitty.saremox.mousebalance.materials.Weight;

public class MeasurementLoader {
	public static List<Measurement> loadMeasurements(Mouse mouse)
	{
		ArrayList<Measurement> measurements = new ArrayList<>();
		try(BufferedReader measurementReader = new BufferedReader(new FileReader(mouse.getName()+".mice")))
		{
			DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			String str;
			while((str = measurementReader.readLine()) != null)
			{
				if(str.isEmpty())
				{
					continue;
				}
				
				String[] measurementString = str.split("/");
				try
				{
					measurements.add(new Measurement(new Weight(Integer.parseInt(measurementString[0])), format.parse(measurementString[1])));
				}
				catch(NumberFormatException e)
				{
					
				}
				catch(ParseException e)
				{
					
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return measurements;
	}
}
