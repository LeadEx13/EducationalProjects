package Simulation;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import Country.Map;
import UI.Main_window;

import javax.swing.JFrame;
// Main initialization
public class Main {
	private static boolean pause=false;
	private static boolean play=false;
	private static boolean initial_play=false;
	private static boolean stop=false;
	
	public static void main(String[] args) throws Exception { 
		Main_window window = new Main_window();
		Map world=window.getmap();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true){
			//Event Tick
			System.out.println("Program is runing....");
			if(stop){
					world=null;
					initial_play=false;
				}
			if(world==null){
				world=window.getmap();
			}
			try {
				if(play && !initial_play){
					for (int i=0;i<world.getSettlements().length;i++)
						world.getSettlements()[i].InitialSimulation();
					initial_play=true;
				}
				if(!pause && !stop && play && initial_play){	
					for (int i=0;i<world.getSettlements().length;i++){
						world.getSettlements()[i].Simulation(world);
					}
					window.updateAll();
					Clock.nextTick();
					Thread.sleep(window.getsleeptime());			
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
		}
	}
	public static File loadFileFunc() {
        FileDialog fd = new FileDialog((Frame) null, "Please choose a file:", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null)
            return null;
        File f = new File(fd.getDirectory(), fd.getFile());
        return f;
	}
	public static void setPause(boolean b){
		pause=b;
	}
	public static void setStop(boolean b){
		stop=b;
	}
	public static void setPlay(boolean b){
		play=b;
	}
	public static void setInitialPlay(boolean b){
		initial_play=b;
	}
	public static boolean getPause(){
		return pause;
	}
	public static boolean getStop(){
		return stop;
	}
}

