package IO;
import Country.*;
import Location.Location;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; // Import the Scanner class to read text files
import java.lang.IllegalArgumentException;
import Population.*;
import Location.*;

public class simulationFile {
	private static final int mean=9;
	private static final int standardDeviation=6;
	/**
     * this method parse the input file and populate the database (Map) 
     * @param inputFile: the path to the inputfile
     * @return map that contain all the information about all settlements
     */
	public static Map init(String inputFile)
	{
		Map m = new Map();
		try {
		      File myObj = new File(inputFile);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        String[] arrOfStr = data.split(";");
		        m.addSettlement(createSettlement(arrOfStr));
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return m;
	}
	private static Settlement createSettlement(String[] splitted_line)
	{
		Settlement s;
		String name = splitted_line[1].trim();
		
		int pointX = Integer.parseInt(splitted_line[2].trim());
		int pointY = Integer.parseInt(splitted_line[3].trim());
		int width = Integer.parseInt(splitted_line[4].trim());
		int height = Integer.parseInt(splitted_line[5].trim());
		int numOfPeople = Integer.parseInt(splitted_line[6].trim());
		Point p = new Point(pointX,pointY);
		Size size = new Size(width,height);
		
		switch(splitted_line[0].trim()) {
		  case "City":
		    s = new City(name, new Location(p, size), null, RamzorColor.Green);
		    break;
		  case "Moshav":
			  s = new Moshav(name, new Location(p, size), null, RamzorColor.Green);
		    break;
		  case "Kibbutz":
			  s = new Kibbutz(name, new Location(p, size), null, RamzorColor.Green);
		    break;
		  default:
		    throw new IllegalArgumentException("invalid settlement type");
		}
		
		List<Person> person_list=new ArrayList<Person>(numOfPeople);
		Random r = new Random();		
		for (int i=0;i<numOfPeople;i++)
		{
			double x = r.nextGaussian();
			while (x < -1 || x > 1)
			{
				x = r.nextGaussian();
			}
			int age = (int)(5*(x*standardDeviation+mean));
			age += r.nextInt(5);			
			person_list.add(new Healthy(age,s.randomLocation(),s));			
		}
		s.setPeople(person_list);
		return s;
		
	}
}
