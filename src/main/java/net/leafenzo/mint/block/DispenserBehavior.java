package net.leafenzo.mint.block;

import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.dispenser.BlockPlacementDispenserBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;

public class DispenserBehavior {
    public static void RegisterDispenserBehaviors() {
        ModInit.LOGGER.debug("Registering dispenser behaviors for " + Super.MOD_ID);

        //Directly get the blockItem for each one instead of using "ShulkerBoxBlock.get(dyeColor)"
        for(Block block : ModBlocks.SHULKER_BOX_BLOCKS) { DispenserBlock.registerBehavior(block.asItem(), new BlockPlacementDispenserBehavior()); }
    }
}
