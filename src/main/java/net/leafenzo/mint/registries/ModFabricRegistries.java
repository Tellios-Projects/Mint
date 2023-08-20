package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;


public class ModFabricRegistries {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        for(Block block : ModBlocks.WOOL_BLOCKS) {
            registry.add(block, 60, 30);
        }
        for(Block block : ModBlocks.CARPET_BLOCKS) {
            registry.add(block, 20, 60);
        }
    }

    public static void registerCompostableItem(Item item, float chance) {
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(item, chance);
        VillagerInteractionRegistries.registerCompostable(item);
    }

    public static void registerVillagerInteractions() {
        //VillagerInteractionRegistries.registerCollectable();
    }
}
