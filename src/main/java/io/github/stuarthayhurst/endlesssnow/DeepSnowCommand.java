package io.github.stuarthayhurst.endlesssnow;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class DeepSnowCommand implements TabExecutor {
    private final EndlessSnow pluginInstance;

    DeepSnowCommand(EndlessSnow instance) {
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

        //Process the argument
        boolean deepSnowEnabled;
        if (args[0].equals("on")) {
            deepSnowEnabled = true;
        } else if (args[0].equals("off")) {
            deepSnowEnabled = false;
        } else {
            return false;
        }

        //Enable or disable deep snow
        this.pluginInstance.setDeepSnowEnabled(deepSnowEnabled);
        sender.sendMessage((deepSnowEnabled ? "Enabled" : "Disabled") + " deep snow");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command,
                                      String label, String[] args) {
        return Arrays.asList("on", "off");
    }
}
