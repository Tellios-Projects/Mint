package net.leafenzo.mint.recipe;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.google.gson.JsonObject;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface ModRecipeSerializer<T extends Recipe<?>> {

    net.minecraft.recipe.RecipeSerializer<ShulkerBoxColoringRecipe> SHULKER_BOX_INCLUSIVE = register("crafting_special_shulkerboxcoloring_including_new_dyes", new SpecialRecipeSerializer(ShulkerBoxColoringRecipe::new));
    T read(Identifier id, JsonObject json);
    T read(Identifier id, PacketByteBuf buf);
    void write(PacketByteBuf buf, T recipe);

    static <S extends net.minecraft.recipe.RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, id, serializer);
    }
}
