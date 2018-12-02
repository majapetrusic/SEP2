package domain.model;

import java.io.Serializable;

public class Room implements Serializable
{
   private int roomNumber;
   private int id;

 public Room(int roomNumber)
   {
      this.roomNumber = roomNumber;

   }



   public int getRoomNumber()
   {
      return roomNumber;
   }

   public void setRoomNumber(int roomNumber)
   {
      this.roomNumber = roomNumber;
   }

   public String toString()
   {
      return "Room#" + this.roomNumber ;

   }
}
