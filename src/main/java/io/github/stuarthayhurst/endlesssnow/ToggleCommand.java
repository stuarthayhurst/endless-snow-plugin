package io.github.stuarthayhurst.endlesssnow;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class ToggleCommand implements TabExecutor {
    private final EndlessSnow pluginInstance;

    ToggleCommand(EndlessSnow instance) {
        this.pluginInstance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Report deep snow status if no value was given
        if (args.length == 0) {
            boolean deepSnowEnabled = this.pluginInstance.getDeepSnowEnabled();
            sender.sendMessage("Deep snow is " + (deepSnowEnabled ? "on" : "off"));
            return true;
        }

        //Enable or disable the snow
        if (args[0].equals("on")) {
            this.pluginInstance.setDeepSnowEnabled(true);
        } else if (args[0].equals("off")) {
            this.pluginInstance.setDeepSnowEnabled(false);
        } else {
          return false;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,
                                      String label, String[] args) {
        return Arrays.asList("on", "off");
    }
}
