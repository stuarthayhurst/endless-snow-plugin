package io.github.stuarthayhurst.endlesssnow;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {
    private final EndlessSnow pluginInstance;

    WeatherListener(EndlessSnow instance) {
        this.pluginInstance = instance;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        //Set override weather changes the persistent storm is active
        if (this.pluginInstance.getStormActive()) {
            if (!event.toWeatherState()) {
                event.setCancelled(true);
                event.getWorld().setStorm(true);
            }
        }
    }
}
