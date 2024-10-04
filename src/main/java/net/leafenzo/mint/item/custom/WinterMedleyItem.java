package net.leafenzo.mint.item.custom;

import net.leafenzo.mint.effect.ModStatusEffects;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class WinterMedleyItem  extends Item {
    public WinterMedleyItem(Settings settings) {
        super(settings);
    }

    private static final int MAX_USE_TIME = 32;
    public int getMaxUseTime(ItemStack stack) { //oddity: for some reason the vanilla game's milk bucket uses a hard coded value for this alongside a MAX_USE_TIME that are not fields of one another
        return 32;
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
            user.addStatusEffect(new StatusEffectInstance(ModStatusEffects.MINT_CHILL, 150, 5));

            // steadily increasing chance of curing stomach ailments
            if(Random.create().nextFloat() < 0.25f + (0.75f * user.getFrozenTicks() / 380 )) { // 500 - (5 * 24) = 380
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

