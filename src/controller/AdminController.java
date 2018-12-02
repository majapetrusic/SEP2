package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import view.LogIn;
import view.MainGUI;
import domain.mediator.Model;
import domain.mediator.ModelClient;
import domain.model.Message;

public class AdminController implements Controller
{
      private Model model;
      private MainGUI gui;

      public AdminController(Model model, MainGUI view)
      {
         this.model = model;
         this.gui = view;
         ((Observable) this.model).addObserver((Observer)view);
      }
      
      @Override
      public void execute(String what) throws Exception
      {
         System.out.println(what);
         switch (what)
         {
            case "Send":

               System.out.println(gui.activeGUI());
               String input = gui.getMessageAdmin();

               if (input.length() > 0)
               {
                  model.addMessageInModel(new Message(input));
                  model.addMessageAdmin(new Message(input), gui
                        .getUssernameFromLogIn(), model
                        .getIdForAdminFromDatabase(gui.getUssernameFromLogIn()));

                  System.out.println(input);
               }
break;
            case "Log in":

               String name = gui.getUssernameFromLogIn();
               String pass = gui.getPasswordFromLogIn();

               System.out.println("name : " + name + "  pass:  " + pass);
               
               if (model.testUser(name, pass))
               {
                  JOptionPane.showMessageDialog(null, " Please type Administrator credentials !!!");
               }
                if (model.testAdmin(name, pass))
               {
                  gui.LogInAdmin();
               
               }
break;
            case "Log out":
               gui.LogOut();
               break;
            case "Edit":
            case "Delete":
            case "Show history":
            case "Create":
                String Newname = gui.getNewUssernameCreate();
                 String Newpass =gui. getNewPasswordCreate();
                 
               System.out.println("NAME: " + Newname + "  Pass: " + Newpass);
                 if(!(Newname == null) && !(Newpass== null))
                 {
                 model.createUser(Newname, Newpass);
                 }
                 break;
         }

      }
   }
  

