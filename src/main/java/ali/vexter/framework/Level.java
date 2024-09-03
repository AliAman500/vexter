package ali.vexter.framework;

import ali.vexter.entities.Player;
import ali.vexter.enumerations.Direction;
import java.util.Random;
import ali.vexter.tools.Handler;

public class Level {
   private int seed;
   private int numberOfPathRooms;
   private RoomManager roomManager;
   private Handler handler;
   private GameManager gameManager;
   private Room pathStartRoom;
   private Room pathEndRoom;
   private Random random;

   public Level(int seed, int numberOfPathRooms, RoomManager roomManager, Handler handler, GameManager game) {
      this.seed = seed;
      this.random = new Random((long)seed);
      this.numberOfPathRooms = numberOfPathRooms;
      this.roomManager = roomManager;
      this.handler = handler;
      this.gameManager = game;
   }

   public Level(int numberOfPathRooms, RoomManager roomManager, Handler handler, GameManager gameManager) {
      this.seed = GameManager.random.nextInt();
      this.random = new Random((long)this.seed);
      this.numberOfPathRooms = numberOfPathRooms;
      this.roomManager = roomManager;
      this.handler = handler;
      this.gameManager = gameManager;
   }

   public void generate() {
      this.pathStartRoom = this.roomManager.createStartRoom(this.random);
      this.gameManager.player = new Player(100, 100, this.handler);
      this.pathStartRoom.centerEntity(this.gameManager.player);
      this.handler.addEntity(this.gameManager.player);
      this.roomManager.createPathRooms(this.numberOfPathRooms, this.random);
      this.pathEndRoom = (Room)this.handler.getRooms().getLast();
      this.roomManager.createExternalRooms(1, this.random);
   }

   public void delete() {
      this.roomManager.deleteAllRooms();
   }

   public int getSeed() {
      return this.seed;
   }

   public void setSeed(int seed) {
      this.seed = seed;
      this.random.setSeed((long)seed);
   }

   public int getNumberOfPathRooms() {
      return this.numberOfPathRooms;
   }

   public RoomManager getRoomManager() {
      return this.roomManager;
   }

   public Room getPathStartRoom() {
      return this.pathStartRoom;
   }

   public Room getPathEndRoom() {
      return this.pathEndRoom;
   }

   public void setStartingDirection(Direction startingDirection) {
      this.roomManager.setStartingDirection(startingDirection);
   }
}
