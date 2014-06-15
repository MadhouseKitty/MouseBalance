package de.kitty.saremox.mousebalance.service.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.lang.model.util.SimpleAnnotationValueVisitor6;

import de.kitty.saremox.mousebalance.materials.Mouse;
import de.kitty.saremox.mousebalance.service.MouseService;


public class MouseLoader {

	public static List<Mouse> loadMice()
	{		
		LinkedList<Mouse> mouseList = new LinkedList<>();
		
		try (BufferedReader savedMice = new BufferedReader(new FileReader("../savedmice.txt")))
		{
			String mouseString;
			
			while ((mouseString = savedMice.readLine()) != null)
			{
				if(mouseString.isEmpty())
				{
					continue;
				}
				
				String [] mouseStringArray = mouseString.split("/");
				try
				{
					mouseList.add(new Mouse(mouseStringArray[0], new SimpleDateFormat().parse(mouseStringArray[1]), mouseStringArray[2]));
				}
				catch (ParseException e)
				{
					
				}
			}			
		} 		
		catch (IOException e) 		
		{
			e.printStackTrace();
		}
		return mouseList;
	}
	
}
