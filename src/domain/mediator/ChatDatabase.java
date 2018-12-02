package domain.mediator;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import domain.model.Message;
import domain.model.User;

public class ChatDatabase implements DatabaseInterface {
   private static Driver driver;
   private final String url = "jdbc:postgresql://localhost:5432/postgres";
   private final String username = "postgres";
   private final String password = "nu1clear2storm";
   private Connection connect;

   public ChatDatabase() throws SQLException {
      driver = new org.postgresql.Driver();
      DriverManager.registerDriver(driver);
      this.connect = DriverManager.getConnection(url, username, password);

   }

   public void deleteFromMessage(String userAccount) throws Exception {
      String deleteStatement = "DELETE FROM \"SEP DB\".messages  WHERE user_account = ?";
      PreparedStatement statement = this.connect
            .prepareStatement(deleteStatement);
      statement.setString(1, userAccount);
      statement.executeUpdate();

   }

   public void deleteFromUser(String userAccount) throws Exception {
      deleteFromMessage(userAccount);
      String deleteStatement = "DELETE FROM \"SEP DB\".users  WHERE user_account = ?";
      PreparedStatement statement = connect
            .prepareStatement(deleteStatement);
      statement.setString(1, userAccount);
      statement.executeUpdate();
   }

   public void deleteFromAdmin(String userAccount) throws Exception {
      Connection connection = DriverManager.getConnection(url, username,
            password);
      deleteFromMessage(userAccount);
      String deleteStatement = "DELETE FROM \"SEP DB\".administrator  WHERE admin_account = ?";
      PreparedStatement statement = connection
            .prepareStatement(deleteStatement);
      statement.setString(1, userAccount);
      statement.executeUpdate();
   }

   public void insertIntoUser(String userAccount, String userPassword)
         throws SQLException {
      String insertStatement = "INSERT INTO \"SEP DB\".users  (user_account, user_password) VALUES (?,?)";
      PreparedStatement statement = connect
            .prepareStatement(insertStatement);
      statement.setString(1, userAccount);
      statement.setString(2, userPassword);
      statement.executeUpdate();
      System.out.println("Record inserted");

   }

   public void insertIntoAdministrator(String adminAccount,
         String adminPassword) throws SQLException {
      String insertStatement = "INSERT INTO \"SEP DB\".administrator  (admin_account, admin_password) VALUES (?,?)";
      PreparedStatement statement = connect
            .prepareStatement(insertStatement);
      statement.setString(1, adminAccount);
      statement.setString(2, adminPassword);
      statement.executeUpdate();
      System.out.println("Record inserted");
   }

   public void insertIntoMessages(Message message, String userAccount,
         int userId) throws Exception {
      String insertStatement = "INSERT INTO \"SEP DB\".messages  (message, user_account, user_id) VALUES(?,?,?)";
      PreparedStatement statement = connect
            .prepareStatement(insertStatement);
      statement.setString(1, message.getBody());
      statement.setString(2, userAccount);
      statement.setInt(3, userId);
      statement.executeUpdate();
      System.out.println("Record inserted");
   }

   public int getIdForAdmin(String adminAccount) throws Exception {
      String statement = "SELECT admin_id FROM \"SEP DB\".administrator a WHERE admin_account = ?";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      selectStatement.setString(1, adminAccount);
      ResultSet rs = selectStatement.executeQuery();

      while (rs.next()) {
         return rs.getInt("admin_id");

      }
      return -1;

   }

   public void insertIntoMessagesAdmin(Message message, String adminAccount,
         int adminId) throws Exception {
      String insertStatement = "INSERT INTO \"SEP DB\".messages (message, admin_account, admin_id) VALUES(?,?,?)";
      PreparedStatement statement = connect
            .prepareStatement(insertStatement);
      statement.setString(1, message.getBody());
      statement.setString(2, adminAccount);
      statement.setInt(3, adminId);
      statement.executeUpdate();
      System.out.println("Record inserted");
   }

