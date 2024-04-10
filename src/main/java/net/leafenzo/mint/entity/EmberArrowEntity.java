package net.leafenzo.mint.entity;

import net.leafenzo.mint.block.EmberBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class EmberArrowEntity extends PersistentProjectileEntity {
    private boolean hasPlacedEmber = false;


    public EmberArrowEntity(World world, LivingEntity owner) {
        super(ModEntityTypes.EMBER_ARROW, owner, world);
    }

    public EmberArrowEntity(World world, double x, double y, double z) {
        super(ModEntityTypes.EMBER_ARROW, x, y, z, world);
    }

    public EmberArrowEntity(EntityType<EmberArrowEntity> emberArrowEntityEntityType, World world) {
        super(emberArrowEntityEntityType, world);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = getWorld();
        BlockPos pos = blockHitResult.getBlockPos().offset(blockHitResult.getSide());
        if (world.getBlockState(pos).isAir()) {
            world.setBlockState(blockHitResult.getBlockPos().offset(blockHitResult.getSide()), ModBlocks.EMBER.getDefaultState().with(EmberBlock.getProperty(blockHitResult.getSide().getOpposite()), true));
        } else if (world.getBlockState(pos).isOf(ModBlocks.EMBER)) {
            BlockState prevState = world.getBlockState(pos);
            world.setBlockState(pos, ModBlocks.EMBER.getStateWithProperties(prevState).with(EmberBlock.getProperty(blockHitResult.getSide().getOpposite()), true));
        } else if (world.getBlockState(pos).getFluidState().isEqualAndStill(Fluids.WATER)) {
            world.setBlockState(pos, ModBlocks.EMBER.getDefaultState().with(EmberBlock.getProperty(blockHitResult.getSide().getOpposite()), true).with(Properties.WATERLOGGED, true));
        }
        hasPlacedEmber = true;
        super.onBlockHit(blockHitResult);
    }

    public void tick() {
        super.tick();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
        if (!this.inGround) {
            hasPlacedEmber = false;
        }
    }

    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.EMBER_ARROW);
    }

    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.setOnFireFor(8);
    }
}
