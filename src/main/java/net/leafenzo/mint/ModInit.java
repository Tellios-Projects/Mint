package net.leafenzo.mint;

import net.fabricmc.api.ModInitializer;
import net.leafenzo.mint.block.DispenserBehavior;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.entity.ModBlockEntityType;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.item.ModItemGroups;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.particle.ModParticleTypes;
import net.leafenzo.mint.potions.ModPotions;
import net.leafenzo.mint.recipe.ModRecipeSerializer;
import net.leafenzo.mint.registries.ModFabricRegistries;
import net.leafenzo.mint.util.ModWorldGen;
//import net.leafenzo.mint.world.ModWorldGenModifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            ModParticleTypes.registerModParticleTypes();
            ModFabricRegistries.registerFlammableBlocks();
            ModFabricRegistries.registerCompostingChances();
            ModFabricRegistries.registerVillagerInteractions();
            ModFabricRegistries.registerFuels();
            DispenserBehavior.RegisterDispenserBehaviors();
            ModRecipeSerializer.registerModRecipeSerializer();
            ModEffects.registerModEffects();
            ModPotions.registerModPotions();
            ModWorldGen.registerWorldGen();
            ModItemGroups.registerModItemGroups();


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

