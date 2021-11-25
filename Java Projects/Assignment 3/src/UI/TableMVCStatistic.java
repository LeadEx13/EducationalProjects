package UI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import Country.City;
import Country.Kibbutz;
import Country.Settlement;
import Population.Sick;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TableMVCStatistic extends JPanel implements ActionListener 
{
	public enum ColumnName {
		ZERO("Settlement Name", 0), ONE("Settlement Type", 1), TWO("Population", 2), THREE("Ramzor color", 3),
		FOUR("Sick Percentages", 4), FIVE("Vaccine doses", 5), SIX("Dead", 6);

		// data members
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
		/**
		 * this class provides default implementations for most of the methods in the
		 * TableModel interface
		 */

		// data members
		private static final double initialcontagion = 0.01;
		private Settlement[] data;
		private final String[] columnNames = { "Settlement Name", "Settlement Type", "Population", "Ramzor color",
				"Sick Percentages", "Vaccine doses", "Dead" };;

		public statisticModel(Settlement[] data) {
			/**
			 * Constructor
			 * 
			 * @param data all the settlements in the map
			 */
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
			/**
			 * get column name
			 * 
			 * @param column the number of column
			 * @return the name of the column
			 */
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

		public void setdouses(int row, int douses) {
			/**
			 * set number of douses in settlement
			 * 
			 * @param row    row index
			 * @param douses number of douses to add the settlement
			 */
			Settlement settlement = data[row];
			settlement.add_vaccine_doses(douses);

			fireTableCellUpdated(row, 5);
		}

		@Override
		public void fireTableDataChanged() {
			/**
			 * update all data
			 */
			fireTableChanged(new TableModelEvent(this, // tableModel
					0, // firstRow
					getRowCount() - 1, // lastRow
					TableModelEvent.ALL_COLUMNS, // column
					TableModelEvent.UPDATE)); // changeType
		}

		public void updateTable() {
			fireTableDataChanged();
		}
	}

	// data members
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
		/**
		 * update data model
		 */
		model.updateTable();
		newFilter();
	}

	public void setSick() {
		/**
		 * sick setter
		 */
		if (table.getSelectedRow() >= 0) {
			model.setSick(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()));
		}

	}

	public void setDouse(int douses) {
		
		if (table.getSelectedRow() >= 0)
			model.setdouses(table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()), douses);
	}

	public void actionPerformed(ActionEvent e) {
		col = column.getItemAt(column.getSelectedIndex()).getcol();
		newFilter();

	}

	public int getcol() {
		
		return col;
	}

	public JTable getTableFromPanel() {
		/**
		 * @return statistic table
		 */
		return table;
	}

	private void newFilter() {
		/**
		 * filter the table by select
		 */
		try {
			sorter.setRowFilter(RowFilter.regexFilter(tbFilterText.getText(), getcol()));
		} catch (java.util.regex.PatternSyntaxException e) {
			// If current expression doesn't parse, don't update.
		}
	}

	private void InitialFilter(String row_name) {
		/**
		 * filter by the selected settlement
		 */
		try {
			sorter.setRowFilter(RowFilter.regexFilter(row_name, getcol()));
		} catch (java.util.regex.PatternSyntaxException e) {
			// If current expression doesn't parse, don't update.
		}
	}


}
