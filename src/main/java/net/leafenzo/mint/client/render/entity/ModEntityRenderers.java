/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/remappedSrc/net/hibiscus/naturespirit/client/NatureSpiritClient.java
 */

package net.leafenzo.mint.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.entity.ElsDyeModBoatEntity;
import net.leafenzo.mint.entity.ElsDyeModEntityTypes;
import net.leafenzo.mint.entity.renderer.EmberArrowEntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.ChestBoatEntityModel;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {
    public static void registerEntityRenderers() {
        ElsDyeModInit.LOGGER.debug("Registering client mod entity renderers for " + ElsDyeMod.MOD_ID);

        for(ElsDyeModBoatEntity.ModBoat boat : ElsDyeModBoatEntity.ModBoat.values()) {
            registerBoatModel(true, boat);
            registerBoatModel(false, boat);
        }
        EntityRendererRegistry.register(ElsDyeModEntityTypes.EMBER_ARROW, EmberArrowEntityRenderer::new);
//        EntityRendererRegistry.register(ModEntityTypes.GAS_BOMB_PROJECTILE, FlyingItemEntityRenderer::new);
//        EntityRendererRegistry.register(ModEntityTypes.CINNABAR_CLOUD, EmptyEntityRenderer::new);

//        EntityRendererRegistry.register(ModEntityTypes.BEETLE, BeetleRenderer::new);
    }

    private static void registerBoatModel(boolean chest, ElsDyeModBoatEntity.ModBoat boat) {
        var type = boat.entityType(chest);
        EntityRendererRegistry.register(type, context -> new ModBoatEntityRenderer(context, chest, boat));
        EntityModelLayerRegistry.registerModelLayer(ModBoatEntityRenderer.getModelLayer(boat, chest),
                () -> chest ? ChestBoatEntityModel.getTexturedModelData() : BoatEntityModel.getTexturedModelData()
        );
    }
}
