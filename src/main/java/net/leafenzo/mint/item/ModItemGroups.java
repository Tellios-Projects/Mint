package net.leafenzo.mint.item;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
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
                     entries.add(ModBlocks.MINT_CONCRETE);
                     entries.add(ModBlocks.MINT_WOOL);
                     entries.add(ModBlocks.MINT_STAINED_GLASS);
                     entries.add(ModBlocks.MINT_TERRACOTTA);
                     entries.add(ModBlocks.MINT_GLAZED_TERRACOTTA);

                    }).build());


//    public static ItemGroup TEMPLATE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "template"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.template"))
//                    .icon(() -> new ItemStack(ModBlocks.)).entries((displayContext, entries) -> {
//                     //entries.add(ModBlocks.);
//                    }).build());
}
