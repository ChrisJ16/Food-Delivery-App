package GUI;

import BusinessLogic.DeliveryServiceProcessing;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;


public class ExtraView extends JFrame {
    DeliveryServiceProcessing dsp;
    private ExtraController extraController;

    private JPanel mainPanel = new JPanel();

    private JPanel panel1 = new JPanel(new GridLayout(1,0));
    private JPanel panel2 = new JPanel(new FlowLayout());
    private JPanel panel3 = new JPanel(new GridLayout(1,0));
    private JPanel panel4 = new JPanel(new FlowLayout());
    private JPanel panel5 = new JPanel(new GridLayout(1,0));
    private JPanel panel6 = new JPanel(new FlowLayout());

    private JTable table1;
    private JScrollPane scrol1;

    private JTable table2 = new JTable(10,7);
    private JScrollPane scrol2=new JScrollPane(table2);

    private JTable table3 = new JTable(10,7);
    private JScrollPane scrol3=new JScrollPane(table3);

    private JLabel label1 = new JLabel("Interval de ora:");
    private JTextField text1 = new JTextField(30);
    private JButton btn1 = new JButton("Cautare comenzi");

    private JLabel label2 = new JLabel("Produse comandate de mai mult de:");
    private JTextField text2 = new JTextField(10);
    private JButton btn2 = new JButton("Cautare produse");

    private JLabel label3 = new JLabel("Produse comandate din ziua ");
    private JTextField text3 = new JTextField(10);
    private JTextField text4 = new JTextField(10);
    private JButton btn3 = new JButton("Cautare prod. din ziua x");

    private JLabel label4 = new JLabel("Clienti care au comandat >= x ori ca o val >= y");
    private JTextField text5 = new JTextField(10);
    private JTextField text6 = new JTextField(10);
    private JButton btn4 = new JButton("Cautare clienti");

    public ExtraView(String name, DeliveryServiceProcessing dsp){
        super(name);
        this.dsp = dsp;
        this.extraController = new ExtraController(this, this.dsp);
        this.initGUI();
    }

    public void initGUI(){
        this.setSize(600, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.mainPanel.setLayout(new GridLayout(6,0));
        this.setContentPane(this.mainPanel);
        this.initTables();
        this.initComoponents();
        this.setCommands();
    }

    private void initComoponents() {
        table1.setFillsViewportHeight(true);
        table1.setFillsViewportHeight(true);
        table3.setFillsViewportHeight(true);
        panel1.add(scrol1);

        panel2.add(label1);
        panel2.add(text1);
        panel2.add(btn1);

        panel3.add(scrol2);

        panel4.add(label2);
        panel4.add(text2);
        panel4.add(btn2);
        panel4.add(label3);
        panel4.add(text3);
        //panel4.add(text4);
        panel4.add(btn3);

        panel5.add(scrol3);
        panel6.add(label4);
        panel6.add(text5);
        panel6.add(text6);
        panel6.add(btn4);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(panel6);
    }

    public void initTables(){
        table1 = extraController.updateTable1();
        scrol1 = new JScrollPane(table1);
        panel1.removeAll();
        panel1.repaint();
        panel1.revalidate();
        panel1.add(scrol1);

        table2 = extraController.updateTable2();
        scrol2 = new JScrollPane(table2);
        panel3.removeAll();
        panel3.repaint();
        panel3.revalidate();
        panel3.add(scrol2);

        table3 = extraController.updateTable3();
        scrol3 = new JScrollPane(table3);
        panel5.removeAll();
        panel5.repaint();
        panel5.revalidate();
        panel5.add(scrol3);
    }

    private void setCommands(){
        btn1.setActionCommand("Cautarecomenzi");
        btn1.addActionListener(this.extraController);
        btn2.setActionCommand("Cautareproduse");
        btn2.addActionListener(this.extraController);
        btn3.setActionCommand("Cautareprodx");
        btn3.addActionListener(this.extraController);
        btn4.setActionCommand("Cautareclienti");
        btn4.addActionListener(this.extraController);
    }

    public String getFiled1Text(){
        return text1.getText().toString();
    }

    public String getFiled2Text(){
        return text2.getText().toString();
    }

    public String getFiled3Text(){
        return text3.getText().toString();
    }

    public String getFiled4Text(){
        return text4.getText().toString();
    }

    public String getFiled5Text(){
        return text5.getText().toString();
    }

    public String getFiled6Text(){
        return text6.getText().toString();
    }
}
