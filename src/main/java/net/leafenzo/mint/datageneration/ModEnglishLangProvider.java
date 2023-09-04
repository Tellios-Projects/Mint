package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItemGroups;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import

import java.nio.file.Path;

public class ModEnglishLangProvider extends FabricLanguageProvider {

    public ModEnglishLangProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/" + Super.MOD_ID + "/lang/en_us.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }

//        translationBuilder.add(ModItemGroups.MINT, "Simple Item Group");

        for(Block block : ModBlocks.WOOL_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Wool");
        }

        for(Block block : ModBlocks.WOOL_CARPET_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Carpet");
        }

        for(Block block : ModBlocks.TERRACOTTA_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Terracotta");
        }

        for(Block block : ModBlocks.CONCRETE_POWDER_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Concrete Powder");
        }

        for(Block block : ModBlocks.CONCRETE_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Concrete");
        }

        for(Block block : ModBlocks.GLAZED_TERRACOTTA_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Glazed Terracotta");
        }

        for(Block block : ModBlocks.STAINED_GLASS_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Stained Glass");
        }

        for(Block block : ModBlocks.STAINED_GLASS_PANE_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Stained Glass Pane");
        }

        for(Block block : ModBlocks.SHULKER_BOX_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Shulker Box");
        }

        for(Block block : ModBlocks.BED_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(block);
            translationBuilder.add(block, color.getName() + " Bed");
        }
    }
}