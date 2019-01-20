package io.github.justincqz.MySquad;

import java.lang.reflect.Field;
import java.util.Map;
import net.minecraft.server.v1_12_R1.EntityTypes;
import net.minecraft.server.v1_12_R1.EntityZombieHusk;
import net.minecraft.server.v1_12_R1.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;

public class CustomNPC extends EntityZombieHusk {


  protected static Field mapStringToClassField, mapClassToStringField, mapClassToIdField, mapStringToIdField;

  static {
    try {
      mapStringToClassField = EntityTypes.class.getDeclaredField("c");
      mapClassToStringField = EntityTypes.class.getDeclaredField("d");
      mapClassToIdField = EntityTypes.class.getDeclaredField("f");
      mapStringToIdField = EntityTypes.class.getDeclaredField("g");

      mapStringToClassField.setAccessible(true);
      mapClassToStringField.setAccessible(true);
      mapClassToIdField.setAccessible(true);
      mapStringToIdField.setAccessible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  protected static void addCustomEntity(Class entityClass, String name, int id) {
    if (mapStringToClassField == null || mapStringToIdField == null || mapClassToStringField == null
        || mapClassToIdField == null) {
      System.out.println("Error has occurred.");
      return;
    } else {
      try {
        Map mapStringToClass = (Map) mapStringToClassField.get(null);
        Map mapStringToId = (Map) mapStringToIdField.get(null);
        Map mapClassToString = (Map) mapClassToStringField.get(null);
        Map mapClassToId = (Map) mapClassToIdField.get(null);

        mapStringToClass.put(name, entityClass);
        mapStringToId.put(name, id);
        mapClassToString.put(entityClass, name);
        mapClassToId.put(entityClass, id);

        mapStringToClassField.set(null, mapStringToClass);
        mapStringToIdField.set(null, mapStringToId);
        mapClassToStringField.set(null, mapClassToString);
        mapClassToIdField.set(null, mapClassToId);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public CustomNPC(World world) {
    super(world);
  }

  public void onLoad() {
    addCustomEntity(CustomNPC.class, "CustomNPC", 23);
  }

  public void spawnCustomEntity(Location loc) {
    World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
    CustomNPC customNPC = new CustomNPC(nmsWorld);
    customNPC.setPosition(loc.getX(), loc.getY(), loc.getZ());
    nmsWorld.addEntity(customNPC);
  }
}
