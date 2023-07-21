package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.data.server.recipe.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;


public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) { super(output); }

//    public static void offerReversibleCompactingRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
//        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(RecipeProvider.hasItem(compactItem), RecipeProvider.conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(reverseId+"_from_"+compactingId));
//        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem).input(Character.valueOf('#'), baseItem).pattern("###").pattern("###").pattern("###").group(compactingGroup).criterion(RecipeProvider.hasItem(baseItem), RecipeProvider.conditionsFromItem(baseItem)).offerTo(exporter, new Identifier(compactingId+"_from_"+reverseId));
//    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //offerWaxingRecipes(exporter);
        offerShapelessRecipe(exporter, ModItems.MINT_DYE, ModItems.MINT_SPRIG, RecipeCategory.MISC.getName(), 1);
        offerStainedGlassDyeingRecipe(exporter, ModBlocks.MINT_STAINED_GLASS, ModItems.MINT_DYE);
        //offerStainedGlassPaneRecipe(exporter, ModBlocks.MINT_STAINED_GLASS_PANE, ModBlocks.MINT_STAINED_GLASS); //TODO ADDME
        //offerStainedGlassPaneDyeingRecipe(exporter, ModBlocks.MINT_STAINED_GLASS_PANE, ModItems.MINT_DYE); //TODO ADDME
        offerConcretePowderDyeingRecipe(exporter, ModBlocks.MINT_CONCRETE, ModItems.MINT_DYE);
        offerTerracottaDyeingRecipe(exporter, ModBlocks.MINT_TERRACOTTA, ModItems.MINT_DYE);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.MINT_TERRACOTTA), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINT_GLAZED_TERRACOTTA, 0.1f, 200);

        //offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, Items.BLAZE_ROD, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLAZE_ROD_BLOCK);
    }
}

