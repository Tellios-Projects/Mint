//Sources:
// Emafire003 - https://github.com/Emafire003/LightWithin/blob/039c74569e1970d0c337987d0ee838ec58c17bd0/src/main/java/me/emafire003/dev/lightwithin/status_effects/LightEffects.java#L12


package net.leafenzo.mint.effect;

import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static StatusEffect MENTHOL = registerStatusEffect("menthol", new MentholEffect(StatusEffectCategory.HARMFUL, 0x00ff96));
//    public static StatusEffect MENTHOL = registerStatusEffect("menthol", new MentholEffect(StatusEffectCategory.HARMFUL, 104189145));

    private static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Super.MOD_ID, name), effect);
    }

    public static void registerModEffects() {
        ModInit.LOGGER.info("Registering status effects for " + Super.MOD_ID);
    }
}