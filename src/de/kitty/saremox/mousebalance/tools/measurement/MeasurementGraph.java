package de.kitty.saremox.mousebalance.tools.measurement;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

import de.kitty.saremox.mousebalance.materials.Measurement;

public class MeasurementGraph extends JPanel
{
	private static final double XAXISLINES = 10;
	private static final double YAXISLINES = 31;
	private final double displayedMeasurePoints;
	
	private List<Measurement> _measurements;
	
	public MeasurementGraph(List<Measurement> _measurements,double YLines)
	{
		super();
		this._measurements = _measurements;
		this.displayedMeasurePoints =  YLines;
	}

	public void set_measurements(List<Measurement> _measurements)
	{
		this._measurements = _measurements;
		this.repaint();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7634698529636753379L;

	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		g2d.setColor(Color.BLACK);
		Dimension mySize = this.getSize();
		int chartStartX = 40;
		int chartStartY = mySize.height-40;
		int chartEndX = mySize.width-40;
		int chartEndY = 40;
		int Yspace = (chartStartY-chartEndY);
		
		g2d.setFont(g2d.getFont().deriveFont(24.0f));
		
		int chartTitelX = (mySize.width - g2d.getFontMetrics().stringWidth("Gewichts Entwicklung Ueber 1 Monat"))/2;
		g2d.drawString("Gewichts Entwicklung Ueber 1 Monat",chartTitelX,25);
		
		// Draw Y Axis lines
		g2d.setStroke(new BasicStroke(1, 1, 1));
		g2d.setColor(Color.GRAY);
		double chartXAxisLineGap = (chartEndX-chartStartX)/YAXISLINES;
		for(int i=0;i<=YAXISLINES;i++)
		{
			g2d.drawLine((int) (chartStartX+chartXAxisLineGap*i), chartStartY,(int) (chartStartX+ chartXAxisLineGap*i), chartEndY);
		}
		// Draw X Axis lines
		double chartYAxisLineGap = (chartStartY-chartEndY)/XAXISLINES;
		for(int i=1;i<=XAXISLINES;i++)
		{
			g2d.drawLine(chartStartX,(int) (chartStartY-chartYAxisLineGap*i),chartEndX,(int) (chartStartY-chartYAxisLineGap*i));
		}
		
		
		// Y Axis
		g2d.setStroke(new BasicStroke(3, 1, 1));
		g2d.drawLine(chartEndX, chartStartY, chartEndX, chartEndY);
		// X Axis
		g2d.drawLine(chartStartX, chartStartY, chartEndX, chartStartY);
		
		/*
		 * Begin to Draw Graph
		 * We use the last Measurement and than get into past
		 */
		int curItem = _measurements.size()-1;
		// Get Maximum Weight
		int maxWeight = 0;
		if(curItem <0)
		{
			return;
		}
		g2d.setColor(new Color(255, 0, 255));
		g2d.setStroke(new BasicStroke(2));
		
		double pixelBetweenPoints = (chartEndX-chartStartX)/displayedMeasurePoints;
		int x = (int) displayedMeasurePoints;
		while(curItem >= 0&& x>=0)
		{		
			Measurement curMeasurement = _measurements.get(curItem);
			if(maxWeight < curMeasurement.getWeight().getWeight())
			{
				maxWeight = curMeasurement.getWeight().getWeight();
			}
			curItem--;
			x--;
		}
		curItem = _measurements.size()-1;
		double pixelPerGramm = (double) Yspace/((double) maxWeight*1.1);
		x = (int) displayedMeasurePoints;
		while(curItem >= 0 && x>=0)
		{
			Measurement curMeasurement = _measurements.get(curItem);
			g2d.fillOval((int) (chartStartX+pixelBetweenPoints*x)-4, (int) (chartStartY-(curMeasurement.getWeight().getWeight()*pixelPerGramm))+4, 8, 8);
			if(x < displayedMeasurePoints)
			{
				g2d.drawLine((int)(chartStartX+pixelBetweenPoints*x), 
							 (int)(chartStartY-(curMeasurement.getWeight().getWeight()*pixelPerGramm)), 
							 (int)(chartStartX+pixelBetweenPoints*(x+1)), 
							 (int)(chartStartY-(_measurements.get(curItem+1).getWeight().getWeight()*pixelPerGramm)));
				
			}
			
			curItem--;
			x--;
		}
	}
}
