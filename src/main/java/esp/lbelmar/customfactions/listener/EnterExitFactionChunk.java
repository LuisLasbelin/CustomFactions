package esp.lbelmar.customfactions.listener;

import esp.lbelmar.customfactions.CustomFactions;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EnterExitFactionChunk implements Listener {

    CustomFactions plugin;


    public EnterExitFactionChunk(CustomFactions customFactions) {
        plugin = customFactions;
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent e){
        if (e.getFrom().getChunk().equals(Objects.requireNonNull(e.getTo()).getChunk())) {
            // chunk are equal
        } else {
            // chunk are not equal
            Player player = e.getPlayer();
            FileConfiguration config = plugin.getConfig();
            String currentChunk = player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ();

            Set<String> factions = Objects.requireNonNull(config.getConfigurationSection("factions")).getKeys(false);
            for (String faction : factions) {
                List<String> chunkList = config.getStringList("factions." + faction + ".chunks");
                for (String chunk : chunkList) {
                    // Chunk is claimed
                    if(chunk.equals(currentChunk)) {
                        player.sendMessage(ChatColor.GREEN + "Entering " + faction);
                        return;
                    }
                }
                player.sendMessage(ChatColor.GREEN + "Entering Wilderness");
                return;
            }
        }
    }
}
