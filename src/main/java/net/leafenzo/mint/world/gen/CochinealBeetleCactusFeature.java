package net.leafenzo.mint.world.gen;

import com.mojang.serialization.Codec;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.custom.CochinealBeetlesBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class CochinealBeetleCactusFeature extends Feature<CochinealBeetleCactusFeatureConfig> {
    public CochinealBeetleCactusFeature(Codec<CochinealBeetleCactusFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<CochinealBeetleCactusFeatureConfig> context) {
        int cactusHeight = context.getRandom().nextBetween(2, 3);
        int beetleChance = context.getConfig().beetle_chance;
        List<Direction> directions = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP);
        for (int i = 0; i < cactusHeight; i++) {
            this.getAndSetState(context.getWorld(), context.getOrigin().up(i), Blocks.CACTUS.getDefaultState());
            for (Direction direction : directions) {
                if (context.getRandom().nextBetween(1, 10) <= beetleChance) {
                    this.getAndSetState(context.getWorld(), context.getOrigin().up(i).offset(direction, 1), ModBlocks.COCHINEAL_BEETLES.getDefaultState().with(CochinealBeetlesBlock.FACING, direction).with(CochinealBeetlesBlock.AGE, context.getRandom().nextBetween(0, 3)));
                }
            }
        }
        return true;
    }

    private void getAndSetState(StructureWorldAccess world, BlockPos pos, BlockState state) {
        if (world.testBlockState(pos, (blockstate) -> blockstate.isAir() || blockstate.isOf(ModBlocks.COCHINEAL_BEETLES))) {
            world.setBlockState(pos, state, 2);
        }
    }
}
