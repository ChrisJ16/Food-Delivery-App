package GUI;

import BusinessLogic.DeliveryServiceProcessing;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;

import javax.swing.*;
import java.security.spec.ECFieldF2m;
import java.util.ArrayList;

public class EmployController implements ObservableGUI{
    private EmployView employView;
    private DeliveryServiceProcessing dsp = new DeliveryServiceProcessing();

    public EmployController(){}

    public EmployController(EmployView employView, DeliveryServiceProcessing dsp){
        this.employView=employView;
        this.dsp=dsp;
    }

    @Override
    public void update() {
        String[] columnNames = {"Id comanda", "Id client", "Data/ora", "Continut"};
        Object[][] data = new Object[50][4];
        int cont = 0;
        for (Order o : dsp.getOrder().keySet())
        {
            data[cont][0] = o.getOrderId();
            data[cont][1] = o.getClientId();
            data[cont][2] = o.getDate();
            data[cont][3] = orderContet(dsp.getOrder().get(o));
            cont++;
        }
        employView.setTable(new JTable(data, columnNames));
        employView.getTable().repaint();
        employView.getTable().revalidate();
    }

    public String orderContet(ArrayList<MenuItem> items)
    {
        String rez = "";
        for (MenuItem mi : items)
            rez += mi.getTitle() + "; ";
        return rez;
    }
}
