package ali.vexter.framework;

import ali.vexter.enumerations.Direction;
import java.util.Random;
import ali.vexter.tools.Handler;
import ali.vexter.tools.Utils;

public class RoomManager {
   private int numberOfRooms;
   private Handler handler;
   private Direction lastDirection = null;
   private Direction startingDirection;

   public RoomManager(Handler handler) {
      this.startingDirection = Direction.RIGHT;
      this.handler = handler;
   }

   public Room createStartRoom(Random random) {
      Room startRoom = Room.emptyRoom.createInstance(0, 0, random);
      startRoom.createExit(this.startingDirection);
      this.lastDirection = this.startingDirection;
      this.handler.addRoom(startRoom);
      return startRoom;
   }

   public void createPathRooms(int numberOfRooms, Random random) {
      Room room;
      for(this.numberOfRooms = numberOfRooms; this.handler.getRooms().size() != numberOfRooms; room.removeOverlaps()) {
         room = ((Room)this.handler.getRooms().getLast()).connect(this.lastDirection, Room.emptyRoom, random);
         this.handler.addRoom(room);
         if (room.getAvailableDirections().length != 0) {
            this.lastDirection = Utils.getRandomDirectionSpecified(random, ((Room)this.handler.getRooms().getLast()).getAvailableDirections());
         }
      }

      this.clearExits();
   }

   public void createExternalRooms(int loops, Random random) {
      for(int i = 0; i < loops; ++i) {
         this.createExternalRooms(random);
      }

   }

   private void createExternalRooms(Random random) {
      int size = this.handler.getRooms().size();

      for(int j = 0; j < size; ++j) {
         Room room = (Room)this.handler.getRooms().get(j);
         if (room.getAvailableDirections().length != 0) {
            this.handler.addRoom(room.connect(Utils.getRandomDirectionSpecified(random, room.getAvailableDirections()), Room.emptyRoom, random));
         }
      }

      this.clearExits();
   }

   private void clearExits() {
      for(int i = 0; i < this.handler.getRooms().size(); ++i) {
         Room room = (Room)this.handler.getRooms().get(i);

         for(int j = 0; j < Direction.values().length; ++j) {
            Direction direction = Direction.values()[j];
            if (room.getSurroundingRoom(direction) != null && room.getSurroundingRoom(direction).hasExit(Utils.getOppositeDirection(direction))) {
               room.createExit(direction);
            }
         }
      }

   }

   public void deleteAllRooms() {
      this.handler.getRooms().clear();
   }

   public int getNumberOfRooms() {
      return this.numberOfRooms;
   }

   public Direction getStartingDirection() {
      return this.startingDirection;
   }

   public void setStartingDirection(Direction startingDirection) {
      this.startingDirection = startingDirection;
   }
}
