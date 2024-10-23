package net.leafenzo.mint.util;

import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import org.apache.http.annotation.Obsolete;

import java.util.*;

public class ModUtil {
//    public static final Item[] VANILLA_DYES = { Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE };
//    public static final Block[] VANILLA_WOOLS = { Blocks.BLACK_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.CYAN_WOOL, Blocks.GRAY_WOOL, Blocks.GREEN_WOOL, Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_GRAY_WOOL, Blocks.LIME_WOOL, Blocks.MAGENTA_WOOL, Blocks.ORANGE_WOOL, Blocks.PINK_WOOL, Blocks.PURPLE_WOOL, Blocks.RED_WOOL, Blocks.YELLOW_WOOL, Blocks.WHITE_WOOL };
//    public static final Block[] VANILLA_BEDS = { Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, Blocks.PINK_BED, Blocks.PURPLE_BED, Blocks.RED_BED, Blocks.YELLOW_BED, Blocks.WHITE_BED };
//    public static final Block[] VANILLA_CARPETS = { Blocks.BLACK_CARPET, Blocks.BLUE_CARPET, Blocks.BROWN_CARPET, Blocks.CYAN_CARPET, Blocks.GRAY_CARPET, Blocks.GREEN_CARPET, Blocks.LIGHT_BLUE_CARPET, Blocks.LIGHT_GRAY_CARPET, Blocks.LIME_CARPET, Blocks.MAGENTA_CARPET, Blocks.ORANGE_CARPET, Blocks.PINK_CARPET, Blocks.PURPLE_CARPET, Blocks.RED_CARPET, Blocks.YELLOW_CARPET, Blocks.WHITE_CARPET };

    public static final DyeColor[] VANILLA_DYE_COLORS = { DyeColor.WHITE, DyeColor.ORANGE, DyeColor.MAGENTA, DyeColor.LIGHT_BLUE, DyeColor.YELLOW, DyeColor.LIME, DyeColor.PINK, DyeColor.GRAY, DyeColor.LIGHT_GRAY, DyeColor.CYAN, DyeColor.PURPLE, DyeColor.BLUE, DyeColor.BROWN, DyeColor.GREEN, DyeColor.RED, DyeColor.BLACK };

