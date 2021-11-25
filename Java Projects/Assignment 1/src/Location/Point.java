package Location;

public class Point {

	private int x;
	private int y;
	
	/**
	   * This method is constructor for class Point.
	   * @param x  x coordinate in two dimensional graph
	   * @param y  y coordinate in two dimensional graph
	   * 
	*/
	public Point(int x, int y) {
		this.x=x;
		this.y=y;		
	}
	
	/**
	   * Get x coordinate of this point	 
	   * @return  x coordinate
	   * 
	*/
	public int getX() {
		return x;
	}
	/**
	   * Set x coordinate to this point
	   * @param x  x coordinate in two dimensional graph
	   * 
	*/
	public  void setX(int x) {
		this.x = x;
	}
	/**
	   * Get y coordinate of this point
	   * @return  y coordinate
	   * 
	*/
	public int getY() {
		return y;
	}
	/**
	   * Set y coordinate to this point
	   * @param y  y coordinate in two dimensional graph
	   * 
	*/
	public  void setY(int y) {
		this.y = y;
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
		if (!(o instanceof Point)) {
			return false;
		}        
		Point p = (Point) o; 
		return this.x == p.x && this.y == p.y;
	}
	/**
	 * Returns a string representation of the object.
	 * @return  a string representation of the object.
	 */
	@Override
    public String toString() {
        return String.format("x:y(" + x + ":" + y+")");
    }
	
}
