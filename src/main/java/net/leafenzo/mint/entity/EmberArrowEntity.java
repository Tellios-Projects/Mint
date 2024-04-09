package net.leafenzo.mint.entity;

import net.leafenzo.mint.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class EmberArrowEntity extends PersistentProjectileEntity {

    public EmberArrowEntity(EntityType<? extends SpectralArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public EmberArrowEntity(World world, LivingEntity owner) {
        super(EntityType.SPECTRAL_ARROW, owner, world);
    }

    public EmberArrowEntity(World world, double x, double y, double z) {
        super(EntityType.SPECTRAL_ARROW, x, y, z, world);
    }



    public void tick() {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }

    }

    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.EMBER_ARROW);
    }

    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.setOnFire(true);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
    }
}
