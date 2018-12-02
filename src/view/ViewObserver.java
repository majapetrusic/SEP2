package view;

import java.util.Observable;
import java.util.Observer;

public interface ViewObserver extends Observer{
   public void update(Observable o, Object arg);
   

}