    public static final Block[] WHITE_VANILLA_COLORED_BLOCKS = { Blocks.WHITE_WOOL, Blocks.WHITE_CARPET, Blocks.WHITE_TERRACOTTA, Blocks.WHITE_CONCRETE, Blocks.WHITE_CONCRETE_POWDER, Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.WHITE_STAINED_GLASS, Blocks.WHITE_STAINED_GLASS_PANE, Blocks.WHITE_SHULKER_BOX, Blocks.WHITE_BED, Blocks.WHITE_CANDLE, Blocks.WHITE_BANNER };
    public static final Block[] ORANGE_VANILLA_COLORED_BLOCKS = { Blocks.ORANGE_WOOL, Blocks.ORANGE_CARPET, Blocks.ORANGE_TERRACOTTA, Blocks.ORANGE_CONCRETE, Blocks.ORANGE_CONCRETE_POWDER, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.ORANGE_STAINED_GLASS, Blocks.ORANGE_STAINED_GLASS_PANE, Blocks.ORANGE_SHULKER_BOX, Blocks.ORANGE_BED, Blocks.ORANGE_CANDLE, Blocks.ORANGE_BANNER };
    public static final Block[] MAGENTA_VANILLA_COLORED_BLOCKS = { Blocks.MAGENTA_WOOL, Blocks.MAGENTA_CARPET, Blocks.MAGENTA_TERRACOTTA, Blocks.MAGENTA_CONCRETE, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.MAGENTA_STAINED_GLASS, Blocks.MAGENTA_STAINED_GLASS_PANE, Blocks.MAGENTA_SHULKER_BOX, Blocks.MAGENTA_BED, Blocks.MAGENTA_CANDLE, Blocks.MAGENTA_BANNER };
    public static final Block[] LIGHT_BLUE_VANILLA_COLORED_BLOCKS = { Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_BLUE_CARPET, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.LIGHT_BLUE_CONCRETE, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_STAINED_GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_BLUE_CANDLE, Blocks.LIGHT_BLUE_BANNER };
    public static final Block[] YELLOW_VANILLA_COLORED_BLOCKS = { Blocks.YELLOW_WOOL, Blocks.YELLOW_CARPET, Blocks.YELLOW_TERRACOTTA, Blocks.YELLOW_CONCRETE, Blocks.YELLOW_CONCRETE_POWDER, Blocks.YELLOW_GLAZED_TERRACOTTA, Blocks.YELLOW_STAINED_GLASS, Blocks.YELLOW_STAINED_GLASS_PANE, Blocks.YELLOW_SHULKER_BOX, Blocks.YELLOW_BED, Blocks.YELLOW_CANDLE, Blocks.YELLOW_BANNER };
    public static final Block[] LIME_VANILLA_COLORED_BLOCKS = { Blocks.LIME_WOOL, Blocks.LIME_CARPET, Blocks.LIME_TERRACOTTA, Blocks.LIME_CONCRETE, Blocks.LIME_CONCRETE_POWDER, Blocks.LIME_GLAZED_TERRACOTTA, Blocks.LIME_STAINED_GLASS, Blocks.LIME_STAINED_GLASS_PANE, Blocks.LIME_SHULKER_BOX, Blocks.LIME_BED, Blocks.LIME_CANDLE, Blocks.LIME_BANNER };
    public static final Block[] PINK_VANILLA_COLORED_BLOCKS = { Blocks.PINK_WOOL, Blocks.PINK_CARPET, Blocks.PINK_TERRACOTTA, Blocks.PINK_CONCRETE, Blocks.PINK_CONCRETE_POWDER, Blocks.PINK_GLAZED_TERRACOTTA, Blocks.PINK_STAINED_GLASS, Blocks.PINK_STAINED_GLASS_PANE, Blocks.PINK_SHULKER_BOX, Blocks.PINK_BED, Blocks.PINK_CANDLE, Blocks.PINK_BANNER };
    public static final Block[] GRAY_VANILLA_COLORED_BLOCKS = { Blocks.GRAY_WOOL, Blocks.GRAY_CARPET, Blocks.GRAY_TERRACOTTA, Blocks.GRAY_CONCRETE, Blocks.GRAY_CONCRETE_POWDER, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GRAY_STAINED_GLASS, Blocks.GRAY_STAINED_GLASS_PANE, Blocks.GRAY_SHULKER_BOX, Blocks.GRAY_BED, Blocks.GRAY_CANDLE, Blocks.GRAY_BANNER };
    public static final Block[] LIGHT_GRAY_VANILLA_COLORED_BLOCKS = { Blocks.LIGHT_GRAY_WOOL, Blocks.LIGHT_GRAY_CARPET, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_CONCRETE, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Blocks.LIGHT_GRAY_STAINED_GLASS, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIGHT_GRAY_BED, Blocks.LIGHT_GRAY_CANDLE, Blocks.LIGHT_GRAY_BANNER };
    public static final Block[] CYAN_VANILLA_COLORED_BLOCKS = { Blocks.CYAN_WOOL, Blocks.CYAN_CARPET, Blocks.CYAN_TERRACOTTA, Blocks.CYAN_CONCRETE, Blocks.CYAN_CONCRETE_POWDER, Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.CYAN_STAINED_GLASS, Blocks.CYAN_STAINED_GLASS_PANE, Blocks.CYAN_SHULKER_BOX, Blocks.CYAN_BED, Blocks.CYAN_CANDLE, Blocks.CYAN_BANNER };
    public static final Block[] PURPLE_VANILLA_COLORED_BLOCKS = { Blocks.PURPLE_WOOL, Blocks.PURPLE_CARPET, Blocks.PURPLE_TERRACOTTA, Blocks.PURPLE_CONCRETE, Blocks.PURPLE_CONCRETE_POWDER, Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.PURPLE_STAINED_GLASS, Blocks.PURPLE_STAINED_GLASS_PANE, Blocks.PURPLE_SHULKER_BOX, Blocks.PURPLE_BED, Blocks.PURPLE_CANDLE, Blocks.PURPLE_BANNER };
    public static final Block[] BLUE_VANILLA_COLORED_BLOCKS = { Blocks.BLUE_WOOL, Blocks.BLUE_CARPET, Blocks.BLUE_TERRACOTTA, Blocks.BLUE_CONCRETE, Blocks.BLUE_CONCRETE_POWDER, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BLUE_STAINED_GLASS, Blocks.BLUE_STAINED_GLASS_PANE, Blocks.BLUE_SHULKER_BOX, Blocks.BLUE_BED, Blocks.BLUE_CANDLE, Blocks.BLUE_BANNER };
    public static final Block[] BROWN_VANILLA_COLORED_BLOCKS = { Blocks.BROWN_WOOL, Blocks.BROWN_CARPET, Blocks.BROWN_TERRACOTTA, Blocks.BROWN_CONCRETE, Blocks.BROWN_CONCRETE_POWDER, Blocks.BROWN_GLAZED_TERRACOTTA, Blocks.BROWN_STAINED_GLASS, Blocks.BROWN_STAINED_GLASS_PANE, Blocks.BROWN_SHULKER_BOX, Blocks.BROWN_BED, Blocks.BROWN_CANDLE, Blocks.BROWN_BANNER };
    public static final Block[] GREEN_VANILLA_COLORED_BLOCKS = { Blocks.GREEN_WOOL, Blocks.GREEN_CARPET, Blocks.GREEN_TERRACOTTA, Blocks.GREEN_CONCRETE, Blocks.GREEN_CONCRETE_POWDER, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.GREEN_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS_PANE, Blocks.GREEN_SHULKER_BOX, Blocks.GREEN_BED, Blocks.GREEN_CANDLE, Blocks.GREEN_BANNER };
    public static final Block[] RED_VANILLA_COLORED_BLOCKS = { Blocks.RED_WOOL, Blocks.RED_CARPET, Blocks.RED_TERRACOTTA, Blocks.RED_CONCRETE, Blocks.RED_CONCRETE_POWDER, Blocks.RED_GLAZED_TERRACOTTA, Blocks.RED_STAINED_GLASS, Blocks.RED_STAINED_GLASS_PANE, Blocks.RED_SHULKER_BOX, Blocks.RED_BED, Blocks.RED_CANDLE, Blocks.RED_BANNER };
    public static final Block[] BLACK_VANILLA_COLORED_BLOCKS = { Blocks.BLACK_WOOL, Blocks.BLACK_CARPET, Blocks.BLACK_TERRACOTTA, Blocks.BLACK_CONCRETE, Blocks.BLACK_CONCRETE_POWDER, Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLACK_STAINED_GLASS, Blocks.BLACK_STAINED_GLASS_PANE, Blocks.BLACK_SHULKER_BOX, Blocks.BLACK_BED, Blocks.BLACK_CANDLE, Blocks.BLACK_BANNER };

