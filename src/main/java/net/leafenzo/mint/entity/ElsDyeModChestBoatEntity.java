/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/remappedSrc/net/hibiscus/naturespirit/entity/HibiscusChestBoatEntity.java
 */

package net.leafenzo.mint.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public final class ElsDyeModChestBoatEntity extends ChestBoatEntity implements ElsDyeModBoatWithData {
    private final ElsDyeModBoatEntity.ModBoat boatData;

    public ElsDyeModChestBoatEntity(EntityType <? extends BoatEntity> entityType, World world, ElsDyeModBoatEntity.ModBoat boatData) {
        super(entityType, world);
        this.boatData = boatData;
    }

    @Override public ElsDyeModBoatEntity.ModBoat getBoatData() {
        return boatData;
    }

    @Override public Type getVariant() {
        return Type.OAK;
    }

    @Override public Item asItem() {
        return boatData.chestBoat().asItem();
    }

}