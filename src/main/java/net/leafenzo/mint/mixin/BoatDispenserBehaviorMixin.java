/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/remappedSrc/net/hibiscus/naturespirit/mixin/BoatDispenserBehaviorMixin.java
 */

package net.leafenzo.mint.mixin;

import net.leafenzo.mint.block.dispenser.ElsDyeModBoatDispenserBehavior;
import net.leafenzo.mint.entity.ElsDyeModBoatEntity;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BoatDispenserBehavior.class) abstract class BoatDispenserBehaviorMixin {
    @ModifyVariable(method = "dispenseSilently", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;setVariant(Lnet/minecraft/entity/vehicle/BoatEntity$Type;)V"), allow = 1)
    private BoatEntity modifyBoat(BoatEntity original) {
        // noinspection ConstantConditions
        if((Object) this instanceof ElsDyeModBoatDispenserBehavior boat) {
            return ElsDyeModBoatEntity.copy(original, boat.getBoatData());
        }
        return original;
    }
}