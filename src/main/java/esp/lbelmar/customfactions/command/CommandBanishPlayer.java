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

public class CommandBanishPlayer implements CommandExecutor {
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
                        // check if the player is the faction
                        List<String> memberList = config.getStringList("factions." + args[0] + ".members");
                        for (String member : memberList) {
                            if(member.equals(args[1])) {
                                memberList.remove(args[1]);
                                config.set("factions." + args[0] + ".members", memberList);
                                sender.sendMessage(args[1] + " was banished from " + args[0]);
                                if(Objects.requireNonNull(Bukkit.getPlayer(args[1])).isOnline()) {
                                    Player player = Bukkit.getPlayer(args[1]);
                                    assert player != null;
                                    player.sendMessage("You were expulsed from " + args[0]);
                                }
                                return true;
                            }
                        }
                        // the player is not in the faction
                        sender.sendMessage(args[1] + " is not a member of " + args[0]);
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
