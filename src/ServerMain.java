import java.sql.SQLException;

import view.ChatView;
import view.ChatViewAdmin;
import view.LogIn;
import view.MainGUI;
import controller.AdminController;
import controller.ClientController;
import controller.Controller;
import domain.mediator.ClientModelProxy;
import domain.mediator.Model;
import domain.mediator.ModelClient;
import domain.mediator.ServerModelManager;

public class ServerMain
{

   public static void main(String[] args) throws Exception
   {
      Model model=new ServerModelManager();
      MainGUI view = new MainGUI();
      Controller controller = new AdminController(model, view);
      view.start(controller);
   }
}