    public static final Block[] WHITE_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.WHITE_SHULKER_BOX, Blocks.WHITE_BED, Blocks.WHITE_CANDLE, Blocks.WHITE_BANNER };
    public static final Block[] ORANGE_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.ORANGE_SHULKER_BOX, Blocks.ORANGE_BED, Blocks.ORANGE_CANDLE, Blocks.ORANGE_BANNER };
    public static final Block[] MAGENTA_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.MAGENTA_SHULKER_BOX, Blocks.MAGENTA_BED, Blocks.MAGENTA_CANDLE, Blocks.MAGENTA_BANNER };
    public static final Block[] LIGHT_BLUE_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_BLUE_CANDLE, Blocks.LIGHT_BLUE_BANNER };
    public static final Block[] YELLOW_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.YELLOW_SHULKER_BOX, Blocks.YELLOW_BED, Blocks.YELLOW_CANDLE, Blocks.YELLOW_BANNER };
    public static final Block[] LIME_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.LIME_SHULKER_BOX, Blocks.LIME_BED, Blocks.LIME_CANDLE, Blocks.LIME_BANNER };
    public static final Block[] PINK_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.PINK_SHULKER_BOX, Blocks.PINK_BED, Blocks.PINK_CANDLE, Blocks.PINK_BANNER };
    public static final Block[] GRAY_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.GRAY_SHULKER_BOX, Blocks.GRAY_BED, Blocks.GRAY_CANDLE, Blocks.GRAY_BANNER };
    public static final Block[] LIGHT_GRAY_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIGHT_GRAY_BED, Blocks.LIGHT_GRAY_CANDLE, Blocks.LIGHT_GRAY_BANNER };
    public static final Block[] CYAN_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.CYAN_SHULKER_BOX, Blocks.CYAN_BED, Blocks.CYAN_CANDLE, Blocks.CYAN_BANNER };
    public static final Block[] PURPLE_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.PURPLE_SHULKER_BOX, Blocks.PURPLE_BED, Blocks.PURPLE_CANDLE, Blocks.PURPLE_BANNER };
    public static final Block[] BLUE_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.BLUE_SHULKER_BOX, Blocks.BLUE_BED, Blocks.BLUE_CANDLE, Blocks.BLUE_BANNER };
    public static final Block[] BROWN_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.BROWN_SHULKER_BOX, Blocks.BROWN_BED, Blocks.BROWN_CANDLE, Blocks.BROWN_BANNER };
    public static final Block[] GREEN_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.GREEN_SHULKER_BOX, Blocks.GREEN_BED, Blocks.GREEN_CANDLE, Blocks.GREEN_BANNER };
    public static final Block[] RED_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.RED_SHULKER_BOX, Blocks.RED_BED, Blocks.RED_CANDLE, Blocks.RED_BANNER };
    public static final Block[] BLACK_VANILLA_FUNCTIONAL_BLOCKS = { Blocks.BLACK_SHULKER_BOX, Blocks.BLACK_BED, Blocks.BLACK_CANDLE, Blocks.BLACK_BANNER };

