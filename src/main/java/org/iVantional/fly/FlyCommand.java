package org.iVantional.fly;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class FlyCommand implements CommandExecutor {

    /**
     * Fly command class with part for targeting someone
     *
     * We control for args
     * Args are our command message in parts example:
     * - We need to target our first part after /fly, so we call for args[%]
     * - Command can look /fly Fly_Ultra, and that name is our arg on position 0
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

        // Here we check if command is only "/fly"
        if (args.length == 0) {

            // If player have true on allow flight
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage("Fly was disabled!");
                return true;
            }

            // Here if allow flight is false
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
                player.sendMessage("Fly was enabled!");
                return true;
            }

            return true;
        }

        // Here we look for if command have something after "/fly"
        if (args.length == 1) {
            // Here we are getting the player "/fly <name>"
            // args[0] is first position in command
            Player target = Bukkit.getPlayer(args[0]);

            // Here we check if target is offline
            if (target == null || !target.isOnline()) {
                player.sendMessage("Target is offline!");
                return true;
            }


            // If target is allowed to fly we have to set if to false we want to turn it off
            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                target.sendMessage("Fly was disabled!");
                player.sendMessage("You disabled Fly to " + target.getName());
                return true;
            }

            // Here we want to turn it on
            if (!target.getAllowFlight()) {
                target.setAllowFlight(true);
                target.sendMessage("Fly was enabled!");
                player.sendMessage("You enabled Fly to " + target.getName());
                return true;
            }

            return true;
        }

        // If sender is outside our command like "/fly dsfgjdfmg dfsklfgsdkf ms"
        player.sendMessage("BAD SYNTAX!");
        return true;
    }
}
