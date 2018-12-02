package domain.model;

import domain.mediator.ServerModelManager;

public class User
{
   private String userName;
   private String passWord;
   private int id;

   public User(String userName, String password)
   {
      this.userName = userName;
      this.passWord = password;

   }

   public String getUserName()
   {
      return userName;
   }

   public void setUser(String userName, String password,int userId)
   {
      this.userName = userName;
      this.passWord = password;
      this.id=userId;
   }

   public String getPassword()
   {
      return passWord;
   }

   public String toString()
   {
      return "User: " + userName + ",id: " + passWord;
   }

   public boolean checkIdentity(String userName, String password)
   {
      if (this.userName == userName && this.passWord == password)
         return true;
      return false;
   }

}