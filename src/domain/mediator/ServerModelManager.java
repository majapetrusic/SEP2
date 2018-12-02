package domain.mediator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import domain.model.Administrator;
import domain.model.AdministratorList;
import domain.model.Message;
import domain.model.MessageList;
import domain.model.RoomList;
import domain.model.User;
import domain.model.UserList;

public class ServerModelManager extends Observable implements Model {
   private MessageList list;
   private ServerConnection server;
   private User user;
   private Administrator admin;
   private UserList userList;
   private AdministratorList adminList;
   private DatabaseInterface db;

   public ServerModelManager() throws SQLException {
      this.db = new ChatDatabase();
      this.list = new MessageList();
      this.userList = new UserList();
      this.adminList = new AdministratorList();
      this.server = new ServerConnection(this);
      new Thread(server, "Server").start();
   }

   public void addMessage(Message message, String userAccount, int userId)
         throws Exception {

      db.insertIntoMessages(message, userAccount, userId);

   }

   public void addMessageAdmin(Message message, String adminAccount,
         int adminId) throws Exception {
      db.insertIntoMessagesAdmin(message, adminAccount, adminId);

   }

   public void addMessageInModel(Message message) {
      list.addMessage(message);
      notifyAboutMessage(message);
      System.out.println("SERVERMODEL");
   }

   public void notifyAboutMessage(Message message) {
      super.setChanged();
      super.notifyObservers(message);

   }

   public void createUser(String userAccount, String userPassword)
         throws Exception {
      User u = new User(userAccount, userPassword);
      userList.addUser(u);
      db.insertIntoUser(userAccount, userPassword);
   }

   public void createAdministrator(String adminAccount, String adminPassword)
         throws Exception {
      Administrator a = new Administrator(adminAccount, adminPassword);
      adminList.addAdmin(a);
      db.insertIntoAdministrator(adminAccount, adminPassword);
   }

   public Message[] getAllMessagesFromModel() throws Exception {

      return list.getAll();
   }

   public ArrayList<String> getAllMessagesFromDatabase() throws Exception {
      return db.getAllMessagesFromUsers();
   }

   public ArrayList<String> getMessagesForPeriodFromDatabase(Date a, Date b)
         throws Exception {
      return db.getMessagesFromUsers(a, b);
   }

   public boolean testUser(String userAccount, String userPassword)
         throws Exception {
      if (db.testUser(userAccount, userPassword) == true) {
         User u = new User(userAccount, userPassword);
         userList.addUser(u);
      }
      return db.testUser(userAccount, userPassword);

   }

   public boolean testAdmin(String adminAccount, String adminPassword)
         throws Exception {
      if (db.testAdmin(adminAccount, adminPassword) == true) {
         Administrator a = new Administrator(adminAccount, adminPassword);
         adminList.addAdmin(a);
      }
      return db.testAdmin(adminAccount, adminPassword);
   }

   public ArrayList<String> getAllUsersFromDatabase() throws Exception {
      return db.getAllUsers();
   }

   public String[] getAllUsersFromModel() throws Exception {

      return userList.getAllUsernames();

   }

   public ArrayList<String> getAllUserPasswords() throws Exception {
      return db.getAllUserPasswords();
   }

   public String[] getAllAdminsFromModel() throws Exception {
      return adminList.getAllUsernames();
   }

   public ArrayList<String> getAllAdminsFromDatabase() throws Exception {
      return db.getAllAdmins();
   }

   public ArrayList<String> getAllAdminPasswords() throws Exception {
      return db.getAllAdminPasswords();
   }

   public void modifyUser(String userAccount, String userPassword, int userId)
         throws SQLException {
      user.setUser(userAccount, userPassword, userId);
      db.modifyUser(userAccount, userPassword, userId);

   }

   public void modifyAdmin(String adminAccount, String adminPassword,
         int adminId) throws SQLException {
      admin.setAdministrator(adminAccount, adminPassword, adminId);
      db.modifyAdmin(adminAccount, adminPassword, adminId);
   }

   public void deleteUser(User user) throws Exception {
      userList.removeUser(user);
      db.deleteFromUser(user.getUserName());
   }

   public void deleteMessageFromModel(Message message) throws Exception {
      list.removeMessage(message);
   }

   public void deleteMessageFromDatabase(String userAccount) throws Exception {
      db.deleteFromMessage(userAccount);
   }

   public int getIdForUserFromDatabase(String userAccount) throws Exception {
      return db.getIdForUser(userAccount);
   }

   public int getIdForAdminFromDatabase(String adminAccount) throws Exception {
      return db.getIdForAdmin(adminAccount);
   }
}