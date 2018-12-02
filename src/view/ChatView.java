package view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JViewport;
import javax.swing.ListModel;

import java.awt.FlowLayout;
import java.awt.CardLayout;

import net.miginfocom.swing.MigLayout;

import javax.swing.SwingConstants;

import controller.ClientController;
import controller.Controller;
import view.Options.ButtonHandler;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ChatView extends JFrame
{

   private JButton general;
   private JTextArea messlist;
   private JScrollPane messages;
   private JTextField text;
   private Button send;

   private JLabel online;
   private JList statusList;
   private JScrollPane status;
   private JButton logOff;

   private static Controller controller;
   private MainGUI mainGui;


   public ChatView(Controller controller)
   {
      super("CHAT");
      this.controller = controller;
      createComponents();
      initializeComponents();
      addComponentsToFrame();
      registerEventHandlers();
   }

   private void createComponents()
   {

      general = new JButton(" General ");
      messlist = new JTextArea("Welcome");
      messlist.setEditable(false);
      messlist.setBackground(SystemColor.inactiveCaption);
      messages = new JScrollPane(messlist);
      messages.setBounds(10, 5, 473, 355);
      text = new JTextField("type here :)");
      text.setBounds(10, 6, 388, 20);
      this.send = new Button("Send");
      send.setBounds(428, 4, 53, 22);

      statusList = new JList();
      statusList.setBackground(Color.LIGHT_GRAY);
      status = new JScrollPane(statusList);
      status.setBounds(10, 5, 166, 386);
      online = new JLabel(" Online ");
      online.setHorizontalAlignment(SwingConstants.CENTER);
      online.setBounds(48, 76, 73, -75);
      logOff = new JButton(" Log - Off ");
      logOff.setBounds(0, 0, 176, 23);

   }

   private void initializeComponents()
   {
      setResizable(false);
      setTitle("  CHAT  ");
      setName("  CHAT  ");
      setLocationRelativeTo(null);
      setSize(730, 500);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2
            - this.getSize().height / 2);

   }

   private void addComponentsToFrame()
   {
      getContentPane()
            .setLayout(new MigLayout("", "[554px][200px]", "[469px]"));

      // left
      JPanel rooms = new JPanel();
      rooms.setBounds(10, 11, 493, 23);
      rooms.setLayout(new GridLayout(1, 4));
      rooms.add(general);
     

      JPanel mesg = new JPanel();
      mesg.setBounds(10, 46, 493, 371);
      mesg.setLayout(null);
      mesg.add(messages);

      JPanel sending = new JPanel();
      sending.setBounds(10, 428, 481, 32);
      sending.setLayout(null);
      sending.add(text);
      sending.add(send);

      JPanel left = new JPanel();
      left.setLayout(null);
      left.add(rooms);
      left.add(mesg);
      left.add(sending);

      // right
      JPanel top = new JPanel();
      top.setBounds(0, 0, 176, 24);
      top.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      top.add(online);

      JPanel mid = new JPanel();
      mid.setBounds(0, 24, 176, 402);
      mid.setLayout(null);
      mid.add(status);

      JPanel bot = new JPanel();
      bot.setBounds(0, 424, 176, 33);
      FlowLayout flowLayout = (FlowLayout) bot.getLayout();
      bot.add(logOff);

      JPanel right = new JPanel();
      right.setLayout(null);
      right.add(top);
      right.add(mid);
      right.add(bot);

      getContentPane().add(left, "cell 0 0,grow");
      getContentPane().add(right, "cell 1 0,grow");

   }

   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)

      {

         if (event.getSource() == send)
         {
            try
            {
               System.out.println(controller);
               controller.execute("Send");
               text.setText("");
            }
            catch (Exception e)
            {
               
               e.printStackTrace();
            }

         }

         if (event.getSource() == logOff)
         {
          try {
               controller.execute("Log out");
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

   private void registerEventHandlers()
   {
      ButtonHandler handler = new ButtonHandler();

      send.addActionListener(handler);
      logOff.addActionListener(handler);
   }

   public void show(Object arg1)
   {
      if (arg1 == null || arg1.toString().length() < 1)
      {
         return;
      }
      String old = messlist.getText();
      if (old.length() > 1)
      {
         old += "\n";
         messlist.setText(old + arg1);
      }

   }

   public String getMessage()
   {
      if (text == null || text.toString().length() < 1)
      {
         return null;
      }
      return text.getText().trim();
   }

   public boolean isActive()
   {
      java.awt.Frame win[] = java.awt.Frame.getFrames();
      boolean isOpen = false;
      for (int i = 0; i < win.length; i++)
      {
         if (win[i].getName().equals("  CHAT  "))
         {
            isOpen = true;
         }
      }
      return isOpen;
   }

   public void LogOut()
   {
      dispose();

   }
}