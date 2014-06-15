package de.kitty.saremox.mousebalance.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.kitty.saremox.mousebalance.materials.Measurement;
import de.kitty.saremox.mousebalance.materials.Mouse;
import de.kitty.saremox.mousebalance.materials.Weight;

public class DialogTool {
	public static Measurement newMeasurementDialog() {
		JTextField measurementWeight = new JTextField(12);
		JTextField measurementDate = new JTextField(12);
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

		JPanel measurementWeightPanel = new JPanel();
		measurementWeightPanel.add(new JLabel("Gewicht:"));
		measurementWeightPanel.add(measurementWeight);
		JPanel measurementDatePanel = new JPanel();
		measurementDatePanel.add(new JLabel("Datum:"));
		measurementWeightPanel.add(Box.createHorizontalStrut(8));
		measurementDatePanel.add(measurementDate);
		measurementDate.setText(formatter.format(new Date()));
		Object[] message = { "", measurementDatePanel, measurementWeightPanel };

		int result = 0;
		while (result != JOptionPane.CANCEL_OPTION) {
			result = JOptionPane.showConfirmDialog(null, message,
					"Create new Mouse", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {

				try {
					Date mDate = formatter.parse(measurementDate.getText());
					int mWeight = Integer.parseInt(measurementWeight.getText());

					return new Measurement(new Weight(mWeight), mDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					message[0] = "Datum bitte im 19.01.1900 Format eingeben";
				} catch (NumberFormatException e) {
					message[0] = "Bitte eine Gueltiges Gewicht in mg angeben";
				}
			}
		}
		return null;
	}

	public static Mouse newMouseDialog() {
		JTextField mouseName = new JTextField(12);
		JTextField mouseBirthDate = new JTextField(12);
		JTextField mouseColour = new JTextField(12);

		JPanel namePanel = new JPanel();
		namePanel.add(new JLabel("Name:"));
		namePanel.add(Box.createHorizontalStrut(22));
		namePanel.add(mouseName);
		JPanel birthPanel = new JPanel();
		birthPanel.add(new JLabel("Birthdate:"));
		birthPanel.add(mouseBirthDate);
		JPanel colourPanel = new JPanel();
		colourPanel.add(new JLabel("Colour:"));
		colourPanel.add(Box.createHorizontalStrut(16));
		colourPanel.add(mouseColour);
		Object[] message = { "", namePanel, birthPanel, colourPanel };

		int result = 0;
		while (result != JOptionPane.CANCEL_OPTION) {
			result = JOptionPane.showConfirmDialog(null, message,
					"Create new Mouse", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
				try {
					Date birth = formatter.parse(mouseBirthDate.getText());
					if (mouseName.getText().isEmpty()) {
						message[0] = "Bitte gib einen namen ein";
					} else {
						return new Mouse(mouseName.getText(), birth,
								mouseColour.getText());
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					message[0] = "Datum bitte im 19.01.1900 Format eingeben";
				}
			}
		}
		return null;
	}

}
