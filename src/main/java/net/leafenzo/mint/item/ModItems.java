package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModItems {

    //MINT - Special
    public static final Item MINT_SPRIG = registerItem("mint_sprig", new MintSprigItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    //.hunger(0)
                    .saturationModifier(4.0f)
                    .build())));

    public static final Item MINT_COOKIE = registerItem("mint_cookie", new MintCookieItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(4)
                    .saturationModifier(0.8f) //double that of a vanilla game cookie
                    .build())));

    public static final Item MINT_TEA = registerItem("mint_tea", new MintTeaItem(new FabricItemSettings().maxCount(1)
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    //.hunger(0)
                    .saturationModifier(4.0f)
                    .build())));

    // Main
    public static final Item MINT_DYE = registerItem("mint_dye", new DyeItem(ModDyeColor.MINT, new FabricItemSettings()));
    public static final Item MINT_BED = registerItem(new BedItem(ModBlocks.MINT_BED, new Item.Settings().maxCount(1)));
    public static final Item MINT_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.MINT_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item MINT_BANNER = registerItem(new BannerItem(ModBlocks.MINT_BANNER, ModBlocks.MINT_WALL_BANNER, new Item.Settings().maxCount(16)));
    public static final Item PEACH_DYE = registerItem("peach_dye", new DyeItem(ModDyeColor.PEACH, new FabricItemSettings()));
    public static final Item PEACH_BED = registerItem(new BedItem(ModBlocks.PEACH_BED, new Item.Settings().maxCount(1)));
    public static final Item PEACH_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.PEACH_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item PEACH_BANNER = registerItem(new BannerItem(ModBlocks.PEACH_BANNER, ModBlocks.PEACH_WALL_BANNER, new Item.Settings().maxCount(16)));
    public static final Item PERIWINKLE_DYE = registerItem("periwinkle_dye", new DyeItem(ModDyeColor.PERIWINKLE, new FabricItemSettings()));
    public static final Item PERIWINKLE_BED = registerItem(new BedItem(ModBlocks.PERIWINKLE_BED, new Item.Settings().maxCount(1)));
    public static final Item PERIWINKLE_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.PERIWINKLE_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item PERIWINKLE_BANNER = registerItem(new BannerItem(ModBlocks.PERIWINKLE_BANNER, ModBlocks.PERIWINKLE_WALL_BANNER, new Item.Settings().maxCount(16)));
    public static final Item ARTICHOKE_DYE = registerItem("artichoke_dye", new DyeItem(ModDyeColor.ARTICHOKE, new FabricItemSettings()));
    public static final Item ARTICHOKE_BED = registerItem(new BedItem(ModBlocks.ARTICHOKE_BED, new Item.Settings().maxCount(1)));
    public static final Item ARTICHOKE_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.ARTICHOKE_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item ARTICHOKE_BANNER = registerItem(new BannerItem(ModBlocks.ARTICHOKE_BANNER, ModBlocks.ARTICHOKE_WALL_BANNER, new Item.Settings().maxCount(16)));

    public static final Item FUCHSIA_DYE = registerItem("fuchsia_dye", new DyeItem(ModDyeColor.FUCHSIA, new FabricItemSettings()));
    public static final Item FUCHSIA_BED = registerItem(new BedItem(ModBlocks.FUCHSIA_BED, new Item.Settings().maxCount(1)));
    public static final Item FUCHSIA_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.FUCHSIA_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item FUCHSIA_BANNER = registerItem(new BannerItem(ModBlocks.FUCHSIA_BANNER, ModBlocks.FUCHSIA_WALL_BANNER, new Item.Settings().maxCount(16)));
    public static final Item VERMILION_DYE = registerItem("vermilion_dye", new DyeItem(ModDyeColor.VERMILION, new FabricItemSettings()));
    public static final Item VERMILION_BED = registerItem(new BedItem(ModBlocks.VERMILION_BED, new Item.Settings().maxCount(1)));
    public static final Item VERMILION_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.VERMILION_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item VERMILION_BANNER = registerItem(new BannerItem(ModBlocks.VERMILION_BANNER, ModBlocks.VERMILION_WALL_BANNER, new Item.Settings().maxCount(16)));
    public static final Item SHAMROCK_DYE = registerItem("shamrock_dye", new DyeItem(ModDyeColor.SHAMROCK, new FabricItemSettings()));
    public static final Item SHAMROCK_BED = registerItem(new BedItem(ModBlocks.SHAMROCK_BED, new Item.Settings().maxCount(1)));
    public static final Item SHAMROCK_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.SHAMROCK_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item SHAMROCK_BANNER = registerItem(new BannerItem(ModBlocks.SHAMROCK_BANNER, ModBlocks.SHAMROCK_WALL_BANNER, new Item.Settings().maxCount(16)));
    public static final Item INDIGO_DYE = registerItem("indigo_dye", new DyeItem(ModDyeColor.INDIGO, new FabricItemSettings()));
    public static final Item INDIGO_BED = registerItem(new BedItem(ModBlocks.INDIGO_BED, new Item.Settings().maxCount(1)));
    public static final Item INDIGO_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.INDIGO_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item INDIGO_BANNER = registerItem(new BannerItem(ModBlocks.INDIGO_BANNER, ModBlocks.INDIGO_WALL_BANNER, new Item.Settings().maxCount(16)));

    public static final Item BANANA_DYE = registerItem("banana_dye", new DyeItem(ModDyeColor.BANANA, new FabricItemSettings()));
    public static final Item BANANA_BED = registerItem(new BedItem(ModBlocks.BANANA_BED, new Item.Settings().maxCount(1)));
    public static final Item BANANA_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.BANANA_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item BANANA_BANNER = registerItem(new BannerItem(ModBlocks.BANANA_BANNER, ModBlocks.BANANA_WALL_BANNER, new Item.Settings().maxCount(16)));

    // Arrays
    public static final Item[] DYE_ITEMS = { MINT_DYE, PEACH_DYE, PERIWINKLE_DYE, ARTICHOKE_DYE, FUCHSIA_DYE, VERMILION_DYE, SHAMROCK_DYE, INDIGO_DYE, BANANA_DYE };

