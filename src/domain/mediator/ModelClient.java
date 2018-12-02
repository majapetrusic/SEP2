package domain.mediator;

import domain.model.Message;

public interface ModelClient {

   void addMessage(Message message, String userAccount, int userId)
         throws Exception;

   void addMessageInModel(Message message);

   void notifyAboutMessage(Message message);

   boolean testUser(String userAccount, String userPassword) throws Exception;

   boolean testAdmin(String adminAccount, String adminPassword)
         throws Exception;

   int getIdForUserFromDatabase(String userAccount) throws Exception;
}
