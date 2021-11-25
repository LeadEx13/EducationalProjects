package UI;

import java.awt.Dimension;
import Virus.VirusManager;
import Virus.Viruses;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class MutationTable extends JDialog {
    private static class MutationModel extends AbstractTableModel {
        private int row_length=Viruses.values().length;
        private final String[] columnNames ={"British Mutation","Chinese Mutation","SouthAfrican Mutation"};
        @Override
        public int getRowCount() {
            return row_length;
        }
        @Override
        public int getColumnCount() {
            return 3;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        	return VirusManager.getValue(rowIndex, columnIndex);
        }
        @Override
        public String getColumnName(int column) {
        	return columnNames[column];
        }
        @Override
        public Class getColumnClass(int column) {
        	return getValueAt(0, column).getClass(); 
        }
        @Override
        public boolean isCellEditable(int row, int col) { 	
        	return true; 
        }   
        @Override
        public void setValueAt(Object aValue, int row, int col) {
        	VirusManager.toogle(row, col);
            fireTableCellUpdated(row, col);
        }
    }

    public MutationTable(JFrame window) {
        super(window, "Edit Mutation ",true);
        MutationModel model = new MutationModel();

        JTable table = new JTable(model);
        String row[]= {"British Variant", "Chinese Variant", "SouthAfrican Variant"};
		RowedTableScroll jt_rowed =new RowedTableScroll(table,row);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(getPreferredSize()));
        table.setFillsViewportHeight(true);
        this.add(new RowedTableScroll(table,row));
        this.getPreferredSize();
        setBounds(390,170,200,300);
        this.pack();
    }
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(700,125);
	}
}
