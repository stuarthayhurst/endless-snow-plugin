package io.github.stuarthayhurst.endlesssnow;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class ToggleCommand implements TabExecutor {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,
                                      String label, String[] args) {
        List<String> results = Arrays.asList("on", "off");

        return results;
    }
}
