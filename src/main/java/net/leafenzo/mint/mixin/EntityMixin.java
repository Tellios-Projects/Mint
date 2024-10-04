package net.leafenzo.mint.mixin;

import net.leafenzo.mint.effect.ModStatusEffects;
import net.leafenzo.mint.effect.ThornsEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(Entity.class)
//@Environment(value= EnvType.CLIENT)
public abstract class EntityMixin {
    @Shadow public abstract void checkDespawn();

    @Inject(method = "applyDamageEffects", at = @At(value = "TAIL"), cancellable = true)
    public void applyDamageEffects(LivingEntity attacker, Entity user, CallbackInfo ci) {
        if (user instanceof LivingEntity) {
            StatusEffectInstance thorns = ((LivingEntity) user).getActiveStatusEffects().get(ModStatusEffects.THORNS);
            if (thorns != null) {
                ThornsEffect.apply(user, (LivingEntity) attacker);
            }
        }
    }

    @Inject(method = "handleFallDamage", at = @At(value = "HEAD"), cancellable = true)
    public void handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
//        if(Entity.class.cast(this) instanceof LivingEntity && LivingEntity.class.cast(this).hasStatusEffect(ModStatusEffects.FAST_FALL)) {
            cir.setReturnValue(false);
//        }
    }
}