   public ArrayList<String> getAllMessagesFromUsers() throws Exception {
      ArrayList<String> list = new ArrayList<>();
      String statement = "SELECT message, user_account, send_at FROM \"SEP DB\".messages m"; //m thing
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
         System.out.println("User sending the message: "
               + rs.getString("user_account"));
         System.out.println("message send: " + rs.getString("message")
               + ", ");
         System.out.println("time of sending: " + rs.getObject("send_at"));
         list.add(rs.getString("user_account").replaceAll("\\s+", "") + ", "
               + rs.getString("message") + ", " + rs.getObject("send_at")
               + "\n");

      }
      System.out.println(list);
      return list;
   }

   public ArrayList<String> getMessagesFromUsers(Date a, Date b)
         throws SQLException {
      ArrayList<String> list = new ArrayList<>();

      String statement = "SELECT message, user_account, send_at FROM \"SEP DB\".messages m";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next() && rs.getDate("send_at").compareTo(a) >= 0
            && rs.getDate("send_at").compareTo(b) <= 0) {
         System.out.println("User sending the message: "
               + rs.getString("user_account"));
         System.out.println("message send: " + rs.getString("message")
               + ", ");
         System.out.println("time of sending: " + rs.getObject("send_at"));
         list.add(rs.getString("user_account").replaceAll("\\s+", "") + ", "
               + rs.getString("message") + ", " + rs.getObject("send_at")
               + "\n");

      }

      System.out.println("List ot databaza : " + list);
      return list;
   }

   public boolean testUser(String userAccount, String userPassword)
         throws Exception {
      boolean correct = false;
      String statement = "SELECT user_account,user_password FROM \"SEP DB\".users u";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
         if (rs.getString("user_account").replaceAll("\\s+", "")
               .equals(userAccount)
               && rs.getString("user_password").replaceAll("\\s+", "")
                     .equals(userPassword)) {
            correct = true;
            return correct;
         }

      }
      return correct;
   }

   public boolean testAdmin(String adminAccount, String adminPassword)
         throws Exception {
      boolean correct = false;
      String statement = "SELECT admin_account,admin_password FROM \"SEP DB\".administrator a";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
         if (rs.getString("admin_account").replaceAll("\\s+", "")
               .equals(adminAccount)
               && rs.getString("admin_password").replaceAll("\\s+", "")
                     .equals(adminPassword)) {
            correct = true;
            return correct;
         }

      }
      return correct;
   }

   public ArrayList<String> getAllUsers() throws Exception {
      ArrayList<String> list = new ArrayList<>();
      String statement = "SELECT user_account FROM \"SEP DB\".users u";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
         list.add(rs.getString("user_account").replaceAll("\\s+", ""));
      }
      System.out.println(list);
      return list;
   }

   public int getIdForUser(String userAccount) throws Exception {
      String statement = "SELECT user_id FROM \"SEP DB\".users u  WHERE user_account = ?";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      selectStatement.setString(1, userAccount);
      ResultSet rs = selectStatement.executeQuery();

      while (rs.next()) {
         return rs.getInt("user_id");

      }
      return -1;

   }

   public ArrayList<String> getAllUserPasswords() throws Exception {
      ArrayList<String> list = new ArrayList<>();

      String statement = "SELECT user_password FROM \"SEP DB\".users u";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
         list.add(rs.getString("user_password").replaceAll("\\s+", ""));
      }
      System.out.println(list);
      return list;
   }

   public ArrayList<String> getAllAdmins() throws Exception {
      ArrayList<String> list = new ArrayList<>();

      String statement = "SELECT admin_account FROM \"SEP DB\".administrator a";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
         list.add(rs.getString("admin_account").replaceAll("\\s+", ""));
      }
      System.out.println(list);
      return list;
   }

   public ArrayList<String> getAllAdminPasswords() throws Exception {
      ArrayList<String> list = new ArrayList<>();

      String statement = "SELECT admin_password FROM \"SEP DB\".administrator a";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
         list.add(rs.getString("admin_password").replaceAll("\\s+", ""));
      }
      return list;
   }

   public void modifyUser(String userAccount, String userPassword, int userId)
         throws SQLException {

      String statement = "UPDATE \"SEP DB\".users  SET user_account = ? , user_password = ? WHERE user_id = ?";
      PreparedStatement updateStatement = connect
            .prepareStatement(statement);
      updateStatement.setString(1, userAccount);
      updateStatement.setString(2, userPassword);
      updateStatement.setInt(3, userId);
      updateStatement.executeUpdate();
      System.out.println("Record updated");

   }

   public void modifyAdmin(String adminAccount, String adminPassword,
         int adminId) throws SQLException {

      String statement = "UPDATE \"SEP DB\".administrator  SET admin_account = ? , admin_password = ? WHERE admin_id = ?";
      PreparedStatement updateStatement = connect
            .prepareStatement(statement);
      updateStatement.setString(1, adminAccount);
      updateStatement.setString(2, adminPassword);
      updateStatement.setInt(3, adminId);
      updateStatement.executeUpdate();
      System.out.println("Record updated");
   }

   public String getUser(int userId) throws SQLException {
      String statement = "SELECT user_account FROM \"SEP DB\".users u WHERE user_id = ?";
      PreparedStatement selectStatement = connect
            .prepareStatement(statement);
      selectStatement.setInt(1, userId);
      ResultSet rs = selectStatement.executeQuery();

      while (rs.next()) {
         return rs.getString("user_account");

      }
      return null;
   }

}
