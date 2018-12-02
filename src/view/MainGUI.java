package view;

import java.util.ArrayList;
import java.util.Observable;

import controller.Controller;

public class MainGUI implements ViewObserver {

   private static Controller controller;
   private LogIn logIn;
   private ChatView chatView;
   private ChatViewAdmin chatViewAdmin;
   private Options option;

   public MainGUI() {
      this.controller = controller;
   }

   public int activeGUI() {
      if (chatView == null)
         return -1;
      if (chatView.isActive()) {
         return 1;
      } else if (chatViewAdmin.isActive())
         return 2;
      return -1;
   }

   @Override
   public void update(Observable o, Object arg) {
      
      if (activeGUI() == 1) {
         System.out.println("userss");
         chatView.show(arg);
         chatViewAdmin.show(arg);
      }
      if (activeGUI() == 2) {
         System.out.println("adminn");
         chatViewAdmin.show(arg);
      }

   }

   public void LogInUser() {

      logIn.dispose();// To close the current window
      chatView.setVisible(true);// Open the new window
   }

   public void LogInAdmin() {
      logIn.dispose();// To close the current window
      chatViewAdmin.setVisible(true);// Open the new windows
   }

   public String getMessage() {
      return chatView.getMessage();
   }

   public String getMessageAdmin() {
      return chatViewAdmin.getMessage();
   }

   public String getUssernameFromLogIn() {
      return logIn.getUssername();
   }

   public String getPasswordFromLogIn() {
      return logIn.getPassword();
   }

   public void start(Controller controller) {
      logIn = new LogIn(controller);
      chatView = new ChatView(controller);
      chatViewAdmin = new ChatViewAdmin(controller);
      option = new Options(controller);
   }

   public void LogOut() {
      
      if (chatView == null ||chatViewAdmin.isActive()) {
         chatViewAdmin.LogOut();
      }
      if (chatView.isActive()) {
         chatView.LogOut();
      }
      
      logIn.setVisible(true);
   }

   public String getNewUssernameCreate() {
      return option.getNewUssername();
   }

   public String getNewPasswordCreate() {
      return option.getNewPassword();
   }

}
