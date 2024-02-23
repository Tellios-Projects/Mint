/*
* Sources:
* https://github.com/witches-kitchen/WK/blob/5457108485584d1e435b7d8701a41d6e3bf4e18b/src/main/java/cf/witcheskitchen/client/registry/WKColorProviderRegistry.java#LL13C10-L13C10
*/
package net.leafenzo.mint.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.item.BlockItem;

@Environment(EnvType.CLIENT)
public class ModColorHandler {
    public static void registerBlockColorProviders() {
        for(Block block : ModBlocks.HAS_FOLIAGE_COLOR_PROVIDER) {
            ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), block);
        }

//        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> pos != null && world != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5, 1.0), //0.5 and 1.0 are the defaults for grass color (Source: BlockColors line 49)
//        );

        for(Block block : ModBlocks.HAS_FOLIAGE_COLOR_PROVIDER) { //Make sure this is done for every block with any color provider!
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ColorProviderRegistry.BLOCK.get(((BlockItem) stack.getItem()).getBlock()).getColor(((BlockItem) stack.getItem()).getBlock().getDefaultState(), null, null, tintIndex), block);
        }
    }
}