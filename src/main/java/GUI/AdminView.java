package GUI;

import BusinessLogic.BaseProduct;
import BusinessLogic.DeliveryServiceProcessing;
import BusinessLogic.MenuItem;
import DataAcces.Serialization;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminView extends JFrame {
    DeliveryServiceProcessing dsp;
    private AdminController adminController;

    private JPanel mainPanel = new JPanel();

    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();

    private JPanel panelAux1 = new JPanel();
    private JPanel panelAux2 = new JPanel();

    private JLabel labelStatus = new JLabel("Status: ");
    private JLabel label1 = new JLabel("Title");
    private JLabel label3 = new JLabel("Rang");
    private JLabel label4 = new JLabel("Calories");
    private JLabel label5 = new JLabel("Protein");

    private JButton btn1 = new JButton("Sterge produs produs compus");
    private JButton btn2 = new JButton("Modifica Produs");
    private JButton btn3 = new JButton("Sterge produs");
    private JButton btn4 = new JButton("Adauga produs compus");
    private JButton btn5 = new JButton("Cauta produs");
    private JButton btn6 = new JButton("Adaugare produs compozit");
    private JButton btn7 = new JButton("Extra");
    private JButton btn8 = new JButton("Import Csv");

    private  JTextField text1 = new JTextField(30);
    private  JTextField text2 = new JTextField(30);

    private JTable table1;
    private JScrollPane scrol1;

    private JTable table2;
    private JScrollPane scrol2;

    private JTable table3;
    private JScrollPane scrol3;


    public AdminView(String name, DeliveryServiceProcessing dsp){
        super(name);
        adminController = new AdminController(this, dsp);
        this.initGUI();
    }

    public void initGUI(){
        this.setSize(700, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.mainPanel.setLayout(new GridLayout(7,0));
        this.setContentPane(this.mainPanel);
        this.initTables();
        this.initComoponents();
        this.setCommands();
    }

    private void initComoponents() {
        table1.setFillsViewportHeight(true);
        panel2.setLayout(new GridLayout(0,2));
        panel2.add(btn2);
        panel2.add(btn3);

        panel3.setLayout(new GridLayout(0,2));
        panel3.add(text2);
        panel3.add(btn8);

        panel4.setLayout(new GridLayout(0,2));
        panel4.add(btn1);
        panel4.add(btn4);

        panel5.setLayout(new GridLayout(0, 2));
        panel5.add(text1);
        panel5.add(btn5);

        panel6.setLayout(new GridLayout(0,2));
        panel6.add(btn6);
        panel6.add(btn7);

        panelAux1.setLayout(new GridLayout(1,0));
        panelAux2.setLayout(new GridLayout(1,0));
        panelAux1.add(scrol1);
        panelAux2.add(scrol2);

        mainPanel.add(panelAux1);
        mainPanel.add(panel5);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panelAux2);
        mainPanel.add(panel6);
    }

    public void initTables(){
        table1 = adminController.updateTable1();
        scrol1 = new JScrollPane(table1);
        panelAux1.removeAll();
        panelAux1.revalidate();
        panelAux1.repaint();
        panelAux1.add(scrol1);

        table2 = adminController.updateTable2();
        scrol2 = new JScrollPane(table2);
        panelAux2.removeAll();
        panelAux2.revalidate();
        panelAux2.repaint();
        panelAux2.add(scrol2);
    }

    private void setCommands(){
        btn1.setActionCommand("Stergeprodusproduscompus");
        btn1.addActionListener(this.adminController);

        btn2.setActionCommand("ModificaProdus");
        btn2.addActionListener(this.adminController);

        btn3.setActionCommand("Stergeprodus");
        btn3.addActionListener(this.adminController);

        btn4.setActionCommand("Adaugaproduscompus");
        btn4.addActionListener(this.adminController);

        btn5.setActionCommand("Cautaprodus");
        btn5.addActionListener(this.adminController);

        btn6.setActionCommand("Adaugareproduscompozit");
        btn6.addActionListener(this.adminController);

        btn7.setActionCommand("Extra");
        btn7.addActionListener(this.adminController);

        btn8.setActionCommand("ImportCsv");
        btn8.addActionListener(this.adminController);
    }

    public String getField1Text(){
        return text1.getText().toString();
    }

    public String getField2Text(){
        return text2.getText().toString();
    }

    public ArrayList<MenuItem> getTable1Data(){
        ArrayList<MenuItem> meniuUpdated = new ArrayList<>();

        for(int i=0; i<adminController.getMenuSize();i++) {
            meniuUpdated.add(new BaseProduct(table1.getValueAt(i,0).toString(),
                    Double.parseDouble(table1.getValueAt(i,1).toString()),
                    Integer.parseInt(table1.getValueAt(i,2).toString()),
                    Integer.parseInt(table1.getValueAt(i,3).toString()),
                    Integer.parseInt(table1.getValueAt(i,4).toString()),
                    Integer.parseInt(table1.getValueAt(i,5).toString()),
                    Integer.parseInt(table1.getValueAt(i,6).toString())));
        }
        return meniuUpdated;
    }

    public MenuItem getTable1SelectedRow(){
        int row = table1.getSelectedRow();
        MenuItem rez = new BaseProduct(table1.getValueAt(row,0).toString(),
                Double.parseDouble(table1.getValueAt(row,1).toString()),
                Integer.parseInt(table1.getValueAt(row,2).toString()),
                Integer.parseInt(table1.getValueAt(row,3).toString()),
                Integer.parseInt(table1.getValueAt(row,4).toString()),
                Integer.parseInt(table1.getValueAt(row,5).toString()),
                Integer.parseInt(table1.getValueAt(row,6).toString()));
        return rez;
    }

    public MenuItem getTable2SelectedRow(){
        int row = table2.getSelectedRow();
        MenuItem rez = new BaseProduct(table2.getValueAt(row,0).toString(),
                Double.parseDouble(table2.getValueAt(row,1).toString()),
                Integer.parseInt(table2.getValueAt(row,2).toString()),
                Integer.parseInt(table2.getValueAt(row,3).toString()),
                Integer.parseInt(table2.getValueAt(row,4).toString()),
                Integer.parseInt(table2.getValueAt(row,5).toString()),
                Integer.parseInt(table2.getValueAt(row,6).toString()));
        return rez;
    }
}
