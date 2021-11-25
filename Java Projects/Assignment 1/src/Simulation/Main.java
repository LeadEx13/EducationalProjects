package Simulation;
import java.util.*;  
import IO.*;
import Population.Healthy;
import Population.Person;
import Population.Sick;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import Country.*;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.print("Please provide the input file path\n");  
		String file_name = sc.nextLine(); 
		Country.Map m = simulationFile.init(file_name);
		// init phase 
		HashMap<Settlement, List<Sick>> sttlementSickMap = new HashMap<>();
		System.out.println("------Init phase------");
		for (Settlement s: m.getSettlements()) {
			List<Person> p = s.getPeople();
			List<Sick> sickList =  new ArrayList<Sick>(p.size()/100);
			for (int i = 0;i < p.size()/100 ;i++) // set 1% of the citizen as Sick
			{
				IVirus v = getRandomVirus();
				p.get(i).contagion(v);
				try {
					sickList.add(new Sick(p.get(i), v));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sttlementSickMap.put(s, sickList);
			Clock.nextTick();
			s.calculateRamzorGrade();

			System.out.println(s);
		}
		System.out.println("------Done init phase------\n");
		//simulation phase
		for (int k = 0;k < 5;k++) {
		for (Settlement s: m.getSettlements()) {
			//ArrayList<Person> sickList = new ArrayList<Person>();
			ArrayList<Person> HealthyList = new ArrayList<Person>();
			List<Person> p = s.getPeople();
			// get the indexes for all sick and healthy people 
			for (int i = 0;i < p.size() ;i++)
			{
				/*if (p.get(i) instanceof Sick )
				{
					sickList.add(p.get(i));
				}*/
				if (p.get(i) instanceof Healthy)
				{
					HealthyList.add(p.get(i));
				}
			}
			// for each sick people choose 6 people and try to contagion them
			List<Sick> tempList = sttlementSickMap.get(s);
			for (int i = 0;i < tempList.size() ;i++)
			{
				// shuffle the healthy list and take the first 6 - it is like to random 6 people
				Collections.shuffle(HealthyList);
				ArrayList<Person> tempCotagionList = new ArrayList<Person>(6);
				for (int j=0; j<6 && j < HealthyList.size() ;j++)
				{
					// in order to get the virus from person that sick we MUST downcast it
					Sick sickP = tempList.get(i);
					Person tempP = HealthyList.get(j);
					if(sickP.getVirus().tryToContagion(sickP,tempP)) {
						HealthyList.get(j).contagion(sickP.getVirus());
						tempCotagionList.add(tempP);
						s.calculateRamzorGrade();
					}
				}
				// remove the contagion people from the healthy list
				for (int j=0;j<tempCotagionList.size();j++)
				{
					HealthyList.remove(tempCotagionList.get(j));
				}
			
			}
			
			Clock.nextTick();
		}
		System.out.println("Map status after iteration number"+ (k+1));
		System.out.println(m);
		}
			
	}
	
	public static IVirus getRandomVirus()
	{
		Random r = new Random();
		IVirus v = null;
		int i = r.nextInt(3);
		switch(i) {
		  case 0:
			  v = new ChineseVariant();
			  break;
		  case 1:
			  v = new BritishVariant();
			  break;
		  case 2:
			  v = new SouthAfricanVariant();
			  break;
		}
		return v;
	}
}


