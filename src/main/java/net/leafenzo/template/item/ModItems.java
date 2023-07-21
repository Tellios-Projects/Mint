package net.leafenzo.template.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leafenzo.template.ModInit;
import net.leafenzo.template.Super;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    //public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));
    //public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        //entries.add(RUBY);
        //entries.add(RAW_RUBY);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ModInit.LOGGER.info("Registering Mod Items for " + Super.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }}
