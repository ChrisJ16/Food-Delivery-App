package DataAcces;

import BusinessLogic.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {
    public static void saveDsp(DeliveryServiceProcessing dsp, String file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.reset();
            objectOutputStream.writeObject(dsp);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception e) {
            System.out.println("Nu s-a salva dsp.ser");
            e.printStackTrace();
        }
    }

    public static DeliveryServiceProcessing returnDsp(String file) {
        DeliveryServiceProcessing ret = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ret = (DeliveryServiceProcessing) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }
        catch (Exception e) {
            System.out.println("Nu s-a incarcat dsp.ser");
            e.printStackTrace();
        }
        return ret;
    }
}
