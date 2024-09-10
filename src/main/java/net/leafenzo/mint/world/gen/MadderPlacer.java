package net.leafenzo.mint.world.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.simibubi.create.foundation.recipe.BlockTagIngredient;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.custom.CochinealBeetlesBlock;
import net.leafenzo.mint.util.ModWorldGen;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class MadderPlacer extends TrunkPlacer {
    public static final Codec<MadderPlacer> CODEC;

    protected final int rootRadius;

    public MadderPlacer(int rootRadius) {
        super(0, 0, 0);
        this.rootRadius = rootRadius;
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModWorldGen.MADDER_PLACER;
    }

    protected static <P extends MadderPlacer> Products.P1<RecordCodecBuilder.Mu<P>, Integer> fillPlacerFields(RecordCodecBuilder.Instance<P> instance) {
        return instance.group(Codec.intRange(2, 10).fieldOf("root_size").forGetter((placer) -> placer.rootRadius));
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        int rootRadius = this.rootRadius;
        replacer.accept(startPos, ModBlocks.MADDER.getDefaultState());
        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if (random.nextFloat() < 0.2) {
                    replacer.accept(startPos.add(i, 0, j), ModBlocks.MADDER.getDefaultState());
                }
            }
        }
        replacer.accept(startPos, ModBlocks.MADDER.getDefaultState());
        for (int depth = 0; depth < rootRadius; depth++) {
            tryPlaceMadderRoot(world, startPos.down(depth), replacer);
            for (int i = depth; i > -depth-1; i--) {
                for (int j = depth; j > -depth-1; j--) {
//                    replacer.accept(startPos.down(depth + 1).add(i, 0, j), Blocks.GOLD_BLOCK.getDefaultState());
                    if (random.nextBetween(0, depth) < 2) {
                        tryPlaceMadderRoot(world, startPos.down(depth).add(i, 0, j), replacer);
                    }
                }
            }
        }
        return ImmutableList.of();
    }

    private void tryPlaceMadderRoot(TestableWorld world, BlockPos pos, BiConsumer<BlockPos, BlockState> replacer) {
        if (world.testBlockState(pos, blockState -> blockState.isOf(Blocks.GRASS_BLOCK)) || world.testBlockState(pos, blockState -> blockState.isOf(ModBlocks.MADDER_ROOTED_GRASS_BLOCK))) {
            replacer.accept(pos, ModBlocks.MADDER_ROOTED_GRASS_BLOCK.getDefaultState());
        } else if (world.testBlockState(pos, blockState -> blockState.isIn(BlockTags.DIRT))) {
            replacer.accept(pos, ModBlocks.MADDER_ROOTED_DIRT.getDefaultState());
        }
    }

    static {
        CODEC = RecordCodecBuilder.create(madderPlacerInstance ->
                fillPlacerFields(madderPlacerInstance).apply(madderPlacerInstance, MadderPlacer::new));
    }
}
