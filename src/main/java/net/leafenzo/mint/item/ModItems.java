package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
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

    //TODO - figure out what's up with slapping the sheep with the same color over and over again?
    // ...is it something to do with <sheepEntity.getColor() != this.color> going off of .byId?
    public static final Item MINT_DYE = registerItem("mint_dye", new DyeItem(ModDyeColor.MINT, new FabricItemSettings()));

    public static final Item MINT_BED = registerItem("mint_bed", new BedItem(ModBlocks.MINT_BED, new Item.Settings().maxCount(1)));
    public static final Item MINT_SHULKER_BOX = registerItem("mint_shulker_box", new BlockItem(ModBlocks.MINT_SHULKER_BOX, new Item.Settings().maxCount(1)));
    public static final Item MINT_BANNER = registerItem("mint_banner", new BannerItem(ModBlocks.MINT_BANNER, ModBlocks.MINT_WALL_BANNER, new Item.Settings().maxCount(16)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), item);
    }
    public static void registerModItems() {
        ModInit.LOGGER.info("Registering Mod Items for " + Super.MOD_ID);
        //ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }}

//    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
//        entries.add(MINT_SEEDS);
//    }
