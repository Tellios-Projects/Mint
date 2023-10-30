package net.leafenzo.mint.item;

import net.leafenzo.mint.entity.IStuntable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class FloweringMelonItem extends Item {
    public FloweringMelonItem(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof PassiveEntity passiveEntity && passiveEntity.isBaby() && !((IStuntable) passiveEntity).isStunted()) {
            ((IStuntable) passiveEntity).setStunted(true);
            World world = entity.getWorld();
            if (!user.isSilent() || entity.isSilent()) {
                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_STRIDER_EAT, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            }
            world.addImportantParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, entity.getX(), entity.getY(), entity.getZ(), 0.0, -0.2, 0.0);
            //NetworkUtil.doEntityParticle(user.world, ParticleTypes.WARPED_SPORE, entity, 15, 0.2F);
            if (!((PlayerEntity) user).getAbilities().creativeMode) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
