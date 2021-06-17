package esp.lbelmar.customfactions.command;

import esp.lbelmar.customfactions.CustomFactions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandFactionMessage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CustomFactions plugin = CustomFactions.getPlugin(CustomFactions.class);

        FileConfiguration config = plugin.getConfig();

        if(args.length > 0) {
            if (args[0].length() > 0) {
                if (config.contains("factions." + args[0] + ".members")) {
                    // Get all the faction members
                    List<String> memberList = config.getStringList("factions." + args[0] + ".members");
                    // Builds the message
                    StringBuilder messageBuilder = new StringBuilder();
                    // Faction name
                    messageBuilder.append(args[0]).append(": ");
                    // Rest of the message
                    for (int i = 1; i < args.length; i++) {
                        messageBuilder.append(args[i]).append(" ");
                    }
                    // For every member in the faction
                    for (String member: memberList) {
                        // Find the player
                        Player receiver = Bukkit.getPlayer(member);
                        if(receiver != null) {
                            if(receiver.isOnline()) {
                                // Send the message
                                receiver.sendMessage(ChatColor.BLUE + messageBuilder.toString());
                            }
                        }
                    }
                    sender.sendMessage(ChatColor.BLUE + messageBuilder.toString());
                    return true;
                }
                sender.sendMessage("The faction " + args[0] + "doesn't exist.");
                return true;
            }
            sender.sendMessage("Syntax error: /tellFaction <faction> [message]");
            return true;
        }
        sender.sendMessage("Syntax error: /tellFaction <faction> [message]");
        return true;
    }
}
