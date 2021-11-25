package Simulation;

import Simulation.Clock;

public class Clock {
	/**
     * this method give the current time 
     * @return current time
     */
	static private long currentTime=0;
	private static int tick_per_day=1;
	static public long now(){
		return Clock.currentTime;
	}
	static public void nextTick(){
		Clock.currentTime += 1;
	}
	public static void set_tick_per_day(int new_tic){
		 tick_per_day = new_tic;
	 }
	 public static long CalcDays(long start_time){
		 long tick=Clock.now()-start_time;
		 long days= (long)Math.ceil(tick/tick_per_day);
		 return days;
	 }
	@Override
    public String toString() {
        return String.format("current time =" + Clock.currentTime);
    }
}
