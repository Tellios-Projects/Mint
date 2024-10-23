package net.leafenzo.mint.potions;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.effect.ElsDyeModEffects;
import net.leafenzo.mint.item.ElsDyeModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElsDyeModPotions {
    public static Potion MINT_CHILL = registerPotion("mint_chill", new Potion(new StatusEffectInstance(ElsDyeModEffects.MINT_CHILL, 900, 0)));
    public static Potion LONG_MINT_CHILL = registerPotion("long_mint_chill", new Potion(new StatusEffectInstance(ElsDyeModEffects.MINT_CHILL, 1800, 0)));
    public static Potion STRONG_MINT_CHILL = registerPotion("strong_mint_chill", new Potion(new StatusEffectInstance(ElsDyeModEffects.MINT_CHILL, 900, 1)));

    public static Potion THORNS = registerPotion("thorns", new Potion(new StatusEffectInstance(ElsDyeModEffects.THORNS, 900, 0)));
    public static Potion LONG_THORNS = registerPotion("long_thorns", new Potion(new StatusEffectInstance(ElsDyeModEffects.THORNS, 1800, 0)));
    public static Potion STRONG_THORNS = registerPotion("strong_thorns", new Potion(new StatusEffectInstance(ElsDyeModEffects.THORNS, 900, 1)));

    private static Potion registerPotion(String name, Potion potion) {
        //BrewingRecipeRegistry.registerPotionType(potion);
        return Registry.register(Registries.POTION, new Identifier(ElsDyeMod.MOD_ID, name), potion);
    }

    public static void registerBrewingRecipeRegistries() {
        ElsDyeModInit.LOGGER.info("Registering brewing recipe registries for " + ElsDyeMod.MOD_ID);

        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(ElsDyeModItems.WINTER_MEDLEY), ElsDyeModPotions.MINT_CHILL);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ElsDyeModPotions.MINT_CHILL, Ingredient.ofItems(Items.REDSTONE), ElsDyeModPotions.LONG_MINT_CHILL);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ElsDyeModPotions.MINT_CHILL, Ingredient.ofItems(Items.GLOWSTONE_DUST), ElsDyeModPotions.STRONG_MINT_CHILL);

        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(ElsDyeModBlocks.THISTLE_FLOWER), ElsDyeModPotions.THORNS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ElsDyeModPotions.THORNS, Ingredient.ofItems(Items.REDSTONE), ElsDyeModPotions.LONG_THORNS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ElsDyeModPotions.THORNS, Ingredient.ofItems(Items.GLOWSTONE_DUST), ElsDyeModPotions.STRONG_THORNS);
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.WATER, Ingredient.ofItems(ElsDyeModBlocks.WAXCAP_MUSHROOM), Potions.THICK);

        BrewingRecipeRegistry.registerPotionType(Items.POTION);
    }

    public static void registerModPotions() {
        ElsDyeModInit.LOGGER.info("Registering potions for " + ElsDyeMod.MOD_ID);

        registerBrewingRecipeRegistries();
    }
}
