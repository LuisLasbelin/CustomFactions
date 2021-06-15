package esp.lbelmar.customfactions.command;

import esp.lbelmar.customfactions.CustomFactions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class CommandGetFactionMembers implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CustomFactions plugin = CustomFactions.getPlugin(CustomFactions.class);

        FileConfiguration config = plugin.getConfig();

        if(args.length > 0) {
            if (args[0].length() > 0) {

                if (config.contains("factions." + args[0] + ".members")) {
                    List<String> memberList = config.getStringList("factions." + args[0] + ".members");

                    StringBuilder message = new StringBuilder("Members of " + args[0] + ": ");
                    for (String member : memberList) {
                        message.append(" | ").append(member);
                    }
                    sender.sendMessage(String.valueOf(message));
                    return true;
                }
                sender.sendMessage(args[0] + " is not a valid faction.");
            }
        }
        sender.sendMessage("Syntax error: /factionMembers <faction>");
        return true;
    }
}
