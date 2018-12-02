package domain.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Message implements Serializable
{
   private int id;
   private String messageBody;
   private Room room;
   private Date time;

   public Message(Date time,Room room, String message)
   {
      this.time=time;
      this.messageBody = message;
      if (message == null)
      {
         this.messageBody = "";
      }
      this.room = new Room((int) (Math.random() * 50 + 1));
   }

   public Message(String message)
   {
      this(new Date(), new Room(253), message);
      setId((int) (messageBody.hashCode() * Math.random()));
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }
   
   public Room getRoom()
   {
      return this.room;
   }
   
   public Date getTime()
   {
      return time;
   }

   public String getBody()
   {
      return messageBody;
   }

   public String toString()
   {
      return  "Message: " + messageBody;
   }
}