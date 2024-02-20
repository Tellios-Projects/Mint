/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/remappedSrc/net/hibiscus/naturespirit/util/WoodSet.java
 */

package net.leafenzo.mint.entity;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.world.World;

import java.util.function.Supplier;

public class ModBoatEntity extends BoatEntity {
    private final ModBoat boatData;

    public ModBoatEntity(EntityType <? extends BoatEntity> type, World world, ModBoat boatData) {
        super(type, world);
        this.boatData = boatData;
    }

    public ModBoat getBoatData() {
        return boatData;
    }

    @Override public Type getVariant() {
        return Type.OAK;
    }

    @Override public void setVariant(Type type) {
    }

    @Override public Item asItem() {
        return boatData.boat().asItem();
    }

    public static BoatEntity copy(BoatEntity original, ModBoat boatData) {
        var chest = original instanceof ChestBoatEntity;
        var boat = boatData.factory(chest).create(boatData.entityType(chest), original.getEntityWorld());
        boat.updatePosition(original.getX(), original.getY(), original.getZ());
        return boat;
    }

    public enum ModBoat implements StringIdentifiable {
        WINTERGREEN(
                "wintergreen",
                () -> ModBlocks.WINTERGREEN_WOODSET.getPlanks(),
                () -> ModBlocks.WINTERGREEN_WOODSET.getBoatItem(),
                () -> ModBlocks.WINTERGREEN_WOODSET.getChestBoatItem(),
                () -> ModBlocks.WINTERGREEN_WOODSET.getBoatEntityType(),
                () -> ModBlocks.WINTERGREEN_WOODSET.getChestBoatEntityType()
        );

        private final String name;
        private final Supplier <ItemConvertible> planks;
        private final Supplier <ItemConvertible> boat;
        private final Supplier <ItemConvertible> chestBoat;
        private final Supplier <EntityType <BoatEntity>> entityType;
        private final Supplier <EntityType <BoatEntity>> chestEntityType;
        public static final StringIdentifiable.Codec <ModBoat> CODEC = StringIdentifiable.createCodec(ModBoatEntity.ModBoat::values);

        ModBoat(String name, Supplier <ItemConvertible> planks, Supplier <ItemConvertible> boat, Supplier <ItemConvertible> chestBoat, Supplier <EntityType <BoatEntity>> entityType, Supplier <EntityType <BoatEntity>> chestEntityType
        ) {
            this.name = name;
            this.planks = planks;
            this.boat = boat;
            this.chestBoat = chestBoat;
            this.entityType = entityType;
            this.chestEntityType = chestEntityType;
        }

        public ItemConvertible planks() {
            return planks.get();
        }

        public ItemConvertible boat() {
            return boat.get();
        }

        public ItemConvertible chestBoat() {
            return chestBoat.get();
        }

        public EntityType <BoatEntity> entityType(boolean chest) {
            return chest ? chestEntityType.get() : entityType.get();
        }

        public static ModBoat getType(String name) {
            return CODEC.byId(name, WINTERGREEN);
        }

        public EntityType.EntityFactory <BoatEntity> factory(boolean chest) {
            return (type, world) -> chest ? new ModChestBoatEntity(type, world, this) : new ModBoatEntity(type, world, this);
        }

        public Identifier id() {
            return new Identifier(Super.MOD_ID, name);
        }

        @Override public String asString() {
            return name;
        }
    }
}