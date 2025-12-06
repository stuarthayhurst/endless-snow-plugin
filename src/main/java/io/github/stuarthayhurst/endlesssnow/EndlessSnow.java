package io.github.stuarthayhurst.endlesssnow;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class EndlessSnow extends JavaPlugin {
    private boolean deepSnowEnabled = false;
    private boolean stormActive = false;

    @Override
    public void onEnable() {
        //Register deep snow and persistent storm commands
        this.getCommand("deepsnow").setExecutor(new DeepSnowCommand(this));
        this.getCommand("persistentstorm").setExecutor(new StormCommand(this));

        //Register snow and weather replacers
        this.getServer().getPluginManager().registerEvents(new SnowListener(this), this);
        this.getServer().getPluginManager().registerEvents(new WeatherListener(this), this);

        //Enable deep snow when the plugin starts
        this.setDeepSnowEnabled(true);

        getLogger().info("Plugin ready");
    }

    @Override
    public void onDisable() {
        this.setDeepSnowEnabled(false);

        getLogger().info("Plugin stopped");
    }

    public void setDeepSnowEnabled(boolean deepSnowEnabled) {
        this.deepSnowEnabled = deepSnowEnabled;
        if (deepSnowEnabled) {
            getLogger().info("Deep snow enabled");
        } else {
            getLogger().info("Deep snow disabled");
        }

        //Allow snow to stack up to entire block, or reset it
        for (World world : Bukkit.getWorlds()) {
            world.setGameRule(GameRule.SNOW_ACCUMULATION_HEIGHT, (deepSnowEnabled ? 8 : 1));
        }
    }

    public boolean getDeepSnowEnabled() {
        return this.deepSnowEnabled;
    }

    public void setStormActive(boolean stormActive) {
        this.stormActive = stormActive;
        if (stormActive) {
            getLogger().info("Persistent storm enabled");
        } else {
            getLogger().info("Persistent storm disabled");
        }

        //Update the weather for all worlds
        for (World world : Bukkit.getWorlds()) {
            world.setStorm(stormActive);
        }
    }

    public boolean getStormActive() {
        return this.stormActive;
    }
}
