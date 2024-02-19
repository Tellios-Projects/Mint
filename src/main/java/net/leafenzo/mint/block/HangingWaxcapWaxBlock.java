package net.leafenzo.mint.block;

import net.leafenzo.mint.particle.ModParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.HangingRootsBlock;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class HangingWaxcapWaxBlock extends HangingRootsBlock {
    public HangingWaxcapWaxBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!HangingWaxcapWaxBlock.canDrip(state)) {
            return;
        }
        float f = random.nextFloat();
        if (f > 0.12f) {
            return;
        }
        HangingWaxcapWaxBlock.createParticle(world, pos, state);
    }

    private static void createParticle(World world, BlockPos pos, BlockState state) {
        Vec3d vec3d = state.getModelOffset(world, pos);
        double d = 0.0625;
        double e = (double)pos.getX() + 0.5 + vec3d.x;
        double f = (double)((float)(pos.getY() + 1) - 0.6875f) - 0.0625;
        double g = (double)pos.getZ() + 0.5 + vec3d.z;
//        Fluid fluid2 = PointedDripstoneBlock.getDripFluid(world, fluid);
        DefaultParticleType particleEffect = ModParticleTypes.DRIPPING_WAXCAP_WAX;
        world.addParticle(particleEffect, e, f, g, 0.0, 0.0, 0.0);
    }

    public static boolean canDrip(BlockState state) {
        return !state.get(Properties.WATERLOGGED);
    }
}
