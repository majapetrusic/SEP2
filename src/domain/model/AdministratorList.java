package domain.model;

import java.util.ArrayList;

public class AdministratorList
{
   private ArrayList<Administrator> admins;

   public AdministratorList()
   {
      this.admins = new ArrayList<>();
   }

   public Administrator getAdmin(int index)
   {
      return admins.get(index);
   }

   public void addAdmin(Administrator admin)
   {
      this.admins.add(admin);
   }

   public void removeAdmin(Administrator admin)
   {
      this.admins.remove(admin);
   }

   public int count()
   {
      return admins.size();
   }

   public String[] getAllUsernames()
   {

      String[] u = new String[admins.size()];
      for (int i = 0; i < admins.size(); i++)
      {
         u[i] = admins.get(i).getUserName();
      }
      return u;

   }

   public String[] getAllPasswords()
   {
      String[] u = new String[admins.size()];
      for (int i = 0; i < admins.size(); i++)
      {
         u[i] = admins.get(i).getPassword();
      }
      return u;
   }

   public String toString()
   {
      String all = "UserList: {";
      for (int i = 0; i < admins.size(); i++)
      {
         all += admins.get(i);
         if (i < admins.size() - 1)
            all += "\n ";
      }
      all += "\n}";
      return all;
   }
}