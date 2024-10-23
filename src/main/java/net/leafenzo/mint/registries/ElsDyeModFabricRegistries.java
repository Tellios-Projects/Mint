package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.*;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.item.ElsDyeModItems;
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


public class ElsDyeModFabricRegistries {
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
        for(Block block : ElsDyeModBlocks.WOOL_BLOCKS) {
            registry.add(block, 60, 30);
        }
        for(Block block : ElsDyeModBlocks.WOOL_CARPET_BLOCKS) {
            registry.add(block, 20, 60);
        }

        //Registry values can be safely overwritten
        registry.add(ElsDyeModBlocks.PEACH_LEAVES, 60, 30);
        registry.add(ElsDyeModBlocks.FLOWERING_PEACH_LEAVES, 60, 30);
        registry.add(ElsDyeModBlocks.PEACH_LOG, 10, 5);
        registry.add(ElsDyeModBlocks.PEACH_WOOD, 10, 5);
        registry.add(ElsDyeModBlocks.STRIPPED_PEACH_LOG, 10, 5);
        registry.add(ElsDyeModBlocks.STRIPPED_PEACH_WOOD, 10, 5);

        registry.add(ElsDyeModBlocks.LAVENDER_BUSHEL, 20, 60);
        registry.add(ElsDyeModBlocks.PERIWINKLE_PETALS, 100, 60);

