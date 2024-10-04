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

public class ModStatusEffects {
    public static StatusEffect MINT_CHILL = registerStatusEffect("mint_chill", new MintChillEffect(StatusEffectCategory.HARMFUL, 0x00ff96));
    public static StatusEffect THORNS = registerStatusEffect("thorns", new ThornsEffect(StatusEffectCategory.BENEFICIAL, 0x682769));
    public static StatusEffect FAST_FALL = registerStatusEffect("fast_fall", new FastFallEffect(StatusEffectCategory.BENEFICIAL, 0xf87d65));
//    public static StatusEffect THORNS = registerStatusEffect("thorns", new ThornsEffect(StatusEffectCategory.BENEFICIAL, 0x682769));

    private static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Super.MOD_ID, name), effect);
    }

    public static void registerModEffects() {
        ModInit.LOGGER.info("Registering status effects for " + Super.MOD_ID);
    }
}