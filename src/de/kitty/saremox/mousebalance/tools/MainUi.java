package de.kitty.saremox.mousebalance.tools;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainUi {
	private JFrame _frame;
    private static JMenuBar _framebar;

	public MainUi(Component _mouselistpanel, Component _measurementstablepanel) {
		super();
		
        
        _frame = new JFrame("Maus Gewichts Stuff");
		_frame.add(_mouselistpanel, BorderLayout.WEST);
		_frame.add(_measurementstablepanel, BorderLayout.CENTER);
		_frame.setJMenuBar(_framebar);
        _frame.setSize(1280, 720);
		_frame.setVisible(true);
	}
    
    public static void registerMenu(JMenu menu)
    {
    	if(_framebar == null)
    	{
    		_framebar = new JMenuBar();
    	}
        _framebar.add(menu);
    }

    public static void removeMenu(JMenu menu)
    {
    	if(_framebar == null)
    	{
    		_framebar = new JMenuBar();
    	}
        _framebar.remove(menu);
    }
}
