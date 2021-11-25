package Simulation;

public class Clock {
	static private long currentTime=0;
	/**
     * this method give the current time 
     * @return current time
     */
	static public long now()
	{
		return Clock.currentTime;
	}
	/**
     * this method increment the time by one
     */
	static public void nextTick()
	{
		Clock.currentTime += 1;
	}
	@Override
    public String toString() {
        return String.format("current time =" + Clock.currentTime);
    }
}
