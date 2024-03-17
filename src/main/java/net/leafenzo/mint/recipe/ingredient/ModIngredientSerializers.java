package net.leafenzo.mint.recipe.ingredient;

import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;

public class ModIngredientSerializers {

    public static final CustomIngredientSerializer<?> POTION = registerIngredientSerializer(new PotionIngredient.Serializer());

    public static CustomIngredientSerializer<?> registerIngredientSerializer(CustomIngredientSerializer<?> value) {
        CustomIngredientSerializer.register(value);
        return value;
    }

    public static void registerModCustomIngredientSerializers() {
            ModInit.LOGGER.debug("Registering custom ingredient serializers for " + Super.MOD_ID);
    }
}
