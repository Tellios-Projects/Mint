/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/remappedSrc/net/hibiscus/naturespirit/client/HibiscusBoatEntityRenderer.java
 */

package net.leafenzo.mint.client.render.entity;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.leafenzo.mint.entity.ModBoatEntity;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.ChestBoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.Map;

public final class ModBoatEntityRenderer extends BoatEntityRenderer {
    public ModBoatEntityRenderer(EntityRendererFactory.Context context, boolean chest, ModBoatEntity.ModBoat boatData) {
        super(context, chest);
        var id = boatData.id();
        var texture = new Identifier(id.getNamespace(), "textures/entity/" + (chest ? "chest_boat/" : "boat/") + id.getPath() + ".png");
        var rootPart = context.getPart(getModelLayer(boatData, chest));
        var model = chest ? new ChestBoatEntityModel(rootPart) : new BoatEntityModel(rootPart);
        texturesAndModels = texturesAndModels.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> Pair.of(texture, model)));
    }

    public static EntityModelLayer getModelLayer(ModBoatEntity.ModBoat boat, boolean chest) {
        var id = boat.id();
        return new EntityModelLayer(new Identifier(id.getNamespace(), (chest ? "chest_boat/" : "boat/") + id.getPath()), "main");
    }
}