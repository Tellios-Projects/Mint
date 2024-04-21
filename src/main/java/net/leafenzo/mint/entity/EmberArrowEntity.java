package net.leafenzo.mint.entity;

import net.leafenzo.mint.block.custom.EmberBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
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
//        if (!world.getBlockState(pos).isFullCube(world, pos)) { // if not a full cube. IE, we've hit a fence. oops nvm this doesn't work, hm... not sure if we should even worry about this, it's not very weird imo. - Leah
//            super.onBlockHit(blockHitResult); return;
//        }
        if (world.getBlockState(pos).isOf(ModBlocks.EMBER)) {
            BlockState prevState = world.getBlockState(pos);
            world.setBlockState(pos, ModBlocks.EMBER.getStateWithProperties(prevState).with(EmberBlock.getProperty(blockHitResult.getSide().getOpposite()), true));
            hasPlacedEmber = false;
        } else if (world.getBlockState(pos).getFluidState().isEqualAndStill(Fluids.WATER)) {
            world.setBlockState(pos, ModBlocks.EMBER.getDefaultState().with(EmberBlock.getProperty(blockHitResult.getSide().getOpposite()), true).with(EmberBlock.WATERLOGGED, true));
            hasPlacedEmber = true;
        } else if (world.getBlockState(pos).isReplaceable()) {
            world.setBlockState(pos, ModBlocks.EMBER.getDefaultState().with(EmberBlock.getProperty(blockHitResult.getSide().getOpposite()), true));
            hasPlacedEmber = true;
        }
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

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        switch (this.pickupType) {
            case ALLOWED -> {
                if(hasPlacedEmber) {
                    return player.getInventory().insertStack(Items.ARROW.getDefaultStack()); // Ember goop comes off the arrow, leaving a regular arrow
                }
                else {
                    return player.getInventory().insertStack(this.asItemStack());
                }
            }
            case CREATIVE_ONLY -> {
                return player.getAbilities().creativeMode;
            }
            default -> {
                return false;
            }
        }
    }

    protected void onHit(LivingEntity target) {
        super.onHit(target);
        target.setOnFireFor(8);
    }
}
