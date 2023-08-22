package net.leafenzo.mint;

import net.fabricmc.api.ClientModInitializer;
import net.leafenzo.mint.client.render.ModColorHandler;
import net.leafenzo.mint.client.render.ModRenderLayers;
import net.leafenzo.mint.entity.client.ModEntityRenderers;

public class ModClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRenderLayers.registerBlockCutouts();
        ModColorHandler.registerBlockColorProviders();
        ModEntityRenderers.registerModEntityRenderers();
        //BlockEntityRendererRegistryImpl.register(BlockEntityType.SHULKER_BOX, ShulkerBoxBlockEntityRenderer::new);
    }
}
