package ali.vexter.tools;

import ali.vexter.ECS.Entity;
import ali.vexter.framework.Camera;
import ali.vexter.framework.Room;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;
import ali.vexter.projectiles.Projectile;

public class Handler {
    private final LinkedList<Room> rooms = new LinkedList<Room>();
    private final LinkedList<Projectile> projectiles = new LinkedList<Projectile>();
    private final LinkedList<Entity> entities = new LinkedList<Entity>();
    private final HashMap<String, LinkedList<Entity>> entitiesMap = new HashMap<String, LinkedList<Entity>>();
    private final Camera camera;

    public Handler(Camera camera) {
        this.camera = camera;
    }

    public void update() {
        for (LinkedList<Entity> entityList : entitiesMap.values()) {
            for (Entity entity : entityList) {
                entity.update();
            }
        }

        for (Projectile projectile : projectiles) {
            projectile.updateLife();
            projectile.update();
        }
    }

    public void render(Graphics g) {
        for (Room room : rooms) {
            if (camera.getBounds().intersects(room.getBounds())) {
                room.render(g);
            }
        }

        for (Projectile projectile : projectiles) {
            projectile.render(g);
        }

        for (LinkedList<Entity> entityList : entitiesMap.values()) {
            for (Entity entity : entityList) {
                entity.render(g);
            }
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        entitiesMap.computeIfAbsent(entity.getTag(), k -> new LinkedList<Entity>()).add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        LinkedList<Entity> entityList = entitiesMap.get(entity.getTag());
        if (entityList != null) {
            entityList.remove(entity);
            if (entityList.isEmpty()) {
                entitiesMap.remove(entity.getTag());
            }
        }
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public LinkedList<Room> getRooms() {
        return rooms;
    }

    public LinkedList<Entity> getEntities(String tag) {
        return entitiesMap.getOrDefault(tag, new LinkedList<Entity>());
    }

    public LinkedList<Entity> getEntities() {
        return entities;
    }

    public Camera getCamera() {
        return camera;
    }
}
