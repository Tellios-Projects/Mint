/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/remappedSrc/net/hibiscus/naturespirit/mixin/BoatEntityMixin.java
 */

package net.leafenzo.mint.mixin;

import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import net.leafenzo.mint.entity.ModBoatWithData;

@Mixin(BoatEntity.class) abstract class BoatEntityMixin {
    @ModifyArg(method = "fall", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;dropItem(Lnet/minecraft/item/ItemConvertible;)Lnet/minecraft/entity/ItemEntity;", ordinal = 0), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity$Type;getBaseBlock()Lnet/minecraft/block/Block;")), allow = 1)
    private ItemConvertible modifyPlanks(ItemConvertible convertible) {
        // noinspection ConstantConditions
        if(this instanceof ModBoatWithData boat) {
            return boat.getBoatData().planks();
        }

        return convertible;
    }
}