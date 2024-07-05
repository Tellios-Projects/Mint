package net.leafenzo.mint.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.compat.twigs.TwigsSoundEvents;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.DyeColor;

import javax.annotation.Nullable;

import java.util.ArrayList;

import static net.leafenzo.mint.registration.ModRegistryHelper.BlockRegistry.*;

public class ModIntegrationBlocks {
    public static void registerModIntegrationBlocks() {
        if (Super.isDatagen() || FabricLoader.getInstance().isModLoaded(Super.TWIGS_MOD_ID)) { registerTwigsIntegrationBlocks(); }
    }
    public static void registerTwigsIntegrationBlocks() {
        registerPackedSiltBlocks();
    }

    //<editor-fold desc ="Twigs - Packed Silt Blocks">
    public static ArrayList<Block> TWIGS_PACKED_SILTS = new ArrayList<Block>();
    @Nullable public static Block MINT_PACKED_SILT;
    @Nullable public static Block PEACH_PACKED_SILT;
    @Nullable public static Block PERIWINKLE_PACKED_SILT;
    @Nullable public static Block ARTICHOKE_PACKED_SILT;
    @Nullable public static Block FUCHSIA_PACKED_SILT;
    @Nullable public static Block VERMILION_PACKED_SILT;
    @Nullable public static Block SHAMROCK_PACKED_SILT;
    @Nullable public static Block INDIGO_PACKED_SILT;
    @Nullable public static Block BANANA_PACKED_SILT;
    @Nullable public static Block CERULEAN_PACKED_SILT;
    @Nullable public static Block ACORN_PACKED_SILT;
    @Nullable public static Block MAUVE_PACKED_SILT;
    @Nullable public static Block MAROON_PACKED_SILT;
    @Nullable public static Block GRAPE_PACKED_SILT;
    @Nullable public static Block NAVY_PACKED_SILT;
    @Nullable public static Block SAP_PACKED_SILT;
    @Nullable public static Block AMBER_PACKED_SILT;
    @Nullable public static Block SAGE_PACKED_SILT;
    @Nullable public static Block VELVET_PACKED_SILT;
    @Nullable public static Block MOLD_PACKED_SILT;
    public static Block createPackedSiltBlock(DyeColor color) {
        Block b = new Block(FabricBlockSettings.copyOf(Blocks.TERRACOTTA).sounds(TwigsSoundEvents.PACKED_SILT).mapColor(color.getMapColor()));
        ModBlocks.DYECOLOR_FROM_BLOCK.put(b, color);
        TWIGS_PACKED_SILTS.add(b);
        return b;
    }
    public static void registerPackedSiltBlocks() {
        MINT_PACKED_SILT = registerBlock("mint_packed_silt", createPackedSiltBlock(ModDyeColor.MINT));
        PEACH_PACKED_SILT = registerBlock("peach_packed_silt", createPackedSiltBlock(ModDyeColor.PEACH));
        PERIWINKLE_PACKED_SILT = registerBlock("periwinkle_packed_silt", createPackedSiltBlock(ModDyeColor.PERIWINKLE));
        ARTICHOKE_PACKED_SILT = registerBlock("artichoke_packed_silt", createPackedSiltBlock(ModDyeColor.ARTICHOKE));
        FUCHSIA_PACKED_SILT = registerBlock("fuchsia_packed_silt", createPackedSiltBlock(ModDyeColor.FUCHSIA));
        VERMILION_PACKED_SILT = registerBlock("vermilion_packed_silt", createPackedSiltBlock(ModDyeColor.VERMILION));
        SHAMROCK_PACKED_SILT = registerBlock("shamrock_packed_silt", createPackedSiltBlock(ModDyeColor.SHAMROCK));
        INDIGO_PACKED_SILT = registerBlock("indigo_packed_silt", createPackedSiltBlock(ModDyeColor.INDIGO));
        BANANA_PACKED_SILT = registerBlock("banana_packed_silt", createPackedSiltBlock(ModDyeColor.BANANA));
        CERULEAN_PACKED_SILT = registerBlock("cerulean_packed_silt", createPackedSiltBlock(ModDyeColor.CERULEAN));
        ACORN_PACKED_SILT = registerBlock("acorn_packed_silt", createPackedSiltBlock(ModDyeColor.ACORN));
        MAUVE_PACKED_SILT = registerBlock("mauve_packed_silt", createPackedSiltBlock(ModDyeColor.MAUVE));
        MAROON_PACKED_SILT = registerBlock("maroon_packed_silt", createPackedSiltBlock(ModDyeColor.MAROON));
        GRAPE_PACKED_SILT = registerBlock("grape_packed_silt", createPackedSiltBlock(ModDyeColor.GRAPE));
        NAVY_PACKED_SILT = registerBlock("navy_packed_silt", createPackedSiltBlock(ModDyeColor.NAVY));
        SAP_PACKED_SILT = registerBlock("sap_packed_silt", createPackedSiltBlock(ModDyeColor.SAP));
        AMBER_PACKED_SILT = registerBlock("amber_packed_silt", createPackedSiltBlock(ModDyeColor.AMBER));
        SAGE_PACKED_SILT = registerBlock("sage_packed_silt", createPackedSiltBlock(ModDyeColor.SAGE));
        VELVET_PACKED_SILT = registerBlock("velvet_packed_silt", createPackedSiltBlock(ModDyeColor.VELVET));
        MOLD_PACKED_SILT = registerBlock("mold_packed_silt", createPackedSiltBlock(ModDyeColor.MOLD));
    }
    //</editor-fold>

}
