package ali.vexter.ECS;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Entity {
   protected HashMap<String, Component> componentMap = new HashMap<String, Component>();
   protected LinkedList<Component> components = new LinkedList<Component>();
   private String tag;

   public Entity(String tag) {
      this.tag = tag;
   }

   public void addComponent(Component comp) {
      this.componentMap.put(comp.getClass().getSimpleName(), comp);
      this.components.add(comp);
   }

   public void removeComponent(Component comp) {
      this.componentMap.remove(comp.getClass().getSimpleName(), comp);
      this.components.remove(comp);
   }

   public void update() {
      for(int i = 0; i < this.components.size(); ++i) {
         ((Component)this.components.get(i)).update();
      }

   }

   public void render(Graphics g) {
      for(int i = 0; i < this.components.size(); ++i) {
         ((Component)this.components.get(i)).render(g);
      }

   }

   public Component getComponent(String name) {
      if (this.componentMap.get(name) == null) {
         throw new IllegalArgumentException("'" + name + "' was not found.");
      } else {
         return (Component)this.componentMap.get(name);
      }
   }

   public LinkedList<Component> getComponents() {
      return this.components;
   }

   public HashMap<String, Component> getComponentMap() {
      return this.componentMap;
   }

   public String getTag() {
      return this.tag;
   }
}
