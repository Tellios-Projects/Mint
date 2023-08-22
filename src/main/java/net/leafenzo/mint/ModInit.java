package net.leafenzo.mint;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.leafenzo.mint.block.DispenserBehavior;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.entity.ModBlockEntityType;
import net.leafenzo.mint.datageneration.ModRecipeProvider;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.entity.ModEntities;
import net.leafenzo.mint.item.ModItemGroups;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.potions.ModPotions;
import net.leafenzo.mint.recipe.ModRecipeSerializer;
import net.leafenzo.mint.registries.ModFabricRegistries;
import net.minecraft.block.DispenserBlock;
import net.minecraft.registry.Registries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class ModInit implements ModInitializer {
        public static final String MOD_ID = Super.MOD_ID;
        public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        @Override
        public void onInitialize() {
            // This code runs as soon as Minecraft is in a mod-load-ready state.
            // However, some things (like resources) may still be uninitialized.
            // Proceed with mild caution.
            ModBlocks.registerModBlocks();
            ModItems.registerModItems();
            ModBlockEntityType.RegisterModBlockEntityTypes();
            ModFabricRegistries.registerFlammableBlocks();
            ModFabricRegistries.registerCompostingChances();
            ModFabricRegistries.registerVillagerInteractions();
            DispenserBehavior.RegisterDispenserBehaviors();
            ModRecipeSerializer.registerModRecipeSerializer();
            ModEffects.registerModEffects();
            ModPotions.registerModPotions();
            ModEntities.registerModEntities();
            ModItemGroups.registerModItemGroups();
            GeckoLib.initialize();
        }
    }

