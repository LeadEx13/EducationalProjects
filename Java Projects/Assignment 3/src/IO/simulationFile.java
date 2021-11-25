package IO;

import Location.Location;
import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; // Import the Scanner class to read text files
import Country.City;
import Country.Kibbutz;
import Country.Map;
import Country.Moshav;
import Country.Settlement;
import Location.Point;
import Location.Size;
import Population.Healthy;

public class simulationFile {
	/**
     * this method parse the input file and populate the database (Map) 
     * @param inputFile: the path to the input file
     * @return map that contain all the information about all settlements
     */
	private static final int standardDeviation=6;
	private final static double capcity=1.3;
	private final static int expectation=9;
	
	public Map loadMap(File file) throws Exception {
		return new Map(simulationFile.parse(file));
	}
	public static Settlement[] parse(File file) throws Exception {
		Settlement[] settlement=null;
		String name = null;
		String[] lineSetl=null;
		String[][] lineNeighbor=null;
		int index=0;
		List<Settlement> settl_temp=new ArrayList<Settlement>();
		int[] numPeopole=null;
		try {
			FileReader fr= new FileReader(file);
			BufferedReader br= new BufferedReader(fr);
			Scanner in=new Scanner(file);
			while (br.readLine()!=null)
				Map.setSize();
			
			lineNeighbor=new String[Map.getSize()][3];
			numPeopole=new int[Map.getSize()];
			name=in.nextLine();
			for (int i=0;i<Map.getSize();i++){
				
				if(name.contains("#")){
					lineNeighbor[index]=name.split(";");
					index++;
				}
				else {
					lineSetl=name.split(";");
					if (parseSettlement(lineSetl)!=null){
						settl_temp.add(parseSettlement(lineSetl));
						numPeopole[i]=Integer.parseInt(lineSetl[6].replace(" ", ""));
					}
					else
						throw new Exception("Error with the Settlement type");	
				}
				if (!(i==Map.getSize()-1))
					name=in.nextLine();
			}
			settlement=new Settlement[settl_temp.size()];
			for (int i=0;i<settl_temp.size();i++){
				settlement[i]=settl_temp.get(i);
			}
			for (int i=0;i<index;i++)
				parseNeighbors(lineNeighbor[i],settlement);
			Healthy new_person;
			for(int i=0;i<settlement.length;i++){
				for(int j=0;j<numPeopole[i];j++) {
					new_person = new Healthy(randomAge(),settlement[i].randomLocation(),settlement[i]);
					settlement[i].AddPerson(new_person);
				}
			}
			in.close();
			br.close();
			fr.close();
			Map.resetSize();
		}
		catch (FileNotFoundException ex) {
			System.out.printf("Error %s\n",ex);
		}
		return settlement;
	}
	private static void parseNeighbors(String[] line,Settlement[] settl) {
		for(int i=0;i<settl.length;i++){
			if(line[1].contains(settl[i].getName())){
				for(int j=0;j<settl.length;j++){
					if(line[2].contains(settl[j].getName())){
						settl[i].getneighbors().add(settl[j]);
						settl[j].getneighbors().add(settl[i]);
					}
				}
			}
		}
	}
	private static Settlement parseSettlement(String[] line) {
		int x=Integer.parseInt(line[2].replace(" ", ""));
		int y=Integer.parseInt(line[3].replace(" ", ""));
		Point p=new Point(x,y);
		int height=Integer.parseInt(line[4].replace(" ", ""));
		int width=Integer.parseInt(line[5].replace(" ", ""));
		Size s1=new Size(height,width);
		int numpeople=Integer.parseInt(line[6].replace(" ", ""));
		Location location=new Location(p,s1);
		
		if (line[0].contentEquals("City")) {

			City c=new City(line[1],location,numpeople,(int) (capcity*numpeople));
			return c;
		}
		else if (line[0].contentEquals("Kibbutz")) {
			Kibbutz k= new Kibbutz(line[1],location,numpeople,(int) (capcity*numpeople)); 
			return k;
		}
		else if (line[0].contentEquals("Moshav")) {
			Moshav m= new Moshav(line[1],location,numpeople,(int) (capcity*numpeople));
			return m;
		}	
		return null;
	}
	private static int randomAge() {
		Random rand = new Random();
		int y =rand.nextInt(5); // 0<=y<=4
		int g=(int) rand.nextGaussian();
		while(g>1 || g<-1) {
			g=(int) rand.nextGaussian();
		}
		double x= (int)g*standardDeviation+expectation;
		int age= (int) Math.abs(5*x+y);
		return Math.abs(age);
		
	}
}
