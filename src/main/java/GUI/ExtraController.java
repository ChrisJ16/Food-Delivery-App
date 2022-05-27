package GUI;

import BusinessLogic.Client;
import BusinessLogic.DeliveryServiceProcessing;
import BusinessLogic.MenuItem;
import BusinessLogic.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtraController implements ActionListener {
    DeliveryServiceProcessing dsp;
    ExtraView extraView;

    private ArrayList<Order> comandaFiltru = new ArrayList<>();
    private ArrayList<Client> clientiList = new ArrayList<>();
    private ArrayList<MenuItem> menuList = new ArrayList<>();

    public ExtraController(ExtraView extraView, DeliveryServiceProcessing dsp){
        this.extraView=extraView;
        this.dsp=dsp;
    }

    public JTable updateTable1(){
        String[] columnsNames = {"Id comanda", "Id client", "Data"};
        Object[][] data = new Object[50][3];
        int cont=0;
        for(Order o : comandaFiltru){
            data[cont][0] = o.getOrderId();
            data[cont][1] = o.getClientId();
            data[cont][2] = o.getDate();
            cont++;
        }

        return new JTable(data, columnsNames);
    }

    public JTable updateTable3(){
        String[] columnsNames = {"Id Client", "User", "Pass"};
        Object[][] data = new Object[50][3];
        int cont=0;
        for(Client c : clientiList){
            data[cont][0] = c.getClientId();
            data[cont][1] = c.getUser();
            data[cont][2] = c.getPass();
            cont++;
        }
        return new JTable(data, columnsNames);
    }

    public JTable updateTable2(){
        String[] columnsNames = {"Nume produs", "Vanzari"};
        Object[][] data = new Object[13000][3];
        int cont=0;
        for(MenuItem mi : menuList){
            data[cont][0] = mi.getTitle();
            data[cont][1] = mi.getOrdersNumbers();
            cont++;
        }
        return new JTable(data, columnsNames);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "Cautarecomenzi":{
                System.out.println("Cautarecomenzi");

                if(!extraView.getFiled1Text().isEmpty()) {
                    String[] split = extraView.getFiled1Text().split("-");
                    int lhour = Integer.parseInt(split[0]), hhour = Integer.parseInt(split[1]);
                    comandaFiltru = dsp.filterByHour(lhour, hhour);
                }
                else
                {
                    comandaFiltru = new ArrayList<>();
                }
                extraView.initTables();
            }break;
            case "Cautareproduse":{
                System.out.println("Cautareproduse");
                if(!extraView.getFiled2Text().isEmpty()){
                    int times = Integer.parseInt(extraView.getFiled2Text());
                    menuList = dsp.findOrdersByNumber(times);
                }else
                {
                    menuList = new ArrayList<>();
                }
                extraView.initTables();
            }break;
            case "Cautareprodx":{
                System.out.println("Cautareprodx");
                if(!extraView.getFiled3Text().isEmpty()){
                    int ziua = Integer.parseInt(extraView.getFiled3Text());
                    menuList = dsp.findOrderByDay(ziua);
                }else
                {
                    menuList = new ArrayList<>();
                }
                extraView.initTables();
            }break;
            case "Cautareclienti":{
                System.out.println("Cautareclienti");
                if(!extraView.getFiled5Text().isEmpty() && !extraView.getFiled6Text().isEmpty()){
                    int n = Integer.parseInt(extraView.getFiled5Text());
                    int price = Integer.parseInt(extraView.getFiled6Text());
                    clientiList = dsp.filterClientBycriteria(n,price);
                }
                else
                {
                    clientiList = new ArrayList<>();
                }
                extraView.initTables();
            }break;
        }
    }
}
