package GUI;

import BusinessLogic.DeliveryServiceProcessing;
import BusinessLogic.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminController implements ActionListener {
    public AdminView adminView;
    public DeliveryServiceProcessing dsp;
    private ArrayList<MenuItem> initialMenu;
    private ArrayList<MenuItem> compositeMenu = new ArrayList<>();

    public AdminController(AdminView adminView, DeliveryServiceProcessing dsp){
        this.adminView = adminView;
        this.dsp=dsp;
        this.initialMenu = dsp.getMeniu();
    }

    public int getMenuSize(){
        return dsp.getMeniu().size();
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

    public JTable updateTable1AfterSearch(ArrayList<MenuItem> menu){
        String[] columnNames = {"Title","Rating","Calories","Protein","Fat","Sodium","Price"};
        String[][] data = new String[13000][7];
        int cont=0;
        ArrayList<MenuItem> meniu = menu;
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
        String[][] data = new String[10][7];

        int cont = 0;
        for (MenuItem mi : compositeMenu) {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "ModificaProdus":
            {
                System.out.println("ModificaProdus");
                ArrayList<MenuItem> updatedProducts=adminView.getTable1Data();
                ArrayList<MenuItem> oldMenu = dsp.getMeniu();
                int cont=0;
                for(MenuItem mi : oldMenu){
                    MenuItem miiix = updatedProducts.get(cont);
                    if(!mi.equals(miiix))
                    {
                        dsp.updateProdus(mi.getTitle(), updatedProducts.get(cont));
                        System.out.println(mi.toString());
                    }
                    cont++;
                }
                adminView.initTables();
            }break;

            case "Stergeprodus":
            {
                System.out.println("Stergeprodus");
                MenuItem mi = adminView.getTable1SelectedRow();
                dsp.deleteProdus(mi.getTitle());
                adminView.initTables();
            }break;

            case "Stergeprodusproduscompus":
            {
                System.out.println("Stergeprodusproduscompus");
                if(adminView.getTable2SelectedRow()!=null)
                    compositeMenu.remove(adminView.getTable2SelectedRow());
                adminView.initTables();
            }break;

            case "Adaugaproduscompus":
            {
                System.out.println("Adaugaproduscompus");
                if(adminView.getTable1SelectedRow()!=null)
                    compositeMenu.add(adminView.getTable1SelectedRow());
                adminView.initTables();

            }break;

            case "Cautaprodus":
            {
                System.out.println("Cautaprodus");
                String field = adminView.getField1Text();

                if(!field.isEmpty()) {
                    ArrayList<MenuItem> listTest = dsp.findByTitle(field);
                    initialMenu=dsp.getMeniu();
                    dsp.setMeniu(listTest);
                }
                else {
                    dsp.setMeniu(initialMenu);
                }
                adminView.initTables();
            }break;

            case "ImportCsv":
            {
                System.out.println("ImportCsv");
                dsp.setMeniu(dsp.importMeniu("products.csv"));
                adminView.initTables();
            }break;

            case "Adaugareproduscompozit":
            {
                System.out.println("Adaugareproduscompozit");
                ArrayList<MenuItem> compositeMenuadd = new ArrayList<>();
                compositeMenuadd= compositeMenu;
                if(compositeMenuadd.size()!=0 && adminView.getField2Text().length() != 0){
                    dsp.creazaComposit(adminView.getField2Text(), compositeMenuadd);
                }
                adminView.initTables();
            }break;

            case "Extra":
            {
                System.out.println("Extra");
                JFrame frame = new ExtraView("Statistici", dsp);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }break;
        }
    }
}
