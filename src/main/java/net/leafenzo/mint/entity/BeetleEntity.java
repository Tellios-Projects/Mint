//package net.leafenzo.mint.entity;
//
//import net.leafenzo.mint.registry.tag.ModTags;
//import net.leafenzo.mint.sound.ModSoundEvents;
//import net.minecraft.advancement.criterion.Criteria;
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.*;
//import net.minecraft.entity.ai.goal.*;
//import net.minecraft.entity.attribute.DefaultAttributeContainer;
//import net.minecraft.entity.attribute.EntityAttributes;
//import net.minecraft.entity.damage.DamageSource;
//import net.minecraft.entity.data.DataTracker;
//import net.minecraft.entity.data.TrackedData;
//import net.minecraft.entity.data.TrackedDataHandlerRegistry;
//import net.minecraft.entity.effect.StatusEffects;
//import net.minecraft.entity.passive.AnimalEntity;
//import net.minecraft.entity.passive.PassiveEntity;
//import net.minecraft.entity.passive.PigEntity;
//import net.minecraft.entity.passive.WolfEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.recipe.Ingredient;
//import net.minecraft.registry.tag.DamageTypeTags;
//import net.minecraft.registry.tag.EntityTypeTags;
//import net.minecraft.server.network.ServerPlayerEntity;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.sound.SoundEvent;
//import net.minecraft.sound.SoundEvents;
//import net.minecraft.stat.Stats;
//import net.minecraft.state.property.BooleanProperty;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.Hand;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.apache.http.annotation.Obsolete;
//import org.jetbrains.annotations.Nullable;
//import software.bernie.geckolib.animatable.GeoEntity;
//import software.bernie.geckolib.core.animatable.GeoAnimatable;
//import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
//import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
//import software.bernie.geckolib.core.animation.*;
//import software.bernie.geckolib.core.animation.AnimationState;
//import software.bernie.geckolib.core.object.PlayState;
//
//import java.util.Properties;
//
//public class BeetleEntity extends PassiveEntity implements GeoEntity {
//    //    private static final TrackedData<Float> SHELL_HEALTH = DataTracker.registerData(BeetleEntity.class, TrackedDataHandlerRegistry.FLOAT);
////    private final ShellComponent shellComponent;
//    private final AnimatableInstanceCache animatableInstanceCache = new SingletonAnimatableInstanceCache(this);
//
//    public BeetleEntity(EntityType<? extends PassiveEntity> entityType, World world) {
//        super((EntityType<? extends AnimalEntity>)entityType, world);
//        //this.shellComponent = new ShellComponent(this.dataTracker, SHELL_HEALTH);
//    }
//
//    @Override
//    public double getTick(Object o) {
//        return 0;
//    }
//
//    public static final float getDefaultMaxHealth() {
//        return 4;
//    }
//    public static final float getDefaultArmor() {
//        return 1;
//    }
//    public static final float getDefaultArmorToughness() {
//        return 4; // big shell :3
//    }
//    public static final float getDefaultMovementSpeed() {
//        return 0.25f;
//    }
//
//    public static DefaultAttributeContainer.Builder setAttributes() {
//        return AnimalEntity.createMobAttributes() //TODO Test healths
//                .add(EntityAttributes.GENERIC_MAX_HEALTH, BeetleEntity.getDefaultMaxHealth())
//                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, BeetleEntity.getDefaultMovementSpeed())
//                .add(EntityAttributes.GENERIC_ARMOR, BeetleEntity.getDefaultArmor())
//                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, BeetleEntity.getDefaultArmorToughness())
//                ;
//    }
//
//    @Override
//    protected void initGoals() {
//        //No swim goal cuz they can't swim
//        this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25));
//        this.goalSelector.add(1, new TemptGoal(this, 4f, Ingredient.fromTag(ModTags.Items.MUSHROOMS), false)); //TODO Test speeds
//        this.goalSelector.add(2, new WanderAroundGoal(this, 0.25f, 1));
//        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
//        this.goalSelector.add(4, new LookAroundGoal(this));
//    }
//
//
//    //    @Override
////    public void writeCustomDataToNbt(NbtCompound nbt) {
////        super.writeCustomDataToNbt(nbt);
////        this.shellComponent.writeNbt(nbt);
////    }
////    @Override
////    public void readCustomDataFromNbt(NbtCompound nbt) {
////        super.readCustomDataFromNbt(nbt);
////        this.shellComponent.readNbt(nbt);
////    }
//
////    public void damageShell(DamageSource source, float amount) {
////        float health = this.shellComponent.getShellHealth();
////        if(health <= 0) {
////            // it's already broken
////            return;
////        }
////
////        health -= amount;
////        if(health <= 0) {
////            // if it's just been broken
////            breakShell(source);
////            return;
////        }
////
////        // if it's just been damaged
////        dataTracker.set(this.SHELL_HEALTH, health);
////    }
////    public void breakShell(DamageSource source) {
////        dataTracker.set(this.SHELL_HEALTH, 0f);
////        playShellBrokenSound(source);
////    }
////    @Override
////    public boolean damage(DamageSource source, float amount) {
////        if(!source.isIndirect()) {
////            damageShell(source, amount);
////            amount -= shellComponent.getShellHealth();
////        }
////        super.damage(source, amount);
////        return false; // won't ever be called but whatever
////    }
////    protected void playShellBrokenSound(DamageSource source) {
////        SoundEvent soundEvent = this.getHurtSound(source);
////        if (soundEvent != null) {
////            this.playSound(soundEvent, this.getSoundVolume(), this.getSoundPitch());
////        }
////    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        return ModSoundEvents.ENTITY_BEETLE_AMBIENT;
//    }
//    @Override
//    protected SoundEvent getDeathSound() {
//        return ModSoundEvents.ENTITY_BEETLE_DEATH;
//    }
//    @Override
//    protected SoundEvent getHurtSound(DamageSource source) {
//        return ModSoundEvents.ENTITY_BEETLE_HURT;
//    }
//
//    @Override
//    protected void playHurtSound(DamageSource source) {
//        SoundEvent soundEvent = this.getHurtSound(source);
//        if (soundEvent != null) {
//            this.playSound(soundEvent, this.getSoundVolume(), this.getSoundPitch());
//        }
//    }
//    @Override
//    protected void playStepSound(BlockPos pos, BlockState state) {
//        this.playSound(ModSoundEvents.ENTITY_BEETLE_STEP, 0.15f, 1.0f);
//    }
//
//    @Nullable
//    @Override
//    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
//        return ModEntityTypes.BEETLE.create(world);
//    }
//
//    @Override
//    public AnimatableInstanceCache getAnimatableInstanceCache() {
//        return animatableInstanceCache;
//    }
//    @Override
//    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
//        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
//    }
//
//    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
//        if(tAnimationState.isMoving()) {
//            tAnimationState.getController().setAnimation(RawAnimation.begin().then("beetle.walk", Animation.LoopType.LOOP));
//        }
//        tAnimationState.getController().setAnimation(RawAnimation.begin().then("beetle.idle", Animation.LoopType.LOOP));
//        return PlayState.CONTINUE;
//    }
//
//}