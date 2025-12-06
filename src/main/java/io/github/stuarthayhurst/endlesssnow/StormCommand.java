package io.github.stuarthayhurst.endlesssnow;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class StormCommand implements TabExecutor {
    private final EndlessSnow pluginInstance;

    StormCommand(EndlessSnow instance) {
        this.pluginInstance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Report persistent storm status if no value was given
        if (args.length == 0) {
            boolean stormActive = this.pluginInstance.getStormActive();
            sender.sendMessage("Persistent storm is " + (stormActive ? "on" : "off"));

            return true;
        }

        //Enable or disable the storm
        if (args[0].equals("on")) {
            this.pluginInstance.setStormActive(true);
        } else if (args[0].equals("off")) {
            this.pluginInstance.setStormActive(false);
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
