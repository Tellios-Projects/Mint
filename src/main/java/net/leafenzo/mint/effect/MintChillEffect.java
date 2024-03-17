package net.leafenzo.mint.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MintChillEffect extends StatusEffect {
    public MintChillEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(amplifier <= 0) {
            entity.setFrozenTicks(80); //will continually keep the player frozen as the potion is in effect
        }
        else if(amplifier == 1) {
            entity.setFrozenTicks(150);  //amplifier makes freezing deal damage, because min freeze damage ticks are 140... or rather they supposedly are; in truth it's a bit finicky
        }
        else if(amplifier >= 2) {
            entity.setFrozenTicks(150);
            entity.damage(entity.getWorld().getDamageSources().freeze(), 0.25f * amplifier);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}