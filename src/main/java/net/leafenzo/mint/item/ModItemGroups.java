package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static void registerModItemGroups() {
        ModInit.LOGGER.debug("Registering item groups for " + Super.MOD_ID);
        modifyVanillaItemGroupEntries();
    }

    //keep in the right places to make a big rainbow ^~^ in conjunction with the other dye mods
    public static void modifyVanillaItemGroupEntries() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            content.addAfter(Blocks.GREEN_WOOL, ModBlocks.MINT_WOOL);
            content.addAfter(Blocks.GREEN_CARPET, ModBlocks.MINT_CARPET);
            content.addAfter(Blocks.GREEN_TERRACOTTA, ModBlocks.MINT_TERRACOTTA);
            content.addAfter(Blocks.GREEN_CONCRETE, ModBlocks.MINT_CONCRETE);
            content.addAfter(Blocks.GREEN_CONCRETE_POWDER, ModBlocks.MINT_CONCRETE_POWDER);
            content.addAfter(Blocks.GREEN_GLAZED_TERRACOTTA, ModBlocks.MINT_GLAZED_TERRACOTTA);
            content.addAfter(Blocks.GREEN_STAINED_GLASS, ModBlocks.MINT_STAINED_GLASS);
            content.addAfter(Blocks.GREEN_STAINED_GLASS_PANE, ModBlocks.MINT_STAINED_GLASS_PANE);
            content.addAfter(Blocks.GREEN_SHULKER_BOX, ModBlocks.MINT_SHULKER_BOX);
            content.addAfter(Blocks.GREEN_BED, ModBlocks.MINT_BED);
            content.addAfter(Blocks.GREEN_CANDLE, ModBlocks.MINT_CANDLE);
            content.addAfter(Blocks.GREEN_BANNER, ModBlocks.MINT_BANNER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.GREEN_DYE, ModItems.MINT_DYE);
        });

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

    public static ItemGroup MINT = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "mint"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mint"))
                    .icon(() -> new ItemStack(ModItems.MINT_SPRIG)).entries((displayContext, entries) -> {
                        entries.add(ModItems.MINT_SPRIG);
                        entries.add(ModItems.MINT_DYE);
                        entries.add(ModItems.MINT_COOKIE);
                        entries.add(ModItems.MINT_TEA);
                        entries.add(ModBlocks.WILD_MINT);
                        entries.add(ModBlocks.MINT_WOOL);
                        entries.add(ModBlocks.MINT_CARPET);
                        entries.add(ModBlocks.MINT_CONCRETE);
                        entries.add(ModBlocks.MINT_CONCRETE_POWDER);
                        entries.add(ModBlocks.MINT_TERRACOTTA);
                        entries.add(ModBlocks.MINT_GLAZED_TERRACOTTA);
                        entries.add(ModBlocks.MINT_SPRIG_BLOCK);
                        entries.add(ModBlocks.MINT_STAINED_GLASS);
                        entries.add(ModBlocks.MINT_STAINED_GLASS_PANE);
                        entries.add(ModItems.MINT_BED);
                        entries.add(ModItems.MINT_BANNER);
                        entries.add(ModItems.MINT_SHULKER_BOX);
                        entries.add(ModBlocks.MINT_CANDLE);
                        entries.add(ModBlocks.MINT_BRICKS);
                        entries.add(ModBlocks.MINT_BRICK_SLAB);
                        entries.add(ModBlocks.MINT_BRICK_STAIRS);
                        //entries.add(ModBlocks.MINT_BRICK_WALL);

                    }).build());

//    public static ItemGroup TEMPLATE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "template"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.template"))
//                    .icon(() -> new ItemStack(ModBlocks.)).entries((displayContext, entries) -> {
//                     //entries.add(ModBlocks.);
//                    }).build());
}
