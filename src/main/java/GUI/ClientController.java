package GUI;

import BusinessLogic.Client;
import BusinessLogic.DeliveryServiceProcessing;
import BusinessLogic.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ClientController implements ActionListener {
    private DeliveryServiceProcessing dsp = new DeliveryServiceProcessing();
    private ClientView clientView;
    private ArrayList<MenuItem> composeMenu = new ArrayList<>();
    private ArrayList<MenuItem> initialMenu;
    private Client loggedClient;

    private EmployController employController;

    public ClientController(ClientView clientView, DeliveryServiceProcessing dsp, Client cl) {
        this.clientView = clientView;
        this.dsp = dsp;
        this.loggedClient = cl;
        this.employController = new EmployController(new EmployView(), this.dsp);
    }

    public JTable updateTable1(){
        String[] columnNames = {"Title","Rating","Calories","Protein","Fat","Sodium","Price"};
        String[][] data = new String[13000][7];
        int cont=0;
        ArrayList<MenuItem> meniu = dsp.getMeniu();
        for(MenuItem mi : meniu){
            data[cont][0] = mi.getTitle();
            data[cont][1] = String.valueOf(mi.getRating());
            data[cont][2] = String.valueOf(mi.getCalories());
            data[cont][3] = String.valueOf(mi.getProtein());
            data[cont][4] = String.valueOf(mi.getFat());
            data[cont][5] = String.valueOf(mi.getSodium());
            data[cont][6] = String.valueOf(mi.getPrice());
            cont++;
        }
        return new JTable(data,columnNames);
    }

    public JTable updateTable2(){
        String[] columnNames = {"Title","Rating","Calories","Protein","Fat","Sodium","Price"};
        String[][] data = new String[13000][7];
        int cont=0;

        for(MenuItem mi : composeMenu){
            data[cont][0] = mi.getTitle();
            data[cont][1] = String.valueOf(mi.getRating());
            data[cont][2] = String.valueOf(mi.getCalories());
            data[cont][3] = String.valueOf(mi.getProtein());
            data[cont][4] = String.valueOf(mi.getFat());
            data[cont][5] = String.valueOf(mi.getSodium());
            data[cont][6] = String.valueOf(mi.getPrice());
            cont++;
        }
        return new JTable(data,columnNames);
    }

    public int getMenuSize(){
        return dsp.getMeniu().size();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            switch (cmd){
                case "Adaugaproduscos":{
                    System.out.println("Adaugaproduscos");
                    composeMenu.add(clientView.getTable1SelectedRow());
                    clientView.initTables();
                }break;
                case "Stergeproduscos":{
                    System.out.println("Stergeproduscos");
                    composeMenu.remove(clientView.getTable2SelectedRow());
                    clientView.initTables();
                }break;
                case "Cautaprodus":{
                    System.out.println("Cautaprodus");
                    String title = clientView.getFields1Text();
                    Double lrating=clientView.getRatingLow(),hrating=clientView.getRatingHigh();
                    int lcalories=clientView.getCaloriesLow(),hcaloreis=clientView.getCaloriesHigh();
                    int lprotein=clientView.getProteinLow(),hprotein=clientView.getProteinHigh();
                    int lfat=clientView.getFatLow(),hfat=clientView.getFatHigh();
                    int lsodium=clientView.getSodiumLow(),hsodium=clientView.getSodiumHigh();
                    int lprice=clientView.getPriceLow(),hprice=clientView.getPriceHigh();

                    /*
                    System.out.println(title + " rating" + lrating + " si " + hrating +
                                        " calorii " + lcalories +  " si " + hcaloreis +
                            " protein " + lprotein +  " si " + hprotein +
                            " fat " + lfat +  " si " + hfat +
                            " sodium " + lsodium +  " si " + hsodium +
                            " price " + lprice +  " si " + hprice);
                     */

                    if(!title.isEmpty()) {
                        ArrayList<MenuItem> listTest = dsp.findByCriteria(title,
                                lrating, hrating,
                                lcalories, hcaloreis,
                                lprotein, hprotein,
                                lfat, hfat,
                                lsodium, hsodium,
                                lprice, hprice);
                        initialMenu = dsp.getMeniu();
                        dsp.setMeniu(listTest);
                    }
                    else
                        dsp.setMeniu(initialMenu);
                    clientView.initTables();
                }break;
                case "Finzalizarecomandaproduscos":{
                    System.out.println("Finzalizarecomandaproduscos");
                    dsp.creazaComanda(loggedClient.getClientId(),composeMenu, employController);
                    //dsp.createOrders(loggedClient.getClientId(),composeMenu);
                    WriteToFile(loggedClient.getClientId(), composeMenu);
                }break;
            }
    }

    public void WriteToFile(int id, ArrayList<MenuItem> menuToWrite){
        try {
            File myObj = new File("bill"+id+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String msg = new String("Comanda realziata cu succes, id " + id + ". Produsele din cos:\n");
        int price = 0;
        for(MenuItem mi : menuToWrite){
            msg += mi.toString() + "\n";
            price+=mi.getPrice();
        }
        msg+="Pret: " + price;
        try {
            FileWriter myWriter = new FileWriter("bill"+id+".txt");
            myWriter.write(msg);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
