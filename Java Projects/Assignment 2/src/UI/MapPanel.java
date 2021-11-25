package UI;

import java.awt.Color;

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
		super.paintComponent(g);
		if (world == null) {
			return;
		}
		
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double dimension_x = (double)this.getWidth() / max_x;
		double dimension_y = (double)this.getHeight() / max_y;

		for (int i = 0; i < world.getSettlements().length; i++) {
			g.setColor(Color.BLACK);
			for (int j = 0; j < world.getSettlements()[i].getneighbors().size(); j++) {
				int center_x1 = findCenterX(i);
				int center_y1 = findCenterY(i);
				int center_x2 = world.getSettlements()[i].getneighbors().get(j).getLocation().getPosition().getX()
						+ world.getSettlements()[i].getneighbors().get(j).getLocation().getSize().getWidth() / 2;
				int center_y2 = world.getSettlements()[i].getneighbors().get(j).getLocation().getPosition().getY()
						+ world.getSettlements()[i].getneighbors().get(j).getLocation().getSize().getHeight() / 2;
				g.drawLine((int)(center_x1*dimension_x), (int)(center_y1*dimension_y), (int)(center_x2*dimension_x),(int) (center_y2*dimension_y));			
			}
		}
		for (int i = 0; i < world.getSettlements().length; i++) {
			g.setColor(Color.BLACK);
			int x=world.getSettlements()[i].getLocation().getPosition().getX();
			int y=world.getSettlements()[i].getLocation().getPosition().getY();
			int height=world.getSettlements()[i].getLocation().getSize().getHeight();
			int width=world.getSettlements()[i].getLocation().getSize().getWidth();
			g.drawRect((int)(x*dimension_x),(int)(y*dimension_y),(int)(width*dimension_x),(int)(height*dimension_y));
			g.setColor(world.getSettlements()[i].getRamzorColor().getColor());
			g.fillRect((int)(x*dimension_x),(int)(y*dimension_y),(int)(width*dimension_x),(int)(height*dimension_y));
			g.setColor(Color.BLACK);
			g.drawString(world.getSettlements()[i].getName(),(int)(x*dimension_x),(int)((y+15)*dimension_y));
			g.setFont(new Font("TimesRoman", Font.PLAIN,(int) (14*dimension_x))); 
		}
	}

	public int findCenterX(int i) {
		return world.getSettlements()[i].getLocation().getPosition().getX()
				+ world.getSettlements()[i].getLocation().getSize().getWidth() / 2;
	}

	public int findCenterY(int i) {
		return world.getSettlements()[i].getLocation().getPosition().getY()
				+ world.getSettlements()[i].getLocation().getSize().getHeight() / 2;
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