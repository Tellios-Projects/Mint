/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/src/main/java/net/hibiscus/naturespirit/registration/HibiscusRegistryHelper.java
 */

package net.leafenzo.mint.registration;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.registry.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.apache.http.annotation.Obsolete;

import java.util.ArrayList;
import java.util.HashMap;

public class ModRegistryHelper {
    public static class ItemRegistry {
        // No idea why I did this instead of using DyeItem.getColor(), there may be a reason but I sure didn't note it.
        public static final HashMap<DyeColor, DyeItem> DYE_ITEM_FROM_COLOR = new HashMap<DyeColor, DyeItem>(); static {
            DYE_ITEM_FROM_COLOR.put(DyeColor.WHITE, (DyeItem) Items.WHITE_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.ORANGE, (DyeItem) Items.ORANGE_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.MAGENTA, (DyeItem) Items.MAGENTA_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.LIGHT_BLUE, (DyeItem) Items.LIGHT_BLUE_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.YELLOW, (DyeItem) Items.YELLOW_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.LIME, (DyeItem) Items.LIME_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.PINK, (DyeItem) Items.PINK_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.GRAY, (DyeItem) Items.GRAY_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.LIGHT_GRAY, (DyeItem) Items.LIGHT_GRAY_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.CYAN, (DyeItem) Items.CYAN_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.PURPLE, (DyeItem) Items.PURPLE_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.BLUE, (DyeItem) Items.BLUE_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.BROWN, (DyeItem) Items.BROWN_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.GREEN, (DyeItem) Items.GREEN_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.RED, (DyeItem) Items.RED_DYE);
            DYE_ITEM_FROM_COLOR.put(DyeColor.BLACK, (DyeItem) Items.BLACK_DYE);
        }
        public static final HashMap<DyeItem, TagKey<Item>> DYE_ITEM_TAG_FROM_DYE_ITEM = new HashMap<DyeItem, TagKey<Item>>(); static {
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.WHITE_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.WHITE_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.ORANGE_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.ORANGE_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.MAGENTA_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.MAGENTA_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.LIGHT_BLUE_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.LIGHT_BLUE_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.YELLOW_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.YELLOW_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.LIME_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.LIME_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.PINK_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.PINK_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.GRAY_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.GRAY_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.LIGHT_GRAY_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.LIGHT_GRAY_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.CYAN_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.CYAN_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.PURPLE_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.PURPLE_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.BLUE_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.BLUE_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.BROWN_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.BROWN_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.GREEN_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.GREEN_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.RED_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.RED_DYE));
            DYE_ITEM_TAG_FROM_DYE_ITEM.put((DyeItem) Items.BLACK_DYE, ModTags.Items.getOrCreateDyeItemCommonTag((DyeItem) Items.BLACK_DYE));
        }
        public static final ArrayList<Item> DYE_ITEMS = new ArrayList<Item>();
        public static final ArrayList<Item> BOAT_ITEMS = new ArrayList<Item>();
        public static final ArrayList<Item> CHEST_BOAT_ITEMS = new ArrayList<Item>();
        public static final ArrayList<Item> SIGN_ITEMS = new ArrayList<Item>();
        public static final ArrayList<Item> HANGING_SIGN_ITEMS = new ArrayList<Item>();
        public static final ArrayList<Item> SPAWN_EGG_ITEMS = new ArrayList<Item>();
