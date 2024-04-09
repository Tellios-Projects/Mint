package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.registration.WoodSet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;


public class ModFabricRegistries {
    public static void registerFlammable(Block block, int burn, int spread) {
        FlammableBlockRegistry flammableBlockRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableBlockRegistry.add(block, burn, spread);
    }
    public static void registerFuel(ItemConvertible item, int value) {
        FuelRegistry fuelRegistry = FuelRegistry.INSTANCE;
        fuelRegistry.add(item, value);
    }
    public static void registerCompostable(ItemConvertible item, float chance) {
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(item, chance);
        VillagerInteractionRegistries.registerCompostable(item);
    }

    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        for(Block block : ModBlocks.WOOL_BLOCKS) {
            registry.add(block, 60, 30);
        }
        for(Block block : ModBlocks.WOOL_CARPET_BLOCKS) {
            registry.add(block, 20, 60);
        }

        //TODO make sure everything that should be in here is here and .Burnable() too if need be

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
//        ModInit.LOGGER.debug("Registering composting chances for " + Super.MOD_ID); //This log is commented out because this is not the only place our mod registers this

        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(ModBlocks.WILD_MINT, 0.65f);
        compostingChanceRegistry.add(ModItems.MINT_SPRIG, 0.65f); // same as wheat
        compostingChanceRegistry.add(ModBlocks.MINT_SPRIG_BLOCK, 1.0f);
        compostingChanceRegistry.add(ModItems.MINT_COOKIE, 0.85f); // same as cookie
        compostingChanceRegistry.add(ModItems.WINTERGREEN_BERRIES, 0.65f);
        compostingChanceRegistry.add(ModItems.WINTER_MEDLEY, 0.85f); // same as cookie

        compostingChanceRegistry.add(ModBlocks.HYPERICUM, 0.65f);
        compostingChanceRegistry.add(ModItems.PEACH, 0.65f);
        compostingChanceRegistry.add(ModItems.PEACH_PIT, 0.5f);
        compostingChanceRegistry.add(ModItems.PEACH_SLICE, 0.3f);

        compostingChanceRegistry.add(ModBlocks.HIDCOTE_LAVENDER, 0.65f);
        compostingChanceRegistry.add(ModBlocks.PERIWINKLE_PETALS, 0.5f);
        compostingChanceRegistry.add(ModItems.LAVENDER_BREAD, 0.85f);

        compostingChanceRegistry.add(ModBlocks.THISTLE_FLOWER, 0.65f);
        compostingChanceRegistry.add(ModBlocks.WAXCAP_STEM_BLOCK, 0.65f);
        compostingChanceRegistry.add(ModBlocks.WAXCAP_CAP_BLOCK, 0.85f);
        compostingChanceRegistry.add(ModBlocks.WAXCAP_GILLS, 0.85f);
        compostingChanceRegistry.add(ModBlocks.WAXCAP_GILL_SLAB, 0.85f);
        compostingChanceRegistry.add(ModBlocks.WAXCAP_MUSHROOM, 0.65f);
        compostingChanceRegistry.add(ModItems.ARTICHOKE, 0.65f);
        compostingChanceRegistry.add(ModItems.ARTICHOKE_HEART, 0.65f);
    }

    public static void registerVillagerInteractions() {
        ModInit.LOGGER.debug("Registering villager interactions for " + Super.MOD_ID);

        VillagerInteractionRegistries.registerCollectable(ModItems.MINT_SPRIG);
        VillagerInteractionRegistries.registerCompostable(ModItems.MINT_SPRIG);

        VillagerInteractionRegistries.registerCollectable(ModItems.ARTICHOKE);
        VillagerInteractionRegistries.registerCompostable(ModItems.ARTICHOKE);
    }

    public static void registerFuels() {
//        ModInit.LOGGER.debug("Registering Smelting Fuels for " + Super.MOD_ID); //This log is commented out because this is not the only place our mod registers this
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModBlocks.PEACH_LOG, 300);
        registry.add(ModItems.PEACH_BRANCH, 100);

        for(ItemConvertible item : ModBlocks.BANNER_BLOCKS) {
            registry.add(item, 300);
        }
        for(ItemConvertible item : ModBlocks.WOOL_BLOCKS) {
            registry.add(item, 100);
        }
        for(ItemConvertible item : ModBlocks.WOOL_CARPET_BLOCKS) {
            registry.add(item, 67);
        }
    }

    public static void modifyLootTables() {
        int amberDesertPyramidWeight = 2;
        int amberDesertWellWeight = 2;
        int amberOceanRuinColdWeight = 2;
        int amberOceanRuinWarmWeight = 2;
        int amberTrailRuinsWeight = 2;

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/desert_pyramid").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ModItems.AMBER).weight(amberDesertPyramidWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/desert_well").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ModItems.AMBER).weight(amberDesertWellWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/ocean_ruin_cold").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ModItems.AMBER).weight(amberOceanRuinColdWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/ocean_warm_cold").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ModItems.AMBER).weight(amberOceanRuinWarmWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/trail_ruins_common").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ModItems.AMBER).weight(amberTrailRuinsWeight)));
            }
        });
    }
}
