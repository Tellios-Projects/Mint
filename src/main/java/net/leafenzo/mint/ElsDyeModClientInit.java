package net.leafenzo.mint;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.leafenzo.mint.client.render.ElsDyeModColorHandler;
import net.leafenzo.mint.client.render.ElsDyeModRenderLayers;
import net.leafenzo.mint.client.render.entity.ModEntityRenderers;
import net.leafenzo.mint.particle.ElsDyeModParticleTypes;

@Environment(EnvType.CLIENT)
public class ElsDyeModClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ElsDyeModRenderLayers.registerBlockCutouts();
        ElsDyeModColorHandler.registerBlockColorProviders();
        ElsDyeModParticleTypes.registerFactoriesForClient();
        ModEntityRenderers.registerEntityRenderers();
    }
}
