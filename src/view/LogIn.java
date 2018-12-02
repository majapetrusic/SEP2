package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

import controller.ClientController;
import controller.Controller;
import domain.mediator.ChatDatabase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class LogIn extends JFrame
{

   private JLabel username;
   private JLabel pass;
   private JLabel time;
   private JTextField usernameTF;
   private JTextField passTF;
   private JButton logIn;
   
   private Controller controller;
   
   public LogIn(Controller controller)
   {
      super("Log In");
      this.controller = controller;
      createComponents();
      initializeComponents();
      System.out.println("fdsfds");
      addComponentsToFrame();
      registerEventHandlers();

   }

   private void createComponents()
   {
      time = new JLabel("Time: ");
      time.setBounds(274, 11, 46, 14);

      username = new JLabel("Username: ");
      username.setBounds(63, 1, 66, 25);
      usernameTF = new JTextField("");
      usernameTF.setBounds(139, 1, 125, 25);
      pass = new JLabel("Password: ");
      pass.setBounds(63, 26, 66, 25);
      passTF = new JPasswordField("");
      passTF.setBounds(139, 26, 125, 25);

      logIn = new JButton(" Log In ");
      logIn.setBounds(155, 23, 91, 23);

   }

   private void initializeComponents()
   {
      setResizable(false);
      setTitle(" Log In ");
      setLocationRelativeTo(null);
      setSize(400, 180);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);

      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
            - this.getSize().height / 2);

   }

   private void addComponentsToFrame()
   {

      JPanel timeP = new JPanel();
      timeP.setBounds(250, 24, 144, -21);
      timeP.setLayout(null);
      timeP.add(time);

      JPanel data = new JPanel();
      data.setBounds(0, 151, 284, -101);
      data.setLayout(null);
      data.add(username);
      data.add(usernameTF);
      data.add(pass);
      data.add(passTF);

      JPanel button = new JPanel();
      button.setBounds(322, 47, 51, 78);
      button.setLayout(null);
      button.add(logIn);

      getContentPane().setLayout(
            new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
      getContentPane().add(timeP);
      getContentPane().add(data);
      getContentPane().add(button);

   }

   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         if (event.getSource() == logIn)
         {
            try
            {
               System.out.println(controller);
               controller.execute("Log in");
            }
            catch (Exception e)
            {
               try
               {
                  controller.execute("Log in");
               }
               catch (Exception e1)
               {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
               JOptionPane.showMessageDialog(null, " Wrong credentials !!!");
               e.printStackTrace();
            }
         }
      }
   }

   private void registerEventHandlers()
   {
      ButtonHandler handler = new ButtonHandler();

      logIn.addActionListener(handler);
   }
   public String getUssername()
   {
      return usernameTF.getText().trim();
   }
   
   public String getPassword()
   {
      return passTF.getText().trim();
   }

}