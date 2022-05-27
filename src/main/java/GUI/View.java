package GUI;

import BusinessLogic.DeliveryServiceProcessing;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    DeliveryServiceProcessing dsp = new DeliveryServiceProcessing();
    private Controller controller;

    private JPanel mainPanel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();

    private JLabel label1 = new JLabel("User");
    private JLabel label2 = new JLabel("Pass");
    private JLabel labelStatus = new JLabel("Status: ");

    private JTextField text1 = new JTextField(30);
    private JTextField text2 = new JTextField(30);

    private JComboBox comb1 = new JComboBox();

    private JButton btn1 = new JButton("Login");
    private JButton btn2 = new JButton("Inregistrare client");

    public View(String name, DeliveryServiceProcessing dsp)
    {
        super(name);
        this.dsp = dsp;
        this.controller = new Controller(this, dsp);
        this.initGUI();
    }

    public void initGUI(){
        this.setSize(400, 170);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.mainPanel.setLayout(new FlowLayout());
        this.setContentPane(this.mainPanel);
        this.initComoponents();
        this.setCommands();
    }

    private void initComoponents() {
        panel1.setLayout(new FlowLayout());
        panel1.add(label1);
        panel1.add(text1);

        panel2.setLayout(new FlowLayout());
        panel2.add(label2);
        panel2.add(text2);

        comb1.setSize(30,5);
        comb1.addItem(new String("- selecteaza user -"));
        comb1.addItem(new String("Administrator"));
        comb1.addItem(new String("Angajat"));
        comb1.addItem(new String("Client"));

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(comb1);
        mainPanel.add(btn1);
        mainPanel.add(btn2);
        mainPanel.add(labelStatus);
    }

    private void setCommands(){
        btn1.setActionCommand("Login");
        btn1.addActionListener(this.controller);
        btn2.setActionCommand("Register");
        btn2.addActionListener(this.controller);
    }

    public String getFiled1Text(){
        return text1.getText().toString();
    }

    public String getFiled2Text(){
        return text2.getText().toString();
    }

    public String getCombBoxSelectedItem(){
        if(comb1.getSelectedIndex()==0)
            return "";
        else
            return comb1.getSelectedItem().toString();
    }

    public void setLabelStatus(String msg){
        labelStatus.setText("Status: " + msg);
    }
}
