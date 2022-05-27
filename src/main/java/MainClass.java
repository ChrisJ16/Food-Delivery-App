import BusinessLogic.DeliveryServiceProcessing;
import DataAcces.Serialization;
import GUI.*;

import javax.swing.*;


public class MainClass {
    public static void main(String[] args) {
        DeliveryServiceProcessing dsp = Serialization.returnDsp("dsp.ser");
        //dsp.setMeniu(dsp.importMenu("products.csv"));
        JFrame frame = new View("Ceau imi place sa mananc aici", dsp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        DeliveryServiceProcessing SaveDsp = dsp;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {Serialization.saveDsp(SaveDsp, "dsp.ser");}));
    }
}
