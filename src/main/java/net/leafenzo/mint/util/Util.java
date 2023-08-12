package net.leafenzo.mint.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.leafenzo.mint.block.ModBlocks;
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


//    public static final List<DyeItem> getAllDyeItems() {
//        //return Arrays.stream(DyeColor.values()).toList();
//    }


    // Util
    //      Array ALL_DYES =  (ArrayList<ItemConvertible>)Arrays.stream(ModItems.DYE_ITEMS).toList(); //TODO fixme


    //      Array ALL_WOOLS = vanilla wools + wools from this mod + wools from other mods??? somehow? (any block with the wool tag! :3)


    //      Array ALL_CARPETS
    //      Array ALL_BEDS


//    public static final Block[] getAllWoolBlocks() {
//
//        //BlockTags.WOOL.values(); //TODO, figure out a safe way to load contents of a tag (appearently this must be done after datapacks are initialized)
//        //Block[] c = Concat(VANILLA_WOOLS, ModBlocks.WOOL_BLOCKS);
//
//
//
//        Block[] wools = TagKey<Block>
//        return null;
//    }

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
