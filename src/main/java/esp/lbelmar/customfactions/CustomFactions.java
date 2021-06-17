package esp.lbelmar.customfactions;

import esp.lbelmar.customfactions.command.*;
import esp.lbelmar.customfactions.listener.CheckFactionChest;
import esp.lbelmar.customfactions.listener.EnterExitFactionChunk;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CustomFactions extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();

        // Listeners
        getServer().getPluginManager().registerEvents(new CheckFactionChest(this), this);
        getServer().getPluginManager().registerEvents(new EnterExitFactionChunk(this), this);


        // Commands
        Objects.requireNonNull(this.getCommand("createFaction")).setExecutor(new CommandCreateFaction());
        Objects.requireNonNull(this.getCommand("invitePlayer")).setExecutor(new CommandInvitePlayer());
        Objects.requireNonNull(this.getCommand("invitation")).setExecutor(new CommandInvitationFaction());
        Objects.requireNonNull(this.getCommand("claimChunk")).setExecutor(new CommandClaimChunk());
        Objects.requireNonNull(this.getCommand("factionMembers")).setExecutor(new CommandGetFactionMembers());
        Objects.requireNonNull(this.getCommand("banishPlayer")).setExecutor(new CommandBanishPlayer());
        Objects.requireNonNull(this.getCommand("tellFaction")).setExecutor(new CommandFactionMessage());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
