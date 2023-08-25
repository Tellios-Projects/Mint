package net.leafenzo.mint.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MentholEffect extends StatusEffect {
    public MentholEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(amplifier <= 0) {
            entity.setFrozenTicks(139); //will continually keep the player frozen as the potion is in effect
        }
        else if(amplifier >= 1) {
            entity.setFrozenTicks(140);  //amplifier makes freezing deal damage, because min freeze damage ticks are 140
        }
       //entity.damage(entity.getWorld().getDamageSources().freeze(), 0.25f * amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}