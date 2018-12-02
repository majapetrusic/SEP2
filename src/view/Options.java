package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import net.miginfocom.swing.MigLayout;
import controller.Controller;

public class Options extends JFrame
{  
   
   JPanel main = new JPanel();
   private JList users;
   private JScrollPane usersScroll;

   
         
   JPanel createP = new JPanel();
   private JLabel username;
   private JLabel pass;
   private JTextField usernameTF;
   private JTextField passTF;
   private JButton createb;
   String newname;
   String newpass;
   
      
   JPanel editP = new JPanel();
   private JLabel username1;
   private JLabel pass1;
   private JTextField usernameTFE;
   private JTextField passTFE;
   private JButton editb;
   
    
   JPanel historyP = new JPanel();
   private JList history;
   private JScrollPane historyScroll;

   
   
private JButton create;
private JButton delete;
private JButton edit;
private JButton showHistory;
private JButton back;
private JButton returnToChat;

private  Controller controller;

public Options(Controller controller)
{
   super("CHAT");
   this.controller=controller;
    createComponents();
     initializeComponents();
     addComponentsToFrame();
     registerEventHandlers();
}

private void createComponents() {
   
    users = new JList(      );
    usersScroll = new JScrollPane(users);
    usersScroll.setBounds(10, 5, 452, 341);
    
   
    username = new JLabel("Username: ");
    username.setBounds(38, 55, 67, 14);
    pass= new JLabel("Password: ");
    pass.setBounds(38, 111, 67, 14);
    usernameTF= new JTextField();
    usernameTF.setBounds(115, 52, 105, 20);  
    passTF= new JPasswordField();
    passTF.setBounds(115, 108, 105, 20);
    createb= new JButton("CREATE");
    createb.setBounds(244, 78, 88, 23);
    
   
    username1 = new JLabel("Username: ");
    username1.setBounds(38, 55, 67, 14);
    pass1= new JLabel("Password: ");
    pass1.setBounds(38, 111, 67, 14);
    usernameTFE= new JTextField();
    usernameTFE.setBounds(115, 52, 105, 20); 
    passTFE= new JPasswordField();
    passTFE.setBounds(115, 108, 105, 20);
    editb= new JButton("EDIT");
    editb.setBounds(244, 78, 77, 23);
    
   
    
   
    history = new JList(      );
    historyScroll = new JScrollPane(history);
    historyScroll.setBounds(10, 5, 452, 341);
    
   
      
    create = new JButton("Create");
    create.setBounds(10, 118, 95, 23);
   edit = new JButton("Edit");
   edit.setBounds(10, 152, 95, 23);
   delete = new JButton("Delete");
   delete.setBounds(10, 186, 95, 23);
   showHistory = new JButton("History");
   showHistory.setBounds(10, 220, 95, 23);
   back = new JButton("Back");
   back.setBounds(10, 37, 95, 23);
   returnToChat = new JButton("To Chat");
    returnToChat.setBounds(10, 274, 98, 23);
   
}

private void initializeComponents()
{
   setTitle(" OPTIONS ");
      setLocationRelativeTo(null);
      setSize(630,400);
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
   
}
private void addComponentsToFrame()
{
    getContentPane().setLayout(new MigLayout("", "[550px][180px]", "[457px]"));
    
   
    main.setBounds(0, 0, 488, 357);
    main.setBorder(null);
    main.setLayout(null);
    main.setVisible(true);
    main.add(usersScroll);

   
   
      createP.setBounds(0, 0, 488, 357);
      createP.setLayout(null);
      createP.add(username);
      createP.add(usernameTF);
      createP.add(pass);
      createP.add(passTF);
      createP.add(createb);
       createP.setVisible(false);

    
       
      editP.setBounds(0, 0, 488, 357);
      editP.setLayout(null);
      editP.add(username1);
      editP.add(usernameTFE);
      editP.add(pass1);
      editP.add(passTFE);
      editP.add(editb);
      editP.setVisible(false);     
      
    
       
      historyP.setBounds(0, 0, 488, 357);
      historyP.setBorder(null);
      historyP.setLayout(null);
      historyP.setVisible(false);
      historyP.add(historyScroll);
      
       JPanel left = new JPanel();
       left.setLayout(null);
       left.add(main);
       left.add(createP);
       left.add(editP);
      
       left.add(historyP);
      getContentPane().add(left, "cell 0 0 , grow");
    
       
      JPanel right = new JPanel();
      right.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
      right.setLayout(null);  
      right.add(create);
      right.add(edit);
      right.add(delete);
      right.add(showHistory);
      right.add(back);
      right.add(returnToChat);
    
      getContentPane().add(right, "cell 1 0,grow");   
}
public class ButtonHandler implements ActionListener {
   public void actionPerformed(ActionEvent event) 

   {
      if (event.getSource() == create)
        {
         createP.setVisible(true); 
           main.setVisible(false);
           editP.setVisible(false);  
           historyP.setVisible(false);
        }
      if (event.getSource() == edit)
        {
         createP.setVisible(false); 
           main.setVisible(false);
           historyP.setVisible(false);
           editP.setVisible(true);
        }
      if(event.getSource() == back)
      {
          editP.setVisible(false); 
         createP.setVisible(false); 
          historyP.setVisible(false);
         main.setVisible(true);
         
      }
      if ( event.getSource() == returnToChat)
      {
         dispose();
          ChatViewAdmin CVA = new ChatViewAdmin(controller);
         CVA.setVisible(true);
      }
      if(event.getSource()== delete)
      {
         
         JOptionPane.showConfirmDialog(null,
                "Are you sure you want to Delete the User ??? ");
      }
   
      if(event.getSource()== showHistory)
      {
          editP.setVisible(false); 
          createP.setVisible(false); 
          main.setVisible(false);
          historyP.setVisible(true);
      }
      
      if(event.getSource() == createb)
      {
         try {
             System.out.println(controller);
            controller.execute("Create");
            JOptionPane.showMessageDialog(null, 
                   "User has been added !!");
         } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                   "Unable to do it !!"
                   + " Or already exist!!");
            
            e.printStackTrace();
         }
         
      }
      
   }
}


private void registerEventHandlers() {
   ButtonHandler handler = new ButtonHandler();

   createb.addActionListener(handler);
   create.addActionListener(handler);
   edit.addActionListener(handler);
   back.addActionListener(handler);
   returnToChat.addActionListener(handler);
   delete.addActionListener(handler);
   showHistory.addActionListener(handler);
}

public String getNewUssername()
{
   return usernameTF.getText().trim();
}
public String getNewPassword()
{
   return passTF.getText().trim();
}

}
   
   
