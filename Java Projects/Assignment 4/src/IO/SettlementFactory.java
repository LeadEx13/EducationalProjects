package IO;

import Country.City;
import Country.Kibbutz;
import Country.Moshav;
import Country.Settlement;
import Location.Location;

public class SettlementFactory {
	public Settlement getSettlementinstance(String settlType,String name,Location location,int population,int capacity ){
		if (settlType.contentEquals("City")){
			City c=new City(name,location,population,capacity);
			return c;
		}
		else if (settlType.contentEquals("Kibbutz")){
			Kibbutz k= new Kibbutz(name,location,population,capacity); 
			return k;
		}
		else {
			Moshav m= new Moshav(name,location,population,capacity);
			return m;
		}	
	}
}