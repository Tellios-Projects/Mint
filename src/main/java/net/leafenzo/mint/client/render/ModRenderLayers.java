package net.leafenzo.mint.client.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class ModRenderLayers {
    public static void registerBlockCutouts() {
        ModInit.LOGGER.info("Registering Client Render Layers for " + Super.MOD_ID);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                ModBlocks.MINT_CROP,

                ModBlocks.CORAL_ANEMONE,
                ModBlocks.PEACH_TREE,

                ModBlocks.PERIWINKLE_PETALS,
                ModBlocks.LAVENDER_OIL_LANTERN,

                ModBlocks.HANGING_WAXCAP_WAX,
                ModBlocks.ARTICHOKE_CROP
        );

        for(Block block : ModBlocks.SMALL_FLOWERS) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
        }
        for(Block block : ModBlocks.MUSHROOM_PLANTS) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
        }
        for(Block block : ModBlocks.FLOWER_POTS) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutoutMipped());
        }

        for(Block block : ModBlocks.STAINED_GLASS_BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
        }
        for(Block block : ModBlocks.STAINED_GLASS_PANE_BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getTranslucent());
        }
    }
}
