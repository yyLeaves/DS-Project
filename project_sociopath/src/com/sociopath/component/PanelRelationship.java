package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Description:
 *
 * @author Yeyang Liu, S2000549
 * Created on: 2021/5/27 19:35, in project com.sociopath.component
 */
public class PanelRelationship extends JPanel{
    private DefaultTableModel model;
    private ArrayList<Student> students = null;

    public PanelRelationship(ArrayList<Student> students) {
        super(new BorderLayout());
        this.students = students;
        int size = students.size();
        Object[] columnNames = new Object[size+1];
        columnNames[0] = "Rep";
        Object[][] rowData = new Object[size][size+1];
        for (int i = 0; i < size; i++) {
            columnNames[i+1] = students.get(i).getId();
            rowData[i][0] = students.get(i).getId();
            for (int j = 0; j < size; j++) {
                Integer rep = Student.getReputation(students.get(i), students.get(j));
                if(rep!=null) {
                    rowData[i][j+1] = rep;
                } else {
                    rowData[i][j+1] = "";
                }
            }
        }

        model = new DefaultTableModel(rowData, columnNames);

        RelationTable relationTable = new RelationTable(model);

        relationTable.setRowHeight(40);

        add(relationTable.getTableHeader(), BorderLayout.NORTH);

        add(relationTable, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("Test");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jf.setContentPane(new PanelRelationship(E0Init.init()));
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    private class RelationTable extends JTable{
        public RelationTable(Object[][] rowData, Object[] columnNames) {
            super(rowData, columnNames);
            // select one line at a time
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setSelectionBackground(Color.LIGHT_GRAY);
            setSelectionForeground(Color.BLUE);

        }

        public RelationTable(TableModel dm) {
            super(dm);
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setSelectionBackground(Color.LIGHT_GRAY);
            setSelectionForeground(Color.BLUE);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
                return false;
        }
    }

}
