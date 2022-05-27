package BusinessLogic;

import java.io.Serializable;
import java.util.Random;

public class Client implements Serializable {
    private static int ContClientId=0;
    private int clientId;
    private String user;
    private String pass;
    private static final long serialVersionUID = 6529685098267757690L;

    public Client(String user, String pass)
    {
        //this.clientId = getContClientId();
        Random rand = new Random();
        this.clientId = rand.nextInt(100);
        this.user = user;
        this.pass = pass;

        //incrementContClientId();
    }

    public static int getContClientId() {
        return Client.ContClientId;
    }

    public static void incrementContClientId() {
        Client.ContClientId++;
    }

    public static void setContClientId(int contClientId) {
        Client.ContClientId = contClientId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
