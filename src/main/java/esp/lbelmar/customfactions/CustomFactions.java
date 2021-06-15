package esp.lbelmar.customfactions;

import esp.lbelmar.customfactions.command.CommandClaimChunk;
import esp.lbelmar.customfactions.command.CommandCreateFaction;
import esp.lbelmar.customfactions.command.CommandInvitationFaction;
import esp.lbelmar.customfactions.command.CommandInvitePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CustomFactions extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();

        Objects.requireNonNull(this.getCommand("createFaction")).setExecutor(new CommandCreateFaction());
        Objects.requireNonNull(this.getCommand("invitePlayer")).setExecutor(new CommandInvitePlayer());
        Objects.requireNonNull(this.getCommand("invitation")).setExecutor(new CommandInvitationFaction());
        Objects.requireNonNull(this.getCommand("claimChunk")).setExecutor(new CommandClaimChunk());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
