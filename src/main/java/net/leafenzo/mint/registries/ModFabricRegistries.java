package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.potions.ModPotions;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;

import java.util.logging.Logger;


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
    public static void registerCompostingChances() {
        ModInit.LOGGER.debug("Registering composting chances for " + Super.MOD_ID);

        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(ModItems.MINT_SPRIG, 0.65f); // same as wheat
        compostingChanceRegistry.add(ModItems.MINT_COOKIE, 0.85f); // same as cookie
    }

    public static void registerVillagerInteractions() {
        ModInit.LOGGER.debug("Registering villager interactions for " + Super.MOD_ID);

        VillagerInteractionRegistries.registerCollectable(ModItems.MINT_SPRIG);
        VillagerInteractionRegistries.registerCompostable(ModItems.MINT_SPRIG);
    }

    public static void registerCompostableItem(Item item, float chance) {
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(item, chance);
        VillagerInteractionRegistries.registerCompostable(item);
    }
}
