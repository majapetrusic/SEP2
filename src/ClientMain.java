import java.sql.SQLException;
import java.util.Observable;

import controller.ClientController;
import controller.Controller;
import domain.mediator.ClientModelProxy;
import domain.mediator.ClientReceiver;
import domain.mediator.Model;
import domain.mediator.ModelClient;
import domain.mediator.ServerModelManager;
import domain.model.Message;
import view.*;

public class ClientMain
{

   public static void main(String[] args) throws Exception
   {
      ModelClient model = new ClientModelProxy();
      MainGUI view = new MainGUI();
      Controller controller = new ClientController(model, view);
      view.start(controller);
   }
}
