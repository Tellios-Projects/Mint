package net.leafenzo.mint.client.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class ModRenderLayers {
    public static void registerBlockCutouts() {
        ModInit.LOGGER.info("Registering Client Render Layers for " + Super.MOD_ID);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                ModBlocks.MINT_CROP,
                ModBlocks.WILD_MINT
                //ModBlocks.MINT_BED
                //ModBlocks.MINT_SHULKER_BOX
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(),
                ModBlocks.MINT_STAINED_GLASS,
                ModBlocks.MINT_STAINED_GLASS_PANE
        );
    }
}
