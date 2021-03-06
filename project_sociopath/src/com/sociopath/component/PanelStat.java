package com.sociopath.component;

import com.sociopath.events.E0Init;
import com.sociopath.events.Student;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Description: Student Stats table panel
 *
 */
public class PanelStat extends JPanel {
    private ArrayList<Student> students = null;

    private DefaultTableModel model;

    public PanelStat(ArrayList<Student> students) {
        super(new BorderLayout());
        this.students = students;

        int size = students.size();
        Object[] columnNames = {"Student ID", "Dive(%)", "lunch start", "lunch period", "friends"};
        Object[][] rowData = new Object[size][5];
        for (int i = 0; i < students.size(); i++) {
            rowData[i][0] = students.get(i).getId();
            rowData[i][1] = students.get(i).getDive();
            rowData[i][2] = students.get(i).getLunchStart();
            rowData[i][3] = students.get(i).getLunchPeriod();
            ArrayList<Student> friendsList = Student.getFriends(students, students.get(i));
            StringBuilder friendsStr = new StringBuilder();
            for (Student student : friendsList) {
                friendsStr.append(student.getId()).append(" ");
            }
            rowData[i][4] = friendsStr;
        }

        model = new DefaultTableModel(rowData, columnNames);

        InfoTable infoTable = new InfoTable(model);
//        InfoTable infoTable = new InfoTable(rowData, columnNames);

        // set height of line
        infoTable.setRowHeight(40);

        add(infoTable.getTableHeader(), BorderLayout.NORTH);

        add(infoTable, BorderLayout.CENTER);

        editListener();
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame("Test");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jf.setContentPane(new PanelStat(E0Init.init()));
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    private class InfoTable extends JTable {
        // TODO: 2021/6/7

        public InfoTable(TableModel dm) {
            super(dm);
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setSelectionBackground(new Color(0xDBD2F3));
            setSelectionForeground(new Color(0x3D3D99));
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // set column "id", "friends" not editable
            if (column == 0 || column == 4)
                return false;
            return true;
        }

    }

    private void editListener() {
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int type = e.getType();
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (type == TableModelEvent.UPDATE) {

                    Object key = model.getValueAt(row, column);

                    switch (column) {
                        case 1:
                            int diveKey;
                            int diveOriginal = students.get(row).getDive();

                            // if is an integer
                            try {
                                Integer.parseInt((String) key);
                            } catch (NumberFormatException exception) {
                                System.out.println("== ERROR! ==\n Dive rate must be an integer!\n");
                                JOptionPane.showMessageDialog(null, "Dive rate must be an integer!\n", "ERROR!", JOptionPane.WARNING_MESSAGE);
                                // value must be a string
                                model.setValueAt("" + diveOriginal, row, column);
                                break;
                            }

                            diveKey = Integer.parseInt((String) key);

                            // if is a descent integer
                            if (diveKey < 0 || diveKey > 100) {
                                System.out.println("== ERROR! ==\n Dive number isn't in the right range!\n");
                                JOptionPane.showMessageDialog(null, "Dive rate must be in [0,100]!", "ERROR!", JOptionPane.WARNING_MESSAGE);
                                model.setValueAt("" + diveOriginal, row, column);
                                break;
                            }

                            if (diveKey != diveOriginal) {
                                System.out.println("== Event == \n You modified the dive rate of Student " + (row + 1) + " from " + diveOriginal + " to " + key + "\n");
                            }

                            students.get(row).setDive(diveKey);

                            break;
                        case 3:
                            int lunchPeriodKey;
                            int lunchPeriodOriginal = students.get(row).getLunchPeriod();

                            // if is an integer
                            try {
                                Integer.parseInt((String) key);
                            } catch (NumberFormatException exception) {
                                System.out.println("== ERROR! ==\n Lunch period must be an integer!");
                                JOptionPane.showMessageDialog(null, "Lunch period must be an integer!\n", "ERROR!", JOptionPane.WARNING_MESSAGE);
                                // value must be a string
                                model.setValueAt("" + lunchPeriodOriginal, row, column);
                                break;
                            }

                            lunchPeriodKey = Integer.parseInt((String) key);

                            // if is a descent integer
                            if (lunchPeriodKey < 6 || lunchPeriodKey > 59) {
                                System.out.println("== ERROR! ==\n Lunch period isn't in the right range!\n");
                                JOptionPane.showMessageDialog(null, "Lunch period must be in (5,60)!", "ERROR!", JOptionPane.WARNING_MESSAGE);

                                model.setValueAt("" + lunchPeriodOriginal, row, column);
                                break;
                            }

                            // if lunch time is in the right range
                            int lunchStart = students.get(row).getLunchStart();
                            int startTimeInMinute = (lunchStart - 1100) / 100 * 60 + lunchStart % 100;
                            if (startTimeInMinute + lunchPeriodKey > 180) {
//                                System.out.println(startTimeInMinute);
//                                System.out.println(startTimeInMinute + lunchPeriodKey);
                                System.out.println("== ERROR! ==\n Lunch time isn't in the right range!\n");
                                JOptionPane.showMessageDialog(null, "Lunch time must be in [1100,1400]!", "ERROR!", JOptionPane.WARNING_MESSAGE);
                                model.setValueAt("" + lunchPeriodOriginal, row, column);
                                break;
                            }

                            if (lunchPeriodKey != lunchPeriodOriginal) {
                                System.out.println("== Event == \n You modified the lunch period of Student " + (row + 1) + " from " + lunchPeriodOriginal + " to " + key + "\n");
                                students.get(row).setLunchPeriod(lunchPeriodKey);
                            }


                            break;

                        case 2:
                            int lunchStartKey;
                            int startOriginal = students.get(row).getLunchStart();

                            try {
                                Integer.parseInt((String) key);
                            } catch (NumberFormatException exception) {
                                System.out.println("== ERROR! ==\n Integer Required!\n");
                                JOptionPane.showMessageDialog(null, "Lunch start must be an integer!\n", "ERROR!", JOptionPane.WARNING_MESSAGE);
                                // value must be a string
                                model.setValueAt("" + startOriginal, row, column);
                                break;
                            }

                            lunchStartKey = Integer.parseInt((String) key);

                            if (lunchStartKey < 1100 || lunchStartKey > 1359 || (lunchStartKey > 1159 && lunchStartKey < 1200) || (lunchStartKey > 1259 && lunchStartKey < 1300)) {
                                System.out.println("== ERROR! ==\n Lunch start isn't in the right range!\n");
                                JOptionPane.showMessageDialog(null, "Lunch start must be in 11:00-14:00!", "ERROR!", JOptionPane.WARNING_MESSAGE);
                                model.setValueAt("" + startOriginal, row, column);
                                break;
                            }

                            int lunchStartInMinute = (lunchStartKey - 1100) / 100 * 60 + lunchStartKey % 100;
                            int lunchPeriod = students.get(row).getLunchPeriod();
                            if (lunchStartInMinute + lunchPeriod > 180) {
                                System.out.println("== ERROR! ==\n Lunch period isn't in the right range!\n");
                                JOptionPane.showMessageDialog(null, "Lunch period must be in 11:00-14:00!", "ERROR!", JOptionPane.WARNING_MESSAGE);
                                model.setValueAt("" + startOriginal, row, column);
                                break;
                            }

                            if (startOriginal != lunchStartKey) {
                                students.get(row).setLunchStart(lunchStartKey);
                                System.out.println("== Event == \n You modified the lunch start of Student " + (row + 1) + " from " + startOriginal + " to " + key + "\n");
                            }

                            break;


                    }
                }

            }
        });
    }

}
