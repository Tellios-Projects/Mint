package net.leafenzo.mint;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.client.rendering.BlockEntityRendererRegistryImpl;
import net.leafenzo.mint.client.render.ModColorHandler;
import net.leafenzo.mint.client.render.ModRenderLayers;
import net.leafenzo.mint.particle.ModParticleTypes;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.ShulkerBoxBlockEntityRenderer;

public class ModClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRenderLayers.registerBlockCutouts();
        ModColorHandler.registerBlockColorProviders();
        ModParticleTypes.registerFactoriesForClient();
        //BlockEntityRendererRegistryImpl.register(BlockEntityType.SHULKER_BOX, ShulkerBoxBlockEntityRenderer::new);
    }
}
