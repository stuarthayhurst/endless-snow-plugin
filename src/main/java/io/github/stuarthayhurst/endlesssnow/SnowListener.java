package io.github.stuarthayhurst.endlesssnow;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
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

    private boolean replaceSnowLayers(Block block, boolean isPrePlace) {
        //Only attempt to replace snow layers
        Material material = block.getType();
        if (material == Material.SNOW) {
            BlockData blockData = block.getBlockData();
            Snow snowData = (Snow)blockData;

            //Replace snow layers deep enough for a block
            int layerTarget = isPrePlace ? 7 : 8;
            if (snowData.getLayers() == layerTarget) {
                block.setType(Material.SNOW_BLOCK);
                return true;
            }
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

        //Cancel the event if we replaced the block
        Block block = event.getBlock();
        boolean didReplace = this.replaceSnowLayers(block, true);
        if (didReplace) {
            event.setCancelled(true);
        }
    }

    //Handle player placed snow
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        //Bail out if deep snow is disabled
        if (!this.pluginInstance.getDeepSnowEnabled()) {
            return;
        }

        Block block = event.getBlockPlaced();
        this.replaceSnowLayers(block, false);
    }
}
