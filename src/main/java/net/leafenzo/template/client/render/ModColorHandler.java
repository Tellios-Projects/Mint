package net.leafenzo.template.client.render;


import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.leafenzo.template.block.ModBlocks;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.BlockItem;

public class ModColorHandler {
    public static void registerBlockColorProviders() {
//        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), //0.5 and 1.0 are the defaults for grass color (Source: BlockColors line 49)
//                ModBlocks.VINE_BLOCK
//        );

//        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0), //0.5 and 1.0 are the defaults for grass color (Source: BlockColors line 49)
//                ModBlocks.GRASS_CLIPPINGS_BLOCK,
//                ModBlocks.FERN_BLOCK
//        );

//        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorProviderRegistry.BLOCK.get(((BlockItem) stack.getItem()).getBlock()).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex),
//                ModBlocks.GRASS_CLIPPINGS_BLOCK,
//                ModBlocks.FERN_BLOCK,
//                ModBlocks.VINE_BLOCK
//        );
    }
}


// Sources:
// https://github.com/witches-kitchen/WK/blob/5457108485584d1e435b7d8701a41d6e3bf4e18b/src/main/java/cf/witcheskitchen/client/registry/WKColorProviderRegistry.java#LL13C10-L13C10