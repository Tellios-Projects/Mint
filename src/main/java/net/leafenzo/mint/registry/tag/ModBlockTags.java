package net.leafenzo.mint.registry.tag;

import net.leafenzo.mint.Super;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;


public class ModBlockTags {
    //public static final TagKey<Block> EXAMPLE = ModBlockTags.of("example_tag");

    private ModBlockTags() {
    }
    public static final TagKey<Block> SPIDER_REPELLENTS = of("spider_repellents");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Super.MOD_ID, id));
    }
}
