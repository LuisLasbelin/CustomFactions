package esp.lbelmar.customfactions.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClaimChunk implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = Bukkit.getPlayer(sender.getName());

        if(args.length > 1) {
            assert player != null;
            if(player.isOnline()) {
                // Get current chunk location
                player.getLocation().getChunk().getX();
                // Check if it is not claimed

            }
        }
        return false;
    }
}
