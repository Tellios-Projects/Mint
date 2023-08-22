package net.leafenzo.mint.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.jetbrains.annotations.Nullable;

public class MentholEffect extends StatusEffect {
    public MentholEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    // TODO ADDME
//    @Override
//    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
//        super.applyInstantEffect(source, attacker, target, amplifier, proximity);
//    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
       entity.setFrozenTicks(200); //will continually keep the player frozen as the potion is in effect
       //entity.damage(entity.getWorld().getDamageSources().freeze(), 0.25f * amplifier); //amplifier applies additional damage
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}