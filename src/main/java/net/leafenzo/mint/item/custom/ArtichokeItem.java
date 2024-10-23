package net.leafenzo.mint.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class ArtichokeItem extends AliasedBlockItem {
    public ArtichokeItem(Block block, Settings settings) {
        super(block, settings);
    }

    private static final int maxUseTime = 32;
    private static final int delay = 2;
    private static final int graceTicks = 16;
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        //This is stupid, but whatever it works
        if (remainingUseTicks % delay == delay-1 && remainingUseTicks < graceTicks)
            if (!world.isClient && !((PlayerEntity) user).getAbilities().creativeMode) {
                user.damage(world.getDamageSources().cactus(), 2);
            }
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        ItemStack result = super.finishUsing(stack, world, user); //super call is necessary for the .food builder in ModItems to work
        return result;
    }

    public int getMaxUseTime(ItemStack stack) { //oddity: for some reason the vanilla game's milk bucket uses a hard coded value for this alongside a MAX_USE_TIME that are not fields of one another
        return maxUseTime;
    }
}
