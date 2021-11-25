package UI;

import java.awt.BorderLayout;
import IO.statisticsFile;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Simulation.Main;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import IO.simulationFile;
import Country.Map;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import java.io.File;
import java.io.IOException;
import Simulation.Clock;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main_window extends JFrame {
	private Map world = null;
	private int sleep_time = 30000;
	private MapPanel map_panel;

	public Main_window() throws IOException {
		super("Main Window");
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		BorderLayout myBorderLayout = new BorderLayout();
		getContentPane().setLayout(myBorderLayout);
		setBounds(390, 170, 200, 300);
		setPreferredSize(new Dimension(1000, 700));
		menu();
		getContentPane().add(map_panel(), BorderLayout.CENTER);
		simulationSpeedSlider();

		this.pack();
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Map getmap() {
		return world;
	}

	public int getsleeptime() {
		return sleep_time;
	}

	public void simulationSpeedSlider() {
		JPanel simulationspeed_p = new JPanel();
		simulationspeed_p.setLayout(new BoxLayout(simulationspeed_p, BoxLayout.LINE_AXIS));
		JSlider simulation_speed = new JSlider();
		simulation_speed.setMajorTickSpacing(5);
		simulation_speed.setMinorTickSpacing(1);
		simulation_speed.setMaximum(60);
		simulation_speed.setValue(30);
		simulation_speed.setToolTipText("Tick per second");
		simulation_speed.setPaintLabels(true);
		simulation_speed.setPaintTicks(true);
		JButton b_speed = new JButton("Set");
		b_speed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sleep_time = 1000 * simulation_speed.getValue();
			}
		});

		simulationspeed_p.add(simulation_speed);
		simulationspeed_p.add(b_speed);

		getContentPane().add(simulationspeed_p, BorderLayout.SOUTH);
	}
	public MapPanel map_panel() {
		map_panel = new MapPanel();
		map_panel.setVisible(true);
		map_panel.repaint();
		map_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(world==null)
					return;
				for (int i = 0; i < world.getSettlements().length; i++) {

					
					int x_settl =(int)( world.getSettlements()[i].getLocation().getPosition().getX()*map_panel.getDimentionX());
					int y_settl = (int)(world.getSettlements()[i].getLocation().getPosition().getY()*map_panel.getDimentionY());
					int h_settl = (int)(world.getSettlements()[i].getLocation().getSize().getHeight()*map_panel.getDimentionY());
					int w_settl = (int)(world.getSettlements()[i].getLocation().getSize().getWidth()*map_panel.getDimentionX());

					if (x_settl <= e.getPoint().getX() && e.getPoint().getX() <= x_settl + w_settl
							&& y_settl <= e.getPoint().getY() && e.getPoint().getY() <= y_settl + h_settl) {
						System.out.println(world.getSettlements()[i].getName());
						StatisticWindow statistic_d = statisticWindow(world, world.getSettlements()[i].getName());
						statistic_d.setVisible(true);
						break;
					}

				}
			}
		});
		return map_panel;
	}

	public StatisticWindow statisticWindow(Map world, String row_name) {
		StatisticWindow statistic_d = new StatisticWindow(this, world, row_name);
		return statistic_d;
	}
	public void menu() throws IOException {
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		JMenu file = new JMenu("File");
		JMenuItem statistics = new JMenuItem("Statistics");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem play = new JMenuItem("Play");
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem stop = new JMenuItem("Stop");
		JMenuItem log = new JMenuItem("save log file");
		
		
		load.setEnabled(true);
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load.setEnabled(false);
				play.setEnabled(true);

				statistics.setEnabled(true);
				File file = Main.loadFileFunc();
				simulationFile simulationFile = new simulationFile();
				try {
					world = simulationFile.loadMap(file);
					map_panel.set_map(world);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		statistics.setSelected(true);
		statistics.setEnabled(false);
		statistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticWindow statistic_d = statisticWindow(world, "");
				statistic_d.setVisible(true);
			}
		});

		JMenuItem edit_mutations = new JMenuItem("Edit Mutations");
		IVirus[] variants = { new BritishVariant(), new ChineseVariant(), new SouthAfricanVariant() };
		MutationTable edit_mutations_d = new MutationTable(this, variants);
		
		edit_mutations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit_mutations_d.setVisible(true);
			}
		});
		//log file
				log.setSelected(true);
				log.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/**
						 * Upload Step: Get the location of the upload file and load the entire map.
						 */
						statisticsFile.loadFileFunc();

					}
				});

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		file.add(load);
		file.addSeparator();
		file.add(statistics);
		file.addSeparator();
		file.add(log);
		file.addSeparator();
		file.add(edit_mutations);
		file.addSeparator();
		file.add(exit);
		menu.add(file);
		JMenu submenu_simulation = new JMenu("Simulation");

		play.setEnabled(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play.setEnabled(false);
				pause.setEnabled(true);
				stop.setEnabled(true);
				Main.setPlay(true);
				Main.setPause(false);
				Main.setStop(false);

			}
		});

		pause.setEnabled(false);
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause.setEnabled(false);
				play.setEnabled(true);
				stop.setEnabled(true);
				Main.setPlay(false);
				Main.setPause(true);
				Main.setStop(false);
			}
		});

		stop.setEnabled(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				play.setEnabled(false);
				pause.setEnabled(false);
				stop.setEnabled(false);
				load.setEnabled(true);
				statistics.setEnabled(false);
				Main.setPlay(false);
				Main.setStop(true);
				Main.setPause(false);
				Main.setInitialPlay(false);
				world = null;
				map_panel.set_map(null);

			}
		});

		JMenuItem set_tick = new JMenuItem("Set tick per day");
		SpinnerModel tick_per_day = new SpinnerNumberModel(1, 1, 100, 1);
		JSpinner spinner = new JSpinner(tick_per_day);
		JPanel p_tick = new JPanel();
		JButton b = new JButton("Set");
		JLabel l_tick = new JLabel("ticks:");
		p_tick.add(l_tick);
		p_tick.add(spinner);
		p_tick.add(b);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int spinner_tick = (Integer) spinner.getValue();
				Clock.set_tick_per_day(spinner_tick);
			}
		});
		JDialog set = new JDialog(this, "Set tick per day", true);
		set.setBounds(390, 170, 200, 300);
		set.getContentPane().add(p_tick);
		set.pack();
		set_tick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				set.setVisible(true);
			}
		});

		submenu_simulation.add(play);
		submenu_simulation.addSeparator();
		submenu_simulation.add(pause);
		submenu_simulation.addSeparator();
		submenu_simulation.add(stop);
		submenu_simulation.addSeparator();
		submenu_simulation.add(set_tick);
		menu.add(submenu_simulation);

		JMenu submenu_help = new JMenu("Help");

		JMenuItem help = new JMenuItem("Help");

		JDialog help_dialog = new JDialog(this, "Help", true);
		help_dialog.setBounds(390, 170, 200, 300);
		JPanel help_p = new JPanel();
		help_p.setLayout(new BoxLayout(help_p, BoxLayout.PAGE_AXIS));

		JLabel l = new JLabel("<html><br/>" + "Simulation of Corona<br/>");

		help_p.add(l);

		help_dialog.getContentPane().add(help_p);
		help_dialog.pack();
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help_dialog.setVisible(true);
			}
		});

		JMenuItem about = new JMenuItem("About");
		JDialog about_dialog = new JDialog(this, "About", true);
		about_dialog.setBounds(390, 170, 200, 300);
		JPanel about_p = new JPanel();
		about_p.setLayout(new BoxLayout(about_p, BoxLayout.PAGE_AXIS));
		JLabel lb = new JLabel("<html>Corona Simulation made by Alex and Vlad<br/>");

		about_p.add(lb);
		about_dialog.getContentPane().add(about_p);
		about_dialog.pack();
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about_dialog.setVisible(true);
			}
		});

		submenu_help.add(help);
		submenu_help.addSeparator();
		submenu_help.add(about);

		menu.add(submenu_help);
	}

	public void updateAll() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				map_panel.repaint();
				StatisticWindow.update_statistics();
			}
		});
	}
}
