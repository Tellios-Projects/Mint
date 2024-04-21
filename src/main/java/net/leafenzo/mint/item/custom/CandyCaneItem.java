package net.leafenzo.mint.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class CandyCaneItem extends Item {
    public CandyCaneItem(Settings settings) {
        super(settings);
    }
    private static final int MAX_USE_TIME = 14;
    public int getMaxUseTime(ItemStack stack) { //oddity: for some reason the vanilla game's milk bucket uses a hard coded value for this alongside a MAX_USE_TIME that are not fields of one another
        return 14;
    }

//    public UseAction getUseAction(ItemStack stack) {
//        return UseAction.EAT;
//    }
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        return ItemUsage.consumeHeldItem(world, user, hand);
//    }
}
