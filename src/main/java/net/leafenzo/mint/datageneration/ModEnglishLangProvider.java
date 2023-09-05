package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.item.ModItemGroups;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import static net.leafenzo.mint.util.ModUtil.toSentanceCase;

public class ModEnglishLangProvider extends FabricLanguageProvider {

    public ModEnglishLangProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
//        try {
//            Path existingFilePath = dataOutput.getModContainer().findPath("assets/" + Super.MOD_ID + "/lang/en_us.json").get();
//            translationBuilder.add(existingFilePath);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to add existing language file!", e);
//        }

//        translationBuilder.add(ModItemGroups.MINT, "Simple Item Group");

        Set<String> allTranslationKeys = new HashSet<String>();
//        for(Identifier id : ModUtil.getAllRegistryIds()) {
//
//        }


        for(Identifier id : ModUtil.allBlockIdsInNamespace(Super.MOD_ID)) {
            String key = Registries.BLOCK.get(id).getTranslationKey();
            if(allTranslationKeys.contains(key)) { continue; } //Skip over duplicate translation keys
            allTranslationKeys.add(key);
            translationBuilder.add(key, toSentanceCase(id.getPath()));
        }
        for(Identifier id : ModUtil.allItemIdsInNamespace(Super.MOD_ID)) {
            String key = Registries.ITEM.get(id).getTranslationKey();
            if(allTranslationKeys.contains(key)) { continue; } //Skip over duplicate translation keys
            allTranslationKeys.add(key);
            translationBuilder.add(Registries.ITEM.get(id), toSentanceCase(id.getPath()));
        }
        for(Identifier id : ModUtil.allItemGroupIdsInNamespace(Super.MOD_ID)) {
            String key = Registries.ITEM_GROUP.get(id).getDisplayName().toString();
            if(allTranslationKeys.contains(key)) { continue; } //Skip over duplicate translation keys
            allTranslationKeys.add(key);
            translationBuilder.add(Registries.ITEM.get(id), toSentanceCase(id.getPath()));
        }



//          for(Block block : ModBlocks.DYECOLOR_FROM_BLOCK.keySet()) {
//              translationBuilder.add(block, toWordCase(Registries.BLOCK.getId(block).toString()));
//          }


//        for(Block block : ModBlocks.WOOL_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Wool");
//        }
//
//        for(Block block : ModBlocks.WOOL_CARPET_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Carpet");
//        }
//
//        for(Block block : ModBlocks.TERRACOTTA_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Terracotta");
//        }
//
//        for(Block block : ModBlocks.CONCRETE_POWDER_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Concrete Powder");
//        }
//
//        for(Block block : ModBlocks.CONCRETE_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Concrete");
//        }
//
//        for(Block block : ModBlocks.GLAZED_TERRACOTTA_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Glazed Terracotta");
//        }
//
//        for(Block block : ModBlocks.STAINED_GLASS_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Stained Glass");
//        }
//
//        for(Block block : ModBlocks.STAINED_GLASS_PANE_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Stained Glass Pane");
//        }
//
//        for(Block block : ModBlocks.SHULKER_BOX_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, toWordCase(color.getName()) + " Shulker Box");
//        }
//
//        for(Block block : ModBlocks.BED_BLOCKS) {
//            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
//            translationBuilder.add(block, color.getName() + " Bed");
//        }
    }


//    public static void TranslationsFromIds(Set<Identifier> original, Set<Identifier> altered) {
//        if(altered.size() < original.size()) { throw new RuntimeException(); }
//        Set<Identifier> toTranslate = new HashSet<>();
//        for(Identifier id : altered) {
//            if( ! original.contains(id)) {
//                toTranslate.add(id);
//            }
//        }
//        if((long) toTranslate.size() == 0) { throw new RuntimeException("bingus"); }
//        for(Identifier a : toTranslate) {
//            System.out.println("BINGUS - " + a.toString());
//        }
//    }
}