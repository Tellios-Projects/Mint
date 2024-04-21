/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/remappedSrc/net/hibiscus/naturespirit/items/HibiscusBoatItem.java
 */

package net.leafenzo.mint.item.custom;

import com.mojang.datafixers.types.Type;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.leafenzo.mint.entity.ModBoatEntity;
import net.leafenzo.mint.entity.ModBoatWithData;
import net.leafenzo.mint.entity.ModChestBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import java.util.List;
import java.util.function.Predicate;

public class ModBoatItem extends BoatItem {
    private final ModBoatEntity.ModBoat boatData;
    private final boolean chest;
    public ModBoatItem(boolean chest, ModBoatEntity.ModBoat boatData, Settings settings) {
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