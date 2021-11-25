package UI;

import java.awt.Color;
import java.awt.Graphics;
import Country.Settlement;

public class NeighborLineDec{
	private Settlement settl1;
	private Settlement settl2;
	public NeighborLineDec(Settlement s1,Settlement s2){
		this.settl1=s1;
		this.settl2=s2;
	}
	public void SetColor(Graphics g){
		Color c1= settl1.getRamzorColor().getColor();
		Color c2= settl2.getRamzorColor().getColor();
		int red=(c1.getRed()+c2.getRed())/2;
		int blue=(c1.getBlue()+c2.getBlue())/2;
		int green=(c1.getGreen()+c2.getGreen())/2;
		Color avg_color=new Color(red, blue, green);
		g.setColor(avg_color);
	}
}