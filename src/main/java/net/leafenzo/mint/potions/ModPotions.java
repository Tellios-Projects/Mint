package net.leafenzo.mint.potions;

import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static Potion MENTHOL = registerPotion("menthol", new Potion(new StatusEffectInstance(ModEffects.MENTHOL, 800, 0)));

    private static Potion registerPotion(String name, Potion potion) {
        return Registry.register(Registries.POTION, new Identifier(Super.MOD_ID, name), potion);
    }

    public static void registerModPotions() {
        ModInit.LOGGER.info("Registering potions for " + Super.MOD_ID);
    }
}
