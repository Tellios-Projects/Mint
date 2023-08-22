package net.leafenzo.mint.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.entity.ModEntities;

public class ModEntityRenderers {
    public static void registerModEntityRenderers() {
        ModInit.LOGGER.debug("Registering entitiy renderers for " + Super.MOD_ID);

        EntityRendererRegistry.register(ModEntities.BEETLE, BeetleRenderer::new);
        //FabricDefaultAttributeRegistry.register(BEETLE, BeetleEntity.setAttributes());
    }
}
