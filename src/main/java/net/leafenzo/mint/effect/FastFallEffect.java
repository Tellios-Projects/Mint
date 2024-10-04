package net.leafenzo.mint.effect;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class FastFallEffect extends StatusEffect {
    protected FastFallEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);

        // we override levitation we come in second.
        if(entity.hasStatusEffect(StatusEffects.LEVITATION)) {
            entity.removeStatusEffect(StatusEffects.LEVITATION);
        }
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // levitation overrides us if it comes second.
        if(entity.hasStatusEffect(StatusEffects.LEVITATION)) {
            entity.removeStatusEffect(ModStatusEffects.FAST_FALL);
        }

        if(!entity.isLogicalSideForUpdatingMovement()) { return; }

        if(isFalling(entity)) {
            entity.addVelocity(0.0f, -0.4f, 0.0f);
        }
        else if(entity.isPlayer() && entity.isSwimming() && entity.isSneaking()) {
            entity.addVelocity(0.0f, -0.2f, 0.0f);
        }

//        .getJumpBoostVelocityModifier()

    }

    public boolean isFalling(LivingEntity entity) {
        boolean a = !entity.isFallFlying() && !entity.isInLava() && !entity.isTouchingWater() && !entity.isOnGround() && !entity.isDead() && !entity.hasVehicle();

        if(entity instanceof PlayerEntity) {
            return a && !((PlayerEntity)entity).isSpectator() && !(((PlayerEntity)entity).getAbilities().flying);
        }
        else {
            return a;
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}

