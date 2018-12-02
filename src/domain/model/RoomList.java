package domain.model;

import java.util.ArrayList;

public class RoomList
{
   private ArrayList<Room> roomList;

   public RoomList()
   {
      this.roomList = new ArrayList<>();
   }

   public Room getRoom(int index)
   {
      return roomList.get(index);
   }

   public ArrayList<Room> getRoomList()
   {
      return roomList;
   }

   public void addRoom(Room room)
   {
      roomList.add(room);
   }

   public int count()
   {
      return roomList.size();
   }

   public String toString()
   {
      String list = "List of Rooms: {";
      for (int i = 0; i < roomList.size(); i++)
      {
         list += roomList.get(i);
         if (i < roomList.size() - 1)
            list += "\n ";

      }
      list += "}";
      return list;

   }

}