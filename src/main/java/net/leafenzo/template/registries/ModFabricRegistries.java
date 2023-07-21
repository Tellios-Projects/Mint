package net.leafenzo.template.registries;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.leafenzo.template.block.ModBlocks;


public class ModFabricRegistries {
    public static void registerOxidizableBlocks() {
        //OxidizableBlocksRegistry.registerOxidizableBlockPair(ModBlocks., ModBlocks.);
    }

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        //registry.add(ModBlocks.EXAMPLE, 65536, 65536);
    }
}
