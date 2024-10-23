package net.leafenzo.mint.mixin.client;

import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockDustParticle.class)
public abstract class BlockDustParticleMixin {
    @Inject(method = "<init>*", at = @At("TAIL"))
    private void init(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, BlockState state, BlockPos blockPos, CallbackInfo ci) {
        if (state.isOf(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK)) {
            ParticleColorAccessor accessor = (ParticleColorAccessor) this;
            accessor.setRed(0.6f);
            accessor.setGreen(0.6f);
            accessor.setBlue(0.6f);
        }
    }
}
