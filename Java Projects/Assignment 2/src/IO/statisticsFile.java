package IO;

import java.io.*;

import Country.City;
import Country.Kibbutz;
import Country.Map;

public class statisticsFile 
{
	public static void writeCsv(Map world ,File file){
		try  {
			PrintWriter writer = new PrintWriter(file);
			StringBuilder sb = new StringBuilder();
			sb.append("Settlement Name");
		    sb.append(',');
		    sb.append("Settlement Type");
		    sb.append(',');
		    sb.append("Population");
		    sb.append(',');
		    sb.append("Ramzor color");
		    sb.append(',');
		    sb.append("Sick Percentages");
		    sb.append(',');
		    sb.append("Vaccine doses");
		    sb.append('\n');
		    for (int i=0;i<world.getSettlements().length;i++) {
		    	sb.append(world.getSettlements()[i].getName());
		    	sb.append(',');
	    		if(world.getSettlements()[i] instanceof City)
	    			sb.append("City");
	    		else if(world.getSettlements()[i] instanceof Kibbutz)
	    			sb.append("Kibbutz");
	    		else
	    			sb.append("Moshav");
	    		sb.append(',');
	    		sb.append(world.getSettlements()[i].getPopulation());
	    		sb.append(',');
	    		sb.append(world.getSettlements()[i].getRamzorColor());
	    		sb.append(',');
	    		sb.append(((double)world.getSettlements()[i].getsick_people().size()/world.getSettlements()[i].getPopulation())*100+"%");
	    		sb.append(',');
	    		sb.append(world.getSettlements()[i].getVaccine_doses());
	    		sb.append('\n');
			}
		    writer.write(sb.toString());
		    writer.close();
		} 
		catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		}
	}
	
}


