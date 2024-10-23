//Sources:
// Emafire003 - https://github.com/Emafire003/LightWithin/blob/039c74569e1970d0c337987d0ee838ec58c17bd0/src/main/java/me/emafire003/dev/lightwithin/status_effects/LightEffects.java#L12


package net.leafenzo.mint.effect;

import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElsDyeModEffects {
    public static StatusEffect MINT_CHILL = registerStatusEffect("mint_chill", new MintChillEffect(StatusEffectCategory.HARMFUL, 0x00ff96));
//    public static StatusEffect MENTHOL = registerStatusEffect("menthol", new MentholEffect(StatusEffectCategory.HARMFUL, 104189145));
    public static StatusEffect THORNS = registerStatusEffect("thorns", new ThornsEffect(StatusEffectCategory.BENEFICIAL, 0x682769));

    private static StatusEffect registerStatusEffect(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(ElsDyeMod.MOD_ID, name), effect);
    }

    public static void registerModEffects() {
        ElsDyeModInit.LOGGER.info("Registering status effects for " + ElsDyeMod.MOD_ID);
    }
}