package esp.lbelmar.customfactions.command;

import esp.lbelmar.customfactions.CustomFactions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CommandClaimChunk implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());

        CustomFactions plugin = CustomFactions.getPlugin(CustomFactions.class);
        FileConfiguration config = plugin.getConfig();

        if(args.length > 0) {
            assert player != null;
            if(player.isOnline()) {
                // Only if the player is the admin of the faction claiming
                if(Objects.requireNonNull(config.getString("factions." + args[0] + ".admin")).equals(sender.getName())) {
                    // Get current chunk location
                    String currentChunk = String.valueOf(player.getLocation().getChunk().getX());
                    // Check if it is not claimed
                    Set<String> factions = Objects.requireNonNull(config.getConfigurationSection("factions")).getKeys(false);
                    for (String faction : factions) {
                        List<String> chunkList = config.getStringList("factions." + faction + ".chunks");
                        for (String chunk : chunkList) {
                            if(chunk.equals(currentChunk)) {
                                sender.sendMessage("This chunk is property of " + factions);
                                return true;
                            }
                        }
                    }
                    // OK the chunk is not claimed
                    if(config.isSet("factions." + args[0])) {
                        List<String> chunkList = config.getStringList("factions." + args[0] + ".chunks");
                        chunkList.add(currentChunk);

                        config.set("factions." + args[0] + ".chunks", chunkList);
                        sender.sendMessage("Chunk claimed for " + args[0]);
                        plugin.saveConfig();
                        return true;
                    }
                }
                sender.sendMessage("You are not the admin of " + args[0]);
                return true;
            }
        }
        sender.sendMessage("Syntax error /claimChunk <faction>");
        return true;
    }
}
