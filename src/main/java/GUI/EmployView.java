package GUI;

import BusinessLogic.DeliveryServiceProcessing;
import org.w3c.dom.html.HTMLTableCaptionElement;

import javax.swing.*;
import java.awt.*;

public class EmployView extends JFrame {
    private DeliveryServiceProcessing dsp;
    private JPanel mainPanel = new JPanel();
    private EmployController employController;

    public EmployView(){}

    public EmployView(String name, DeliveryServiceProcessing dsp){
        super(name);
        this.dsp=dsp;
        employController = new EmployController(this,dsp);
        initGUI();
    }

    private JPanel panel= new JPanel(new GridLayout(1,0));

    private JTable table;
    private JScrollPane scrol;

    public void initGUI(){
        this.setSize(600, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.mainPanel.setLayout(new GridLayout(2,0));
        this.setContentPane(this.mainPanel);
        this.initTables();
        this.initComoponents();
        this.setCommands();
    }

    public JTable getTable(){
        return table;
    }

    public void setTable(JTable t){
        table = t;
        scrol = new JScrollPane(table);
        panel.removeAll();
        panel.repaint();
        panel.revalidate();
    }

    private void initComoponents() {
        employController.update();

        table.setFillsViewportHeight(true);
        panel.add(scrol);

        mainPanel.add(panel);
    }

    private void initTables() {

    }

    private void setCommands(){

    }
}
