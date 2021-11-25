package UI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import Population.Sick;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import Country.City;
import Country.Kibbutz;
import Country.Settlement;


public class TableMVCStatistic extends JPanel implements ActionListener {
	public enum ColumnName {
		ZERO("Settlement Name", 0), ONE("Settlement Type", 1), TWO("Population", 2), THREE("Ramzor color", 3),
		FOUR("Sick Percentages", 4), FIVE("Vaccine doses", 5), SIX("Dead", 6);
		private final int col;
		private final String colname;
		ColumnName(String name, int col) {
			
			this.col = col;
			this.colname = name;
		}
		public int getcol() {
			return col;
		}
		@Override
		public String toString() {
			return colname;
		}

	}
	public class statisticModel extends AbstractTableModel {
		private static final double initialcontagion = 0.01;
		private Settlement[] data;
		private final String[] columnNames = { "Settlement Name", "Settlement Type", "Population", "Ramzor color",
				"Sick Percentages", "Vaccine doses", "Dead" };;
		public statisticModel(Settlement[] data) {
			this.data = data;
		}
		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return 7;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			Settlement settlement = data[rowIndex];
			switch (columnIndex) {
			case 0:
				return settlement.getName();
			case 1:
				if (settlement instanceof City)
					return "City";
				else if (settlement instanceof Kibbutz)
					return "Kibbutz";
				else
					return "Moshav";
			case 2:
				return settlement.getPopulation();
			case 3:
				return settlement.getRamzorColor();
			case 4:
				return settlement.contagiousPercent();
			case 5:
				return settlement.getVaccine_doses();
			case 6:
				return settlement.getdead();
			}
			return null;
		}
		@Override
		public String getColumnName(int column) {
			return columnNames[column];
		}
		@Override
		public Class getColumnClass(int c) {
			
			return getValueAt(0, c).getClass();
		}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return columnIndex >= 0;
		}
		public void setSick(int row) {
			Random rand = new Random();
			IVirus virus = null;
			Settlement settlement = data[row];
			int x1 = rand.nextInt();
			if (x1 % 3 == 0)
				virus = new BritishVariant();
			else if (x1 % 3 == 1)
				virus = new ChineseVariant();
			else
				virus = new SouthAfricanVariant();
			double numContagion = settlement.getPopulation() * initialcontagion;
			for (int i = 0; i < numContagion && settlement.gethealthy_people().size() > 0; i++) {

				int x = rand.nextInt(settlement.gethealthy_people().size());
				if (settlement.gethealthy_people().get(x).contagion(virus) instanceof Sick) {
					settlement.addSick(virus,x);
				}
			}
			settlement.setRamzorColor(settlement.calculateRamzorGrade());

			fireTableCellUpdated(row, 4);
			fireTableCellUpdated(row, 3);
		}
		public void setdoses(int row, int doses) {
			Settlement settlement = data[row];
			settlement.add_vaccine_doses(doses);

			fireTableCellUpdated(row, 5);
		}
		@Override
		public void fireTableDataChanged() {
			fireTableChanged(new TableModelEvent(this,0,getRowCount() - 1,TableModelEvent.ALL_COLUMNS,TableModelEvent.UPDATE));
		}
		public void updateTable() {
			fireTableDataChanged();
		}
	}

	private TableRowSorter<statisticModel> sorter;
	private JTextField tbFilterText;
	private int col;
	private JTable table;
	private statisticModel model;
	private final JComboBox<ColumnName> column;

	public TableMVCStatistic(Settlement[] data, String row_name) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
		column = new JComboBox<>(ColumnName.values());
		column.setSelectedIndex(0);
		column.addActionListener(this);
		model = new statisticModel(data);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 150));
		table.setFillsViewportHeight(true);
		table.setRowSorter(sorter = new TableRowSorter<statisticModel>(model));
		InitialFilter(row_name);
		this.add(new JLabel("filter:"));
		this.add(top);
		this.add(new JScrollPane(table));
		top.add(new JLabel("Column:"));
		top.add(column);
		top.add(new JLabel("Row:"));
		top.add(tbFilterText = new JTextField());

		tbFilterText.setToolTipText("Filter Row");
		column.setToolTipText("Filter Column");
		tbFilterText.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				newFilter();
			}
			public void removeUpdate(DocumentEvent e) {
				newFilter();
			}
			public void changedUpdate(DocumentEvent e) {
				newFilter();
			}
		});
		this.setVisible(true);
	}
	public void updateModel() {
		model.updateTable();
		newFilter();
	}
	public void setSick() {
		if (table.getSelectedRow() >= 0) {
			model.setSick(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()));
		}
	}
	public void setdose(int doses) {
		if (table.getSelectedRow() >= 0)
			model.setdoses(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()), doses);
	}
	public void actionPerformed(ActionEvent e) {
		col = column.getItemAt(column.getSelectedIndex()).getcol();
		newFilter();
	}
	public int getcol() {
		return col;
	}
	public JTable getTableFromPanel() {
		return table;
	}
	private void newFilter() {
		try {
			sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), getcol()));
		} catch (java.util.regex.PatternSyntaxException e) {
		  }
	}
	private void InitialFilter(String row_name) {
		try {
			sorter.setRowFilter(RowFilter.regexFilter(row_name, getcol()));
		} catch (java.util.regex.PatternSyntaxException e) {
		  }
	}
}
