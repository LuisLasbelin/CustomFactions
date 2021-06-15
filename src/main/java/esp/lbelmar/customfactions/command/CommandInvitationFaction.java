package esp.lbelmar.customfactions.command;

import esp.lbelmar.customfactions.CustomFactions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class CommandInvitationFaction implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        CustomFactions plugin = CustomFactions.getPlugin(CustomFactions.class);

        FileConfiguration config = plugin.getConfig();

        if(args.length > 1) {
            if(args[0].length() > 1) {
                // the faction exists
                if(config.contains("factions." + args[0])) {
                    // Check if the player is in the invited list
                    List<String> invitedList = config.getStringList("factions." + args[0] + ".invited");
                    for(String invited : invitedList) {
                        if(invited.equals(sender.getName())) {
                            if(args[1].length() > 1) {
                                if(args[1].equals("yes")) {
                                    invitedList.remove(sender.getName());
                                    config.set("factions." + args[0] + ".invited", invitedList);
                                    List<String> memberList = config.getStringList("factions." + args[0] + ".members");
                                    memberList.add(sender.getName());
                                    config.set("factions." + args[0] + ".members", memberList);
                                    sender.sendMessage("You are now a member of " + args[0]);
                                    plugin.saveConfig();
                                    return true;
                                }
                                if(args[1].equals("no")) {
                                    invitedList.remove(sender.getName());
                                    config.set("factions." + args[0] + ".invited", invitedList);
                                    sender.sendMessage("You have dismissed the invitation from " + args[0]);
                                    plugin.saveConfig();
                                    return true;
                                }
                            }
                            sender.sendMessage("Syntax error /invitation <faction> <yes/no>");
                            return true;
                        }
                    }
                    sender.sendMessage("You have not been invited to " + args[0]);
                    return true;
                }
            }
        }
        sender.sendMessage("Syntax error /invitation <faction> <yes/no>");
        return true;
    }
}
