package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.util.ModDyeColor;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static net.leafenzo.mint.util.ModUtil.formatMultipleIdsForErrorLog;

public class ModItemGroups {
    public static void registerModItemGroups() {
        ModInit.LOGGER.debug("Registering item groups for " + Super.MOD_ID);
        modifyVanillaItemGroupEntries();
    }

    // you can tell I was having a rough time huh :p
    public static RuntimeException ColoredBlocksNotEqualError(DyeColor ca, DyeColor cb, Block[] a, Block[] b) {
        String errorMessage = (ca.getName() + " has " + a.length + " blocks in the colored blocks category" + ", while " + cb.getName() + " has " + b.length + " (" + formatMultipleIdsForErrorLog(a) + " VS " + formatMultipleIdsForErrorLog(b) + ")");
        ModInit.LOGGER.error(errorMessage);
        return new RuntimeException(errorMessage);
    }
    public static RuntimeException FunctionalBlocksNotEqualError(DyeColor ca, DyeColor cb, Block[] a, Block[] b) {
        String errorMessage = (ca.getName() + " has " + a.length + " blocks in the functional blocks category, while " + cb.getName() + " has " + b.length + " (" + formatMultipleIdsForErrorLog(a) + " VS " + formatMultipleIdsForErrorLog(b) + ")");
        ModInit.LOGGER.error(errorMessage);
        return new RuntimeException(errorMessage);
    }

