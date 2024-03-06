/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/remappedSrc/net/hibiscus/naturespirit/entity/HibiscusChestBoatEntity.java
 */

package net.leafenzo.mint.entity;

import net.minecraft.block.WoodType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.world.World;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.entity.EntityType;

public final class ModChestBoatEntity extends ChestBoatEntity implements ModBoatWithData {
    private final ModBoatEntity.ModBoat boatData;

    public ModChestBoatEntity(EntityType <? extends BoatEntity> entityType, World world, ModBoatEntity.ModBoat boatData) {
        super(entityType, world);
        this.boatData = boatData;
    }

    @Override public ModBoatEntity.ModBoat getBoatData() {
        return boatData;
    }

    @Override public Type getVariant() {
        return Type.OAK;
    }

    @Override public Item asItem() {
        return boatData.chestBoat().asItem();
    }

}