package net.leafenzo.mint.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.DyeColor;

import java.util.List;

public class DyeSortingTables {

    //In the right places to make a big rainbow ^~^
    //  Roughly sorted by Hue then Light to Dark
    public static final List<DyeColor> ITEM_GROUP_DYE_ORDER = new ImmutableList.Builder<DyeColor>()
            .add(DyeColor.WHITE)
            .add(DyeColor.LIGHT_GRAY)
            .add(DyeColor.GRAY)
            .add(DyeColor.BLACK)
            .add(ModDyeColor.ACORN)
            .add(DyeColor.BROWN)
            .add(ModDyeColor.MOLD)
            .add(ModDyeColor.MAROON)
            .add(DyeColor.RED)
            .add(ModDyeColor.PEACH)
            .add(ModDyeColor.VERMILION)
            .add(DyeColor.ORANGE)
            .add(ModDyeColor.AMBER)
            .add(DyeColor.YELLOW)
            .add(ModDyeColor.BANANA)
            .add(ModDyeColor.ARTICHOKE)
            .add(DyeColor.LIME)
            .add(ModDyeColor.SAP)
            .add(DyeColor.GREEN)
            .add(ModDyeColor.SAGE)
            .add(ModDyeColor.SHAMROCK)
            .add(ModDyeColor.MINT)
            .add(DyeColor.CYAN)
            .add(ModDyeColor.CERULEAN)
            .add(DyeColor.LIGHT_BLUE)
            .add(ModDyeColor.NAVY)
            .add(DyeColor.BLUE)
            .add(ModDyeColor.PERIWINKLE)
            .add(ModDyeColor.GRAPE)
            .add(DyeColor.PURPLE)
            .add(ModDyeColor.INDIGO)
            .add(DyeColor.MAGENTA)
            .add(ModDyeColor.VELVET)
            .add(ModDyeColor.MAUVE)
            .add(ModDyeColor.FUCHSIA)
            .add(DyeColor.PINK)
            .build();
}
