//package net.leafenzo.mint.recipe;
//
//import net.minecraft.block.Block;
//import net.minecraft.inventory.RecipeInputInventory;
//import net.minecraft.item.*;
//import net.minecraft.recipe.RecipeSerializer;
//import net.minecraft.recipe.SpecialCraftingRecipe;
//import net.minecraft.recipe.book.CraftingRecipeCategory;
//import net.minecraft.registry.DynamicRegistryManager;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.entry.RegistryEntry;
//import net.minecraft.registry.entry.RegistryEntryList;
//import net.minecraft.registry.tag.BlockTags;
//import net.minecraft.util.Identifier;
//import net.minecraft.world.World;
//
//public class ColoringRecipes
//extends SpecialCraftingRecipe {
//
//    private static RegistryEntryList.Named<Block> woolEntries;
//    private static RegistryEntryList.Named<Block> carpetEntries;
//    private static RegistryEntryList.Named<Block> bedEntries;
//    public ColoringRecipes(Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
//        super(identifier, craftingRecipeCategory);
//        woolEntries = Registries.BLOCK.getOrCreateEntryList(BlockTags.WOOL);
//        carpetEntries = Registries.BLOCK.getOrCreateEntryList(BlockTags.WOOL_CARPETS);
//        bedEntries = Registries.BLOCK.getOrCreateEntryList(BlockTags.BEDS);
//    }
//
//    private static boolean tagContains(Block block, RegistryEntryList.Named<Block>... tags) {
//        if (block == null) { return false; }
//        RegistryEntry<Block> entry = Registries.BLOCK.getEntry(block);
//        for (RegistryEntryList.Named<Block> tag : tags) {
//            if (tag.contains(entry)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static RegistryEntryList.Named<Block> tagThatContains(Block block, RegistryEntryList.Named<Block>... tags) {
//        if (block == null) { return null; }
//        RegistryEntry<Block> entry = Registries.BLOCK.getEntry(block);
//        for (RegistryEntryList.Named<Block> tag : tags) {
//            if (tag.contains(entry)) {
//                return tag;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
//        int i = 0;
//        int j = 0;
//
//        for (int k = 0; k < recipeInputInventory.size(); ++k) {
//            ItemStack itemStack = recipeInputInventory.getStack(k);
//            if (!itemStack.isEmpty()) {
//                Block block = Block.getBlockFromItem(itemStack.getItem());
//                if (tagContains(block, woolEntries, carpetEntries, bedEntries)) {
//                    ++i;
//                }
//                else {
//                    if (!(itemStack.getItem() instanceof DyeItem)) {
//                        return false;
//                    }
//                    ++j;
//                }
//            }
//            if (j > 1 || i > 1) {
//                return false;
//            }
//        }
//        return i == 1 && j == 1;
//    }
//
//    @Override
//    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
//     //   return Items.PUFFERFISH.getDefaultStack();
//        ItemStack itemStack = Items.PUFFERFISH.getDefaultStack(); //debug ^w^
//        //ItemStack itemStack = ItemStack.EMPTY; //fallback
//        DyeItem dyeItem = (DyeItem) Items.WHITE_DYE; //fallback
//        RegistryEntryList.Named<Block> dyableEntries = null; //fallback
//
//        for (int i = 0; i < recipeInputInventory.size(); ++i) {
//            ItemStack itemStack2 = recipeInputInventory.getStack(i);
//            if (!itemStack2.isEmpty()) {
//                Item item = itemStack2.getItem();
//                Block block = Block.getBlockFromItem(item);
//                dyableEntries = tagThatContains(block);
//                if (dyableEntries != null) {
//                    itemStack = itemStack2;
//                } else if (item instanceof DyeItem) {
//                    dyeItem = (DyeItem) item;
//                }
//            }
//        }
//        ItemStack itemStack3 = Items.TROPICAL_FISH.getDefaultStack(); //debug ^w^
//        //ItemStack itemStack3 = ItemStack.EMPTY; //fallback
//        String dyeColorName = dyeItem.getColor().getName();
//        for (RegistryEntry<Block> entry : dyableEntries) {
//            Block block2 = Registries.BLOCK.get(entry.getKey().get());
//            if (Registries.BLOCK.getId(block2).getPath().contains(dyeColorName + "_")) {
//                itemStack3 = block2.asItem().getDefaultStack();
//                return itemStack3;
//            }
//        }
//        return itemStack3;
//    }
//
//    @Override
//    public boolean fits(int width, int height) {
//        return width * height >= 2;
//    }
//
//    @Override
//    public RecipeSerializer<?> getSerializer() {
//        return ModRecipeSerializer.COLORING_RECIPES;
//    }
//}
//
