package domain.mediator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import domain.model.Administrator;
import domain.model.AdministratorList;
import domain.model.Message;
import domain.model.MessageList;
import domain.model.User;
import domain.model.UserList;

public class ClientModelProxy extends Observable implements ModelClient {
   private ClientModelManager proxy;
   private DatabaseInterface db;

   public ClientModelProxy() throws SQLException {
      this.db = new ChatDatabase();
      try {
         proxy = new ClientModelManager(this);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Override
   public void addMessage(Message message, String userAccount, int userId)
         throws Exception {

      db.insertIntoMessages(message, userAccount, userId);
   }

   public void addMessageInModel(Message message) {
      proxy.addMessageInModel(message);
   }

   @Override
   public void notifyAboutMessage(Message message) {
      super.setChanged();
      super.notifyObservers(message);

   }

   @Override
   public boolean testUser(String userAccount, String userPassword)
         throws Exception {
      return db.testUser(userAccount, userPassword);

   }

   @Override
   public boolean testAdmin(String adminAccount, String adminPassword)
         throws Exception {
      return db.testAdmin(adminAccount, adminPassword);
   }

   @Override
   public int getIdForUserFromDatabase(String userAccount) throws Exception {
      return db.getIdForUser(userAccount);
   }
}

