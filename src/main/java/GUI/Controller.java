package GUI;

import BusinessLogic.Client;
import BusinessLogic.DeliveryServiceProcessing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {
    private View view;
    private DeliveryServiceProcessing dsp;

    public Controller(View v, DeliveryServiceProcessing dsp) {
        this.view = v;
        this.dsp = dsp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            String user= view.getFiled1Text();
            String pass = view.getFiled2Text();
            String type = view.getCombBoxSelectedItem();

            switch (cmd){
                case "Login":
                {
                    switch (type){
                        case "Administrator":
                        {
                            String userAdmin = "admin";
                            String passAdmin = "ad1";
                            if(userAdmin.equals(user) && passAdmin.equals(pass)) {
                                JFrame frame = new AdminView("Administrator", dsp);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setLocationRelativeTo(null);
                                frame.setResizable(false);
                                frame.setVisible(true);
                                view.setLabelStatus("Admin logat cu succes!");
                            }
                            else
                                view.setLabelStatus("Logare invalida!");
                        }break;
                        case "Angajat":
                        {
                            String userEmp = "emp";
                            String passEmp= "em1";
                            if(userEmp.equals(user) && passEmp.equals(pass)) {
                                JFrame frame = new EmployView("Angajat", dsp);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setLocationRelativeTo(null);
                                frame.setResizable(false);
                                frame.setVisible(true);
                                view.setLabelStatus("Angajat logat cu succes!");
                            }else
                                view.setLabelStatus("Logare invalida!");
                        }break;
                        case "Client":
                        {
                            ArrayList<Client> listaClienti = dsp.getClienti();
                            boolean ok=false;
                            for(Client c : listaClienti){
                                if(c.getUser().equals(user) && c.getPass().equals(pass)){
                                    JFrame frame = new ClientView("Client", dsp, c);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setLocationRelativeTo(null);
                                    frame.setResizable(false);
                                    frame.setVisible(true);
                                    view.setLabelStatus("Client logat cu succes! id: " + c.getClientId());
                                    return;
                                }
                            }
                            System.out.println("User sau parola incorecta!");
                            /*
                            JFrame frame = new ClientView("Client", dsp);
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.setLocationRelativeTo(null);
                            frame.setResizable(false);
                            frame.setVisible(true);
                            view.setLabelStatus("Client logat cu succes!");
                             */
                        }break;
                        default:{
                            view.setLabelStatus("Nu a fost selectat tipul de user!");
                        }
                    }
                }break;

                case "Register":
                {
                    System.out.println("Register");

                    ArrayList<Client> listaClienti = dsp.getClienti();
                    for(Client c : listaClienti){
                        if(c.getUser().equals(user)){
                            view.setLabelStatus("Clientul exista deja!");
                            return;
                        }
                    }

                    Client newCLient = new Client(user, pass);
                    dsp.creazaClient(newCLient);
                    view.setLabelStatus("Client creat cu succes!");
                }break;
            }
    }
}
