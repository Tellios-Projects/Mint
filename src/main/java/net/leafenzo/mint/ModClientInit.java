package net.leafenzo.mint;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.leafenzo.mint.client.render.ModColorHandler;
import net.leafenzo.mint.client.render.ModRenderLayers;
import net.leafenzo.mint.client.render.entity.ModEntityRenderers;
import net.leafenzo.mint.particle.ModParticleTypes;

@Environment(EnvType.CLIENT)
public class ModClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRenderLayers.registerBlockCutouts();
        ModColorHandler.registerBlockColorProviders();
        ModParticleTypes.registerFactoriesForClient();
        ModEntityRenderers.registerEntityRenderers();
    }
}
