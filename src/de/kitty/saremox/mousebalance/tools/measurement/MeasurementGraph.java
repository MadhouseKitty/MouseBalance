package de.kitty.saremox.mousebalance.tools.measurement;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPanel;

import de.kitty.saremox.mousebalance.materials.Measurement;

public class MeasurementGraph extends JPanel
{
	private static final double XAXISLINES = 10;
	private static final double DAYSPERMONTH = 30;
	private final double displayedMeasurePoints;
	private final int months;
	
	private List<Measurement> _measurements;
	
	public MeasurementGraph(List<Measurement> _measurements,int months)
	{
		super();
		this._measurements = _measurements;
		this.months = months;
		this.displayedMeasurePoints =  months*DAYSPERMONTH;
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
		int chartStartX = 20;
		int chartStartY = mySize.height-40;
		int chartEndX = mySize.width-60;
		int chartEndY = 40;
		int Yspace = (chartStartY-chartEndY);
		int pixelForDate = g2d.getFontMetrics().stringWidth("xx.xx");
		
		
		g2d.setFont(g2d.getFont().deriveFont(24.0f));
		
		int chartTitelX = (mySize.width - g2d.getFontMetrics().stringWidth("Gewichts Entwicklung Ueber 1 Monat"))/2;
		g2d.drawString("Gewichts Entwicklung Ueber 1 Monat",chartTitelX,25);
		
		// Draw Y Axis lines
		g2d.setStroke(new BasicStroke(1, 1, 1));
		g2d.setColor(Color.GRAY);
		double chartXAxisLineGap = (chartEndX-chartStartX)/DAYSPERMONTH;
		for(int i=0;i<=DAYSPERMONTH;i++)
		{
			if(i%4 == 0)
			{
				g2d.drawLine((int) (chartStartX+chartXAxisLineGap*i), chartStartY+10,(int) (chartStartX+ chartXAxisLineGap*i), chartEndY);
			} else {
				g2d.drawLine((int) (chartStartX+chartXAxisLineGap*i), chartStartY+20,(int) (chartStartX+ chartXAxisLineGap*i), chartEndY);
			}
		}
		// Draw X Axis lines
		double chartYAxisLineGap = (chartStartY-chartEndY)/XAXISLINES;
		for(int i=1;i<=XAXISLINES;i++)
		{
			g2d.drawLine(chartStartX,(int) (chartStartY-chartYAxisLineGap*i),chartEndX+10,(int) (chartStartY-chartYAxisLineGap*i));
		}
		
		
		// Y Axis
		g2d.setStroke(new BasicStroke(3, 1, 1));
		g2d.drawLine(chartEndX, chartStartY+5, chartEndX, chartEndY);
		// X Axis
		g2d.drawLine(chartStartX, chartStartY, chartEndX+5, chartStartY);
		
		/*
		 * Begin to Draw Graph
		 * We use the last Measurement and than get into past
		 */
		int curItem = _measurements.size()-1;
		// Get Maximum Weight
		int maxWeight = 1;
		if(curItem <0)
		{
			return;
		}
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
		// It Looks better if we don't have Chart points at the top of our Chart
		maxWeight*=1.1;
		double pixelPerGramm = (double) Yspace/((double) maxWeight);
		x = (int) displayedMeasurePoints;
		g2d.setFont(g2d.getFont().deriveFont(14.0f));
		while(curItem >= 0 && x>=0)
		{
			g2d.setColor(new Color(255,0,255));
			Measurement curMeasurement = _measurements.get(curItem);
			g2d.fillOval((int) (chartStartX+pixelBetweenPoints*x)-4, (int) (chartStartY-(curMeasurement.getWeight().getWeight()*pixelPerGramm))-4, 8, 8);
			if(x < displayedMeasurePoints)
			{
				g2d.drawLine((int)(chartStartX+pixelBetweenPoints*x), 
							 (int) (chartStartY-(curMeasurement.getWeight().getWeight()*pixelPerGramm)), 
							 (int)(chartStartX+pixelBetweenPoints*(x+1)), 
							 (int)(chartStartY-(_measurements.get(curItem+1).getWeight().getWeight()*pixelPerGramm)));
				
			}
			g2d.setColor(Color.BLACK);
			if(x%(6*this.months) == 0)
			{
				g2d.drawString(new SimpleDateFormat("dd.MM").format(curMeasurement.getDate()), (float)(chartStartX+(pixelBetweenPoints*x)-pixelForDate/2), chartStartY+15);
			} else if(x%(6*this.months) == (3*this.months)){
				g2d.drawString(new SimpleDateFormat("dd.MM").format(curMeasurement.getDate()), (float)(chartStartX+(pixelBetweenPoints*x)-pixelForDate/2), chartStartY+30);

			}
			curItem--;
			x--;
		}
		g2d.setColor(Color.BLACK);
		for(int i=0;i<DAYSPERMONTH;i++)
		{
			g2d.drawString(Integer.toString((int) ((i*chartYAxisLineGap)/pixelPerGramm)), chartEndX+10,(int) (chartStartY-i*chartYAxisLineGap)+7);
		}
	}
}
