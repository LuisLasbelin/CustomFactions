package esp.lbelmar.customfactions.command;

import esp.lbelmar.customfactions.CustomFactions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CommandInvitePlayer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        CustomFactions plugin = CustomFactions.getPlugin(CustomFactions.class);

        FileConfiguration config = plugin.getConfig();

        if(args.length > 1) {
            if(args[0].length() > 0) {
                // faction name, check if the player is admin
                if(Objects.requireNonNull(config.getString("factions." + args[0] + ".admin")).equals(sender.getName())) {
                    // player name, check if the player is not already in the faction
                    if(args[1].length() > 0) {
                        // check if the player is online
                        if(Objects.requireNonNull(Bukkit.getPlayer(args[1])).isOnline()) {
                            // check if the player is the faction
                            List<String> memberList = config.getStringList("factions." + args[0] + ".members");
                            for (String member : memberList) {
                                if(member.equals(args[1])) {
                                    sender.sendMessage(args[1] + " is already a member of " + args[0]);
                                    return true;
                                }
                            }
                            // OK, the player is not in the faction, invite it
                            List<String> invitedList = config.getStringList("factions." + args[0] + ".invited");
                            invitedList.add(args[1]);
                            config.set("factions." + args[0] + ".invited", invitedList);
                            sender.sendMessage(args[1] + " invited to " + args[0]);
                            return true;
                        }
                        // Player is not online
                        sender.sendMessage(args[1] + " is not online.");
                        return true;
                    }
                }
                sender.sendMessage("You are not the admin of " + args[0]);
                return true;
            }
        }

        sender.sendMessage("Syntax error: /invitePlayer <faction> <player>");
        return false;
    }
}
