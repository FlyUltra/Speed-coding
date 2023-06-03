package org.iVantional;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.iVantional.fly.FlyCommand;
import org.iVantional.god.GodCommand;

import java.util.logging.Level;

/**
 *
 * If you have any questions about developing, or you need something to example
 * You can go to our Discord https://discord.com/invite/E8AkAjaX8m
 * and contact me _Fly_#0571 ❤
 *
 */
public class Main extends JavaPlugin {

    /**
     *
     * Instance for our main class, we can call on this
     *
     */
    private static Main instance;

    /**
     *
     * When plugin enable this method run, and send content
     *
     */
    @Override
    public void onEnable() {
        instance = this;

        registerCommand(new FlyCommand(), "fly");
        registerCommand(new GodCommand(), "god");

        getLogger().log(Level.INFO, "Plugin was enabled!");

    }

    /**
     *
     * When plugin disable this method run, and send content
     *
     */
    @Override
    public void onDisable() {
        getLogger().log(Level.WARNING, "Plugin was disabled!");

    }

    /**
     *
     * Getter for our instance class
     *
     * @return istance
     */
    public static Main getInstance() {
        return instance;
    }

    /**
     *
     * This is custom method for us to have better register
     *
     */
    public void registerCommand(CommandExecutor command, String cmdName) {
        getCommand(cmdName).setExecutor(command);
    }

}
