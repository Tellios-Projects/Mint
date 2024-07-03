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
import net.leafenzo.mint.registries.ModFabricRegistries;
import net.leafenzo.mint.registries.ModVillagerTrades;
import net.leafenzo.mint.util.ModWorldGen;
//import net.leafenzo.mint.world.ModWorldGenModifications;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ModInit implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(Super.MOD_ID);

    @Override
    public void onInitialize() {
        ModBuiltinResourcePacks.registerBuiltinPacks();
        ModBlocks.registerModBlocks();
//            ModIntegrations.registerModIntegrationBlocks();
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
    }
}