    public static void addItemsOfColorAfterItemsOfAnotherColor(DyeColor colorBefore, DyeColor colorAfter) {
        @NotNull Block[] coloredBlocksBefore = ModUtil.ColoredBlocksOfColor(colorBefore);
        @NotNull Block[] coloredBlocksAfter = ModUtil.ColoredBlocksOfColor(colorAfter);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            if(coloredBlocksBefore.length != coloredBlocksAfter.length) {
                throw ColoredBlocksNotEqualError(colorBefore, colorAfter, coloredBlocksBefore, coloredBlocksAfter);
            }
            for (int i = 0; i < coloredBlocksBefore.length; i++) {
                content.addAfter(coloredBlocksBefore[i].asItem(), coloredBlocksAfter[i].asItem());
            }
        });

        @NotNull Block[] functionalBlocksBefore = ModUtil.FunctionalBlocksOfColor(colorBefore);
        @NotNull Block[] functionalBlocksAfter = ModUtil.FunctionalBlocksOfColor(colorAfter);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            if(functionalBlocksBefore.length != functionalBlocksAfter.length) {
                throw FunctionalBlocksNotEqualError(colorBefore, colorAfter, coloredBlocksBefore, coloredBlocksAfter);
            }
            for (int i = 0; i < functionalBlocksBefore.length; i++) {
                content.addAfter(functionalBlocksBefore[i].asItem(), functionalBlocksAfter[i].asItem());
            }
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
               content.addAfter(DyeItem.byColor(colorBefore), DyeItem.byColor(colorAfter));
        });
    }

    //keep in the right places to make a big rainbow ^~^ in conjunction with our other dye mods as well
    public static void modifyVanillaItemGroupEntries() {
        //White
        //Light Gray
        //Gray
        //Black
        //Brown
         //Acorn
        //Red
        //Vermilion
        addItemsOfColorAfterItemsOfAnotherColor(DyeColor.RED, ModDyeColor.VERMILION);
        //Peach
        addItemsOfColorAfterItemsOfAnotherColor(ModDyeColor.VERMILION, ModDyeColor.PEACH);
        //Orange
        //Yellow
        //Banana
        addItemsOfColorAfterItemsOfAnotherColor(DyeColor.YELLOW, ModDyeColor.BANANA);
        //Artichoke
        addItemsOfColorAfterItemsOfAnotherColor(ModDyeColor.BANANA, ModDyeColor.ARTICHOKE);
        //Lime
        //Green
        //Sap
        //Shamrock
         addItemsOfColorAfterItemsOfAnotherColor(DyeColor.GREEN, ModDyeColor.SHAMROCK); //TODO addItemsOfColorAfterItemsOfAnotherColor(ModDyeColor.SAP, ModDyeColor.SHAMROCK);
        //Mint
        addItemsOfColorAfterItemsOfAnotherColor(ModDyeColor.SHAMROCK, ModDyeColor.MINT);
        //Light Blue
        //Cyan
        //Blue
        //Periwinkle
        addItemsOfColorAfterItemsOfAnotherColor(DyeColor.BLUE, ModDyeColor.PERIWINKLE);
        //Indigo
        addItemsOfColorAfterItemsOfAnotherColor(ModDyeColor.PERIWINKLE, ModDyeColor.INDIGO);
        //Purple
        //Magenta
        //Fuchsia
        addItemsOfColorAfterItemsOfAnotherColor(DyeColor.MAGENTA, ModDyeColor.FUCHSIA);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            content.addAfter(Items.COOKIE, ModItems.MINT_COOKIE);
            content.addAfter(Items.MILK_BUCKET, ModItems.MINT_SPRIG, ModItems.MINT_TEA);
//            content.addAfter(Items.GLOW_BERRIES, ModItems.MINT_SPRIG);
//            content.addAfter(Items.MILK_BUCKET, ModItems.MINT_TEA);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Items.LARGE_FERN, ModBlocks.WILD_MINT, ModBlocks.MINT_SPRIG_BLOCK); //TODO, figure out if mint sprig block should be squashed exclusive
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Blocks.DARK_PRISMARINE_SLAB, ModBlocks.MINT_BRICKS, ModBlocks.MINT_BRICK_STAIRS, ModBlocks.MINT_BRICK_SLAB);
        });
    }


    //TODO, eventually comment out these item groups as they're only for debug purposes really
    public static ItemGroup MINT = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "mint"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".mint"))
                    .icon(() -> new ItemStack(ModItems.MINT_SPRIG)).entries((displayContext, entries) -> {
                        entries.add(ModItems.MINT_DYE);
                        entries.add(ModBlocks.MINT_WOOL);
                        entries.add(ModBlocks.MINT_CARPET);
                        entries.add(ModBlocks.MINT_CONCRETE);
                        entries.add(ModBlocks.MINT_CONCRETE_POWDER);
                        entries.add(ModBlocks.MINT_TERRACOTTA);
                        entries.add(ModBlocks.MINT_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.MINT_STAINED_GLASS);
                        entries.add(ModBlocks.MINT_STAINED_GLASS_PANE);
                        entries.add(ModItems.MINT_BED);
                        entries.add(ModItems.MINT_BANNER);
                        entries.add(ModItems.MINT_SHULKER_BOX);
                        entries.add(ModBlocks.MINT_CANDLE);

                        entries.add(ModItems.MINT_SPRIG);
                        entries.add(ModItems.MINT_COOKIE);
                        entries.add(ModBlocks.MINT_SPRIG_BLOCK);
                        entries.add(ModItems.MINT_TEA);
                        entries.add(ModBlocks.WILD_MINT);
                        entries.add(ModBlocks.MINT_BRICKS);
                        entries.add(ModBlocks.MINT_BRICK_SLAB);
                        entries.add(ModBlocks.MINT_BRICK_STAIRS);
                        //entries.add(ModBlocks.MINT_BRICK_WALL);
                    }).build());

    public static ItemGroup PEACH = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "peach"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".peach"))
                    .icon(() -> new ItemStack(ModItems.PEACH_DYE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.PEACH_DYE);
                        entries.add(ModBlocks.PEACH_WOOL);
                        entries.add(ModBlocks.PEACH_CARPET);
                        entries.add(ModBlocks.PEACH_CONCRETE);
                        entries.add(ModBlocks.PEACH_CONCRETE_POWDER);
                        entries.add(ModBlocks.PEACH_TERRACOTTA);
                        entries.add(ModBlocks.PEACH_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.PEACH_STAINED_GLASS);
                        entries.add(ModBlocks.PEACH_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.PEACH_BED);
                        entries.add(ModBlocks.PEACH_BANNER);
                        entries.add(ModBlocks.PEACH_SHULKER_BOX);
                        entries.add(ModBlocks.PEACH_CANDLE);

                        entries.add(ModBlocks.HYPERICUM);
                        entries.add(ModBlocks.PEACH_LOG);
                        entries.add(ModItems.PEACH);
                        entries.add(ModItems.PEACH_PIT);
                        entries.add(ModItems.PEACH_SLICE);
                        entries.add(ModItems.GOLDEN_PEACH);
                        entries.add(ModItems.PEACH_COBBLER);
                        entries.add(ModItems.FRUIT_SALAD);
                        entries.add(ModItems.AMBROSIA);
                        entries.add(ModItems.CORAL_ANEMONE);
                        entries.add(ModItems.COOKED_ANEMONE);
                        entries.add(ModItems.PEACH_BRANCH);
                    }).build());

    public static ItemGroup PERIWINKLE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "periwinkle"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".periwinkle"))
                    .icon(() -> new ItemStack(ModItems.PERIWINKLE_DYE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.PERIWINKLE_DYE);
                        entries.add(ModBlocks.PERIWINKLE_WOOL);
                        entries.add(ModBlocks.PERIWINKLE_CARPET);
                        entries.add(ModBlocks.PERIWINKLE_CONCRETE);
                        entries.add(ModBlocks.PERIWINKLE_CONCRETE_POWDER);
                        entries.add(ModBlocks.PERIWINKLE_TERRACOTTA);
                        entries.add(ModBlocks.PERIWINKLE_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.PERIWINKLE_STAINED_GLASS);
                        entries.add(ModBlocks.PERIWINKLE_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.PERIWINKLE_BED);
                        entries.add(ModBlocks.PERIWINKLE_BANNER);
                        entries.add(ModBlocks.PERIWINKLE_SHULKER_BOX);
                        entries.add(ModBlocks.PERIWINKLE_CANDLE);
                    }).build());

    public static ItemGroup ARTICHOKE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "artichoke"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".artichoke"))
                    .icon(() -> new ItemStack(ModItems.ARTICHOKE_DYE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.ARTICHOKE_DYE);
                        entries.add(ModBlocks.ARTICHOKE_WOOL);
                        entries.add(ModBlocks.ARTICHOKE_CARPET);
                        entries.add(ModBlocks.ARTICHOKE_CONCRETE);
                        entries.add(ModBlocks.ARTICHOKE_CONCRETE_POWDER);
                        entries.add(ModBlocks.ARTICHOKE_TERRACOTTA);
                        entries.add(ModBlocks.ARTICHOKE_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.ARTICHOKE_STAINED_GLASS);
                        entries.add(ModBlocks.ARTICHOKE_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.ARTICHOKE_BED);
                        entries.add(ModBlocks.ARTICHOKE_BANNER);
                        entries.add(ModBlocks.ARTICHOKE_SHULKER_BOX);
                        entries.add(ModBlocks.ARTICHOKE_CANDLE);
                    }).build());

    public static ItemGroup FUCHSIA = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "fuchsia"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".fuchsia"))
                    .icon(() -> new ItemStack(ModItems.FUCHSIA_DYE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.FUCHSIA_DYE);
                        entries.add(ModBlocks.FUCHSIA_WOOL);
                        entries.add(ModBlocks.FUCHSIA_CARPET);
                        entries.add(ModBlocks.FUCHSIA_CONCRETE);
                        entries.add(ModBlocks.FUCHSIA_CONCRETE_POWDER);
                        entries.add(ModBlocks.FUCHSIA_TERRACOTTA);
                        entries.add(ModBlocks.FUCHSIA_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.FUCHSIA_STAINED_GLASS);
                        entries.add(ModBlocks.FUCHSIA_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.FUCHSIA_BED);
                        entries.add(ModBlocks.FUCHSIA_BANNER);
                        entries.add(ModBlocks.FUCHSIA_SHULKER_BOX);
                        entries.add(ModBlocks.FUCHSIA_CANDLE);
                    }).build());

    public static ItemGroup VERMILION = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "vermilion"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".vermilion"))
                    .icon(() -> new ItemStack(ModItems.VERMILION_DYE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.VERMILION_DYE);
                        entries.add(ModBlocks.VERMILION_WOOL);
                        entries.add(ModBlocks.VERMILION_CARPET);
                        entries.add(ModBlocks.VERMILION_CONCRETE);
                        entries.add(ModBlocks.VERMILION_CONCRETE_POWDER);
                        entries.add(ModBlocks.VERMILION_TERRACOTTA);
                        entries.add(ModBlocks.VERMILION_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.VERMILION_STAINED_GLASS);
                        entries.add(ModBlocks.VERMILION_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.VERMILION_BED);
                        entries.add(ModBlocks.VERMILION_BANNER);
                        entries.add(ModBlocks.VERMILION_SHULKER_BOX);
                        entries.add(ModBlocks.VERMILION_CANDLE);
                    }).build());

    public static ItemGroup SHAMROCK = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "shamrock"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".shamrock"))
                    .icon(() -> new ItemStack(ModItems.SHAMROCK_DYE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.SHAMROCK_DYE);
                        entries.add(ModBlocks.SHAMROCK_WOOL);
                        entries.add(ModBlocks.SHAMROCK_CARPET);
                        entries.add(ModBlocks.SHAMROCK_CONCRETE);
                        entries.add(ModBlocks.SHAMROCK_CONCRETE_POWDER);
                        entries.add(ModBlocks.SHAMROCK_TERRACOTTA);
                        entries.add(ModBlocks.SHAMROCK_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.SHAMROCK_STAINED_GLASS);
                        entries.add(ModBlocks.SHAMROCK_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.SHAMROCK_BED);
                        entries.add(ModBlocks.SHAMROCK_BANNER);
                        entries.add(ModBlocks.SHAMROCK_SHULKER_BOX);
                        entries.add(ModBlocks.SHAMROCK_CANDLE);
                    }).build());

    public static ItemGroup INDIGO = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "indigo"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".indigo"))
                    .icon(() -> new ItemStack(ModItems.INDIGO_DYE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.INDIGO_DYE);
                        entries.add(ModBlocks.INDIGO_WOOL);
                        entries.add(ModBlocks.INDIGO_CARPET);
                        entries.add(ModBlocks.INDIGO_CONCRETE);
                        entries.add(ModBlocks.INDIGO_CONCRETE_POWDER);
                        entries.add(ModBlocks.INDIGO_TERRACOTTA);
                        entries.add(ModBlocks.INDIGO_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.INDIGO_STAINED_GLASS);
                        entries.add(ModBlocks.INDIGO_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.INDIGO_BED);
                        entries.add(ModBlocks.INDIGO_BANNER);
                        entries.add(ModBlocks.INDIGO_SHULKER_BOX);
                        entries.add(ModBlocks.INDIGO_CANDLE);
                    }).build());

    public static ItemGroup BANANA = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "banana"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".banana"))
                    .icon(() -> new ItemStack(ModItems.BANANA_DYE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.BANANA_DYE);
                        entries.add(ModBlocks.BANANA_WOOL);
                        entries.add(ModBlocks.BANANA_CARPET);
                        entries.add(ModBlocks.BANANA_CONCRETE);
                        entries.add(ModBlocks.BANANA_CONCRETE_POWDER);
                        entries.add(ModBlocks.BANANA_TERRACOTTA);
                        entries.add(ModBlocks.BANANA_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.BANANA_STAINED_GLASS);
                        entries.add(ModBlocks.BANANA_STAINED_GLASS_PANE);
                        entries.add(ModBlocks.BANANA_BED);
                        entries.add(ModBlocks.BANANA_BANNER);
                        entries.add(ModBlocks.BANANA_SHULKER_BOX);
                        entries.add(ModBlocks.BANANA_CANDLE);
                    }).build());
}
