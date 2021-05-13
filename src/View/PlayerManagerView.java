package View;

import Controller.PlayerController;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PlayerManagerView {
    private JPanel panel1;
    public final DefaultTableModel tableModel = new PlayerController().retrievePlayerTable();
    private JTable playerTable = new JTable(new DefaultTableModel(tableModel)) {
        @Override
        public int getRowCount() {
            return 0;
        }

        @Override
        public int getColumnCount() {
            return 0;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return null;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
    })

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
