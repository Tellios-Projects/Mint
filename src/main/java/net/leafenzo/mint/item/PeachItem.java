package net.leafenzo.mint.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apache.http.annotation.Obsolete;

/**
 * Eliza doesn't like the idea of peaches clogging up the players inventory, so it should go unused unless she changes her mind.
 */
@Obsolete
public class PeachItem
extends Item {
    public PeachItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (user instanceof PlayerEntity && ((PlayerEntity)user).getAbilities().creativeMode) {
            return itemStack;
        }
        return new ItemStack(ModItems.PEACH_PIT);
    }
}
