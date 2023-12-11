/*
SOURCE:
LASER KNIGHTS - https://github.com/HyperCubeMC/Biome-Makeover/blob/fe700551beaba2930a58456f5ec63a3f1c60b339/src/main/java/party/lemons/biomemakeover/mixin/PassiveEntityMixin.java#L18
 */


package net.leafenzo.mint.mixin;

import net.leafenzo.mint.entity.IStuntable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PassiveEntity.class)
public abstract class PassiveEntityMixin extends PathAwareEntity implements IStuntable {
//    @Unique
//    private static final TrackedData<Boolean> STUNTED = DataTracker.registerData(PassiveEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Shadow @Final private static TrackedData<Boolean> CHILD;
    @Shadow protected int breedingAge;
    @Shadow public abstract boolean isReadyToBreed();
    @Unique private boolean isStunted = false;

    protected PassiveEntityMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world); ///TODO uhhhh testme. I worry for where the datatracker actually IS // dawg. past me. i don't know wtf you meant by this.
    }

    @Inject(at = @At("HEAD"), method = "isBaby", cancellable = true)
    private void isBaby(CallbackInfoReturnable<Boolean> cbi)
    {
        if(isStunted()) cbi.setReturnValue(true);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeCustomDataToTag(NbtCompound nbt, CallbackInfo ci)
    {
        nbt.putBoolean("IsStunted", this.isStunted);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void readCustomDataFromTag(NbtCompound nbt, CallbackInfo cbi)
    {
        if(nbt.contains("IsStunted")) isStunted = nbt.getBoolean("IsStunted");
    }

    @Inject(method = "getBreedingAge", at = @At("HEAD"), cancellable = true)
    public void getBreedingAge(CallbackInfoReturnable<Integer> cir)
    {
        if(isStunted()) {
            cir.setReturnValue(-6000);
            dataTracker.set(CHILD, true);
        }
    }

    @Inject(at = @At("HEAD"), method = "setBreedingAge", cancellable = true)
    public void setBreedingAge(int age, CallbackInfo cbi)
    {
        if(isStunted()) {
            breedingAge = -6000;
            dataTracker.set(CHILD, true);
            cbi.cancel();
        }
    }

    @Unique
    public boolean isStunted() {
        return isStunted;
    }

    @Unique
    public void setStunted(boolean stunted) {
        this.isStunted = stunted;
    }


//    @Inject(method = "setBaby", at = @At("RETURN"))
//    private static void setBaby(boolean baby, CallbackInfo ci) {
//        if(!baby) { //if being grown up with a command
//
//        }
//    }
}
