package net.leafenzo.mint;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.leafenzo.mint.block.DispenserBehavior;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.block.entity.ElsDyeModBlockEntityType;
import net.leafenzo.mint.effect.ElsDyeModEffects;
import net.leafenzo.mint.entity.ElsDyeModEntityTypes;
import net.leafenzo.mint.item.ElsDyeModItemGroups;
import net.leafenzo.mint.item.ElsDyeModItems;
import net.leafenzo.mint.particle.ElsDyeModParticleTypes;
import net.leafenzo.mint.potions.ElsDyeModPotions;
import net.leafenzo.mint.recipe.ElsDyeModRecipeSerializer;
import net.leafenzo.mint.recipe.ingredient.ModIngredientSerializers;
import net.leafenzo.mint.registries.ElsDyeModFabricRegistries;
import net.leafenzo.mint.registries.ElsDyeModVillagerTrades;
import net.leafenzo.mint.util.ElsDyeModWorldGen;
import net.leafenzo.mint.world.gen.ModFeatures;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

import java.util.Optional;

public class ElsDyeModInit implements ModInitializer {
        public static final String MOD_ID = ElsDyeMod.MOD_ID;
        public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        private static void registerBuiltinResourcePack(ModContainer modContainer, String forModID) {
            if (FabricLoader.getInstance().isModLoaded(forModID)) {
                    ResourceManagerHelper.registerBuiltinResourcePack(
                            new Identifier(ElsDyeMod.MOD_ID, forModID),
                            modContainer,
                            Text.translatable("pack." + ElsDyeMod.MOD_ID + forModID),
                            ResourcePackActivationType.ALWAYS_ENABLED
                    );
            }
        }

        @Override
        public void onInitialize() {
            Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer("mint");
            if(modContainer.isPresent()) {
                registerBuiltinResourcePack(modContainer.get(), "amendments");
                registerBuiltinResourcePack(modContainer.get(), "biomemakeover");
                registerBuiltinResourcePack(modContainer.get(), "botanypots");
                registerBuiltinResourcePack(modContainer.get(), "comforts");
                registerBuiltinResourcePack(modContainer.get(), "cookingforblockheads");
                registerBuiltinResourcePack(modContainer.get(), "create");
                registerBuiltinResourcePack(modContainer.get(), "createdeco");
                registerBuiltinResourcePack(modContainer.get(), "excessive_building");
                registerBuiltinResourcePack(modContainer.get(), "sleep_tight");
                registerBuiltinResourcePack(modContainer.get(), "snowyspirit");
                registerBuiltinResourcePack(modContainer.get(), "supplementaries");
                registerBuiltinResourcePack(modContainer.get(), "suppsquared");
            }

            ElsDyeModBlocks.registerModBlocks();
            ElsDyeModItems.registerModItems();
            ElsDyeModBlockEntityType.RegisterModBlockEntityTypes();
            ElsDyeModEntityTypes.registerEntityTypes();
            ElsDyeModParticleTypes.registerModParticleTypes();
            ElsDyeModFabricRegistries.registerStrippables();
            ElsDyeModFabricRegistries.registerFlammableBlocks();
            ElsDyeModFabricRegistries.registerCompostingChances();
            ElsDyeModFabricRegistries.registerVillagerInteractions();
            ElsDyeModVillagerTrades.registerVillagerTrades();
            ElsDyeModFabricRegistries.registerFuels();
            DispenserBehavior.RegisterDispenserBehaviors();
            ModIngredientSerializers.registerModCustomIngredientSerializers();
            ElsDyeModRecipeSerializer.registerModRecipeSerializer();
            ElsDyeModEffects.registerModEffects();
            ElsDyeModPotions.registerModPotions();
            ElsDyeModWorldGen.registerWorldGen();
            ElsDyeModFabricRegistries.modifyLootTables();
            ElsDyeModItemGroups.registerModItemGroups();
            ModFeatures.registerFeatures();
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
