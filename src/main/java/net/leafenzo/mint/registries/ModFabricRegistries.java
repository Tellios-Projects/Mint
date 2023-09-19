package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;


public class ModFabricRegistries {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        for(Block block : ModBlocks.WOOL_BLOCKS) {
            registry.add(block, 60, 30);
        }
        for(Block block : ModBlocks.WOOL_CARPET_BLOCKS) {
            registry.add(block, 20, 60);
        }

        registry.add(ModBlocks.PEACH_LOG, 5, 5);

        registry.add(ModBlocks.LAVENDER_BUSHEL, 20, 60);
        registry.add(ModBlocks.PERIWINKLE_PETALS, 100, 60);

        // Extremely flammable because of their oily wax
        registry.add(ModBlocks.HANGING_WAXCAP_WAX, 20, 60);
        registry.add(ModBlocks.WAXCAP_WAX_BLOCK, 3, 60);
        registry.add(ModBlocks.WAXCAP_GILL_SLAB, 3, 60);
        //registry.add(ModBlocks.WAXCAP_STEM_BLOCK, 3, 60);
        registry.add(ModBlocks.WAXCAP_CAP_BLOCK, 3, 60);
    }
    public static void registerCompostingChances() {
        ModInit.LOGGER.debug("Registering composting chances for " + Super.MOD_ID);

        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(ModItems.MINT_SPRIG, 0.65f); // same as wheat
        compostingChanceRegistry.add(ModItems.MINT_COOKIE, 0.85f); // same as cookie

        // TODO Peach, Periwinkle, and Artichoke composting chances
    }

    public static void registerVillagerInteractions() {
        ModInit.LOGGER.debug("Registering villager interactions for " + Super.MOD_ID);

        VillagerInteractionRegistries.registerCollectable(ModItems.MINT_SPRIG);
        VillagerInteractionRegistries.registerCompostable(ModItems.MINT_SPRIG);

        VillagerInteractionRegistries.registerCollectable(ModItems.ARTICHOKE);
        VillagerInteractionRegistries.registerCompostable(ModItems.ARTICHOKE);
    }

    public static void registerCompostableItem(Item item, float chance) {
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(item, chance);
        VillagerInteractionRegistries.registerCompostable(item);
    }
}
