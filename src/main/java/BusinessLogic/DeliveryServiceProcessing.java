package BusinessLogic;

import GUI.ObservableGUI;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryServiceProcessing extends Observable implements IDeliveryServiceProcessing, Serializable {

    private ArrayList<MenuItem> meniu = new ArrayList<>();
    private Map<Order, ArrayList<MenuItem>> comanda = new HashMap<>();
    private ArrayList<Client> clienti = new ArrayList<>();
    private static final long serialVersionUID = 6529685098267757690L;

    public DeliveryServiceProcessing(){}

    public boolean checkCond(){
        return (meniu!=null && comanda!=null && clienti!=null);
    }

    private static MenuItem mappingCriteria(String str){
        assert str != null;
        String[] args = str.split(",");
        BaseProduct baseProduct = new BaseProduct(
                args[0].toString(),
                Float.parseFloat(args[1]),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),
                Integer.parseInt(args[4]),
                Integer.parseInt(args[5]),
                Integer.parseInt(args[6])
        );
        assert baseProduct!=null;
        return baseProduct;
    }

    @Override
    public ArrayList<MenuItem> importMeniu(String file) {
        try {
            assert file!=null;
            assert checkCond();
            InputStream inputStream = new FileInputStream(file);
            BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));

            return read.lines().skip(1).map(DeliveryServiceProcessing::mappingCriteria).
                    collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(MenuItem::getTitle))), ArrayList::new));
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void creazaClient(Client client) {
        assert client != null;
        for(Client cl : clienti)
            if(cl.equals(client))
                return;
        clienti.add(client);
    }

    public ArrayList<Client> getClienti(){
        return clienti;
    }

    @Override
    public void adaugaProdus(MenuItem produs) {
            assert produs != null;
            meniu.add(produs);
    }

    @Override
    public void updateProdus(String title, MenuItem produs) {
        assert title != null;
        assert produs != null;
        for(int i=0; i<meniu.size(); i++)
            if(meniu.get(i).getTitle().trim().equals(title.trim())){
                meniu.set(i,produs);
            }
    }

    @Override
    public void deleteProdus(String title) {
        assert title != null;
        for (MenuItem mi : meniu)
            if (mi.getTitle().trim().equals(title.trim())){
                meniu.remove(mi);
                return;
            }
    }

    @Override
    public void creazaComposit(String title, ArrayList<MenuItem> listaProduse) {
        assert title != null;
        assert listaProduse != null;
        MenuItem produs = new CompositeProduct(title, listaProduse);
        this.adaugaProdus(produs);
    }

    @Override
    public Order creazaComanda(int clientId, ArrayList<MenuItem> listaProduse, ObservableGUI obs) {
        assert obs != null;
        assert listaProduse != null;
        Order newOrder = new Order(clientId);
        comanda.put(newOrder, listaProduse);
        for (MenuItem mi : listaProduse){
            for(MenuItem mix : meniu){
                if(mi.getTitle() == mix.getTitle())
                    mix.setOrdersNumbers(mix.getOrdersNumbers()+1);
            }
        }
        // mi.setOrdersNumbers(mi.getOrdersNumbers()+1);
        notifyObserver(obs);
        assert newOrder != null;
        return newOrder;
    }

    /*
    public Order createOrders(int clientId, ArrayList<MenuItem> listaProduse) {
        assert listaProduse != null;
        Order newOrder = new Order(clientId);
        comanda.put(newOrder, listaProduse);

        assert newOrder != null;
        return newOrder;
    }
    */

    public Map<Order,ArrayList<MenuItem>> getOrder() {
        return comanda;
    }

    @Override
    public void notifyObserver(ObservableGUI obs) { assert obs != null; obs.update();}

    public ArrayList<MenuItem> getMeniu(){
        return meniu;
    }

    public void setMeniu(ArrayList<MenuItem> meniu){
        this.meniu = meniu;
    }

    public ArrayList<MenuItem> findByTitle(String title){
        ArrayList<MenuItem> rez = (ArrayList<MenuItem>) meniu.stream().filter(s->s.getTitle().trim().contains(title)).collect(Collectors.toList());
        return rez;
    }

    public ArrayList<MenuItem> findByCriteria
            (   String title,
                Double lrating,Double hrating,
                int lcalories, int hcaloreis,
                int lprotein,int hprotein,
                int lfat,int hfat,
                int lsodium,int hsodium,
                int lprice,int hprice
            )
    {
        ArrayList<MenuItem> ret = (ArrayList<MenuItem>) meniu.stream().filter(
                s -> s.getRating() >= lrating && s.getRating() <= hrating
                && s.getCalories() >= lcalories && s.getCalories() <= hcaloreis
                && s.getProtein() >= lprotein && s.getProtein() <= hprotein
                && s.getFat() >= lfat && s.getFat() <= hfat
                && s.getSodium() >= lsodium && s.getSodium() <= hsodium
                && s.getPrice() >= lprice && s.getPrice() <= hprice
                && s.getTitle().trim().contains(title.trim())
        ).collect(Collectors.toList());
        return ret;
    }

    public ArrayList<Order> filterByHour(int lhour, int hhour) {
        return (ArrayList<Order>) comanda.keySet().stream().filter(s -> s.getDate().getHour() >= lhour && s.getDate().getHour() <= hhour).collect(Collectors.toList());
    }

    public ArrayList<Client> filterClientBycriteria(int n, int price) {
        ArrayList<Client> ret = new ArrayList<>();
        for (Client c : clienti)
        {
            int criteriu = comanda.keySet().stream().filter(s -> s.getClientId() == c.getClientId() && orderByPrice(comanda.get(s)) > price).collect(Collectors.toList()).size();
            if (criteriu >= n)
                ret.add(c);
        }
        return ret;
    }

    public int orderByPrice(ArrayList<MenuItem> menuList) {
        int ret = 0;
        for (MenuItem m : menuList)
            ret += m.getPrice();
        return ret;
    }

    public ArrayList<MenuItem> findOrdersByNumber(int n) {
        return (ArrayList<MenuItem>) meniu.stream().filter(s -> s.getOrdersNumbers() >= n).collect(Collectors.toList());
    }

    public ArrayList<MenuItem> findOrderByDay(int zi){
        ArrayList<Order> allOrders = (ArrayList<Order>) comanda.keySet().stream().filter(s -> s.getDate().getDayOfMonth() == zi).collect(Collectors.toList());
        HashSet<MenuItem> ret = new HashSet<>();
        for (Order ord : allOrders)
            ret.addAll(comanda.get(ord));
        return new ArrayList<>(ret);
    }
}
