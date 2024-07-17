package net.leafenzo.mint;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.leafenzo.mint.block.DispenserBehavior;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.entity.ModBlockEntityType;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.entity.ModEntityTypes;
import net.leafenzo.mint.item.ModItemGroups;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.particle.ModParticleTypes;
import net.leafenzo.mint.potions.ModPotions;
import net.leafenzo.mint.recipe.ModRecipeSerializer;
import net.leafenzo.mint.recipe.ingredient.ModIngredientSerializers;
import net.leafenzo.mint.recipe.ingredient.PotionIngredient;
import net.leafenzo.mint.registries.ModFabricRegistries;
import net.leafenzo.mint.registries.ModVillagerTrades;
import net.leafenzo.mint.util.ModWorldGen;
//import net.leafenzo.mint.world.ModWorldGenModifications;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.system.windows.POINT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

import java.util.ArrayList;
import java.util.Optional;

public class ModInit implements ModInitializer {
        public static final String MOD_ID = Super.MOD_ID;
        public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        private static void registerBuiltinResourcePack(ModContainer modContainer, String forModID) {
            if (FabricLoader.getInstance().isModLoaded(forModID)) {
                    ResourceManagerHelper.registerBuiltinResourcePack(
                            new Identifier(Super.MOD_ID, forModID),
                            modContainer,
                            Text.translatable("pack." + Super.MOD_ID + forModID),
                            ResourcePackActivationType.ALWAYS_ENABLED
                    );
            }
        }

        @Override
        public void onInitialize() {
            Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer("mint");
            if(modContainer.isPresent()) {
                registerBuiltinResourcePack(modContainer.get(), "amendments");
                registerBuiltinResourcePack(modContainer.get(), "botanypots");
                registerBuiltinResourcePack(modContainer.get(), "comforts");
                registerBuiltinResourcePack(modContainer.get(), "supplementaries");
                registerBuiltinResourcePack(modContainer.get(), "suppsquared");
                registerBuiltinResourcePack(modContainer.get(), "snowyspirit");
                registerBuiltinResourcePack(modContainer.get(), "biomemakeover");
                registerBuiltinResourcePack(modContainer.get(), "create");
                registerBuiltinResourcePack(modContainer.get(), "createdeco");
                registerBuiltinResourcePack(modContainer.get(), "sleep_tight");
            }

            ModBlocks.registerModBlocks();
            ModItems.registerModItems();
            ModBlockEntityType.RegisterModBlockEntityTypes();
            ModEntityTypes.registerEntityTypes();
            ModParticleTypes.registerModParticleTypes();
            ModFabricRegistries.registerStrippables();
            ModFabricRegistries.registerFlammableBlocks();
            ModFabricRegistries.registerCompostingChances();
            ModFabricRegistries.registerVillagerInteractions();
            ModVillagerTrades.registerVillagerTrades();
            ModFabricRegistries.registerFuels();
            DispenserBehavior.RegisterDispenserBehaviors();
            ModIngredientSerializers.registerModCustomIngredientSerializers();
            ModRecipeSerializer.registerModRecipeSerializer();
            ModEffects.registerModEffects();
            ModPotions.registerModPotions();
            ModWorldGen.registerWorldGen();
            ModFabricRegistries.modifyLootTables();
            ModItemGroups.registerModItemGroups();
            GeckoLib.initialize();

//            List<DyeColor> original = Arrays.stream(DyeColor.values()).toList();
//            LOGGER.debug("BIG MEOW 1 : " + Arrays.toString(original.toArray()));
//            List<DyeColor> colors = new java.util.ArrayList<>(original);
//            for (DyeColor color : original) {
//                if(Arrays.asList(ModDyeColor.VALUES).contains(color)) {
//                    colors.remove(color);
//                }
//            }
//            LOGGER.debug("BIG MEOW 2 : " + Arrays.toString(colors.toArray()));
//            var colors2 = (DyeColor[]) Arrays.stream(original.toArray())
//                .filter((a) -> !Arrays.asList(ModDyeColor.VALUES).contains((DyeColor) a))
//                .toArray();
//            LOGGER.debug("BIG MEOW 3 : " + Arrays.toString(colors2));

//            for(Identifier id : ModUtil.allBlockIdsInNamespace(Super.MOD_ID)) {
//                System.out.println("BLOCK BINGUS - " + Registries.BLOCK.get(id).getTranslationKey());
//                //translationBuilder.add(id, toSentanceCase(id.getPath()));
//            }
//            for(Identifier id : ModUtil.allItemIdsInNamespace(Super.MOD_ID)) {
//                System.out.println("ITEM BINGUS - " + Registries.ITEM.get(id).getTranslationKey());
//                //translationBuilder.add(id, toSentanceCase(id.getPath()));
//            }

//            System.out.println("BIG MEOW 0a! - " + new PotionIngredient(Potions.WATER).requiredPotion.toString());
//            System.out.println("BIG MEOW 0b! - " + new Identifier(new PotionIngredient(Potions.WATER).requiredPotion.toString()));
//            System.out.println("BIG MEOW 1! - " + Ingredient.fromJson(new PotionIngredient(Potions.WATER).toVanilla().toJson()));
//            System.out.println("BIG MEOW 2! - " + Registries.POTION.getId(Potions.WATER).toString());
//            System.out.println("BIG MEOW 3! - " + Registries.POTION.getId(Potions.WATER).getPath());

//            Set<Identifier> altered = ModUtil.getAllRegistryIds();
//            ModEnglishLangProvider.TranslationsFromIds(original, altered);
        }
}
