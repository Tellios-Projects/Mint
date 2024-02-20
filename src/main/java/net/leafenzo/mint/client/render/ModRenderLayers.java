package net.leafenzo.mint.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ModRenderLayers {
    public static void registerBlockCutouts() {
        ModInit.LOGGER.info("Registering Client Render Layers for " + Super.MOD_ID);

        for(Block block : ModBlocks.RENDER_LAYER_CUTOUT_MIPPED) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
        }

        for(Block block : ModBlocks.RENDER_LAYER_TRANSLUCENT) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
        }
    }
}
