package Location;

public class Location {
	
	private  Point position;
	private  Size size;
	
	/**
	   * This method is constructor for class Location.
	   * @param position  position is the top left corner 
	   * @param s  size represent the width and the height borders
	   * 
	*/
	public Location( Point position, Size s) {
		this.position = position;
		this.size = s;		
	}
	/**
	   * Get the position attribute	 
	   * @return  position - top left corner 
	   * 
	*/
	public  Point getPosition() {
		return position;
	}
	/**
	   * Set the position attribute (the top left corner)
	   * @param position represent the the top left corner 
	   * 
	*/
	public  void setPosition(Point position) {
		this.position = position;
	}
	/**
	   * Get size attribute	 
	   * @return  size - width and the height borders
	   * 
	*/
	public Size getSize() {
		return size;
	}
	/**
	   * Set size attribute
	   * @param width represent the width and the height borders
	   * 
	*/
	public void setSize(Size size) {
		this.size = size;
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
		if (!(o instanceof Location)) {
			return false;
		}        
		Location l = (Location) o; 
		return this.position.equals(l.position) && this.size.equals(l.size);
	}
	/**
	 * Returns a string representation of the object.
	 * @return  a string representation of the object.
	 */
	@Override
    public String toString() {
        return String.format("positon=" +position + ";size=" + size);
    }


}
