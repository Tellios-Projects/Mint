package net.leafenzo.mint.mixin;

import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.block.custom.MadderRootedGrassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GrassBlock.class)
public class GrassBlockMixin {
    @Unique
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!SpreadableBlock.canSurvive(state, world, pos)) {
            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
        } else {
            if (world.getLightLevel(pos.up()) >= 9) {
                BlockState blockState = Blocks.GRASS_BLOCK.getDefaultState();

                for(int i = 0; i < 4; ++i) {
                    BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (world.getBlockState(blockPos).isOf(Blocks.DIRT) && SpreadableBlock.canSpread(blockState, world, blockPos)) {
                        world.setBlockState(blockPos, blockState.with(GrassBlock.SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                    } else if (world.getBlockState(blockPos).isOf(ElsDyeModBlocks.MADDER_ROOTED_DIRT) && SpreadableBlock.canSpread(blockState, world, blockPos)) {
                        blockState = ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK.getDefaultState();
                        world.setBlockState(blockPos, blockState.with(MadderRootedGrassBlock.SNOWY, world.getBlockState(blockPos.up()).isOf(Blocks.SNOW)));
                    }
                }
            }

        }
    }
}
