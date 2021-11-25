package Location;

import Location.Size;

public class Size {
	
	private int width;
	private int height;
	public Size(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	public int getWidth() {
		return this.width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public  int getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setSize(Size s) {
		this.height=s.getHeight();
		this.width=s.getWidth();
	}
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
	@Override
    public String toString() {
		return String.format("width:height(" + width + ":" + height+")");
    }
	public Object replicate() {
		return new Size(this.getHeight(),this.getWidth());
	}
}
