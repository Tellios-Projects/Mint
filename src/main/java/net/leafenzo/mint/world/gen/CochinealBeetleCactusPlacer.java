/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/remappedSrc/net/hibiscus/naturespirit/world/feature/trunk/MapleTrunkPlacer.java
 */

package net.leafenzo.mint.world.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.custom.CochinealBeetlesBlock;
import net.leafenzo.mint.util.ModWorldGen;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

public class CochinealBeetleCactusPlacer extends TrunkPlacer {
    public static final Codec<CochinealBeetleCactusPlacer> CODEC;

    protected final int beetleChance;

    public CochinealBeetleCactusPlacer(int beetleChance) {
        super(0, 0, 0);
        this.beetleChance = beetleChance;
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModWorldGen.COCHINEAL_CACTUS_PLACER;
    }

    protected static <P extends CochinealBeetleCactusPlacer> Products.P1<RecordCodecBuilder.Mu<P>, Integer> fillPlacerFields(RecordCodecBuilder.Instance<P> instance) {
        return instance.group(Codec.intRange(0, 10).fieldOf("beetle_chance").forGetter((placer) -> placer.beetleChance));
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        int cactusHeight = random.nextBetween(2, 3);
        int beetleChance = this.beetleChance;
        Direction[] directions = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP).toArray(new Direction[0]);
        for (int i = 0; i < cactusHeight; i++) {
            replacer.accept(startPos.up(i), Blocks.CACTUS.getDefaultState());
            for (Direction direction : directions) {
                if (random.nextBetween(1, 10) <= beetleChance) {
                    replacer.accept(startPos.up(i).offset(direction, 1), ModBlocks.COCHINEAL_BEETLES.getDefaultState().with(CochinealBeetlesBlock.FACING, direction).with(CochinealBeetlesBlock.AGE, random.nextBetween(0, 3)));
                }
            }
        }
        return ImmutableList.of();
    }

    static {
        CODEC = RecordCodecBuilder.create(cochinealBeetleCactusPlacerInstance ->
                fillPlacerFields(cochinealBeetleCactusPlacerInstance).apply(cochinealBeetleCactusPlacerInstance, CochinealBeetleCactusPlacer::new));
    }
}
