package net.leafenzo.mint.item;

import net.leafenzo.mint.particle.ModParticleTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class LavenderSoapItem extends Item {
//    private final static int brushesNeededToClean = 5;
//    private int brushesCount;
//    private long nextParticleTime;
//    private static final double MAX_SOAPING_DISTANCE = Math.sqrt(ServerPlayNetworkHandler.MAX_BREAK_SQUARED_DISTANCE) - 1.0;

    public LavenderSoapItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof PassiveEntity passiveEntity) {
            World world = user.getWorld();

            //Summon bubble effects
            addParticles(world, user, entity);

            //Play sfx
            if (!user.isSilent()) {
                world.playSound(null, entity.getBlockPos(), SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundCategory.PLAYERS, 3.0f, 1.0f);
            }

            //Help grow up
            if(passiveEntity.isBaby()) {
            int age = (int)((float)(-passiveEntity.getBreedingAge()) / 20 * 0.33f); // thirds the time it will take them to grow up (but can't be spammed due to happiness cooldown, I think.)
            passiveEntity.growUp(age, true);
            }

            //Damage soap
            if (!((PlayerEntity)user).getAbilities().creativeMode) {
                stack.damage(1, user, playerx -> playerx.sendToolBreakStatus(hand));
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    private void addParticles(World world, LivingEntity user, Entity entity) {
        if (world.isClient) {
            Random random = world.getRandom();
            int bubblesCount = random.nextBetween(3, 5);
            int sudsCount = random.nextBetween(5, 8);
            for (int i = 0; i < bubblesCount; i++) { // Bubbles
                double xPos = random.nextGaussian() * 0.15;
                double ySpeed = 0.1;
                double zPos = random.nextGaussian() * 0.15;
                world.addParticle(ModParticleTypes.SOAP_BUBBLE, entity.getParticleX(1.0) + xPos, entity.getRandomBodyY() + 0.5, entity.getParticleZ(1.0) + zPos, 0, ySpeed, 0);
            }
            for (int i = 0; i < sudsCount; i++) { // Spinning Suds
                double xPos = random.nextGaussian() * 0.005;
                double ySpeed = 0.0f;
                double zPos = random.nextGaussian() * 0.005;
                world.addParticle(ModParticleTypes.SOAP_SPLASH, entity.getParticleX(1.0) + xPos, entity.getRandomBodyY(), entity.getParticleZ(1.0) + zPos, 0, ySpeed, 0);
            }
        }
    }

//        @Override
//    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
//        brushesCount = brushesNeededToClean; // Reset
////        targetEntity = entity;
//        return super.useOnEntity(stack, user, entity, hand);
//    }
//
//    @Override
//    public UseAction getUseAction(ItemStack stack) {
//        return UseAction.BRUSH;
//    }
//
//    @Override
//    public int getMaxUseTime(ItemStack stack) {
//        return 200;
//    }
//
//    @Override
//    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
//        PlayerEntity playerEntity = (PlayerEntity) user;
//        HitResult hitResult = this.getHitResult(user);
//        if (!(hitResult instanceof EntityHitResult entityHitResult) || hitResult.getType() != HitResult.Type.ENTITY) {
//            user.stopUsingItem();
//            return;
//        }
//
//        int i = this.getMaxUseTime(stack) - remainingUseTicks + 1;
//        boolean bl = i % 10 == 5;
//        if(bl) {
//            Entity entity = entityHitResult.getEntity();
//            this.addParticles(world, user, entity);
//            world.playSound(playerEntity, entity.getBlockPos(), SoundEvents.ITEM_BRUSH_BRUSHING_SAND, SoundCategory.PLAYERS);
//            if (entity instanceof PassiveEntity) {
//                //Grow
////                entity.kill();
//            }
//
//            if (!world.isClient() && (entity instanceof PassiveEntity && (checkIfFinishedCleaning(world.getTime(), playerEntity)))) {
//                EquipmentSlot equipmentSlot = stack.equals(playerEntity.getEquippedStack(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
//                stack.damage(1, user, userx -> userx.sendEquipmentBreakStatus(equipmentSlot));
//            }
//        }
//        super.usageTick(world, user, stack, remainingUseTicks);
//    }
//    public boolean checkIfFinishedCleaning(long worldTime, PlayerEntity player) {
//        nextParticleTime = worldTime + 40L;
//        if (worldTime < nextParticleTime || !(player.getWorld() instanceof ServerWorld)) {
//            return false;
//        }
//        nextParticleTime = worldTime + 10L;
//        if (++this.brushesCount >= brushesNeededToClean) {
//            this.brushesCount = 0;
//            return true;
//        }
//        return false;
//    }
//    private HitResult getHitResult(LivingEntity user) {
//        return ProjectileUtil.getCollision(user, entity -> !entity.isSpectator() && entity.canHit(), MAX_SOAPING_DISTANCE);
//    }
}
