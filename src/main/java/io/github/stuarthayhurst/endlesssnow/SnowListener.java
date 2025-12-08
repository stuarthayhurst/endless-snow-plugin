package io.github.stuarthayhurst.endlesssnow;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.block.data.type.Snow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SnowListener implements Listener {
    private final EndlessSnow pluginInstance;

    SnowListener(EndlessSnow instance) {
        this.pluginInstance = instance;
    }

    private boolean checkNaturalLeaves(BlockData blockData) {
        if (blockData instanceof Leaves leafData) {
            return !leafData.isPersistent();
        }

        return false;
    }

    //Handle snow fall
    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        //Bail out if deep snow is disabled
        if (!this.pluginInstance.getDeepSnowEnabled()) {
            return;
        }

        //Cancel the snow if the snow rate says so
        if (!this.pluginInstance.rollSnowPlacement()) {
            event.setCancelled(true);
            return;
        }

        //Only attempt to replace snow layers
        Block block = event.getBlock();
        Material material = block.getType();
        if (material == Material.SNOW) {
            BlockData blockData = block.getBlockData();
            Snow snowData = (Snow)blockData;

            //Replace snow layers deep enough for a block
            if (snowData.getLayers() == 7) {
                //Cancel natural snow on natural leaves when leaf accumulation is disabled
                if (!this.pluginInstance.getAllowLeafAccumulation()) {
                    Location snowLocation = block.getLocation().add(0, -1, 0);
                    if (this.checkNaturalLeaves(snowLocation.getBlock().getBlockData())) {
                        event.setCancelled(true);
                        return;
                    }
                }

                //Replace the layers with a block and cancel the event
                block.setType(Material.SNOW_BLOCK);
                event.setCancelled(true);
            }
        }
    }

    //Handle player placed snow
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        //Bail out if deep snow is disabled
        if (!this.pluginInstance.getDeepSnowEnabled()) {
            return;
        }

        //Only attempt to replace snow layers
        Block block = event.getBlockPlaced();
        Material material = block.getType();
        if (material == Material.SNOW) {
            BlockData blockData = block.getBlockData();
            Snow snowData = (Snow)blockData;

            //Replace snow layers deep enough for a block
            if (snowData.getLayers() == 8) {
                block.setType(Material.SNOW_BLOCK);
            }
        }
    }
}
