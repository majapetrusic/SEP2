package domain.mediator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import domain.model.Message;
import domain.model.User;

public interface Model {

   void addMessage(Message message, String userAccount, int userId)
         throws Exception;

   void addMessageAdmin(Message message, String adminAccount, int adminId)
         throws Exception;

   void addMessageInModel(Message message);

   void notifyAboutMessage(Message message);

   void createUser(String userAccount, String userPassword) throws Exception;

   void createAdministrator(String adminAccount, String adminPassword)
         throws Exception;

   Message[] getAllMessagesFromModel() throws Exception;

   ArrayList<String> getAllMessagesFromDatabase() throws Exception;

   boolean testUser(String userAccount, String userPassword) throws Exception;

   boolean testAdmin(String adminAccount, String adminPassword)
         throws Exception;

   ArrayList<String> getAllUsersFromDatabase() throws Exception;

   String[] getAllUsersFromModel() throws Exception;

   ArrayList<String> getAllUserPasswords() throws Exception;

   ArrayList<String> getAllAdminsFromDatabase() throws Exception;

   String[] getAllAdminsFromModel() throws Exception;

   ArrayList<String> getAllAdminPasswords() throws Exception;

   void modifyUser(String userAccount, String userPassword, int userId)
         throws SQLException;

   void modifyAdmin(String adminAccount, String adminPassword, int adminId)
         throws SQLException;

   void deleteUser(User user) throws Exception;

   void deleteMessageFromModel(Message message) throws Exception;

   void deleteMessageFromDatabase(String userAccount) throws Exception;

   ArrayList<String> getMessagesForPeriodFromDatabase(Date a, Date b)
         throws Exception;

   int getIdForUserFromDatabase(String userAccount) throws Exception;

   int getIdForAdminFromDatabase(String ussernameFromLogIn) throws Exception;

}