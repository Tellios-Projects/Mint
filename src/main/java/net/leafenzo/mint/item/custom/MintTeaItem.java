package net.leafenzo.mint.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class MintTeaItem extends Item {
    public MintTeaItem(Settings settings) {
        super(settings);
    }

    private static final int MAX_USE_TIME = 28;
    public int getMaxUseTime(ItemStack stack) { //oddity: for some reason the vanilla game's milk bucket uses a hard coded value for this alongside a MAX_USE_TIME that are not fields of one another
        return 28;
    }
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
//        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
//            stack.decrement(1);
//        }
        if (!world.isClient) {
            //add 5 seconds of freezing per snack, maxing out at 25 seconds of freezing
            user.setFrozenTicks(user.getFrozenTicks() <= 500 ? user.getFrozenTicks() + 20 : user.getFrozenTicks());

            // cures stomach ailments
            user.removeStatusEffect(StatusEffects.POISON);
            user.removeStatusEffect(StatusEffects.NAUSEA);
            user.removeStatusEffect(StatusEffects.HUNGER);
        }
        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        if (user instanceof PlayerEntity playerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
            if (!playerEntity.getInventory().insertStack(itemStack)) {
                playerEntity.dropItem(itemStack, false);
            }
        }

        ItemStack result = super.finishUsing(stack, world, user); //super call is necessary for the .food builder in ModItems to work
        return result;
    }
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}

// https://github.com/SlimeKnights/TinkersConstruct/blob/99af4c56431f79f5679f0ba82e084fb875ae1a24/src/main/java/slimeknights/tconstruct/fluids/item/ContainerFoodItem.java
