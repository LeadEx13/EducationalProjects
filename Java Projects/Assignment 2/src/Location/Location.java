package Location;

import Location.Location;

public class Location {
	
	private  Point position;
	private  Size size;
	
	public Location( Point position, Size s) {
		this.position = position;
		this.size = s;		
	}
	public  Point getPosition() {
		return position;
	}
	public  void setPosition(Point position) {
		this.position = position;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Location getLocation() {
		Location l=(Location) this.replicate();
		return l;
	}
	public void setLocation(Location l) {
		this.position.setPoint(l.position);
		this.size.setSize(l.size);
	}
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
	@Override
    public String toString() {
        return String.format("positon=" +position + ";size=" + size);
    }
	public Object replicate() {
		return new Location(this.getPosition(),this.getSize());
	}
}