//    public static Item getDyeItemByColor(DyeColor color) {
//        if (color.equals(ModDyeColor.MINT)) {
//            return MINT_DYE;
//        } else if (color.equals(ModDyeColor.PEACH)) {
//            return PEACH_DYE;
//        } else if (color.equals(ModDyeColor.PERIWINKLE)) {
//            return PERIWINKLE_DYE;
//        } else if (color.equals(ModDyeColor.ARTICHOKE)) {
//            return ARTICHOKE_DYE;
//        } else if (color.equals(ModDyeColor.FUCHSIA)) {
//            return FUCHSIA_DYE;
//        } else if (color.equals(ModDyeColor.VERMILION)) {
//            return VERMILION_DYE;
//        } else if (color.equals(ModDyeColor.SHAMROCK)) {
//            return SHAMROCK_DYE;
//        } else if (color.equals(ModDyeColor.INDIGO)) {
//            return INDIGO_DYE;
//        } else if (color.equals(ModDyeColor.BANANA)) {
//            return BANANA_DYE;
//        }
//        return DyeItem.byColor(color); // now we know it's vanilla, so it's safe to pass through
//    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), (Item)item);
    }

    private static Item registerItem(BlockItem item) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(item.getBlock()), (Item)item);
    }

    private static Item registerItem(Block block, Item item) {
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), (Item)item);
    }

    public static void registerModItems() {
        ModInit.LOGGER.info("Registering Mod Items for " + Super.MOD_ID);
        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }}

//    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
//        entries.add(MINT_SEEDS);
//    }
