package UI;

import java.awt.Color;
import java.util.Iterator;
import Country.Settlement;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import Country.Map;

public class MapPanel extends JPanel {
	private Map world = null;
	private int max_x = 0;
	private int max_y = 0;

	public MapPanel() {
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	}
	@Override
	public void paintComponent(Graphics g) {
		/**
		 * Calls the UI delegate's paint method
		 * 
		 * @param g the Graphics object
		 */
		
		super.paintComponent(g);
		if (world == null) {
			return;
		}
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		double dimension_x = (double)this.getWidth() / max_x;
		double dimension_y = (double)this.getHeight() / max_y;
		Iterator<Settlement> iter = world.iterator();
		while(iter.hasNext()) {
			Settlement s=iter.next();

			for (int j = 0; j < s.getneighbors().size(); j++) {

				int center_x1 = (int) findCenterX(s);
				int center_y1 = (int) findCenterY(s);
				int center_x2 = (int) (s.getneighbors().get(j).getLocation().getPosition().getPoint_x()
						+ s.getneighbors().get(j).getLocation().getSize().getWidth() / 2);
				int center_y2 = (int) (s.getneighbors().get(j).getLocation().getPosition().getPoint_y()
						+ s.getneighbors().get(j).getLocation().getSize().getHeight() / 2);
				NeighborLineDec n1= new NeighborLineDec(s,s.getneighbors().get(j));
				n1.SetColor(g);
				g.drawLine((int)(center_x1*dimension_x), (int)(center_y1*dimension_y), (int)(center_x2*dimension_x),(int) (center_y2*dimension_y));			
			}
		}
		iter=world.iterator();
		while(iter.hasNext()) {
			Settlement s=iter.next();
			g.setColor(Color.BLACK);
			int x=(int) s.getLocation().getPosition().getPoint_x();
			int y=(int) s.getLocation().getPosition().getPoint_y();
			int height=s.getLocation().getSize().getHeight();
			int width=s.getLocation().getSize().getWidth();
			g.drawRect((int)(x*dimension_x),(int)(y*dimension_y),(int)(width*dimension_x),(int)(height*dimension_y));
			g.setColor(s.getRamzorColor().getColor());
			g.fillRect((int)(x*dimension_x),(int)(y*dimension_y),(int)(width*dimension_x),(int)(height*dimension_y));
			g.setColor(Color.BLACK);
			g.drawString(s.getName(),(int)(x*dimension_x),(int)((y+15)*dimension_y));
			g.setFont(new Font("TimesRoman", Font.PLAIN,(int) (14*dimension_x)));
		}
	}

	private double findCenterY(Settlement s) {
		// TODO Auto-generated method stub
		return s.getLocation().getPosition().getPoint_y()
				+ s.getLocation().getSize().getHeight() / 2;	}

	public double findCenterX(Settlement s) {
		return s.getLocation().getPosition().getPoint_x()
				+ s.getLocation().getSize().getWidth() / 2;
	}

	public void set_map(Map world) {

		this.world = world;
		max_x = max_y = 0;
		if (world != null)
			for (int i = 0; i < world.getSettlements().length; i++) {
				if (world.getSettlements()[i].getLocation().getPosition().getX()+world.getSettlements()[i].getLocation().getSize().getWidth() > max_x) {
					max_x = world.getSettlements()[i].getLocation().getPosition().getX()+world.getSettlements()[i].getLocation().getSize().getWidth();
				}
				if (world.getSettlements()[i].getLocation().getPosition().getY()+world.getSettlements()[i].getLocation().getSize().getHeight() > max_y) {
					max_y = world.getSettlements()[i].getLocation().getPosition().getY()+world.getSettlements()[i].getLocation().getSize().getHeight();
				}
			}
		max_x += 10;
		max_y +=10;
		this.repaint();
	}
	public double getDimentionX(){
		return (double)this.getWidth() / max_x;
	}
	public double getDimentionY(){
		return (double)this.getHeight() / max_y;
	}

}