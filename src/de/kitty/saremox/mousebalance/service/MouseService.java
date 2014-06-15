package de.kitty.saremox.mousebalance.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import de.kitty.saremox.mousebalance.materials.Mouse;
import de.kitty.saremox.mousebalance.service.io.MouseLoader;
import de.kitty.saremox.mousebalance.service.io.MouseSaver;

public class MouseService extends Observable {
	private List<Mouse> _mouseList;

	public MouseService() {
		_mouseList = MouseLoader.loadMice();
	}

	public void addMouse(Mouse sweetMouse) {
		_mouseList.add(sweetMouse);
		MouseSaver.saveMouse(sweetMouse);
		this.setChanged();
		this.notifyObservers();
	}

	public List<Mouse> getMouseList() {
		return new ArrayList<>(_mouseList);
	}

	public void removeMouse(Mouse sweetMouse) {
		_mouseList.remove(sweetMouse);
		this.setChanged();
		this.notifyObservers();
	} 
}