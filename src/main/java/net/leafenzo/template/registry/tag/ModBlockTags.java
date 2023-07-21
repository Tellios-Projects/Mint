package net.leafenzo.template.registry.tag;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;


public class ModBlockTags {
    //public static final TagKey<Block> EXAMPLE = ModBlockTags.of("example_tag");

    private ModBlockTags() {
    }

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(id));
    }
}
