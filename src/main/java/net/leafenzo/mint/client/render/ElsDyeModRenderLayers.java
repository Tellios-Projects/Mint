package net.leafenzo.mint.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ElsDyeModRenderLayers {
    public static void registerBlockCutouts() {
        ElsDyeModInit.LOGGER.info("Registering Client Render Layers for " + ElsDyeMod.MOD_ID);

        for(Block block : ElsDyeModBlocks.RENDER_LAYER_CUTOUT) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }

        for(Block block : ElsDyeModBlocks.RENDER_LAYER_CUTOUT_MIPPED) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
        }

        for(Block block : ElsDyeModBlocks.RENDER_LAYER_TRANSLUCENT) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
        }
    }
}
