package domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.model.Message;

public class ServerCommunication implements Observer, Runnable {
   private ObjectInputStream inClient;
   private ObjectOutputStream outClient;
   private Model model;

   public ServerCommunication(Socket clientSocket, Model model)
         throws IOException {

      this.model = model;
      // create output stream attached to the socket
      outClient = new ObjectOutputStream(clientSocket.getOutputStream());
      // create input stream attached to the socket
      inClient = new ObjectInputStream(clientSocket.getInputStream());
   }

   public void run() {
      ((Observable) model).addObserver(this);

      Message m = new Message("Server: A client has connected");
      System.out.println("DEBUG" + m);
      model.addMessageInModel(m);
      try {
         Thread.sleep(1000);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      while (true) {

         try {
            Message message = (Message) inClient.readObject();
            model.addMessageInModel(message);
            System.out.println("DEBUG: " + message);
         } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

   }

   public void update(Observable arg0, Object arg1) {
      try {

         Message message = (Message) arg1;
         outClient.writeObject(message);

      } catch (Exception e) {

         try {
            ((Observable) model).deleteObserver(this);
            Message m = new Message("Server: A client has disconnected");
            System.out.println("DEBUG: " + m);
            model.addMessageInModel(m);
         } catch (Exception e2) {
         }
      }

   }

}