package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.leafenzo.mint.block.ModBlocks;


public class ModFabricRegistries {
    public static void registerOxidizableBlocks() {
        //OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks., ModBlocks.);
    }

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(ModBlocks.MINT_WOOL, 60, 30);
    }
}
