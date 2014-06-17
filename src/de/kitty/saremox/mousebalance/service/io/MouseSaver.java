package de.kitty.saremox.mousebalance.service.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import de.kitty.saremox.mousebalance.materials.Mouse;


public class MouseSaver {

	public void saveMice(List<Mouse> _mouseList) {
		
		try (FileWriter mouselist = new FileWriter("mice.list"))
		{
			for(Mouse mouse : _mouseList)
			{
				mouselist.write(mouse.getSaveString()+"\n");
				
			}
		}
		catch (IOException e) 		
		{
			e.printStackTrace();
		}
	}
	
	public static void saveMouse(Mouse mouse) {
		
		try(FileWriter savedMice = new FileWriter("mice.list", true))
		{
			savedMice.write(mouse.getSaveString()+"\n");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
