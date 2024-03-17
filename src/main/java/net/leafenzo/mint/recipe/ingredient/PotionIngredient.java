/*
 * SOURCES:
 * glisco - https://github.com/wisp-forest/affinity/blob/73653a6cf33452dbe8c7e22db832c2c24cf80415/src/main/java/io/wispforest/affinity/recipe/ingredient/PotionIngredient.java
 */

package net.leafenzo.mint.recipe.ingredient;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredient;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.leafenzo.mint.Super;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public class PotionIngredient implements CustomIngredient {

    public static final Codec<PotionIngredient> CODEC = RecordCodecBuilder.create(instance -> instance
            .group(Registries.POTION.getCodec().fieldOf("potion").forGetter(ingredient -> ingredient.requiredPotion))
            .apply(instance, PotionIngredient::new));

    public final Potion requiredPotion;

    public PotionIngredient(Potion requiredPotion) {
        this.requiredPotion = requiredPotion;
    }

    @Override
    public boolean test(ItemStack stack) {
        return (stack.isOf(Items.POTION) || stack.isOf(Items.SPLASH_POTION) || stack.isOf(Items.LINGERING_POTION)) && PotionUtil.getPotion(stack) == requiredPotion;
    }

    @Override
    public List<ItemStack> getMatchingStacks() {
        return List.of(
                PotionUtil.setPotion(Items.POTION.getDefaultStack(), this.requiredPotion),
                PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), this.requiredPotion),
                PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), this.requiredPotion)
        );
    }

    @Override
    public boolean requiresTesting() {
        return true;
    }

    @Override
    public CustomIngredientSerializer<?> getSerializer() {
        return ModIngredientSerializers.POTION;
    }

    public static final class Serializer implements CustomIngredientSerializer<PotionIngredient> {
        @Override
        public Identifier getIdentifier() {
            return new Identifier(Super.MOD_ID, "potion");
        }

        @Override
        public PotionIngredient read(JsonObject json) {
            if (json.has("potion")) {
                return new PotionIngredient(Potions.WATER);
//                Identifier id = new Identifier(JsonHelper.getElement(json, "potion").toString());
//                Potion potion = Registries.POTION.get(id);
//                return new PotionIngredient(potion);
            }
            else { throw new RuntimeException(json.toString() + " has no member of name: \"potion\"!"); }
//            return new PotionIngredient(Potions.WATER);
        }

        @Override
        public void write(JsonObject json, PotionIngredient ingredient) {
            json.addProperty("potion", Registries.POTION.getId(ingredient.requiredPotion).getPath());
        }

        @Override
        public PotionIngredient read(PacketByteBuf buf) {
            return new PotionIngredient(buf.readRegistryValue(Registries.POTION));
        }

        @Override
        public void write(PacketByteBuf buf, PotionIngredient ingredient) {
            buf.writeRegistryValue(Registries.POTION, ingredient.requiredPotion);
        }
    }

}
