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
       entity.setFrozenTicks(200); //will continually keep the player frozen as the potion is in effect
       entity.damage(entity.getWorld().getDamageSources().freeze(), 0.25f * amplifier); //amplifier applies additional damage
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}