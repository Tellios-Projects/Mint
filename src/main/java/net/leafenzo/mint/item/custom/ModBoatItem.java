/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/remappedSrc/net/hibiscus/naturespirit/items/HibiscusBoatItem.java
 */

package net.leafenzo.mint.item.custom;

import net.leafenzo.mint.entity.ElsDyeModBoatEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class ModBoatItem extends BoatItem {
    private final ElsDyeModBoatEntity.ModBoat boatData;
    private final boolean chest;
    public ModBoatItem(boolean chest, ElsDyeModBoatEntity.ModBoat boatData, Settings settings) {
        super(chest, BoatEntity.Type.OAK, settings);
        this.chest = chest;
        this.boatData = boatData;
    }
    @Override
    protected BoatEntity createEntity(World world, HitResult hitResult) {
        var entity = boatData.factory(chest).create(boatData.entityType(chest), world);
        entity.updatePosition(hitResult.getPos().x, hitResult.getPos().y, hitResult.getPos().z);
        return entity;
    }
}