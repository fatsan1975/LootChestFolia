package fr.black_eyes.lootchest.compatibilties;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GriefPreventions  {
	public static boolean isInGriefPreventionClaim(Location loc) {
		try {
			Object grief = Bukkit.getServer().getPluginManager().getPlugin("GriefPrevention");
			if (grief == null) {
				return false;
			}
			Field dataStoreField = grief.getClass().getField("dataStore");
			Object dataStore = dataStoreField.get(grief);
			Method getClaimAt = dataStore.getClass().getMethod("getClaimAt", Location.class, boolean.class, Object.class);
			Object claim = getClaimAt.invoke(dataStore, loc, true, null);
			return claim != null;
		} catch (Exception ignored) {
			return false;
		}
	}

	private GriefPreventions() {
		throw new IllegalStateException("Utility class");
	}
}
