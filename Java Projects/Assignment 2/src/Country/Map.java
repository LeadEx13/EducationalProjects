package Country;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
	
	private  Settlement[] settlements;
	private static int size=0;
	public Map(){
		this.settlements = new Settlement[]{};
	}
	public Map(Settlement[] s){
		this.settlements=new Settlement[size];
		this.setSettlements(s);
	}
	public static int getSize() {
		return size;
		}
	public static void setSize() {
		size++;
	}
	public static void resetSize() {
		size=0;
	}
	public  Settlement[] getSettlements() {
		return this.settlements;
	}
	public  void setSettlements(Settlement[] s) {
		this.settlements = new Settlement[s.length];
        for (int i = 0; i < s.length; i++)
        	this.settlements[i] = s[i];
	}
	public void removeSettlement(Settlement s){
		ArrayList<Settlement> tempList = new ArrayList<Settlement>(Arrays.asList(this.settlements));
	    tempList.remove(s);
	    this.settlements = tempList.toArray(this.settlements);		
	}
	public void addSettlement(Settlement s){
		ArrayList<Settlement> tempList = new ArrayList<Settlement>(Arrays.asList(this.settlements));
	    tempList.add(s);
	    this.settlements = tempList.toArray(this.settlements);		
	}
	@Override
    public String toString() {
		String temp = "";
		for (Settlement s: settlements) {
			temp += s.toString() + "\n";
		}		
        return temp;
    }
}
