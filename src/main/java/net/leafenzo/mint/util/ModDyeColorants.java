package net.leafenzo.mint.util;

import com.mojang.serialization.Lifecycle;
import io.wispforest.gelatin.common.misc.GelatinConstants;
import io.wispforest.gelatin.dye_registry.DyeColorant;
import io.wispforest.gelatin.dye_registry.DyeColorantRegistry;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.leafenzo.mint.Super;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleDefaultedRegistry;
import net.minecraft.util.Identifier;

public class ModDyeColorants {

    public static final DyeColorant MINT = registerDyeColor("mint", MapColor.LICHEN_GREEN,  0x65FF8E, 0x65FF8E, 0x65FF8E);
    public static final DyeColorant PEACH = registerDyeColor("peach", MapColor.RAW_IRON_PINK,  0xFB925F, 0xFB925F, 0xFB925F);
    public static final DyeColorant PERIWINKLE = registerDyeColor("periwinkle", MapColor.PALE_PURPLE,  0x9986EB, 0x9986EB, 0x9986EB);
    public static final DyeColorant ARTICHOKE = registerDyeColor("artichoke", MapColor.TERRACOTTA_LIME,  0xC1D81E, 0xC1D81E, 0xC1D81E);
    public static final DyeColorant FUCHSIA = registerDyeColor("fuchsia", MapColor.DULL_PINK,  0xDC5998, 0xDC5998, 0xDC5998);
    public static final DyeColorant VERMILION = registerDyeColor("vermilion", MapColor.DULL_RED,  0xFB6528, 0xFB6528, 0xFB6528);
    public static final DyeColorant SHAMROCK = registerDyeColor("shamrock", MapColor.EMERALD_GREEN,  0x23E500, 0x23E500, 0x23E500);
    public static final DyeColorant INDIGO = registerDyeColor("indigo", MapColor.LAPIS_BLUE,  0x7531DA, 0x7531DA, 0x7531DA);
    public static final DyeColorant BANANA = registerDyeColor("banana", MapColor.PALE_YELLOW,  0xE2C179, 0xE2C179, 0xE2C179);

    public static DyeColorant registerDyeColor(Identifier identifier, MapColor mapColor, int baseColor) {
        return registerDyeColor(identifier, mapColor, baseColor, baseColor, baseColor);
    }
    public static DyeColorant registerDyeColor(String identifierPath, MapColor mapColor, int baseColor, int fireworkColor, int signColor) {
        return registerDyeColor(new Identifier(Super.MOD_ID + identifierPath), mapColor, baseColor, baseColor, baseColor);
    }
    public static DyeColorant registerDyeColor(Identifier identifier, MapColor mapColor, int baseColor, int fireworkColor, int signColor) {
        DyeColorant dyeColor = new DyeColorant(mapColor, baseColor, fireworkColor, signColor);

        return Registry.register(DyeColorantRegistry.DYE_COLOR, identifier, dyeColor);
    }
}
