package fr.black_eyes.lootchest.compatibilties;

import org.bukkit.Location;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FactionsX {
	public static boolean isInXClaim(Location loc) {
		try {
			Class<?> gridManagerClass = Class.forName("net.prosavage.factionsx.manager.GridManager");
			Field instanceField = gridManagerClass.getField("INSTANCE");
			Object manager = instanceField.get(null);
			Object faction = gridManagerClass.getMethod("getFactionAt", org.bukkit.Chunk.class).invoke(manager, loc.getChunk());
			Method isWilderness = faction.getClass().getMethod("isWilderness");
			return !(boolean) isWilderness.invoke(faction);
		} catch (Exception ignored) {
			return false;
		}
	}

	private FactionsX() {
		throw new IllegalStateException("Utility class");
	}

}
