package io.github.stuarthayhurst.endlesssnow;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class EndlessSnow extends JavaPlugin {
    private final FileConfiguration config = getConfig();

    private boolean deepSnowEnabled = false;
    private boolean stormActive = false;

    @Override
    public void onEnable() {
        //Handle default config
        this.config.addDefault("deepSnow", true);
        this.config.addDefault("persistentStorm", false);
        this.config.addDefault("allowLeafAccumulation", false);
        this.config.addDefault("snowPlacementReduction", 1.0);
        this.config.options().copyDefaults(true);
        this.saveConfig();

        //Register deep snow and persistent storm commands
        this.getCommand("deepsnow").setExecutor(new DeepSnowCommand(this));
        this.getCommand("persistentstorm").setExecutor(new StormCommand(this));

        //Register snow and weather replacers
        this.getServer().getPluginManager().registerEvents(new SnowListener(this), this);
        this.getServer().getPluginManager().registerEvents(new WeatherListener(this), this);

        //Apply configs
        this.setDeepSnowEnabled(config.getBoolean("deepSnow"));
        if (config.getBoolean("persistentStorm")) {
            this.setStormActive(true);
        }

        this.getLogger().info("Plugin ready");
    }

    @Override
    public void onDisable() {
        this.setDeepSnowEnabled(false);

        this.getLogger().info("Plugin stopped");
    }

    public void setDeepSnowEnabled(boolean deepSnowEnabled) {
        this.deepSnowEnabled = deepSnowEnabled;
        if (deepSnowEnabled) {
            this.getLogger().info("Deep snow enabled");
        } else {
            this.getLogger().info("Deep snow disabled");
        }

        //Allow snow to stack up to entire block, or reset it
        for (World world : Bukkit.getWorlds()) {
            world.setGameRule(GameRule.MAX_SNOW_ACCUMULATION_HEIGHT, (deepSnowEnabled ? 8 : 1));
        }
    }

    public boolean getDeepSnowEnabled() {
        return this.deepSnowEnabled;
    }

    public void setStormActive(boolean stormActive) {
        this.stormActive = stormActive;
        if (stormActive) {
            this.getLogger().info("Persistent storm enabled");
        } else {
            this.getLogger().info("Persistent storm disabled");
        }

        //Update the weather for all worlds
        for (World world : Bukkit.getWorlds()) {
            world.setStorm(stormActive);
        }
    }

    public boolean getStormActive() {
        return this.stormActive;
    }

    public boolean getAllowLeafAccumulation() {
        return this.config.getBoolean("allowLeafAccumulation");
    }

    //Return true if natural snow passes the snow rate reducer
    public boolean rollSnowPlacement() {
        double placementReduction = this.config.getDouble("snowPlacementReduction");
        return (Math.random() < (1.0 / placementReduction));
    }
}
