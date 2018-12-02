package domain.model;

public class Administrator
{
   private String userName;
   private String passWord;
   private int id;

   public Administrator(String userName, String password)
   {
      this.userName = userName;
      this.passWord = password;
      

   }

   public String getUserName()
   {
      return userName;
   }

   public void setAdministrator(String userName, String password, int adminId)
   {
      this.userName = userName;
      this.passWord = password;
      this.id=adminId;
   }

   public String getPassword()
   {
      return passWord;
   }
   
   public boolean checkIdentity(String userName, String password)
   {
      if(this.userName==userName && this.passWord==password)
         return true;
      return false;
   }

   public String toString()
   {
      return "Administrator: "+userName+",id: "+id;
   }
}
