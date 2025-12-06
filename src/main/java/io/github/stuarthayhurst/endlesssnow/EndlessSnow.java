package io.github.stuarthayhurst.endlesssnow;

import org.bukkit.plugin.java.JavaPlugin;

public class EndlessSnow extends JavaPlugin {
    private boolean deepSnowEnabled = false;

    @Override
    public void onEnable() {
        //Register deep snow toggle command
        this.getCommand("deepsnow").setExecutor(new ToggleCommand(this));

        //Register snow replacer
        this.getServer().getPluginManager().registerEvents(new SnowListener(this), this);

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
    }

    public boolean getDeepSnowEnabled() {
        return this.deepSnowEnabled;
    }
}
