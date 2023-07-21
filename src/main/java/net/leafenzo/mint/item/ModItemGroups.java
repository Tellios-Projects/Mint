package net.leafenzo.mint.item;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.leafenzo.mint.Super;
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
                    .icon(() -> new ItemStack(ModItems.MINT_SEEDS)).entries((displayContext, entries) -> {
                     entries.add(ModItems.MINT_SEEDS);
                     entries.add(ModItems.MINT_SPRIG);
                     entries.add(ModItems.MINT_DYE);
                    }).build());


//    public static ItemGroup TEMPLATE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "template"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.template"))
//                    .icon(() -> new ItemStack(ModBlocks.)).entries((displayContext, entries) -> {
//                     //entries.add(ModBlocks.);
//                    }).build());
}
