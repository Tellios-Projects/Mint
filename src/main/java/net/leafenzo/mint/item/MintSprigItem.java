package net.leafenzo.mint.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MintSprigItem extends Item {
    public MintSprigItem(Settings settings) {
        super(settings);
    }

    private static final int MAX_USE_TIME = 24;
    public int getMaxUseTime(ItemStack stack) {
        return 24;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            stack.decrement(1);
        }
        if (!world.isClient) {
            //add 5 seconds of freezing per snack, maxing out 25 seconds of freezing
            user.setFrozenTicks(user.getFrozenTicks() <= 500 ? user.getFrozenTicks() + 100 : user.getFrozenTicks());

            // increasing chance of curing certain ailments
            if(Random.create().nextFloat() < 0.25f + (0.75f / user.getFrozenTicks())) {
                user.removeStatusEffect(StatusEffects.POISON);
                user.removeStatusEffect(StatusEffects.NAUSEA);
                user.removeStatusEffect(StatusEffects.HUNGER);
            }
        }

        ItemStack result = super.finishUsing(stack, world, user); //super call is necessary for the .food builder in ModItems to work
        return result;
    }
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}

// https://github.com/SlimeKnights/TinkersConstruct/blob/99af4c56431f79f5679f0ba82e084fb875ae1a24/src/main/java/slimeknights/tconstruct/fluids/item/ContainerFoodItem.java
