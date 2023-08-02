package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    //TODO

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
                        entries.add(ModBlocks.MINT_CANDLE_CAKE);

                        entries.add(ModBlocks.MINT_BRICKS);
                        entries.add(ModBlocks.MINT_BRICK_SLAB);
                        entries.add(ModBlocks.MINT_BRICK_STAIRS);
                        //entries.add(ModBlocks.MINT_BRICK_WALL);
                    }).build());

    public static void registerModItemGroups() {
        ModInit.LOGGER.info("Registering item groups for " + Super.MOD_ID);
    }

//    public static ItemGroup TEMPLATE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "template"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.template"))
//                    .icon(() -> new ItemStack(ModBlocks.)).entries((displayContext, entries) -> {
//                     //entries.add(ModBlocks.);
//                    }).build());
}
