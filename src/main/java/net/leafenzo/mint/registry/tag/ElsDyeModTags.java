package net.leafenzo.mint.registry.tag;

import net.leafenzo.mint.ElsDyeMod;
import net.minecraft.block.Block;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;


public class ElsDyeModTags {
    public static class Blocks {
        public static final TagKey<Block> GLASS = getOrCreateCommonTag("glass");
        public static final TagKey<Block> GLASS_BLOCKS = getOrCreateCommonTag("glass_blocks");  //common tags just gotta be silly ig

//        public static final TagKey<Block> MUSHROOM_BLOCKS = getOrCreateTag("mushroom_blocks");
        public static TagKey<Block> getOrCreateTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(ElsDyeMod.MOD_ID, name));
        }
        private static TagKey<Block> getOrCreateCommonTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }
    }
    public static class Items {
        public static final TagKey<Item> DYES = getOrCreateCommonTag("dyes");
        public static final TagKey<Item> GOLD_INGOTS = getOrCreateCommonTag("gold_ingots");
        public static final TagKey<Item> IRON_INGOTS = getOrCreateCommonTag("iron_ingots");
        public static final TagKey<Item> MUSHROOMS = getOrCreateCommonTag("mushrooms");
        public static final TagKey<Item> WAX = getOrCreateCommonTag("wax");
        public static final TagKey<Item> COBBLESTONE = getOrCreateCommonTag("cobblestone");

        public static final TagKey<Item> WINTERGREEN_CANDY_CANE_BLOCKS = getOrCreateTag("wintergreen_candy_cane_blocks");
        public static final TagKey<Item> PEPPERMINT_CANDY_CANE_BLOCKS = getOrCreateTag("peppermint_candy_cane_blocks");

        public static TagKey<Item> getOrCreateTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(ElsDyeMod.MOD_ID, name));
        }
        public static TagKey<Item> getOrCreateCommonTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
        public static TagKey<Item> getOrCreateDyeItemCommonTag(DyeItem item) {
            return getOrCreateCommonTag(item.getColor().getName()+"_dyes");
        }
    }
}
