package domain.model;

import java.util.ArrayList;
import java.util.Arrays;

import domain.mediator.ServerModelManager;

public class UserList
{

   private ArrayList<User> user;

   public UserList()
   {

      this.user = new ArrayList<>();
   }

   public User getUser(int index)
   {
      return user.get(index);
   }

   public void addUser(User user)
   {
      this.user.add(user);
   }

   public void removeUser(User user)
   {
      this.user.remove(user);
   }

   public int count()
   {
      return user.size();
   }

   public String[] getAllUsernames() 
   {

      String[] u = new String[user.size()];
      for (int i = 0; i < user.size(); i++)
      {
         u[i] = user.get(i).getUserName();
      }
      return u;

   }

   public String[] getAllPasswords()
   {
      String[] u = new String[user.size()];
      for (int i = 0; i < user.size(); i++)
      {
         u[i] = user.get(i).getPassword();
      }
      return u;
   }

   public String toString()
   {
      String all = "UserList: {";
      for (int i = 0; i < user.size(); i++)
      {
         all += user.get(i);
         if (i < user.size() - 1)
            all += "\n ";
      }
      all += "\n}";
      return all;
   }

   public static void main(String[] args) {
   UserList userlist = new UserList();
   userlist.toString();
   userlist.getAllUsernames();
}
}