package esp.lbelmar.customfactions.listener;

import esp.lbelmar.customfactions.CustomFactions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CheckFactionChest implements Listener {

    CustomFactions plugin;


    public CheckFactionChest(CustomFactions customFactions) {
        plugin = customFactions;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK || Objects.requireNonNull(e.getClickedBlock()).getType() != Material.CHEST) {
            return;
        }
        FileConfiguration config = plugin.getConfig();
        String currentChunk = e.getClickedBlock().getLocation().getChunk().getX() + "," + e.getClickedBlock().getLocation().getChunk().getZ();
        // Check if it is not claimed
        Set<String> factions = Objects.requireNonNull(config.getConfigurationSection("factions")).getKeys(false);
        for (String faction : factions) {
            List<String> chunkList = config.getStringList("factions." + faction + ".chunks");
            for (String chunk : chunkList) {
                if(chunk.equals(currentChunk)) {
                    // The chunk is claimed
                    List<String> memberList = config.getStringList("factions." + faction + ".members");
                    if(!memberList.contains(e.getPlayer().getName())) {
                        // You cant open this
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.RED + "You are not a member of the faction " + faction);
                        return;
                    }
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        FileConfiguration config = plugin.getConfig();
        // Get current chunk location
        String currentChunk = e.getBlock().getLocation().getChunk().getX() + "," + e.getBlock().getLocation().getChunk().getZ();
        // Check if it is not claimed
        Set<String> factions = Objects.requireNonNull(config.getConfigurationSection("factions")).getKeys(false);
        for (String faction : factions) {
            List<String> chunkList = config.getStringList("factions." + faction + ".chunks");
            for (String chunk : chunkList) {
                if(chunk.equals(currentChunk)) {
                    // The chunk is claimed
                    List<String> memberList = config.getStringList("factions." + faction + ".members");
                    if(!memberList.contains(e.getPlayer().getName())) {
                        // You cant open this
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.RED + "You are not a member of the faction " + faction);
                        return;
                    }
                    return;
                }
            }
        }
    }
}
