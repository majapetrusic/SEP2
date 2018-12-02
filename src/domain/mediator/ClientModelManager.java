package domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import domain.model.Message;
import domain.model.User;

public class ClientModelManager implements ModelClient, Observer {

   private ObjectInputStream inServer;
   private ObjectOutputStream outServer;
   private ModelClient model;
   private Socket socket;

   private static final String HOST = "localhost";
   private static final int PORT = 6759;

   public ClientModelManager(ModelClient model) throws IOException {
      this.model = model;

      try {
         socket = new Socket(HOST, PORT);
         outServer = new ObjectOutputStream(socket.getOutputStream());
         inServer = new ObjectInputStream(socket.getInputStream());
         ClientReceiver receiver = new ClientReceiver(inServer, model);
         new Thread(receiver, "Receiver").start();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Override
   public void addMessageInModel(Message message) {
      try {
         outServer.writeObject(message);
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("DEBUG: Proxy add: " + message);
   }

   @Override
   public void notifyAboutMessage(Message message) {
      // TODO Auto-generated method stub
      model.notifyAboutMessage(message);
   }

   @Override
   public void update(Observable arg0, Object arg1) {
      try {
         Message message = (Message) arg1;
         outServer.writeObject(message);
      } catch (Exception e) {
         // no client connection
         System.out.println("DEBUG: Exception for client broadcast " + " - "
               + e.getMessage());
         ((Observable) model).deleteObserver(this);
         Message m = new Message(
               "DEBUG: Server> A client has been disconnected");
         System.out.println("DEBUG: " + m);
         model.addMessageInModel(m);
      }

   }

   @Override
   public void addMessage(Message message, String userAccount, int userId)
         throws Exception {
      // TODO Auto-generated method stub
      model.addMessage(message, userAccount, userId);
   }

   @Override
   public boolean testUser(String userAccount, String userPassword)
         throws Exception {
      // TODO Auto-generated method stub
      return(model.testUser(userAccount, userPassword));
      //return false;
   }

   @Override
   public boolean testAdmin(String adminAccount, String adminPassword)
         throws Exception {
      // TODO Auto-generated method stub
      return(model.testAdmin(adminAccount, adminPassword));
      //return false;
   }

   @Override
   public int getIdForUserFromDatabase(String userAccount) throws Exception {
      return model.getIdForUserFromDatabase(userAccount);
   }

}