package BusinessLogic;

import GUI.ObservableGUI;

import java.util.ArrayList;

public interface IDeliveryServiceProcessing {
    public ArrayList<MenuItem> importMeniu(String file);
    public void creazaClient(Client client);
    public void adaugaProdus(MenuItem produs);
    public void updateProdus(String title, MenuItem produs);
    public void deleteProdus(String title);
    public void creazaComposit(String title, ArrayList<MenuItem> listaProduse);
    public Order creazaComanda(int clientId, ArrayList<MenuItem> listaProduse, ObservableGUI obs);
}
