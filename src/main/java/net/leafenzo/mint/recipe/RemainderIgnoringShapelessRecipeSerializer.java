package net.leafenzo.mint.recipe;


import com.google.gson.JsonObject;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;

//public class RemainderIgnoringShapelessRecipeSerializer extends ShapelessRecipe.Serializer {
//    public static final RemainderIgnoringShapelessRecipeSerializer INSTANCE = new RemainderIgnoringShapelessRecipeSerializer();
//
//    @Override
//    public ShapelessRecipe read(Identifier identifier, JsonObject jsonObject) {
//        return new RemainderIgnoringShapelessRecipe(super.read(identifier, jsonObject));
//    }
//    @Override
//    public ShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
//        return new RemainderIgnoringShapelessRecipe(super.read(identifier, packetByteBuf));
//    }
//
//}