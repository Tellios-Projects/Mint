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
import net.minecraft.util.math.random.Random;
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
            Random random = entity.getRandom();

            if (!user.isSilent() || entity.isSilent()) {
                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.NEUTRAL, 1.0f, 0.4f);
                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_STRIDER_EAT, SoundCategory.NEUTRAL, 1.2f, 0.7f);
            }
            
            for (int i = 0; i < 8; i++) {
                double xPos = random.nextGaussian() * 0.4;
                double ySpeed = -0.5;
                double zPos = random.nextGaussian() * 0.4;
                world.addParticle(ParticleTypes.CRIT, entity.getParticleX(1.0) + xPos, entity.getRandomBodyY() + 0.5, entity.getParticleZ(1.0) + zPos, 0, ySpeed, 0);
            }

            if (!((PlayerEntity) user).getAbilities().creativeMode) {
                stack.decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