//    public static final ArrayList<Item> DYED_PAPER_ITEMS = new ArrayList<Item>();

        public static Item registerItem(Identifier id, Item item) {
            return Registry.register(Registries.ITEM, id, item);
        }

        public static Item registerItem(String name, Item item) {
            return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), item);
        }

        public static Item registerItem(BlockItem item) {
            return Registry.register(Registries.ITEM, Registries.BLOCK.getId(item.getBlock()), (Item) item);
        }

        public static Item registerSpawnEgg(String name, EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor, Item.Settings settings) {
            Item i = new SpawnEggItem(type, primaryColor, secondaryColor, settings);
            SPAWN_EGG_ITEMS.add(i);
            return registerItem(name, i);
        }

        public static Item[] toItemArray(ArrayList<Item> input) {
            Item[] array = new Item[input.size()];
            return input.toArray(array);
        }

        public static Item registerItem(Block block, Item item) {
            BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
            return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), item);
        }
    }

    public static class BlockRegistry {
//    public static final HashMap<Block, FlowerPotBlock> FLOWER_POT_FROM_BLOCK = new HashMap<Block, FlowerPotBlock>();
//    public static final HashMap<Block, DyeColor> DYECOLOR_FROM_BLOCK = new HashMap<Block, DyeColor>();
//    public static final HashMap<Block, Block> WOOL_CARPET_FROM_WOOL = new HashMap<Block, Block>();
//    public static final HashMap<Block, Block> CANDLE_CAKE_FROM_CANDLE = new HashMap<Block, Block>();
//    public static final HashMap<Block, Block> STAINED_GLASS_PANE_FROM_STAINED_GLASS = new HashMap<Block, Block>();
//    public static final HashMap<Block, Block> WALL_BANNER_FROM_BANNER = new HashMap<Block, Block>();
//    public static final ArrayList<Block> WOOL_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> WOOL_CARPET_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> DYED_TERRACOTTA_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> CONCRETE_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> CONCRETE_POWDER_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> GLAZED_TERRACOTTA_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> STAINED_GLASS_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> STAINED_GLASS_PANE_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> SHULKER_BOX_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> BED_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> CANDLE_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> CANDLE_CAKE_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> BANNER_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> WALL_BANNER_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> SMALL_FLOWERS = new ArrayList<Block>();
//    public static final ArrayList<Block> FLOWER_POTS = new ArrayList<Block>();
//    public static final ArrayList<Block> MUSHROOM_PLANTS = new ArrayList<Block>();
//    public static final ArrayList<Block> SLABS = new ArrayList<Block>();
//    public static final ArrayList<Block> STAIRS = new ArrayList<Block>();
//    public static final ArrayList<Block> WALLS = new ArrayList<Block>();
//    public static final ArrayList<Block> WOODEN_SLABS = new ArrayList<Block>();
//    public static final ArrayList<Block> WOODEN_STAIRS = new ArrayList<Block>();
//    public static final ArrayList<Block> WOODEN_FENCES = new ArrayList<Block>();
//    public static final ArrayList<Block> FENCE_GATES = new ArrayList<Block>();
//    public static final ArrayList<Block> PLANKS = new ArrayList<Block>();
//    public static final ArrayList<Block> LOGS = new ArrayList<Block>();
//    public static final ArrayList<Block> LOGS_THAT_BURN = new ArrayList<Block>();
//    public static final ArrayList<Block> WOODEN_DOORS = new ArrayList<Block>();
//    public static final ArrayList<Block> WOODEN_TRAPDOORS = new ArrayList<Block>();
//    public static final ArrayList<Block> WOODEN_PRESSURE_PLATES = new ArrayList<Block>();
//    public static final ArrayList<Block> WOODEN_BUTTONS = new ArrayList<Block>();
//    public static final ArrayList<Block> SIGNS = new ArrayList<Block>();
//    public static final ArrayList<Block> RENDER_LAYER_CUTOUT_MIPPED = new ArrayList<Block>();
//    public static final ArrayList<Block> RENDER_LAYER_TRANSLUCENT = new ArrayList<Block>();
//    public static final ArrayList<Block> LEAVES = new ArrayList<Block>();
//    public static final ArrayList<Block> SAPLINGS = new ArrayList<Block>();
//    public static final ArrayList<WoodSet> WOODSETS = new ArrayList<WoodSet>();
//    public static final ArrayList<Block> ALL_CORRUGATED_IRON_BLOCKS = new ArrayList<Block>();
//    public static final ArrayList<Block> ALL_MUCKTUFF_BLOCKS = new ArrayList<Block>();
//    /**
//     * This list is just used in ItemGroups
//     */ public static final ArrayList<Block> COLORED_BLOCKS = new ArrayList<Block>();
//    /**
//     * This list is just used in ItemGroups
//     */ public static final ArrayList<Block> FUNCTIONAL_BLOCKS = new ArrayList<Block>();

        public static final HashMap<DyeColor, Block> COLOR_FROM_WOOL = new HashMap<DyeColor, Block>(); static {
            COLOR_FROM_WOOL.put(DyeColor.WHITE, Blocks.WHITE_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.LIME, Blocks.LIME_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.PINK, Blocks.PINK_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.GRAY, Blocks.GRAY_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.CYAN, Blocks.CYAN_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.BLUE, Blocks.BLUE_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.BROWN, Blocks.BROWN_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.GREEN, Blocks.GREEN_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.RED, Blocks.RED_WOOL);
            COLOR_FROM_WOOL.put(DyeColor.BLACK, Blocks.BLACK_WOOL);
        }


        public static Block registerBlock(String name, Block block) {
            registerBlockItem(name,block);
            return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
        }
        public static Block registerBlockWithoutBlockItem(String name, Block block) {
            return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
        }
        private static Item registerBlockItem(String name, Block block) {
            BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
            //ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(blockItem)); // only for pre 1.20.1, around cuz I'd forget otherwise
            return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), blockItem);
        }

        /**
         * @param group unused 1.20+, only defined here in that version to make backporting easier.
         */
        @Obsolete
        public static Block registerBlock(String name, Block block, ItemGroup group) {
            registerBlockItem(name,block,group);
            return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
        }

        /**
         * @param group unused 1.20+, only defined here in that version to make backporting easier.
         */
        @Obsolete
        private static Item registerBlockItem(String name, Block block, ItemGroup group) {
            BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
            //ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(blockItem)); // only for pre 1.20.1, around cuz I'd forget otherwise
            return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), blockItem);
        }
    }
}
