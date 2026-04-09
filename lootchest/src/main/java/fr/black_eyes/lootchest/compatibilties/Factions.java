package fr.black_eyes.lootchest.compatibilties;

import org.bukkit.Location;
import java.lang.reflect.Method;

public class Factions {
	public static boolean isInClaim(Location loc) {
		try {
			Class<?> psClass = Class.forName("com.massivecraft.massivecore.ps.PS");
			Method valueOf = psClass.getMethod("valueOf", Location.class);
			Object ps = valueOf.invoke(null, loc);

			Class<?> boardCollClass = Class.forName("com.massivecraft.factions.entity.BoardColl");
			Object boardColl = boardCollClass.getMethod("get").invoke(null);
			Object factionAt = boardCollClass.getMethod("getFactionAt", psClass).invoke(boardColl, ps);

			Class<?> factionCollClass = Class.forName("com.massivecraft.factions.entity.FactionColl");
			Object noneFaction = factionCollClass.getMethod("get").invoke(null);
			noneFaction = factionCollClass.getMethod("getNone").invoke(noneFaction);
			return !factionAt.equals(noneFaction);
		}catch(Exception ignored){
		}
		return false;
	}

	private Factions() {
		throw new IllegalStateException("Utility class");
	}
}
