package Location;

import Location.Point;

public class Point {
	/**
	   * 
	   * This method is constructor for class Point.
	   * @param x  x coordinate in two dimensional graph
	   * @param y  y coordinate in two dimensional graph
	   * 
	*/

	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;		
	}
	public int getX() {
		return x;
	}
	public  void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public  void setY(int y) {
		this.y = y;
	}
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
	@Override
    public String toString() {
        return String.format("x:y(" + x + ":" + y+")");
    }
	public void setPoint(Point p){
		this.x=p.getX();
		this.y=p.getY();
	}
	public Point getPoint() {
		Point p=(Point) this.replicate();
		return p;
	}
	public Object replicate() {
		return new Point(this.getX(),this.getY());
	}
}
