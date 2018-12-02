package domain.mediator;

import java.io.ObjectInputStream;

import domain.model.Message;

public class ClientReceiver implements Runnable {
   private ObjectInputStream inServer;
   private ModelClient model;

   public ClientReceiver(ObjectInputStream inFromServer, ModelClient model) {
      this.inServer = inFromServer;
      this.model = model;
   }

   @Override
   public void run() {
      try {
         while (true) {
            Message message = (Message) inServer.readObject();
            System.out.println("Receiver: " + message);
            model.notifyAboutMessage(message);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

}
