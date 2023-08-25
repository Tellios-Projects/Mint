package net.leafenzo.mint.potions;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static Potion MENTHOL = registerPotion("menthol", new Potion(new StatusEffectInstance(ModEffects.MENTHOL, 900, 0)));
    public static Potion LONG_MENTHOL = registerPotion("long_menthol", new Potion(new StatusEffectInstance(ModEffects.MENTHOL, 1800, 0)));
    public static Potion STRONG_MENTHOL = registerPotion("strong_menthol", new Potion(new StatusEffectInstance(ModEffects.MENTHOL, 900, 1)));

    private static Potion registerPotion(String name, Potion potion) {
        //BrewingRecipeRegistry.registerPotionType(potion);
        return Registry.register(Registries.POTION, new Identifier(Super.MOD_ID, name), potion);
    }

    public static void registerBrewingRecipeRegistries() {
        ModInit.LOGGER.info("Registering brewing recipe registries for " + Super.MOD_ID);

        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(ModItems.MINT_SPRIG), ModPotions.MENTHOL);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotions.MENTHOL, Ingredient.ofItems(Items.REDSTONE), ModPotions.LONG_MENTHOL);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotions.MENTHOL, Ingredient.ofItems(Items.GLOWSTONE_DUST), ModPotions.STRONG_MENTHOL);
        BrewingRecipeRegistry.registerPotionType(Items.POTION);
    }

    public static void registerModPotions() {
        ModInit.LOGGER.info("Registering potions for " + Super.MOD_ID);

        registerBrewingRecipeRegistries();
    }
}
