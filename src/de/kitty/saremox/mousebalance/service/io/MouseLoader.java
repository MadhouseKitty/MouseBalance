package de.kitty.saremox.mousebalance.service.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.kitty.saremox.mousebalance.materials.Mouse;


public class MouseLoader {

	public static List<Mouse> loadMice()
	{		
		LinkedList<Mouse> mouseList = new LinkedList<>();
		
		try (BufferedReader savedMice = new BufferedReader(new FileReader("mice.list")))
		{
			String mouseString;
			
			while ((mouseString = savedMice.readLine()) != null)
			{
				if(mouseString.isEmpty())
				{
					continue;
				}
				Mouse mouse = Mouse.loadMouseString(mouseString);
				if(mouse != null)
				{
					mouseList.add(mouse);
				}
			}			
		} 		
		catch (IOException e) 		
		{
            // Save File not Found
		}
		return mouseList;
	}
	
}
