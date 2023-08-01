package net.leafenzo.mint;

import net.fabricmc.api.ModInitializer;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.entity.sensor.ModSensorType;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.potions.ModPotions;
import net.leafenzo.mint.registries.ModFabricRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModInit implements ModInitializer {
    public static final String MOD_ID = Super.MOD_ID;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModFabricRegistries.registerFlammableBlocks();
        ModSensorType.registerSensorTypes();

        ModEffects.registerModEffects();
        ModPotions.registerModPotions();
    }
}

