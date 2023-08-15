package net.leafenzo.mint.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.registry.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static final Item[] VANILLA_DYES = { Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE };
    public static final Block[] VANILLA_WOOLS = { Blocks.BLACK_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.CYAN_WOOL, Blocks.GRAY_WOOL, Blocks.GREEN_WOOL, Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_GRAY_WOOL, Blocks.LIME_WOOL, Blocks.MAGENTA_WOOL, Blocks.ORANGE_WOOL, Blocks.PINK_WOOL, Blocks.PURPLE_WOOL, Blocks.RED_WOOL, Blocks.YELLOW_WOOL, Blocks.WHITE_WOOL };
    public static final Block[] VANILLA_BEDS = { Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, Blocks.PINK_BED, Blocks.PURPLE_BED, Blocks.RED_BED, Blocks.YELLOW_BED, Blocks.WHITE_BED };
    public static final Block[] VANILLA_CARPETS = { Blocks.BLACK_CARPET, Blocks.BLUE_CARPET, Blocks.BROWN_CARPET, Blocks.CYAN_CARPET, Blocks.GRAY_CARPET, Blocks.GREEN_CARPET, Blocks.LIGHT_BLUE_CARPET, Blocks.LIGHT_GRAY_CARPET, Blocks.LIME_CARPET, Blocks.MAGENTA_CARPET, Blocks.ORANGE_CARPET, Blocks.PINK_CARPET, Blocks.PURPLE_CARPET, Blocks.RED_CARPET, Blocks.YELLOW_CARPET, Blocks.WHITE_CARPET };

    // this is smelly isn't it... oh well.
    public static Block[] ColoredBlocksOfColor(DyeColor color) {
        switch (color) {
            case WHITE: return WHITE_VANILLA_COLORED_BLOCKS;
            case ORANGE: return ORANGE_VANILLA_COLORED_BLOCKS;
            case MAGENTA: return MAGENTA_VANILLA_COLORED_BLOCKS;
            case LIGHT_BLUE: return LIGHT_BLUE_VANILLA_COLORED_BLOCKS;
            case YELLOW: return YELLOW_VANILLA_COLORED_BLOCKS;
            case LIME: return LIME_VANILLA_COLORED_BLOCKS;
            case PINK: return PINK_VANILLA_COLORED_BLOCKS;
            case GRAY: return GRAY_VANILLA_COLORED_BLOCKS;
            case LIGHT_GRAY: return LIGHT_GRAY_VANILLA_COLORED_BLOCKS;
            case CYAN: return CYAN_VANILLA_COLORED_BLOCKS;
            case PURPLE: return PURPLE_VANILLA_COLORED_BLOCKS;
            case BLUE: return BLUE_VANILLA_COLORED_BLOCKS;
            case BROWN: return BROWN_VANILLA_COLORED_BLOCKS;
            case GREEN: return GREEN_VANILLA_COLORED_BLOCKS;
            case RED: return RED_VANILLA_COLORED_BLOCKS;
        }
        if (color.equals(ModDyeColor.MINT)) {
            return MINT_COLORED_BLOCKS;
        } else if (color.equals(ModDyeColor.PEACH)) {
            return PEACH_COLORED_BLOCKS;
        } else if (color.equals(ModDyeColor.PERIWINKLE)) {
            return PERIWINKLE_COLORED_BLOCKS;
        } else if (color.equals(ModDyeColor.ARTICHOKE)) {
            return ARTICHOKE_COLORED_BLOCKS;
        } else if (color.equals(ModDyeColor.FUCHSIA)) {
            return FUCHSIA_COLORED_BLOCKS;
        } else if (color.equals(ModDyeColor.VERMILION)) {
            return VERMILION_COLORED_BLOCKS;
        } else if (color.equals(ModDyeColor.SHAMROCK)) {
            return SHAMROCK_COLORED_BLOCKS;
        } else if (color.equals(ModDyeColor.INDIGO)) {
            return INDIGO_COLORED_BLOCKS;
        } else if (color.equals(ModDyeColor.BANANA)) {
            return BANANA_COLORED_BLOCKS;
        }
        return null; //Error
    }
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

    public static final Block[] MINT_COLORED_BLOCKS = { ModBlocks.MINT_WOOL, ModBlocks.MINT_CARPET, ModBlocks.MINT_TERRACOTTA, ModBlocks.MINT_CONCRETE, ModBlocks.MINT_CONCRETE_POWDER, ModBlocks.MINT_GLAZED_TERRACOTTA, ModBlocks.MINT_STAINED_GLASS, ModBlocks.MINT_STAINED_GLASS_PANE, ModBlocks.MINT_SHULKER_BOX, ModBlocks.MINT_BED, ModBlocks.MINT_CANDLE, ModBlocks.MINT_BANNER };
    public static final Block[] PEACH_COLORED_BLOCKS = { ModBlocks.PEACH_WOOL, ModBlocks.PEACH_CARPET, ModBlocks.PEACH_TERRACOTTA, ModBlocks.PEACH_CONCRETE, ModBlocks.PEACH_CONCRETE_POWDER, ModBlocks.PEACH_GLAZED_TERRACOTTA, ModBlocks.PEACH_STAINED_GLASS, ModBlocks.PEACH_STAINED_GLASS_PANE, ModBlocks.PEACH_SHULKER_BOX, ModBlocks.PEACH_BED, ModBlocks.PEACH_CANDLE, ModBlocks.PEACH_BANNER };
    public static final Block[] PERIWINKLE_COLORED_BLOCKS = { ModBlocks.PERIWINKLE_WOOL, ModBlocks.PERIWINKLE_CARPET, ModBlocks.PERIWINKLE_TERRACOTTA, ModBlocks.PERIWINKLE_CONCRETE, ModBlocks.PERIWINKLE_CONCRETE_POWDER, ModBlocks.PERIWINKLE_GLAZED_TERRACOTTA, ModBlocks.PERIWINKLE_STAINED_GLASS, ModBlocks.PERIWINKLE_STAINED_GLASS_PANE, ModBlocks.PERIWINKLE_SHULKER_BOX, ModBlocks.PERIWINKLE_BED, ModBlocks.PERIWINKLE_CANDLE, ModBlocks.PERIWINKLE_BANNER };
    public static final Block[] ARTICHOKE_COLORED_BLOCKS = { ModBlocks.ARTICHOKE_WOOL, ModBlocks.ARTICHOKE_CARPET, ModBlocks.ARTICHOKE_TERRACOTTA, ModBlocks.ARTICHOKE_CONCRETE, ModBlocks.ARTICHOKE_CONCRETE_POWDER, ModBlocks.ARTICHOKE_GLAZED_TERRACOTTA, ModBlocks.ARTICHOKE_STAINED_GLASS, ModBlocks.ARTICHOKE_STAINED_GLASS_PANE, ModBlocks.ARTICHOKE_SHULKER_BOX, ModBlocks.ARTICHOKE_BED, ModBlocks.ARTICHOKE_CANDLE, ModBlocks.ARTICHOKE_BANNER };
    public static final Block[] FUCHSIA_COLORED_BLOCKS = { ModBlocks.FUCHSIA_WOOL, ModBlocks.FUCHSIA_CARPET, ModBlocks.FUCHSIA_TERRACOTTA, ModBlocks.FUCHSIA_CONCRETE, ModBlocks.FUCHSIA_CONCRETE_POWDER, ModBlocks.FUCHSIA_GLAZED_TERRACOTTA, ModBlocks.FUCHSIA_STAINED_GLASS, ModBlocks.FUCHSIA_STAINED_GLASS_PANE, ModBlocks.FUCHSIA_SHULKER_BOX, ModBlocks.FUCHSIA_BED, ModBlocks.FUCHSIA_CANDLE, ModBlocks.FUCHSIA_BANNER };
    public static final Block[] VERMILION_COLORED_BLOCKS = { ModBlocks.VERMILION_WOOL, ModBlocks.VERMILION_CARPET, ModBlocks.VERMILION_TERRACOTTA, ModBlocks.VERMILION_CONCRETE, ModBlocks.VERMILION_CONCRETE_POWDER, ModBlocks.VERMILION_GLAZED_TERRACOTTA, ModBlocks.VERMILION_STAINED_GLASS, ModBlocks.VERMILION_STAINED_GLASS_PANE, ModBlocks.VERMILION_SHULKER_BOX, ModBlocks.VERMILION_BED, ModBlocks.VERMILION_CANDLE, ModBlocks.VERMILION_BANNER };
    public static final Block[] SHAMROCK_COLORED_BLOCKS = { ModBlocks.SHAMROCK_WOOL, ModBlocks.SHAMROCK_CARPET, ModBlocks.SHAMROCK_TERRACOTTA, ModBlocks.SHAMROCK_CONCRETE, ModBlocks.SHAMROCK_CONCRETE_POWDER, ModBlocks.SHAMROCK_GLAZED_TERRACOTTA, ModBlocks.SHAMROCK_STAINED_GLASS, ModBlocks.SHAMROCK_STAINED_GLASS_PANE, ModBlocks.SHAMROCK_SHULKER_BOX, ModBlocks.SHAMROCK_BED, ModBlocks.SHAMROCK_CANDLE, ModBlocks.SHAMROCK_BANNER };
    public static final Block[] INDIGO_COLORED_BLOCKS = { ModBlocks.INDIGO_WOOL, ModBlocks.INDIGO_CARPET, ModBlocks.INDIGO_TERRACOTTA, ModBlocks.INDIGO_CONCRETE, ModBlocks.INDIGO_CONCRETE_POWDER, ModBlocks.INDIGO_GLAZED_TERRACOTTA, ModBlocks.INDIGO_STAINED_GLASS, ModBlocks.INDIGO_STAINED_GLASS_PANE, ModBlocks.INDIGO_SHULKER_BOX, ModBlocks.INDIGO_BED, ModBlocks.INDIGO_CANDLE, ModBlocks.INDIGO_BANNER };
    public static final Block[] BANANA_COLORED_BLOCKS = { ModBlocks.BANANA_WOOL, ModBlocks.BANANA_CARPET, ModBlocks.BANANA_TERRACOTTA, ModBlocks.BANANA_CONCRETE, ModBlocks.BANANA_CONCRETE_POWDER, ModBlocks.BANANA_GLAZED_TERRACOTTA, ModBlocks.BANANA_STAINED_GLASS, ModBlocks.BANANA_STAINED_GLASS_PANE, ModBlocks.BANANA_SHULKER_BOX, ModBlocks.BANANA_BED, ModBlocks.BANANA_CANDLE, ModBlocks.BANANA_BANNER };

    public static Block[] FunctionalBlocksOfColor(DyeColor color) {
        switch (color) {
            case WHITE: return WHITE_VANILLA_FUNCTIONAL_BLOCKS;
            case ORANGE: return ORANGE_VANILLA_FUNCTIONAL_BLOCKS;
            case MAGENTA: return MAGENTA_VANILLA_FUNCTIONAL_BLOCKS;
            case LIGHT_BLUE: return LIGHT_BLUE_VANILLA_FUNCTIONAL_BLOCKS;
            case YELLOW: return YELLOW_VANILLA_FUNCTIONAL_BLOCKS;
            case LIME: return LIME_VANILLA_FUNCTIONAL_BLOCKS;
            case PINK: return PINK_VANILLA_FUNCTIONAL_BLOCKS;
            case GRAY: return GRAY_VANILLA_FUNCTIONAL_BLOCKS;
            case LIGHT_GRAY: return LIGHT_GRAY_VANILLA_FUNCTIONAL_BLOCKS;
            case CYAN: return CYAN_VANILLA_FUNCTIONAL_BLOCKS;
            case PURPLE: return PURPLE_VANILLA_FUNCTIONAL_BLOCKS;
            case BLUE: return BLUE_VANILLA_FUNCTIONAL_BLOCKS;
            case BROWN: return BROWN_VANILLA_FUNCTIONAL_BLOCKS;
            case GREEN: return GREEN_VANILLA_FUNCTIONAL_BLOCKS;
            case RED: return RED_VANILLA_FUNCTIONAL_BLOCKS;
        }
        if (color.equals(ModDyeColor.MINT)) {
            return MINT_FUNCTIONAL_BLOCKS;
        } else if (color.equals(ModDyeColor.PEACH)) {
            return PEACH_FUNCTIONAL_BLOCKS;
        } else if (color.equals(ModDyeColor.PERIWINKLE)) {
            return PERIWINKLE_FUNCTIONAL_BLOCKS;
        } else if (color.equals(ModDyeColor.ARTICHOKE)) {
            return ARTICHOKE_FUNCTIONAL_BLOCKS;
        } else if (color.equals(ModDyeColor.FUCHSIA)) {
            return FUCHSIA_FUNCTIONAL_BLOCKS;
        } else if (color.equals(ModDyeColor.VERMILION)) {
            return VERMILION_FUNCTIONAL_BLOCKS;
        } else if (color.equals(ModDyeColor.SHAMROCK)) {
            return SHAMROCK_FUNCTIONAL_BLOCKS;
        } else if (color.equals(ModDyeColor.INDIGO)) {
            return INDIGO_FUNCTIONAL_BLOCKS;
        } else if (color.equals(ModDyeColor.BANANA)) {
            return BANANA_FUNCTIONAL_BLOCKS;
        }
        return null; //Error
    }
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

    public static final Block[] MINT_FUNCTIONAL_BLOCKS = { ModBlocks.MINT_SHULKER_BOX, ModBlocks.MINT_BED, ModBlocks.MINT_CANDLE, ModBlocks.MINT_BANNER };
    public static final Block[] PEACH_FUNCTIONAL_BLOCKS = { ModBlocks.PEACH_SHULKER_BOX, ModBlocks.PEACH_BED, ModBlocks.PEACH_CANDLE, ModBlocks.PEACH_BANNER };
    public static final Block[] PERIWINKLE_FUNCTIONAL_BLOCKS = { ModBlocks.PERIWINKLE_SHULKER_BOX, ModBlocks.PERIWINKLE_BED, ModBlocks.PERIWINKLE_CANDLE, ModBlocks.PERIWINKLE_BANNER };
    public static final Block[] ARTICHOKE_FUNCTIONAL_BLOCKS = { ModBlocks.ARTICHOKE_SHULKER_BOX, ModBlocks.ARTICHOKE_BED, ModBlocks.ARTICHOKE_CANDLE, ModBlocks.ARTICHOKE_BANNER };
    public static final Block[] FUCHSIA_FUNCTIONAL_BLOCKS = { ModBlocks.FUCHSIA_SHULKER_BOX, ModBlocks.FUCHSIA_BED, ModBlocks.FUCHSIA_CANDLE, ModBlocks.FUCHSIA_BANNER };
    public static final Block[] VERMILION_FUNCTIONAL_BLOCKS = { ModBlocks.VERMILION_SHULKER_BOX, ModBlocks.VERMILION_BED, ModBlocks.VERMILION_CANDLE, ModBlocks.VERMILION_BANNER };
    public static final Block[] SHAMROCK_FUNCTIONAL_BLOCKS = { ModBlocks.SHAMROCK_SHULKER_BOX, ModBlocks.SHAMROCK_BED, ModBlocks.SHAMROCK_CANDLE, ModBlocks.SHAMROCK_BANNER };
    public static final Block[] INDIGO_FUNCTIONAL_BLOCKS = { ModBlocks.INDIGO_SHULKER_BOX, ModBlocks.INDIGO_BED, ModBlocks.INDIGO_CANDLE, ModBlocks.INDIGO_BANNER };
    public static final Block[] BANANA_FUNCTIONAL_BLOCKS = { ModBlocks.BANANA_SHULKER_BOX, ModBlocks.BANANA_BED, ModBlocks.BANANA_CANDLE, ModBlocks.BANANA_BANNER };

    public static ItemConvertible[] Concat(ItemConvertible[] a, ItemConvertible[] b) {
        int al = a.length;
        int bl = b.length;
        int tl = a.length + b.length;
        ItemConvertible[] c = new ItemConvertible[tl];
        System.arraycopy(a, 0, c, 0, al);
        System.arraycopy(b, 0, c, al, bl);
        return c;
    }
}
