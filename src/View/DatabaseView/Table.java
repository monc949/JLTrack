package View.DatabaseView;



import javax.swing.JFrame;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




/**
 * Basic Layout for the Database Management Windows
 */
public class Table extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

        public Table(int i) {
            if (i == 1) {
                JFrame frame = new PlayerView();
                frame.setTitle("Product Table");
                frame.setSize(1200, 700);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } 

            if (i == 2) {
                JFrame frame = new MatchView();
                frame.setTitle("Match Table");
                frame.setSize(1200, 700);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } 
            
    }



    
}