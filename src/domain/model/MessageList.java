package domain.model;

import java.util.ArrayList;
import java.util.Date;

import domain.model.Message;

public class MessageList
{
   private ArrayList<Message> message;
   
   public MessageList()
   {
      this.message=new ArrayList<>();
   }
   
   public Message getMessage(int index)
   {
      return message.get(index);
   }
   
   public void addMessage(Message message)
   {
      this.message.add(message);
   }
   public void removeMessage(Message message)
   {
      this.message.remove(message);
   }
   public int count()
   {
      return message.size();
   }
   
   public Message[] getAll()
   {
      Message[] m = new Message[message.size()];
      for (int i=0; i<message.size(); i++)
      {
         m[i] = message.get(i); // ??
      }
      return m;
   }
   
   public Message[] getAllMessagesInAPeriod(Date a, Date b)
   {
      Message[] m = new Message[message.size()];
      for (int i=0; i<message.size(); i++)
      {
         if (message.get(i).getTime().compareTo(a)>=0 && message.get(i).getTime().compareTo(b)<=0)
            m[i]=message.get(i);
      }
      return m;
   }
   
  


   public String toString()
   {
      String all = "MessageList: {";
      for (int i = 0; i < message.size(); i++)
      {
         all += message.get(i);
         if (i < message.size()-1)
            all += "\n ";
      }
      all += "\n}";
      return all;
   }
}