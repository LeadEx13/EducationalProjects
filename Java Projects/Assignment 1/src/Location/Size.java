package Location;

public class Size {
	
	private int width;
	private int height;
	
	/**
	   * This method is constructor for class Size.
	   * @param width represent the width of two dimensional object 
	   * @param height represent the height of two dimensional object 
	   * 
	*/
	public Size(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	/**
	   * Get the width attribute	 
	   * @return  width
	   * 
	*/
	public int getWidth() {
		return this.width;
	}
	/**
	   * Set width attribute
	   * @param width represent the width of two dimensional object
	   * 
	*/
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	   * Get the height attribute	 
	   * @return  height
	   * 
	*/
	public  int getHeight() {
		return this.height;
	}
	/**
	   * Set height attribute
	   * @param height represent the height of two dimensional object
	   * 
	*/
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @param   o the reference object with which to compare.
     * @return  {@code true} if this object is the same as the obj
     *          argument; 
     * @see     java.lang.Object
	 * 
	 */
	@Override
	public boolean equals(Object o) {	  
		if (o == this) {
			return true;
		}
		if (!(o instanceof Size)) {
			return false;
		}        
		Size s = (Size) o; 
		return this.width == s.width && this.height == s.height;
	}
	/**
	 * Returns a string representation of the object.
	 * @return  a string representation of the object.
	 */
	@Override
    public String toString() {
		return String.format("width:height(" + width + ":" + height+")");
    }

}
