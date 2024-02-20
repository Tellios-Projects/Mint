/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/remappedSrc/net/hibiscus/naturespirit/client/NatureSpiritClient.java
 */

package net.leafenzo.mint.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.entity.ModBoatEntity;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.ChestBoatEntityModel;

@Environment(EnvType.CLIENT)
public class ModEntityRenderers {
    public static void registerEntityRenderers() {
        ModInit.LOGGER.debug("Registering client mod entity renderers for " + Super.MOD_ID);

        for(ModBoatEntity.ModBoat boat : ModBoatEntity.ModBoat.values()) {
            registerBoatModel(true, boat);
            registerBoatModel(false, boat);
        }
    }

    private static void registerBoatModel(boolean chest, ModBoatEntity.ModBoat boat) {
        var type = boat.entityType(chest);
        EntityRendererRegistry.register(type, context -> new ModBoatEntityRenderer(context, chest, boat));
        EntityModelLayerRegistry.registerModelLayer(ModBoatEntityRenderer.getModelLayer(boat, chest),
                () -> chest ? ChestBoatEntityModel.getTexturedModelData() : BoatEntityModel.getTexturedModelData()
        );
    }
}
