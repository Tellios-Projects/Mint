package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ElsDyeModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ElsDyeModLootTableGenerator::new);
        pack.addProvider(ElsDyeModRecipeProvider::new);
        pack.addProvider(ElsDyeModBlockTagGenerator::new);
        pack.addProvider(ElsDyeModItemTagGenerator::new);
        pack.addProvider(ElsDyeModModelProvider::new);
        pack.addProvider(ElsDyeModWorldGenerator::new);
        pack.addProvider(ElsDyeModEnglishLangProvider::new);
    }
    @Override public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ElsDyeModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ElsDyeModPlacedFeatures::bootstrap);
//        registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
        System.out.println("Built Registry");
    }
}
