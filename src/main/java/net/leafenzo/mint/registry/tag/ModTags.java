package net.leafenzo.mint.registry.tag;

import com.mojang.datafixers.types.templates.Tag;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.leafenzo.mint.Super;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;


public class ModTags {
    public static class Blocks {
//        public static final TagKey<Blocks>  = createTa
        public static final TagKey<Block> GLASS = getOrCreateCommonTag("glass");
        public static final TagKey<Block> GLASS_BLOCKS = getOrCreateCommonTag("glass_blocks");  //common tags just gotta be silly ig


        public static TagKey<Block> getOrCreateTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Super.MOD_ID, name));
        }
        private static TagKey<Block> getOrCreateCommonTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }
    }
    public static class Items {
        public static final TagKey<Item> DYES = getOrCreateCommonTag("dyes");
        public static final TagKey<Item> GOLD_INGOTS = getOrCreateCommonTag("gold_ingots");
        public static TagKey<Item> getOrCreateTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Super.MOD_ID, name));
        }
        private static TagKey<Item>  getOrCreateCommonTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
    }
}
