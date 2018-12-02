package controller;

import java.util.Observable;
import java.util.Observer;

import view.*;
import domain.mediator.Model;
import domain.mediator.ModelClient;
import domain.model.Message;

public class ClientController implements Controller
{
   private ModelClient model;
   private MainGUI gui;

   public ClientController(ModelClient model, MainGUI view)
   {
      this.model = model;
      this.gui = view;
      ((Observable) this.model).addObserver((Observer) view);
   }

   @Override
   public void execute(String what) throws Exception
   {
      System.out.println(what);
      switch (what)
      {
         case "Send":

            System.out.println(gui.activeGUI());
            String input = gui.getMessage();

            if (input.length() > 0)
            {
               model.addMessageInModel(new Message(input));
               model.addMessage(new Message(input), gui
                     .getUssernameFromLogIn(), model
                     .getIdForUserFromDatabase(gui.getUssernameFromLogIn()));

               System.out.println(input);
            }
break;
         case "Log in":

            String name = gui.getUssernameFromLogIn();
            String pass = gui.getPasswordFromLogIn();

            if (model.testUser(name, pass))
            {
               gui.LogInUser();
            }
            else if (model.testAdmin(name, pass))
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

      }

   }

}
