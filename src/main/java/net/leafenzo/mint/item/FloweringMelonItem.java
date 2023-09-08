package net.leafenzo.mint.item;

import net.leafenzo.mint.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class FloweringMelonItem extends Item {
    public FloweringMelonItem(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof PassiveEntity passiveEntity) {
            passiveEntity.setBaby(true);
            // TODO MAKEME
            // Make them never grow up
//            passiveEntity.setBreedingAge(-999999999); // doesn't work
//
//            passiveEntity.addStatusEffect(new StatusEffectInstance(ModEffects.MINT_CHILL, 100, 1, true, false, false));

            //passiveEntity.writeNbt(new NbtCompound()).putInt("ForcedAge", -99999); //TODO REPLACEME WITH SOMETHING THAT ACTUALLY WORKS

            if (!((PlayerEntity)user).getAbilities().creativeMode) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
