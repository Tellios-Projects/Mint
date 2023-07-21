package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item MINT_SEEDS = registerItem("mint_seeds", new AliasedBlockItem(ModBlocks.MINT, new FabricItemSettings()));
    //public static final Item MINT_SEEDS = registerItem("mint_seeds", new AliasedBlockItem(ModBlocks.MINT, new FabricItemSettings().group(ModItemGroups.MINT))); //before 1.20

    public static final Item MINT_SPRIG = registerItem("mint_sprig", new MintSprigItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    //.hunger(0)
                    .saturationModifier(5.0f)
                    .build())));

    public static final Item MINT_DYE = registerItem("mint_dye", new DyeItem(DyeColor.BLACK, new FabricItemSettings()));


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
