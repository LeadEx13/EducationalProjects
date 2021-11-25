package UI;

import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;

public class MutationTable extends JDialog 
{
    private static class MutationModel extends AbstractTableModel {
        private IVirus[] data;
        private final String[] columnNames ={"British Mutation","Chinese Mutation","SouthAfrican Mutation"};

        public MutationModel(IVirus[] data) {
        	data[0]=new BritishVariant();
        	data[1]=new ChineseVariant();
        	data[2]=new SouthAfricanVariant();
            this.data = data;
        }
        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        	IVirus virus = data[rowIndex];
           if(rowIndex == 0) {
        	   switch (columnIndex) {
               case 0: 
            	   return BritishVariant.get_british_m();
               case 1:
   	    			return BritishVariant.get_chinese_m();
               case 2: return BritishVariant.get_southafrican_m();
        	   }
           }
           if(rowIndex == 1) {
        	   switch (columnIndex) {
               case 0: 
            	   return  ChineseVariant.get_british_m();
               case 1:
   	    			return  ChineseVariant.get_chinese_m();
               case 2: return  ChineseVariant.get_southafrican_m();
        	   }
           }
           if(rowIndex == 2) {
        	   switch (columnIndex) {
               case 0: 
            	   return  SouthAfricanVariant.get_british_m();
               case 1:
   	    			return  SouthAfricanVariant.get_chinese_m();
               case 2: return  SouthAfricanVariant.get_southafrican_m();
        	   }
           }
           return null;
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
        	boolean bl=(Boolean) aValue;
            if(row == 0){
            	if(col==0)
            		BritishVariant.set_british_m(bl);
            	if(col==1)
            		BritishVariant.set_chinese_m(bl);
            	if(col==2)
            		BritishVariant.set_southafrican_m(bl);
            }
            if(row == 1){
            	if(col==0)
            		ChineseVariant.set_british_m(bl);
            	if(col==1)
            		ChineseVariant.set_chinese_m(bl);
            	if(col==2)
            		ChineseVariant.set_southafrican_m(bl);
            }
            if(row == 2){
            	if(col==0)
            		SouthAfricanVariant.set_british_m(bl);
            	if(col==1)
            		SouthAfricanVariant.set_chinese_m(bl);
            	if(col==2)
            		SouthAfricanVariant.set_southafrican_m(bl);
            }
            fireTableCellUpdated(row, col);
        }
    }

    public MutationTable(JFrame window,IVirus[] data) {
        super(window, "Edit Mutation ",true);
        MutationModel model = new MutationModel(data);

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
