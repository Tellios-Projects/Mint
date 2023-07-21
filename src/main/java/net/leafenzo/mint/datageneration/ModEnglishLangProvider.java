//package net.leafenzo.mint.datageneration;
//
//import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
//import net.leafenzo.mint.Super;
//import net.leafenzo.mint.item.ModItemGroups;
//import net.leafenzo.mint.item.ModItems;
//
//import java.nio.file.Path;
//
//public class ModEnglishLangProvider extends FabricLanguageProvider {
//    public ModEnglishLangProvider(FabricDataOutput dataGenerator) {
//        super(dataGenerator, "en_us");
//    }
//
//    @Override
//    public void generateTranslations(TranslationBuilder translationBuilder) {
//        //translationBuilder.add(ModItemGroups.MINT, "Simple Item Group");
//        translationBuilder.add(ModItems.MINT_DYE, "Simple Item");
//        translationBuilder.add(ModItems.MINT_SEEDS, "Simple Block");
//        translationBuilder.add(ModItems.MINT_SPRIG, "Simple Item Group");
//
//
//        // Load an existing language file.
//        try {
//            Path existingFilePath = dataGenerator.getModContainer().findPath("assets" + Super.MOD_ID + "/lang/en_us.existing.json").get();
//            translationBuilder.add(existingFilePath);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to add existing language file!", e);
//        }
//    }
//
//}