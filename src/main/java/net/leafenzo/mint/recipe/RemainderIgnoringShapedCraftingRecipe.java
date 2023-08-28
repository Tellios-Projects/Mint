//package net.leafenzo.mint.recipe;
//
//import net.minecraft.inventory.CraftingInventory;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.recipe.Ingredient;
//import net.minecraft.recipe.ShapedRecipe;
//import net.minecraft.recipe.book.CraftingRecipeCategory;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.collection.DefaultedList;
//
//public class RemainderIgnoringShapedCraftingRecipe extends ShapedRecipe {
//    public RemainderIgnoringShapedCraftingRecipe(Identifier id, String group, CraftingRecipeCategory category, int width, int height, DefaultedList<Ingredient> input, ItemStack output, boolean showNotification) {
//        super(id, group, category, width, height, input, output, showNotification);
//    }
//    public RemainderIgnoringShapedCraftingRecipe(Identifier id, String group, CraftingRecipeCategory category, int width, int height, DefaultedList<Ingredient> input, ItemStack output) {
//        super(id, group, category, width, height, input, output);
//    }
//
//    @Override
//    default public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
//        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
////        for (int i = 0; i < defaultedList.size(); ++i) {
////            Item item = inventory.getStack(i).getItem();
////            if (!item.hasRecipeRemainder()) continue;
////            defaultedList.set(i, new ItemStack(item.getRecipeRemainder()));
////        }
//        return defaultedList;
//    }
//}
