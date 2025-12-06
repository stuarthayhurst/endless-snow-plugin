package io.github.stuarthayhurst.endlesssnow;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements CommandExecutor {
    private EndlessSnow pluginInstance;

    ToggleCommand(EndlessSnow instance) {
        this.pluginInstance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Requires at least one argument
        if (args.length == 0) {
            return false;
        }

        //Enable or disable the snow
        if (args[0].equals("on")) {
            this.pluginInstance.setSnowActive(true);
        } else if (args[0].equals("off")) {
            this.pluginInstance.setSnowActive(false);
        } else {
          return false;
        }

        return true;
    }
}
