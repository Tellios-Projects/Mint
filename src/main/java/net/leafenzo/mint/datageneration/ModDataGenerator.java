package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModLootTableGenerator::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModBlockTagGenerator::new);
        pack.addProvider(ModItemTagGenerator::new);
        pack.addProvider(ModModelProvider::new);
        //pack.addProvider(ModWorldGenerator::new);
        pack.addProvider(ModEnglishLangProvider::new);
    }
}
