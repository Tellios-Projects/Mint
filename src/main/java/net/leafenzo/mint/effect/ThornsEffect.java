// SOURCES:
// marcus8448 - https://github.com/marcus8448/MotherlodeCore/blob/5721f584c4bb6776a052b8bc11df93fffd376c6f/motherlode-potions/src/main/java/motherlode/potions/ThornsEffect.java#L9

package net.leafenzo.mint.effect;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

public class ThornsEffect extends StatusEffect {
    protected ThornsEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public static void apply(Entity user, LivingEntity attacker) {
        Random random = attacker.getRandom();
        int level = ((LivingEntity) user).getStatusEffect(ModEffects.THORNS).getAmplifier() + 2;
        if(!shouldDamageAttacker(level+3, random)) { //The level is treated as if it were increased to balance it against thorns armor (which has 12 levels that a player can hold at once)
            return;
        }
        int rand = (1 + random.nextInt(4));
        attacker.damage(attacker.getDamageSources().thorns(user), rand * level);
    }

    public static boolean shouldDamageAttacker(int level, Random random) {
        if (level <= 0) {
            return false;
        }
        return random.nextFloat() < 0.15f * (float)level;
    }
}
