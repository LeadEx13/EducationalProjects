
package Country;

public enum RamzorColor {
	/**
	 *The virus coefficient in the settlement is up to number 0.4
	 */
    Green  (0.4),
    /**
	 *The virus coefficient in the settlement is above 0.4 and up to number 0.6
	 */
    Yellow(0.6),
    /**
	 *The virus coefficient in the settlement is above 0.6 and up to 0.8
	 */
    Orange   (0.8),
    /**
	 *The virus coefficient in the settlement is above 0.8 and up to 1
	 */
    Red   (1);


    private final double Color;

    RamzorColor(double Color) {
        this.Color = Color;
    }

    public double getColor() {
        return this.Color;
    }
	/**
	   * This method calculate the enum value according the give value
	   * @param the value we need to convert to enum.	   
	   * @return ramzorColor the color of this settlement   
	   * 
	*/
    public static RamzorColor byValue(double value) {
        for (RamzorColor rc : RamzorColor.values()) {
            if (value <= rc.Color) {
                return rc;
            }
        }
        // workaround 
        if (value > 1)
        	return Red;
        return null;
    }

}