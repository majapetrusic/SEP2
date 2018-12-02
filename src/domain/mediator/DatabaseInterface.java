package domain.mediator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import domain.model.Message;

public interface DatabaseInterface {

   void insertIntoUser(String userAccount, String userPassword)
         throws SQLException;

   void insertIntoAdministrator(String adminAccount, String adminPassword)
         throws SQLException;

   ArrayList<String> getAllMessagesFromUsers() throws Exception;

   ArrayList<String> getMessagesFromUsers(Date a, Date b) throws SQLException;

   void insertIntoMessagesAdmin(Message message, String adminAccount,
         int adminId) throws Exception;

   void insertIntoMessages(Message message, String userAccount, int userId)
         throws Exception;

   String getUser(int userId) throws SQLException;

   boolean testUser(String userAccount, String userPassword) throws Exception;

   boolean testAdmin(String adminAccount, String adminPassword)
         throws Exception;

   ArrayList<String> getAllUsers() throws Exception;

   ArrayList<String> getAllUserPasswords() throws Exception;

   ArrayList<String> getAllAdmins() throws Exception;

   ArrayList<String> getAllAdminPasswords() throws Exception;

   void modifyUser(String userAccount, String userPassword, int userId)
         throws SQLException;

   void modifyAdmin(String adminAccount, String adminPassword, int adminId)
         throws SQLException;

   void deleteFromUser(String userAccount) throws Exception;

   void deleteFromMessage(String userAccount) throws Exception;

   void deleteFromAdmin(String userAccount) throws Exception;

   int getIdForUser(String userAccount) throws Exception;

   int getIdForAdmin(String adminAccount) throws Exception;
}