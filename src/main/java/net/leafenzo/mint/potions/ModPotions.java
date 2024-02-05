package net.leafenzo.mint.potions;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.fabricmc.fabric.mixin.item.ItemStackMixin;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPotions {
    public static Potion MINT_CHILL = registerPotion("mint_chill", new Potion(new StatusEffectInstance(ModEffects.MINT_CHILL, 900, 0)));
    public static Potion LONG_MINT_CHILL = registerPotion("long_mint_chill", new Potion(new StatusEffectInstance(ModEffects.MINT_CHILL, 1800, 0)));
    public static Potion STRONG_MINT_CHILL = registerPotion("strong_mint_chill", new Potion(new StatusEffectInstance(ModEffects.MINT_CHILL, 900, 1)));

    public static Potion THORNS = registerPotion("thorns", new Potion(new StatusEffectInstance(ModEffects.THORNS, 900, 0)));
    public static Potion LONG_THORNS = registerPotion("long_thorns", new Potion(new StatusEffectInstance(ModEffects.THORNS, 1800, 0)));
    public static Potion STRONG_THORNS = registerPotion("strong_thorns", new Potion(new StatusEffectInstance(ModEffects.THORNS, 900, 1)));

    private static Potion registerPotion(String name, Potion potion) {
        //BrewingRecipeRegistry.registerPotionType(potion);
        return Registry.register(Registries.POTION, new Identifier(Super.MOD_ID, name), potion);
    }

    public static void registerBrewingRecipeRegistries() {
        ModInit.LOGGER.info("Registering brewing recipe registries for " + Super.MOD_ID);

        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(ModItems.MINT_SPRIG), ModPotions.MINT_CHILL);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotions.MINT_CHILL, Ingredient.ofItems(Items.REDSTONE), ModPotions.LONG_MINT_CHILL);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotions.MINT_CHILL, Ingredient.ofItems(Items.GLOWSTONE_DUST), ModPotions.STRONG_MINT_CHILL);

        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(ModBlocks.THISTLE_FLOWER), ModPotions.THORNS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotions.THORNS, Ingredient.ofItems(Items.REDSTONE), ModPotions.LONG_THORNS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotions.THORNS, Ingredient.ofItems(Items.GLOWSTONE_DUST), ModPotions.STRONG_THORNS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.WATER, Ingredient.ofItems(ModBlocks.WAXCAP_MUSHROOM), Potions.THICK);

        BrewingRecipeRegistry.registerPotionType(Items.POTION);
    }

    public static void registerModPotions() {
        ModInit.LOGGER.info("Registering potions for " + Super.MOD_ID);

        registerBrewingRecipeRegistries();
    }
}
