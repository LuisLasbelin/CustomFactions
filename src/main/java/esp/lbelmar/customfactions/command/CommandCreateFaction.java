package esp.lbelmar.customfactions.command;

import esp.lbelmar.customfactions.CustomFactions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class CommandCreateFaction implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        CustomFactions plugin = CustomFactions.getPlugin(CustomFactions.class);

        FileConfiguration config = plugin.getConfig();

        if(args.length > 0) {
            if(args[0].length() > 0) {

                if(!config.contains("factions." + args[0])) {
                    config.set("factions." + args[0] + ".admin", sender.getName());

                    ArrayList<String> list = new ArrayList<>();
                    list.add(sender.getName());

                    config.set("factions." + args[0] + ".members", list);

                    Bukkit.broadcastMessage("The faction " + args[0] + " has been founded! Its leader is now " + sender.getName());
                    plugin.saveConfig();
                    return true;
                }
                sender.sendMessage(args[0] + " already exists!");
                return true;
            }
        }
        sender.sendMessage("Syntax error: /createFaction <name>");
        return true;
    }
}
