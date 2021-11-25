package IO;

import java.awt.FileDialog;
import java.util.Iterator;

import java.awt.Frame;
import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


import Country.City;
import Country.Kibbutz;
import Country.Map;
import Country.Settlement;


public class statisticsFile {
	public static Logger logger=null;
	public static String path=null;
	public static Originator originator = new Originator();   
	public static Memento memento;
	public static Caretaker caretaker = new Caretaker();
	public static int index=-1; 
	public static FileHandler fh=null;

	public static void writeCsv(Map world ,File file){
		/**
		 * The function saves all data
		 * @param Map world with all the data
		 * @param File saving all data 
		 * 
		 */
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
		    Iterator<Settlement> iter = world.iterator();
		    while (iter.hasNext()){
		    	Settlement s= iter.next();
		    	sb.append(s.getName());
		    	sb.append(',');
	    		if(s instanceof City)
	    			sb.append("City");
	    		else if(s instanceof Kibbutz)
	    			sb.append("Kibbutz");
	    		else
	    			sb.append("Moshav");
	    		sb.append(',');
	    		sb.append(s.getPopulation());
	    		sb.append(',');
	    		sb.append(s.getRamzorColor());
	    		sb.append(',');
	    		sb.append(((double)s.getsick_people().size()/s.getPopulation())*100+"%");
	    		sb.append(',');
	    		sb.append(s.getVaccine_doses());
	    		sb.append('\n');
			}
		    writer.write(sb.toString());
		} 
		catch (FileNotFoundException e) {
		      System.out.println(e.getMessage());
		}
	}
	public static void writeLog(Settlement s) {
		Logger logger = Logger.getLogger("");  
	    try { 
	        fh = new FileHandler(path); 
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	        logger.info(s.getName()+" Number of sick: "+s.getsick_people().size()+" Number of dead: "+s.getdead()+"\n");

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	public static void loadFileFunc() {
		/**
		 * load new file
		 * @return file
		 */
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return ;
        path=fd.getFile();
        originator.setState(path);
        memento = originator.createMemento(); 
        caretaker.addMemento(memento);
        index++;
        try {
			fh = new FileHandler(path);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
}


