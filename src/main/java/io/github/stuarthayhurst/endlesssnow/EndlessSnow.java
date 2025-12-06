package io.github.stuarthayhurst.endlesssnow;

import org.bukkit.plugin.java.JavaPlugin;

public class EndlessSnow extends JavaPlugin {
    private boolean snowActive = false;

    @Override
    public void onEnable() {
        this.getCommand("togglesnow").setExecutor(new ToggleCommand(this));

        //Enable snow when the plugin starts
        this.setSnowActive(true);

        getLogger().info("Plugin ready");
    }

    @Override
    public void onDisable() {
        this.setSnowActive(false);
        getLogger().info("Plugin stopped");
    }

    public void setSnowActive(boolean newState) {
        if (this.snowActive == newState) {
            return;
        }
        this.snowActive = newState;

        if (newState) {
            getLogger().info("Snow on");
        } else {
            getLogger().info("Snow off");
        }
    }
}