        registry.add(ElsDyeModBlocks.HANGING_WAXCAP_WAX, 20, 60);
        registry.add(ElsDyeModBlocks.WAXCAP_WAX_BLOCK, 3, 60);
        registry.add(ElsDyeModBlocks.WAXCAP_GILL_SLAB, 3, 60);
        registry.add(ElsDyeModBlocks.WAXCAP_STEM_BLOCK, 3, 60);
        registry.add(ElsDyeModBlocks.WAXCAP_CAP_BLOCK, 3, 60);
    }
    public static void registerCompostingChances() {
//        ModInit.LOGGER.debug("Registering composting chances for " + Super.MOD_ID); //This log is commented out because this is not the only place our mod registers this
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        for(ItemConvertible item : ElsDyeModBlocks.SAPLINGS) {
            compostingChanceRegistry.add(item, 0.3f);
        }
        for(ItemConvertible item : ElsDyeModBlocks.LEAVES) {
            compostingChanceRegistry.add(item, 0.3f);
        }

        //Registry values can be safely overwritten
        compostingChanceRegistry.add(ElsDyeModBlocks.WILD_MINT, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.MINT_SPRIG, 0.65f); // same as wheat
        compostingChanceRegistry.add(ElsDyeModBlocks.MINT_SPRIG_BLOCK, 1.0f);
        compostingChanceRegistry.add(ElsDyeModItems.MINT_COOKIE, 0.85f); // same as cookie
        compostingChanceRegistry.add(ElsDyeModItems.WINTERGREEN_BERRIES, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.WINTER_MEDLEY, 0.85f); // same as cookie

        compostingChanceRegistry.add(ElsDyeModBlocks.HYPERICUM, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.PEACH, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.PEACH_PIT, 0.5f);
        compostingChanceRegistry.add(ElsDyeModItems.PEACH_SLICE, 0.3f);

        compostingChanceRegistry.add(ElsDyeModBlocks.HIDCOTE_LAVENDER, 0.65f);
        compostingChanceRegistry.add(ElsDyeModBlocks.PERIWINKLE_PETALS, 0.5f);
        compostingChanceRegistry.add(ElsDyeModItems.LAVENDER_BREAD, 0.85f);

        compostingChanceRegistry.add(ElsDyeModBlocks.THISTLE_FLOWER, 0.65f);
        compostingChanceRegistry.add(ElsDyeModBlocks.WAXCAP_STEM_BLOCK, 0.65f);
        compostingChanceRegistry.add(ElsDyeModBlocks.WAXCAP_CAP_BLOCK, 0.85f);
        compostingChanceRegistry.add(ElsDyeModBlocks.WAXCAP_GILLS, 0.85f);
        compostingChanceRegistry.add(ElsDyeModBlocks.WAXCAP_GILL_SLAB, 0.85f);
        compostingChanceRegistry.add(ElsDyeModBlocks.WAXCAP_MUSHROOM, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.ARTICHOKE, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.ARTICHOKE_HEART, 0.65f);

        compostingChanceRegistry.add(ElsDyeModItems.PINEAPPLE, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.PINEAPPLE_SLICES, 0.3f);
        compostingChanceRegistry.add(ElsDyeModItems.PINEAPPLE_CROWN, 0.5f);
        compostingChanceRegistry.add(ElsDyeModItems.PINEAPPLE_KEBAB, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.PINEAPPLE_TART, 0.5f);
        compostingChanceRegistry.add(ElsDyeModItems.SAVANNABUD_SEEDS, 0.3f);
        compostingChanceRegistry.add(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS, 0.85f);

        compostingChanceRegistry.add(ElsDyeModBlocks.STRAWBERRY_PLANT, 0.65f);
        compostingChanceRegistry.add(ElsDyeModItems.STRAWBERRY, 0.3f);
        compostingChanceRegistry.add(ElsDyeModItems.CHOCOLATE_STRAWBERRY, 0.3f);
        compostingChanceRegistry.add(ElsDyeModItems.STRAWBERRY_SHORTCAKE, 0.85f);
        compostingChanceRegistry.add(ElsDyeModItems.STRAWBERRY_CHEESECAKE, 1f);
        compostingChanceRegistry.add(ElsDyeModItems.ANGEL_FOOD_CAKE, 1f);
        compostingChanceRegistry.add(ElsDyeModItems.CHERRIES, 0.3f);
        compostingChanceRegistry.add(ElsDyeModItems.CHERRY_PIE, 0.85f);
        compostingChanceRegistry.add(ElsDyeModBlocks.CORDYLINE, 0.65f);
        compostingChanceRegistry.add(ElsDyeModBlocks.PLUM_CORDYLINE, 0.65f);
        compostingChanceRegistry.add(ElsDyeModBlocks.TALL_CORDYLINE, 0.85f);
        compostingChanceRegistry.add(ElsDyeModBlocks.TALL_PLUM_CORDYLINE, 0.85f);
        compostingChanceRegistry.add(ElsDyeModBlocks.VELVET_CAKE, 1f);
        compostingChanceRegistry.add(ElsDyeModItems.POKEBERRIES, 0.65f);
    }

    public static void registerVillagerInteractions() {
        ElsDyeModInit.LOGGER.debug("Registering villager interactions for " + ElsDyeMod.MOD_ID);
        VillagerInteractionRegistries.registerCollectable(ElsDyeModItems.MINT_SPRIG);
        VillagerInteractionRegistries.registerCompostable(ElsDyeModItems.MINT_SPRIG);
        VillagerInteractionRegistries.registerCollectable(ElsDyeModItems.ARTICHOKE);
        VillagerInteractionRegistries.registerCompostable(ElsDyeModItems.ARTICHOKE);
    }

    public static void registerFuels() {
//        ModInit.LOGGER.debug("Registering Smelting Fuels for " + Super.MOD_ID); //This log is commented out because this is not the only place our mod registers this
        FuelRegistry registry = FuelRegistry.INSTANCE;
        for(ItemConvertible item : ElsDyeModBlocks.SAPLINGS) {
            registry.add(item, 100);
        }
        for(ItemConvertible item : ElsDyeModBlocks.LOGS_THAT_BURN) {
            registry.add(item, 300);
        }
        for(ItemConvertible item : ElsDyeModBlocks.BANNER_BLOCKS) {
            registry.add(item, 300);
        }
        for(ItemConvertible item : ElsDyeModBlocks.WOOL_BLOCKS) {
            registry.add(item, 100);
        }
        for(ItemConvertible item : ElsDyeModBlocks.WOOL_CARPET_BLOCKS) {
            registry.add(item, 67);
        }

        //Registry values can be safely overwritten
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getSapling());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getBoatItem());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getChestBoatItem());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getSignItem());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getHangingSignItem());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getPlanks());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getSlab());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getStairs());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getButton());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getDoor());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getTrapDoor());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getFence());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getFenceGate());
        registry.remove(ElsDyeModBlocks.WINTERGREEN_WOODSET.getPressurePlate());

        registry.add(ElsDyeModBlocks.PEACH_LOG, 200);
        registry.add(ElsDyeModBlocks.PEACH_WOOD, 200);
        registry.add(ElsDyeModBlocks.STRIPPED_PEACH_LOG, 200);
        registry.add(ElsDyeModBlocks.STRIPPED_PEACH_WOOD, 200);
        registry.add(ElsDyeModItems.PEACH_BRANCH, 100);
    }

    public static void registerStrippables() {
        StrippableBlockRegistry.register(ElsDyeModBlocks.PEACH_LOG, ElsDyeModBlocks.STRIPPED_PEACH_LOG);
        StrippableBlockRegistry.register(ElsDyeModBlocks.PEACH_WOOD, ElsDyeModBlocks.STRIPPED_PEACH_WOOD);
    }

    public static void modifyLootTables() {
        int amberDesertPyramidWeight = 2;
        int amberDesertWellWeight = 2;
        int amberOceanRuinColdWeight = 3;
        int amberOceanRuinWarmWeight = 3;
        int amberTrailRuinsWeight = 2;

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/desert_pyramid").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ElsDyeModItems.AMBER).weight(amberDesertPyramidWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/desert_well").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ElsDyeModItems.AMBER).weight(amberDesertWellWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/ocean_ruin_cold").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ElsDyeModItems.AMBER).weight(amberOceanRuinColdWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/ocean_ruin_warm").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ElsDyeModItems.AMBER).weight(amberOceanRuinWarmWeight)));
            }
        });
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "archaeology/trail_ruins_common").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ElsDyeModItems.AMBER).weight(amberTrailRuinsWeight)));
            }
        });

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "gameplay/sniffer_digging").equals(id)) {
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(ElsDyeModItems.SAVANNABUD_SEEDS)));
            }
        });

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && new Identifier("minecraft", "blocks/cherry_leaves").equals(id)) {
                tableBuilder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS).with(ItemEntry.builder(ElsDyeModItems.CHERRIES).conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.03f, 0.0305555557f, 0.03125f, 0.033333334f, 0.06f)).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))));
            }
        });
    }
}
