
package Country;
import java.awt.Color;
public enum RamzorColor {
	/**
	 *Reworked enum for gui
	 */
	GREEN (0.4, Color.green,1.0),
	/**
	 *The virus coefficient in the settlement is above 0.4 and up to number 0.6
	 */
	YELLOW(0.6, Color.yellow,0.8),
	/**
	 *The virus coefficient in the settlement is above 0.6 and up to 0.8
	 */
	ORANGE(0.8, Color.orange,0.6),
	/**
	 *The virus coefficient in the settlement is above 0.8 and up to 1
	 */
	RED(1.0, Color.red,0.4);
	
    private final double var; 
    private final Color color;  
    private final double c; 
    RamzorColor(double var, Color color,double c) {
        this.var = var;
        this.color = color;
        this.c =c;
    }
	public double getVal() {
		return var;
	}
	public Color getColor() {
		return color;
	}
	public double getP() {
		return c;
	}

}