package GUI;

import BusinessLogic.BaseProduct;
import BusinessLogic.Client;
import BusinessLogic.DeliveryServiceProcessing;
import BusinessLogic.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.GZIPInputStream;

public class ClientView extends JFrame {
    private DeliveryServiceProcessing dsp=new DeliveryServiceProcessing();
    private ClientController clientController;

    private JPanel mainPanel = new JPanel();

    private JPanel panel1 = new JPanel(new GridLayout(0,1));
    private JPanel panel2 = new JPanel(new FlowLayout());
    private JPanel panel3 = new JPanel(new GridLayout(0,1));
    private JPanel panel4 = new JPanel(new FlowLayout());
    private JPanel panel5 = new JPanel(new FlowLayout());

    private JButton btn1 = new JButton("Adauga produs cos");
    private JButton btn2 = new JButton("Sterge produs cos");
    private JButton btn3 = new JButton("Finzalizare comanda produs cos");
    private JButton btn4 = new JButton("Cauta produs");

    private JLabel label1 = new JLabel("Status: ");
    private JTextField text1 = new JTextField(50);

    private JLabel label2 = new JLabel("Rating: ");
    private JTextField text2 = new JTextField(4);

    private JLabel label3 = new JLabel("Calorii:");
    private JTextField text3 = new JTextField(4);

    private JLabel label4 = new JLabel("Proteine:");
    private JTextField text4 = new JTextField(4);

    private JLabel label5 = new JLabel("Grasime:");
    private JTextField text5 = new JTextField(4);

    private JLabel label6 = new JLabel("Sodiu:");
    private JTextField text6 = new JTextField(4);

    private JLabel label7 = new JLabel("Pret: ");
    private JTextField text7 = new JTextField(4);

    private JTable table1;
    private JScrollPane scrol1;

    private JTable table2 = new JTable(10,7);
    private JScrollPane scrol2;

    public ClientView(String name, DeliveryServiceProcessing dsp, Client cl){
        super(name);
        clientController = new ClientController(this, dsp, cl);
        this.dsp=dsp;
        this.initGUI();
    }

    public void initGUI(){
        this.setSize(620, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.mainPanel.setLayout(new GridLayout(4,0));
        this.setContentPane(this.mainPanel);
        this.initTables();
        this.initComoponents();
        this.setCommands();
    }

    private void initComoponents() {
        table1.setFillsViewportHeight(true);
        panel1.add(scrol1);

        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn4);
        panel2.add(text1);

        panel3.add(scrol2);

        panel4.add(btn3);
        panel4.add(label1);

        panel5.add(label2);
        panel5.add(text2);
        panel5.add(label3);
        panel5.add(text3);
        panel5.add(label4);
        panel5.add(text4);
        panel5.add(label5);
        panel5.add(text5);
        panel5.add(label6);
        panel5.add(text6);
        panel5.add(label7);
        panel5.add(text7);
        panel2.add(panel5);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
    }

    public void initTables(){
        table1 = clientController.updateTable1();
        scrol1 = new JScrollPane(table1);
        panel1.removeAll();
        panel1.revalidate();
        panel1.repaint();
        panel1.add(scrol1);

        table2 = clientController.updateTable2();
        scrol2 = new JScrollPane(table2);
        panel3.removeAll();
        panel3.revalidate();
        panel3.repaint();
        panel3.add(scrol2);
    }

    private void setCommands(){
        btn1.setActionCommand("Adaugaproduscos");
        btn1.addActionListener(this.clientController);
        btn2.setActionCommand("Stergeproduscos");
        btn2.addActionListener(this.clientController);
        btn3.setActionCommand("Finzalizarecomandaproduscos");
        btn3.addActionListener(this.clientController);
        btn4.setActionCommand("Cautaprodus");
        btn4.addActionListener(this.clientController);
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

    public String getFields1Text(){
        return text1.getText().toString();
    }

    public Double getRatingLow(){
        String[] str = text2.getText().toString().split("-");
        return Double.parseDouble(str[0]);
    }

    public Double getRatingHigh(){
        String[] str = text2.getText().toString().split("-");
        return Double.parseDouble(str[1]);
    }

    public int getCaloriesLow(){
        String[] str = text3.getText().toString().split("-");
        return Integer.parseInt(str[0]);
    }

    public int getCaloriesHigh(){
        String[] str = text3.getText().toString().split("-");
        return Integer.parseInt(str[1]);
    }

    public int getProteinLow(){
        String[] str = text4.getText().toString().split("-");
        return Integer.parseInt(str[0]);
    }

    public int getProteinHigh(){
        String[] str = text4.getText().toString().split("-");
        return Integer.parseInt(str[1]);
    }

    public int getFatLow(){
        String[] str = text5.getText().toString().split("-");
        return Integer.parseInt(str[0]);
    }

    public int getFatHigh(){
        String[] str = text5.getText().toString().split("-");
        return Integer.parseInt(str[1]);
    }

    public int getSodiumLow(){
        String[] str = text6.getText().toString().split("-");
        return Integer.parseInt(str[0]);
    }

    public int getSodiumHigh(){
        String[] str = text6.getText().toString().split("-");
        return Integer.parseInt(str[1]);
    }

    public int getPriceLow(){
        String[] str = text7.getText().toString().split("-");
        return Integer.parseInt(str[0]);
    }

    public int getPriceHigh(){
        String[] str = text7.getText().toString().split("-");
        return Integer.parseInt(str[1]);
    }
}
