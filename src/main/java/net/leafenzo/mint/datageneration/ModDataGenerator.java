package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ModDataGenerator implements DataGeneratorEntrypoint {

    // Using an accessor like this didn't work because of how the target class is final... I think?
    public net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack createPack(net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator fabricDataGenerator, String forModId) {
        return new net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack(true, forModId, ((FabricDataGenerator)(Object) fabricDataGenerator).getFabricOutput());
    }

    @Override
    public void onInitializeDataGenerator(net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator fabricDataGenerator) {
        net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModLootTableGenerator::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModBlockTagGenerator::new);
        pack.addProvider(ModItemTagGenerator::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModWorldGenerator::new);
        pack.addProvider(ModEnglishLangProvider::new);

        net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack supplimentaries = fabricDataGenerator.;
    }
    @Override public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
//        registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
        System.out.println("Built Registry");
    }
}
