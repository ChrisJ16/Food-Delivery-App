package BusinessLogic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

public class Order implements Serializable {
    private static int globalOrderId = 0;
    private int orderId;
    private int clientId;
    private LocalDateTime date;
    private static final long serialVersionUID = 6529685098267757690L;

    public Order(int clientId)
    {
        Random rand = new Random();
        this.orderId = rand.nextInt(100);
        this.clientId = clientId;
        this.date = LocalDateTime.now();
    }

    public static int getGlobalOrderId() {
        return Order.globalOrderId;
    }

    public static void setGlobalOrderId(int globalOrderId) {
        Order.globalOrderId = globalOrderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
