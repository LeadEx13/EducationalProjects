package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;
import Simulation.Clock;

public class Sick extends Person{
	private long contagiousTime; // clock
	private IVirus virus;
	public Sick(int age, Point locantion, Settlement settlement,IVirus virus) {
		super(age, locantion, settlement);
		this.virus = virus;
		this.contagiousTime = Clock.now();
	}
	public Sick(Person person, IVirus virus) throws Exception{
		super(person);
		if(person instanceof Sick) {
			throw new Exception("person is already sick");
		}
		this.virus = virus;
		this.contagiousTime = Clock.now();
	}
	public long getContagiousTime(){
		return this.contagiousTime;
	}
	public void SetContagiousTime(long contagiousTime) {
		this.contagiousTime = contagiousTime;
	}
	@Override
	public double contagionProbability() {
		return 1;
	}
	public Person recover() {
		Convalescent c=new Convalescent(this.GetAge(),this.GetLocation(),this.GetSettlement(),this.getVirus());
		return c;
	}
	public boolean tryToDie() {
			return this.virus.tryToKill(this);
	}
	public IVirus getVirus() {
		return virus;
	}
	public void setVirus(IVirus virus) {
		this.virus = virus;
	}
	@Override
    public String toString() {
        return String.format(super.toString()+ ";contagiousTime = " + contagiousTime +";virus = " + virus);
    }
}
	