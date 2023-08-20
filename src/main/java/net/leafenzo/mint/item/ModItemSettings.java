package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.leafenzo.mint.registries.ModFabricRegistries;
import net.minecraft.item.Item;

public class ModItemSettings extends FabricItemSettings {

    public ModItemSettings compostingChance(Item item, float chance) {
        ModFabricRegistries.registerCompostableItem(item, chance);
        return this;
    }
}
