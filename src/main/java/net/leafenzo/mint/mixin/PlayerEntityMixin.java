package net.leafenzo.mint.mixin;

import net.leafenzo.mint.effect.ModStatusEffects;
import net.leafenzo.mint.effect.ThornsEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Debug(export = true)
@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "attack(Lnet/minecraft/entity/Entity;)V", at = @At(value = "INVOKE", target = "net/minecraft/enchantment/EnchantmentHelper.onUserDamaged (Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/Entity;)V"), cancellable = true)
    public void attack(Entity target, CallbackInfo ci) {
        StatusEffectInstance thorns = ((LivingEntity) target).getActiveStatusEffects().get(ModStatusEffects.THORNS);
        if (thorns != null) {
            ThornsEffect.apply(target, (LivingEntity)(Object) this);
        }
    }
}
