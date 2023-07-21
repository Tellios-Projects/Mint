package net.leafenzo.mint;

import net.fabricmc.api.ClientModInitializer;
import net.leafenzo.mint.client.render.ModColorHandler;
import net.leafenzo.mint.client.render.ModRenderLayers;

public class ModClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRenderLayers.registerBlockCutouts();
        ModColorHandler.registerBlockColorProviders();

//        HudRenderCallback.EVENT.register(new DenseCobwebHudOverlay());
    }
}
