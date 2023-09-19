package net.leafenzo.mint.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.effect.ThornsEffect;
import net.leafenzo.mint.util.interfaces.IEntityInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Debug(export = true)
@Mixin(Entity.class)
@Environment(value= EnvType.CLIENT)
public abstract class EntityMixin {
//    @Shadow public abstract String toString();

//    @Override
//    void applyDamageEffects(LivingEntity attacker, Entity target, CallbackInfo ci);
//
//    @Override
//    void onUserDamaged(LivingEntity target, Entity attacker);

    @Inject(method = "applyDamageEffects", at = @At(value = "TAIL"), cancellable = true)
    public void applyDamageEffects(LivingEntity attacker, Entity target, CallbackInfo ci) {
        if (target instanceof LivingEntity) {
            StatusEffectInstance thorns = ((LivingEntity) target).getActiveStatusEffects().get(ModEffects.THORNS);
            if (thorns != null) {
                ThornsEffect.apply(attacker, (LivingEntity) target);
            }
        }
    }
}
