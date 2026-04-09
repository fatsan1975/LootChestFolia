package fr.black_eyes.lootchest.commands.commands;

import java.util.Collections;

import org.bukkit.command.CommandSender;

import fr.black_eyes.lootchest.Lootchest;
import fr.black_eyes.lootchest.Main;
import fr.black_eyes.lootchest.SchedulerCompat;
import fr.black_eyes.lootchest.commands.ArgType;
import fr.black_eyes.lootchest.commands.SubCommand;
import fr.black_eyes.simpleJavaPlugin.Utils;

@SuppressWarnings("deprecation")
public class DespawnAllCommand extends SubCommand {
	
	public DespawnAllCommand() {
		super("despawnall", Collections.emptyList(), Collections.singletonList(ArgType.WORLD));
	}
	
	@Override
	protected void onCommand(CommandSender sender, String[] args) {
		if(args.length == 2) {
			String worldName = args[1];
			for (final Lootchest l : Main.getInstance().getLootChest().values()) {
				if (!l.getWorld().equals(worldName)) {
					continue;
				}
				SchedulerCompat.runRegionLater(Main.getInstance(), l.getActualLocation(), 5L, () -> l.spawn(false, true));
			}
			Utils.msg(sender, "AllChestsDespawnedInWorld", "[World]", worldName);
		}else if(args.length == 1) {
			for (final Lootchest l : Main.getInstance().getLootChest().values()) {
				SchedulerCompat.runRegionLater(Main.getInstance(), l.getActualLocation(), 5L, () -> l.spawn(false, true));
			}
			Utils.msg(sender, "AllChestsDespawned", " ", " ");
		}
		
		
	}
}