//    public static Block FirstBlockOfColor(Block block, Block[] blocks) {
//        DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK;
//
//    }

    public static Block[] ColoredBlocksOfColor(DyeColor color) {
        // for vanilla colors
        switch (color) {
            case WHITE:
                return WHITE_VANILLA_COLORED_BLOCKS;
            case ORANGE:
                return ORANGE_VANILLA_COLORED_BLOCKS;
            case MAGENTA:
                return MAGENTA_VANILLA_COLORED_BLOCKS;
            case LIGHT_BLUE:
                return LIGHT_BLUE_VANILLA_COLORED_BLOCKS;
            case YELLOW:
                return YELLOW_VANILLA_COLORED_BLOCKS;
            case LIME:
                return LIME_VANILLA_COLORED_BLOCKS;
            case PINK:
                return PINK_VANILLA_COLORED_BLOCKS;
            case GRAY:
                return GRAY_VANILLA_COLORED_BLOCKS;
            case LIGHT_GRAY:
                return LIGHT_GRAY_VANILLA_COLORED_BLOCKS;
            case CYAN:
                return CYAN_VANILLA_COLORED_BLOCKS;
            case PURPLE:
                return PURPLE_VANILLA_COLORED_BLOCKS;
            case BLUE:
                return BLUE_VANILLA_COLORED_BLOCKS;
            case BROWN:
                return BROWN_VANILLA_COLORED_BLOCKS;
            case GREEN:
                return GREEN_VANILLA_COLORED_BLOCKS;
            case RED:
                return RED_VANILLA_COLORED_BLOCKS;
            case BLACK:
                return BLACK_VANILLA_COLORED_BLOCKS;
        }
        // for colors added by this mod
        return ModBlocks.allMatchesOfColor(ModBlocks.COLORED_BLOCKS, color);
    }
    public static Block[] FunctionalBlocksOfColor(DyeColor color) {
        // for vanilla colors
        switch (color) {
            case WHITE:
                return WHITE_VANILLA_FUNCTIONAL_BLOCKS;
            case ORANGE:
                return ORANGE_VANILLA_FUNCTIONAL_BLOCKS;
            case MAGENTA:
                return MAGENTA_VANILLA_FUNCTIONAL_BLOCKS;
            case LIGHT_BLUE:
                return LIGHT_BLUE_VANILLA_FUNCTIONAL_BLOCKS;
            case YELLOW:
                return YELLOW_VANILLA_FUNCTIONAL_BLOCKS;
            case LIME:
                return LIME_VANILLA_FUNCTIONAL_BLOCKS;
            case PINK:
                return PINK_VANILLA_FUNCTIONAL_BLOCKS;
            case GRAY:
                return GRAY_VANILLA_FUNCTIONAL_BLOCKS;
            case LIGHT_GRAY:
                return LIGHT_GRAY_VANILLA_FUNCTIONAL_BLOCKS;
            case CYAN:
                return CYAN_VANILLA_FUNCTIONAL_BLOCKS;
            case PURPLE:
                return PURPLE_VANILLA_FUNCTIONAL_BLOCKS;
            case BLUE:
                return BLUE_VANILLA_FUNCTIONAL_BLOCKS;
            case BROWN:
                return BROWN_VANILLA_FUNCTIONAL_BLOCKS;
            case GREEN:
                return GREEN_VANILLA_FUNCTIONAL_BLOCKS;
            case RED:
                return RED_VANILLA_FUNCTIONAL_BLOCKS;
            case BLACK:
                return BLACK_VANILLA_FUNCTIONAL_BLOCKS;
        }
        // for colors added by this mod
        return ModBlocks.allMatchesOfColor(ModBlocks.FUNCTIONAL_BLOCKS, color);
    }

    public static <T> T[] concat(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

    public static String formatMultipleIdsForErrorLog(Block[] blocks) {
        String s = "{ ";
        for (int i = 0; i < blocks.length; i++) {
            s = s.concat(Registries.BLOCK.getId(blocks[i]).toString());
            if(i != blocks.length - 1) { s = s.concat(", "); } // if not the last entry
        }
        return s.concat(" }");
    }

    public static String toSentanceCase(String s) {
        String[] words = s.split("[\\s|_]");
        StringBuilder capitalizeWord = new StringBuilder();
        for(String w : words){
            String first = w.substring(0,1);
            String afterfirst = w.substring(1);
            capitalizeWord
                    .append(first.toUpperCase())
                    .append(afterfirst)
                    .append(" ");
        }
        return capitalizeWord.toString().trim();
    }


    public static Set<Identifier> allBlockIdsInNamespace(String namespace) {
        Set<Identifier> set = Registries.BLOCK.getIds();
        Set<Identifier> a = new HashSet<>();
        for(Identifier id : set) {
            if(Objects.equals(id.getNamespace(), namespace)) {
                a.add(id);
            }
        }
        return a;
    }
    public static Set<Identifier> allItemIdsInNamespace(String namespace) {
        Set<Identifier> set = Registries.ITEM.getIds();
        Set<Identifier> a = new HashSet<>();
        for(Identifier id : set) {
            if(Objects.equals(id.getNamespace(), namespace)) {
                a.add(id);
            }
        }
        return a;
    }
    public static Set<Identifier> allItemGroupIdsInNamespace(String namespace) {
        Set<Identifier> set = Registries.ITEM_GROUP.getIds();
        Set<Identifier> a = new HashSet<>();
        for(Identifier id : set) {
            if(Objects.equals(id.getNamespace(), namespace)) {
                a.add(id);
            }
        }
        return a;
    }

//    public static Set<Identifier> allItemGroupIdsInNamespace(String namespace) {
//        Set<Identifier> set = Registries.ITEM_GROUP.getIds();
//        Set<Identifier> a = new HashSet<>();
//        for(Identifier id : set) {
//            if(Objects.equals(id.getNamespace(), namespace)) {
//                a.add(id);
//            }
//        }
//        return a;
//    }

//    public static HashMap<Identifier, Registry<?>> REGISTRY_FROM_ID = new HashMap<Identifier, Registry<?>>();

    public static Set<Identifier> allPotionIdsInNamespace(String namespace) {
        Set<Identifier> set = Registries.POTION.getIds();
        Set<Identifier> a = new HashSet<>();
        for(Identifier id : set) {
            if(Objects.equals(id.getNamespace(), namespace)) {
                a.add(id);
            }
        }
        return a;
    }
    public static Set<Identifier> allStatusEffectIdsInNamespace(String namespace) {
        Set<Identifier> set = Registries.STATUS_EFFECT.getIds();
        Set<Identifier> a = new HashSet<>();
        for(Identifier id : set) {
            if(Objects.equals(id.getNamespace(), namespace)) {
                a.add(id);
            }
        }
        return a;
    }

    public static int randomBetweenWithChanceToFail(Random random, int min, int max, float chance) {
        if(chance < random.nextFloat()) { return 0; }
        return random.nextBetween(min, max);
    }

//    public static Set<Identifier> allTranslatableInNamespace(String namespace) {
//        Set<Identifier> set = Registries.POTION.getIds();
//        Set<Identifier> a = new HashSet<>();
//        for(Identifier id : set) {
//            if(Objects.equals(id.getNamespace(), namespace)) {
//                a.add(id);
//            }
//        }
//        return a;
//    }


    @Obsolete
    public static Set<Identifier> allIdsInNamespace(String namespace) {
        Set<Identifier> set = ModUtil.getAllRegistryIds();
        Set<Identifier> a = new HashSet<>();
        for(Identifier id : set) {
            if(Objects.equals(id.getNamespace(), namespace)) {
                a.add(id);
            }
        }
        return a;
    }



    @Obsolete
    public static Set<Identifier> getAllRegistryIds() {
        Set<Identifier> set = new HashSet<Identifier>();
        set.addAll(Registries.COMMAND_ARGUMENT_TYPE.getIds());
        set.addAll(Registries.ITEM.getIds());
        set.addAll(Registries.BLOCK_ENTITY_TYPE.getIds());
        set.addAll(Registries.CUSTOM_STAT.getIds());
        set.addAll(Registries.FOLIAGE_PLACER_TYPE.getIds());
        set.addAll(Registries.STAT_TYPE.getIds());
        set.addAll(Registries.MATERIAL_RULE.getIds());
        set.addAll(Registries.STRUCTURE_TYPE.getIds());
        set.addAll(Registries.ATTRIBUTE.getIds());
        set.addAll(Registries.POSITION_SOURCE_TYPE.getIds());
        set.addAll(Registries.HEIGHT_PROVIDER_TYPE.getIds());
        set.addAll(Registries.RULE_BLOCK_ENTITY_MODIFIER.getIds());
        set.addAll(Registries.DENSITY_FUNCTION_TYPE.getIds());
        set.addAll(Registries.POSITION_SOURCE_TYPE.getIds());
        set.addAll(Registries.FLUID.getIds());
        set.addAll(Registries.LOOT_CONDITION_TYPE.getIds());
        set.addAll(Registries.STRUCTURE_POOL_ELEMENT.getIds());
        set.addAll(Registries.ACTIVITY.getIds());
        set.addAll(Registries.RECIPE_SERIALIZER.getIds());
        set.addAll(Registries.FROG_VARIANT.getIds());
        set.addAll(Registries.BANNER_PATTERN.getIds());
        set.addAll(Registries.INSTRUMENT.getIds());
        set.addAll(Registries.DECORATED_POT_PATTERN.getIds());
        set.addAll(Registries.FEATURE_SIZE_TYPE.getIds());
        set.addAll(Registries.POINT_OF_INTEREST_TYPE.getIds());
        //mob_effect ?
        set.addAll(Registries.LOOT_POOL_ENTRY_TYPE.getIds());
        set.addAll(Registries.BLOCK_STATE_PROVIDER_TYPE.getIds());
        set.addAll(Registries.CHUNK_GENERATOR.getIds());
        set.addAll(Registries.FLOAT_PROVIDER_TYPE.getIds());
        set.addAll(Registries.CHUNK_STATUS.getIds());
        set.addAll(Registries.ENCHANTMENT.getIds());
        set.addAll(Registries.LOOT_FUNCTION_TYPE.getIds());
        set.addAll(Registries.STRUCTURE_PROCESSOR.getIds());
        set.addAll(Registries.LOOT_SCORE_PROVIDER_TYPE.getIds());
        set.addAll(Registries.TREE_DECORATOR_TYPE.getIds());
        set.addAll(Registries.SCHEDULE.getIds());
        set.addAll(Registries.MATERIAL_CONDITION.getIds());
        set.addAll(Registries.ENTITY_TYPE.getIds());
        set.addAll(Registries.VILLAGER_PROFESSION.getIds());
        set.addAll(Registries.POTION.getIds());
        set.addAll(Registries.RECIPE_TYPE.getIds());
        set.addAll(Registries.INT_PROVIDER_TYPE.getIds());
        set.addAll(Registries.FEATURE.getIds());
        set.addAll(Registries.CAT_VARIANT.getIds());
        set.addAll(Registries.POS_RULE_TEST.getIds());
        set.addAll(Registries.STRUCTURE_PLACEMENT.getIds());
        set.addAll(Registries.LOOT_NBT_PROVIDER_TYPE.getIds());
        //menu?
        set.addAll(Registries.TRUNK_PLACER_TYPE.getIds());
        //creative mode tab?
        set.addAll(Registries.PLACEMENT_MODIFIER_TYPE.getIds());
        set.addAll(Registries.CARVER.getIds());
        set.addAll(Registries.LOOT_NUMBER_PROVIDER_TYPE.getIds());
        set.addAll(Registries.STRUCTURE_PIECE.getIds());
        set.addAll(Registries.SOUND_EVENT.getIds());
        set.addAll(Registries.PARTICLE_TYPE.getIds());
        set.addAll(Registries.GAME_EVENT.getIds());
        set.addAll(Registries.BIOME_SOURCE.getIds());
        set.addAll(Registries.ROOT_PLACER_TYPE.getIds());
        set.addAll(Registries.VILLAGER_TYPE.getIds());
        set.addAll(Registries.PAINTING_VARIANT.getIds());
        set.addAll(Registries.BLOCK_PREDICATE_TYPE.getIds());
        set.addAll(Registries.BLOCK.getIds());
        set.addAll(Registries.SENSOR_TYPE.getIds());
        set.addAll(Registries.RULE_TEST.getIds());
        set.addAll(Registries.MEMORY_MODULE_TYPE.getIds());
        return set;
    }

    public static Direction randomHorizontalDirectionWithCoordinateSeed(BlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        long seed = ((long) x * z) - y;
        Random random = Random.create(seed);

        int d = random.nextInt(4);
        return Direction.byId(d+2);
    }

    public static final boolean mario = true;
    public static final boolean bros = true;
    public static void thenOnly(boolean bros) { }

//    public static <E> void addToIfNotNull(ArrayList<E> collection, E value) {
//        if(value != null) {
//            collection.add(value);
//        }
//    }
    public static void addToIfNotNull(ArrayList<ItemStack> collection, ItemConvertible item) {
        if(item != null) {
            collection.add(item.asItem().getDefaultStack());
        }
    }    // this is smelly isn't it... oh well.
//    public static Block[] ColoredBlocksOfColor(DyeColor color) {
//        switch (color) {
//            case WHITE: return WHITE_VANILLA_COLORED_BLOCKS;
//            case ORANGE: return ORANGE_VANILLA_COLORED_BLOCKS;
//            case MAGENTA: return MAGENTA_VANILLA_COLORED_BLOCKS;
//            case LIGHT_BLUE: return LIGHT_BLUE_VANILLA_COLORED_BLOCKS;
//            case YELLOW: return YELLOW_VANILLA_COLORED_BLOCKS;
//            case LIME: return LIME_VANILLA_COLORED_BLOCKS;
//            case PINK: return PINK_VANILLA_COLORED_BLOCKS;
//            case GRAY: return GRAY_VANILLA_COLORED_BLOCKS;
//            case LIGHT_GRAY: return LIGHT_GRAY_VANILLA_COLORED_BLOCKS;
//            case CYAN: return CYAN_VANILLA_COLORED_BLOCKS;
//            case PURPLE: return PURPLE_VANILLA_COLORED_BLOCKS;
//            case BLUE: return BLUE_VANILLA_COLORED_BLOCKS;
//            case BROWN: return BROWN_VANILLA_COLORED_BLOCKS;
//            case GREEN: return GREEN_VANILLA_COLORED_BLOCKS;
//            case RED: return RED_VANILLA_COLORED_BLOCKS;
//        }
//        if (color.equals(ModDyeColor.MINT)) {
//            return MINT_COLORED_BLOCKS;
//        } else if (color.equals(ModDyeColor.PEACH)) {
//            return PEACH_COLORED_BLOCKS;
//        } else if (color.equals(ModDyeColor.PERIWINKLE)) {
//            return PERIWINKLE_COLORED_BLOCKS;
//        } else if (color.equals(ModDyeColor.ARTICHOKE)) {
//            return ARTICHOKE_COLORED_BLOCKS;
//        } else if (color.equals(ModDyeColor.FUCHSIA)) {
//            return FUCHSIA_COLORED_BLOCKS;
//        } else if (color.equals(ModDyeColor.VERMILION)) {
//            return VERMILION_COLORED_BLOCKS;
//        } else if (color.equals(ModDyeColor.SHAMROCK)) {
//            return SHAMROCK_COLORED_BLOCKS;
//        } else if (color.equals(ModDyeColor.INDIGO)) {
//            return INDIGO_COLORED_BLOCKS;
//        } else if (color.equals(ModDyeColor.BANANA)) {
//            return BANANA_COLORED_BLOCKS;
//        }
//        return null; //Error
//    }

//    public static final Block[] MINT_COLORED_BLOCKS = { ModBlocks.MINT_WOOL, ModBlocks.MINT_CARPET, ModBlocks.MINT_TERRACOTTA, ModBlocks.MINT_CONCRETE, ModBlocks.MINT_CONCRETE_POWDER, ModBlocks.MINT_GLAZED_TERRACOTTA, ModBlocks.MINT_STAINED_GLASS, ModBlocks.MINT_STAINED_GLASS_PANE, ModBlocks.MINT_SHULKER_BOX, ModBlocks.MINT_BED, ModBlocks.MINT_CANDLE, ModBlocks.MINT_BANNER };
//    public static final Block[] PEACH_COLORED_BLOCKS = { ModBlocks.PEACH_WOOL, ModBlocks.PEACH_CARPET, ModBlocks.PEACH_TERRACOTTA, ModBlocks.PEACH_CONCRETE, ModBlocks.PEACH_CONCRETE_POWDER, ModBlocks.PEACH_GLAZED_TERRACOTTA, ModBlocks.PEACH_STAINED_GLASS, ModBlocks.PEACH_STAINED_GLASS_PANE, ModBlocks.PEACH_SHULKER_BOX, ModBlocks.PEACH_BED, ModBlocks.PEACH_CANDLE, ModBlocks.PEACH_BANNER };
//    public static final Block[] PERIWINKLE_COLORED_BLOCKS = { ModBlocks.PERIWINKLE_WOOL, ModBlocks.PERIWINKLE_CARPET, ModBlocks.PERIWINKLE_TERRACOTTA, ModBlocks.PERIWINKLE_CONCRETE, ModBlocks.PERIWINKLE_CONCRETE_POWDER, ModBlocks.PERIWINKLE_GLAZED_TERRACOTTA, ModBlocks.PERIWINKLE_STAINED_GLASS, ModBlocks.PERIWINKLE_STAINED_GLASS_PANE, ModBlocks.PERIWINKLE_SHULKER_BOX, ModBlocks.PERIWINKLE_BED, ModBlocks.PERIWINKLE_CANDLE, ModBlocks.PERIWINKLE_BANNER };
//    public static final Block[] ARTICHOKE_COLORED_BLOCKS = { ModBlocks.ARTICHOKE_WOOL, ModBlocks.ARTICHOKE_CARPET, ModBlocks.ARTICHOKE_TERRACOTTA, ModBlocks.ARTICHOKE_CONCRETE, ModBlocks.ARTICHOKE_CONCRETE_POWDER, ModBlocks.ARTICHOKE_GLAZED_TERRACOTTA, ModBlocks.ARTICHOKE_STAINED_GLASS, ModBlocks.ARTICHOKE_STAINED_GLASS_PANE, ModBlocks.ARTICHOKE_SHULKER_BOX, ModBlocks.ARTICHOKE_BED, ModBlocks.ARTICHOKE_CANDLE, ModBlocks.ARTICHOKE_BANNER };
//    public static final Block[] FUCHSIA_COLORED_BLOCKS = { ModBlocks.FUCHSIA_WOOL, ModBlocks.FUCHSIA_CARPET, ModBlocks.FUCHSIA_TERRACOTTA, ModBlocks.FUCHSIA_CONCRETE, ModBlocks.FUCHSIA_CONCRETE_POWDER, ModBlocks.FUCHSIA_GLAZED_TERRACOTTA, ModBlocks.FUCHSIA_STAINED_GLASS, ModBlocks.FUCHSIA_STAINED_GLASS_PANE, ModBlocks.FUCHSIA_SHULKER_BOX, ModBlocks.FUCHSIA_BED, ModBlocks.FUCHSIA_CANDLE, ModBlocks.FUCHSIA_BANNER };
//    public static final Block[] VERMILION_COLORED_BLOCKS = { ModBlocks.VERMILION_WOOL, ModBlocks.VERMILION_CARPET, ModBlocks.VERMILION_TERRACOTTA, ModBlocks.VERMILION_CONCRETE, ModBlocks.VERMILION_CONCRETE_POWDER, ModBlocks.VERMILION_GLAZED_TERRACOTTA, ModBlocks.VERMILION_STAINED_GLASS, ModBlocks.VERMILION_STAINED_GLASS_PANE, ModBlocks.VERMILION_SHULKER_BOX, ModBlocks.VERMILION_BED, ModBlocks.VERMILION_CANDLE, ModBlocks.VERMILION_BANNER };
//    public static final Block[] SHAMROCK_COLORED_BLOCKS = { ModBlocks.SHAMROCK_WOOL, ModBlocks.SHAMROCK_CARPET, ModBlocks.SHAMROCK_TERRACOTTA, ModBlocks.SHAMROCK_CONCRETE, ModBlocks.SHAMROCK_CONCRETE_POWDER, ModBlocks.SHAMROCK_GLAZED_TERRACOTTA, ModBlocks.SHAMROCK_STAINED_GLASS, ModBlocks.SHAMROCK_STAINED_GLASS_PANE, ModBlocks.SHAMROCK_SHULKER_BOX, ModBlocks.SHAMROCK_BED, ModBlocks.SHAMROCK_CANDLE, ModBlocks.SHAMROCK_BANNER };
//    public static final Block[] INDIGO_COLORED_BLOCKS = { ModBlocks.INDIGO_WOOL, ModBlocks.INDIGO_CARPET, ModBlocks.INDIGO_TERRACOTTA, ModBlocks.INDIGO_CONCRETE, ModBlocks.INDIGO_CONCRETE_POWDER, ModBlocks.INDIGO_GLAZED_TERRACOTTA, ModBlocks.INDIGO_STAINED_GLASS, ModBlocks.INDIGO_STAINED_GLASS_PANE, ModBlocks.INDIGO_SHULKER_BOX, ModBlocks.INDIGO_BED, ModBlocks.INDIGO_CANDLE, ModBlocks.INDIGO_BANNER };
//    public static final Block[] BANANA_COLORED_BLOCKS = { ModBlocks.BANANA_WOOL, ModBlocks.BANANA_CARPET, ModBlocks.BANANA_TERRACOTTA, ModBlocks.BANANA_CONCRETE, ModBlocks.BANANA_CONCRETE_POWDER, ModBlocks.BANANA_GLAZED_TERRACOTTA, ModBlocks.BANANA_STAINED_GLASS, ModBlocks.BANANA_STAINED_GLASS_PANE, ModBlocks.BANANA_SHULKER_BOX, ModBlocks.BANANA_BED, ModBlocks.BANANA_CANDLE, ModBlocks.BANANA_BANNER };

//    public static Block[] FunctionalBlocksOfColor(DyeColor color) {
//        switch (color) {
//            case WHITE: return WHITE_VANILLA_FUNCTIONAL_BLOCKS;
//            case ORANGE: return ORANGE_VANILLA_FUNCTIONAL_BLOCKS;
//            case MAGENTA: return MAGENTA_VANILLA_FUNCTIONAL_BLOCKS;
//            case LIGHT_BLUE: return LIGHT_BLUE_VANILLA_FUNCTIONAL_BLOCKS;
//            case YELLOW: return YELLOW_VANILLA_FUNCTIONAL_BLOCKS;
//            case LIME: return LIME_VANILLA_FUNCTIONAL_BLOCKS;
//            case PINK: return PINK_VANILLA_FUNCTIONAL_BLOCKS;
//            case GRAY: return GRAY_VANILLA_FUNCTIONAL_BLOCKS;
//            case LIGHT_GRAY: return LIGHT_GRAY_VANILLA_FUNCTIONAL_BLOCKS;
//            case CYAN: return CYAN_VANILLA_FUNCTIONAL_BLOCKS;
//            case PURPLE: return PURPLE_VANILLA_FUNCTIONAL_BLOCKS;
//            case BLUE: return BLUE_VANILLA_FUNCTIONAL_BLOCKS;
//            case BROWN: return BROWN_VANILLA_FUNCTIONAL_BLOCKS;
//            case GREEN: return GREEN_VANILLA_FUNCTIONAL_BLOCKS;
//            case RED: return RED_VANILLA_FUNCTIONAL_BLOCKS;
//        }
//        if (color.equals(ModDyeColor.MINT)) {
//            return MINT_FUNCTIONAL_BLOCKS;
//        } else if (color.equals(ModDyeColor.PEACH)) {
//            return PEACH_FUNCTIONAL_BLOCKS;
//        } else if (color.equals(ModDyeColor.PERIWINKLE)) {
//            return PERIWINKLE_FUNCTIONAL_BLOCKS;
//        } else if (color.equals(ModDyeColor.ARTICHOKE)) {
//            return ARTICHOKE_FUNCTIONAL_BLOCKS;
//        } else if (color.equals(ModDyeColor.FUCHSIA)) {
//            return FUCHSIA_FUNCTIONAL_BLOCKS;
//        } else if (color.equals(ModDyeColor.VERMILION)) {
//            return VERMILION_FUNCTIONAL_BLOCKS;
//        } else if (color.equals(ModDyeColor.SHAMROCK)) {
//            return SHAMROCK_FUNCTIONAL_BLOCKS;
//        } else if (color.equals(ModDyeColor.INDIGO)) {
//            return INDIGO_FUNCTIONAL_BLOCKS;
//        } else if (color.equals(ModDyeColor.BANANA)) {
//            return BANANA_FUNCTIONAL_BLOCKS;
//        }
//        return null; //Error
//    }

//    public static final Block[] MINT_FUNCTIONAL_BLOCKS = { ModBlocks.MINT_SHULKER_BOX, ModBlocks.MINT_BED, ModBlocks.MINT_CANDLE, ModBlocks.MINT_BANNER };
//    public static final Block[] PEACH_FUNCTIONAL_BLOCKS = { ModBlocks.PEACH_SHULKER_BOX, ModBlocks.PEACH_BED, ModBlocks.PEACH_CANDLE, ModBlocks.PEACH_BANNER };
//    public static final Block[] PERIWINKLE_FUNCTIONAL_BLOCKS = { ModBlocks.PERIWINKLE_SHULKER_BOX, ModBlocks.PERIWINKLE_BED, ModBlocks.PERIWINKLE_CANDLE, ModBlocks.PERIWINKLE_BANNER };
//    public static final Block[] ARTICHOKE_FUNCTIONAL_BLOCKS = { ModBlocks.ARTICHOKE_SHULKER_BOX, ModBlocks.ARTICHOKE_BED, ModBlocks.ARTICHOKE_CANDLE, ModBlocks.ARTICHOKE_BANNER };
//    public static final Block[] FUCHSIA_FUNCTIONAL_BLOCKS = { ModBlocks.FUCHSIA_SHULKER_BOX, ModBlocks.FUCHSIA_BED, ModBlocks.FUCHSIA_CANDLE, ModBlocks.FUCHSIA_BANNER };
//    public static final Block[] VERMILION_FUNCTIONAL_BLOCKS = { ModBlocks.VERMILION_SHULKER_BOX, ModBlocks.VERMILION_BED, ModBlocks.VERMILION_CANDLE, ModBlocks.VERMILION_BANNER };
//    public static final Block[] SHAMROCK_FUNCTIONAL_BLOCKS = { ModBlocks.SHAMROCK_SHULKER_BOX, ModBlocks.SHAMROCK_BED, ModBlocks.SHAMROCK_CANDLE, ModBlocks.SHAMROCK_BANNER };
//    public static final Block[] INDIGO_FUNCTIONAL_BLOCKS = { ModBlocks.INDIGO_SHULKER_BOX, ModBlocks.INDIGO_BED, ModBlocks.INDIGO_CANDLE, ModBlocks.INDIGO_BANNER };
//    public static final Block[] BANANA_FUNCTIONAL_BLOCKS = { ModBlocks.BANANA_SHULKER_BOX, ModBlocks.BANANA_BED, ModBlocks.BANANA_CANDLE, ModBlocks.BANANA_BANNER };

//    public static ItemConvertible[] Concat(ItemConvertible[] a, ItemConvertible[] b) {
//        int al = a.length;
//        int bl = b.length;
//        int tl = a.length + b.length;
//        ItemConvertible[] c = new ItemConvertible[tl];
//        System.arraycopy(a, 0, c, 0, al);
//        System.arraycopy(b, 0, c, al, bl);
//        return c;
//    }
}
