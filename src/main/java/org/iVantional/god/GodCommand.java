package org.iVantional.god;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class GodCommand implements CommandExecutor {

    /**
     * God command class with part for targeting someone
     *
     * We control for args
     * Args are our command message in parts example:
     * - We need to target our first part after /god, so we call for args[%]
     * - Command can look /god Fly_Ultra, and that name is our arg on position 0
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        // Here we check for if sender is online player
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().log(Level.INFO, "Sender is console!");
            return true;
        }

        // Our def. for Player from sender;
        Player player = (Player) sender;

        // Here we check if command is only "/god"
        if (args.length == 0) {

            // If player have true invulnerable
            if (player.isInvulnerable()) {
                player.setInvulnerable(false);
                player.sendMessage("God mode is OFF now!");
                return true;
            }

            // Here if invulnerable is false
            if (!player.isInvulnerable()) {
                player.setInvulnerable(true);
                player.sendMessage("God mode is ON now!");
                return true;
            }
        }

        // Here we look for if command have something after "/god"
        if (args.length == 1) {
            // Here we are getting the player "/god <name>"
            // args[0] is first position in command
            Player target = Bukkit.getPlayer(args[0]);

            // Here we check if target is offline
            if (target == null || !target.isOnline()) {
                player.sendMessage("Target is offline!");
                return true;
            }

            // If target is invulnerable, we have to set it to false we want to turn it off
            if (target.isInvulnerable()) {
                target.setInvulnerable(false);
                target.sendMessage("Your god mode is OFF now!");
                player.sendMessage("You turn off god mode to " + target.getName());
                return true;
            }

            // Here we want to turn it on
            if (!target.isInvulnerable()) {
                target.setInvulnerable(true);
                target.sendMessage("Your god mode is ON now!");
                player.sendMessage("You turn on god mode to " + target.getName());
                return true;
            }

        }

        // This part is for our sender if he misses args like "/god dfgkdfg dsfsdf ggdfg" in example situation he sends 3 args, and we control only for first (length = 0)
        player.sendMessage("BAD SYNTAX!");
        return true;
    }
}
