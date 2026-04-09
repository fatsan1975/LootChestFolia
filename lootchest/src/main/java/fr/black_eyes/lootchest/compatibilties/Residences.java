package fr.black_eyes.lootchest.compatibilties;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Residences  {
	public static boolean isInResidence(Location loc) {
		try {
			if (Bukkit.getServer().getPluginManager().getPlugin("Residence") == null) {
				return false;
			}
			Class<?> residenceApiClass = Class.forName("com.bekvon.bukkit.residence.api.ResidenceApi");
			Object manager = residenceApiClass.getMethod("getResidenceManager").invoke(null);
			Object residence = manager.getClass().getMethod("getByLoc", Location.class).invoke(manager, loc);
			return residence != null;
		} catch (Exception ignored) {
			return false;
		}
	}

	private Residences() {
		throw new IllegalStateException("Utility class");
	}
}
