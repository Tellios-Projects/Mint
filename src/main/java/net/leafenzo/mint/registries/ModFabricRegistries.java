package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.*;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import static net.minecraft.data.server.loottable.BlockLootTableGenerator.WITHOUT_SILK_TOUCH_NOR_SHEARS;


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

        //Registry values can be safely overwritten
        registry.add(ModBlocks.PEACH_LEAVES, 60, 30);
        registry.add(ModBlocks.FLOWERING_PEACH_LEAVES, 60, 30);
        registry.add(ModBlocks.PEACH_LOG, 10, 5);
        registry.add(ModBlocks.PEACH_WOOD, 10, 5);
        registry.add(ModBlocks.STRIPPED_PEACH_LOG, 10, 5);
        registry.add(ModBlocks.STRIPPED_PEACH_WOOD, 10, 5);

        registry.add(ModBlocks.LAVENDER_BUSHEL, 20, 60);
        registry.add(ModBlocks.PERIWINKLE_PETALS, 100, 60);

        registry.add(ModBlocks.HANGING_WAXCAP_WAX, 20, 60);
        registry.add(ModBlocks.WAXCAP_WAX_BLOCK, 3, 60);
        registry.add(ModBlocks.WAXCAP_GILL_SLAB, 3, 60);
        registry.add(ModBlocks.WAXCAP_STEM_BLOCK, 3, 60);
        registry.add(ModBlocks.WAXCAP_CAP_BLOCK, 3, 60);
    }
    public static void registerCompostingChances() {
//        ModInit.LOGGER.debug("Registering composting chances for " + Super.MOD_ID); //This log is commented out because this is not the only place our mod registers this
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        for(ItemConvertible item : ModBlocks.SAPLINGS) {
            compostingChanceRegistry.add(item, 0.3f);
        }
        for(ItemConvertible item : ModBlocks.LEAVES) {
            compostingChanceRegistry.add(item, 0.3f);
        }

        //Registry values can be safely overwritten
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

        compostingChanceRegistry.add(ModItems.PINEAPPLE, 0.65f);
        compostingChanceRegistry.add(ModItems.PINEAPPLE_SLICES, 0.3f);
        compostingChanceRegistry.add(ModItems.PINEAPPLE_CROWN, 0.5f);
        compostingChanceRegistry.add(ModItems.PINEAPPLE_KEBAB, 0.65f);
        compostingChanceRegistry.add(ModItems.PINEAPPLE_TART, 0.5f);
        compostingChanceRegistry.add(ModItems.SAVANNABUD_SEEDS, 0.3f);
        compostingChanceRegistry.add(ModBlocks.SHIMMERING_SAVANNABUDS, 0.85f);

        compostingChanceRegistry.add(ModBlocks.STRAWBERRY_PLANT, 0.65f);
        compostingChanceRegistry.add(ModItems.STRAWBERRY, 0.3f);
        compostingChanceRegistry.add(ModItems.CHOCOLATE_STRAWBERRY, 0.3f);
        compostingChanceRegistry.add(ModItems.STRAWBERRY_SHORTCAKE, 0.85f);
        compostingChanceRegistry.add(ModItems.STRAWBERRY_CHEESECAKE, 1f);
        compostingChanceRegistry.add(ModItems.ANGEL_FOOD_CAKE, 1f);
        compostingChanceRegistry.add(ModItems.CHERRIES, 0.3f);
        compostingChanceRegistry.add(ModItems.CHERRY_PIE, 0.85f);
        compostingChanceRegistry.add(ModBlocks.CORDYLINE, 0.65f);
        compostingChanceRegistry.add(ModBlocks.PLUM_CORDYLINE, 0.65f);
        compostingChanceRegistry.add(ModBlocks.TALL_CORDYLINE, 0.85f);
        compostingChanceRegistry.add(ModBlocks.TALL_PLUM_CORDYLINE, 0.85f);
        compostingChanceRegistry.add(ModBlocks.VELVET_CAKE, 1f);
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
        for(ItemConvertible item : ModBlocks.SAPLINGS) {
            registry.add(item, 100);
        }
        for(ItemConvertible item : ModBlocks.LOGS_THAT_BURN) {
            registry.add(item, 300);
        }
        for(ItemConvertible item : ModBlocks.BANNER_BLOCKS) {
            registry.add(item, 300);
        }
        for(ItemConvertible item : ModBlocks.WOOL_BLOCKS) {
            registry.add(item, 100);
        }
        for(ItemConvertible item : ModBlocks.WOOL_CARPET_BLOCKS) {
            registry.add(item, 67);
        }

        //Registry values can be safely overwritten
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getSapling());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getBoatItem());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getChestBoatItem());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getSignItem());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getHangingSignItem());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getPlanks());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getSlab());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getStairs());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getButton());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getDoor());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getTrapDoor());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getFence());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getFenceGate());
        registry.remove(ModBlocks.WINTERGREEN_WOODSET.getPressurePlate());

        registry.add(ModBlocks.PEACH_LOG, 200);
        registry.add(ModBlocks.PEACH_WOOD, 200);
        registry.add(ModBlocks.STRIPPED_PEACH_LOG, 200);
        registry.add(ModBlocks.STRIPPED_PEACH_WOOD, 200);
        registry.add(ModItems.PEACH_BRANCH, 100);
    }

    public static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.PEACH_LOG, ModBlocks.STRIPPED_PEACH_LOG);
        StrippableBlockRegistry.register(ModBlocks.PEACH_WOOD, ModBlocks.STRIPPED_PEACH_WOOD);
    }

    public static void modifyLootTables() {
        int amberDesertPyramidWeight = 2;
        int amberDesertWellWeight = 2;
        int amberOceanRuinColdWeight = 3;
        int amberOceanRuinWarmWeight = 3;
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
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/ocean_ruin_warm").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ModItems.AMBER).weight(amberOceanRuinWarmWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/trail_ruins_common").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ModItems.AMBER).weight(amberTrailRuinsWeight)));
            }
        });

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "gameplay/sniffer_digging").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ModItems.SAVANNABUD_SEEDS)));
            }
        });

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "blocks/cherry_leaves").equals(id)) {
                tableBuilder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS).with(ItemEntry.builder(ModItems.CHERRIES).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.03f, 0.0305555557f, 0.03125f, 0.033333334f, 0.06f)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))));
            }
        });
    }
}
