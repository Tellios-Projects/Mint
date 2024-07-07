package net.leafenzo.mint.item.custom;

import net.leafenzo.mint.entity.GasBombProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GasBombItem extends Item {
    public GasBombItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.6f, 0.9f + world.random.nextFloat() / 4);
        if (!world.isClient) {
            GasBombProjectileEntity gasBombProjectileEntity = new GasBombProjectileEntity(user, world);
            gasBombProjectileEntity.setItem(itemStack);
            gasBombProjectileEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, 0.8f, 2f);
            world.spawnEntity(gasBombProjectileEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.isCreative()) {
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}