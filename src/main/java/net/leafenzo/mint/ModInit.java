package net.leafenzo.mint;

import net.fabricmc.api.ModInitializer;
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
import net.leafenzo.mint.registries.ModFabricRegistries;
import net.leafenzo.mint.registries.ModVillagerTrades;
import net.leafenzo.mint.util.ModDyeColor;
import net.leafenzo.mint.util.ModWorldGen;
//import net.leafenzo.mint.world.ModWorldGenModifications;
import net.minecraft.util.DyeColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class ModInit implements ModInitializer {
        public static final String MOD_ID = Super.MOD_ID;
        public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        @Override
        public void onInitialize() {
            //Determine the original registries; used to determine what translations to automatically generate.
//            Set<Identifier> original = ModUtil.getAllRegistryIds();

            ModBlocks.registerModBlocks();
            ModItems.registerModItems();
            ModBlockEntityType.RegisterModBlockEntityTypes();
            ModEntityTypes.registerEntityTypes();
            ModParticleTypes.registerModParticleTypes();
            ModFabricRegistries.registerFlammableBlocks();
            ModFabricRegistries.registerCompostingChances();
            ModFabricRegistries.registerVillagerInteractions();
            ModVillagerTrades.registerVillagerTrades();
            ModFabricRegistries.registerFuels();
            DispenserBehavior.RegisterDispenserBehaviors();
            ModRecipeSerializer.registerModRecipeSerializer();
            ModEffects.registerModEffects();
            ModPotions.registerModPotions();
            ModWorldGen.registerWorldGen();
            ModItemGroups.registerModItemGroups();

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

//            Set<Identifier> altered = ModUtil.getAllRegistryIds();
//            ModEnglishLangProvider.TranslationsFromIds(original, altered);
        }
    }

