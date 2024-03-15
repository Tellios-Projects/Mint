/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/remappedSrc/net/hibiscus/naturespirit/world/feature/trunk/MapleTrunkPlacer.java
 */

package net.leafenzo.mint.world.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.leafenzo.mint.util.ModUtil;
import net.leafenzo.mint.util.ModWorldGen;
import net.minecraft.block.BlockState;
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

public class WintergreenTrunkPlacer extends TrunkPlacer {
    public static final Codec<WintergreenTrunkPlacer> CODEC;

    public WintergreenTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModWorldGen.WINTERGREEN_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
//        TrunkPlacer.setToDirt(world, replacer, random, startPos.down(), config);
//        for (int i = 0; i < height; ++i) {
//            this.getAndSetState(world, replacer, random, startPos.up(i), config);
//        }
//        return ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(height), 0, false));

        Direction d = ModUtil.randomHorizontalDirectionWithCoordinateSeed(startPos);

        TrunkPlacer.setToDirt(world, replacer, random, startPos.down(), config);
        int base = Math.max(baseHeight, 11);
        for (int i = 0; i < height; ++i) {
            if(i < base-4) {
                this.getAndSetState(world, replacer, random, startPos.up(i), config);
            }
            else if(i < base-1) {
                this.getAndSetState(world, replacer, random, startPos.up(i).offset(d, 1), config);
            }
            else {
                this.getAndSetState(world, replacer, random, startPos.up(i).offset(d, 2), config);
            }
        }

        return ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(height), 0, false));
    }

    static {
        CODEC = RecordCodecBuilder.create(instance -> WintergreenTrunkPlacer.fillTrunkPlacerFields(instance).apply(instance, WintergreenTrunkPlacer::new));

//        RecordCodecBuilder.create(instance ->
//                BendingTrunkPlacer.fillTrunkPlacerFields(instance).and(instance.group(Codecs.POSITIVE_INT.optionalFieldOf("min_height_for_leaves", 1)
//                        .forGetter(placer -> placer.minHeightForLeaves), ((MapCodec)IntProvider.createValidatingCodec(1, 64).fieldOf("bend_length"))
//                        .forGetter(placer -> placer.bendLength))).apply((Applicative<BendingTrunkPlacer, ?>)instance, BendingTrunkPlacer::new));


        //        BRANCH_START_OFFSET_FROM_TOP_CODEC = Codecs.validate(UniformIntProvider.CODEC, (branchStartOffsetFromTop) -> {
//            return branchStartOffsetFromTop.getMax() - branchStartOffsetFromTop.getMin() < 1 ? DataResult.error(() -> {
//                return "Need at least 2 blocks variation for the branch starts to fit both branches";
//            }) : DataResult.success(branchStartOffsetFromTop);
//        });
//        CODEC = RecordCodecBuilder.create((instance) -> {
//            return fillTrunkPlacerFields(instance).and(instance.group(IntProvider.createValidatingCodec(1, 5).fieldOf("branch_count").forGetter((trunkPlacer) -> {
//                return trunkPlacer.branchCount;
//            }), IntProvider.createValidatingCodec(1, 16).fieldOf("branch_horizontal_length").forGetter((trunkPlacer) -> {
//                return trunkPlacer.branchHorizontalLength;
//            }), IntProvider.createValidatingCodec(-16, 0, BRANCH_START_OFFSET_FROM_TOP_CODEC).fieldOf("branch_start_offset_from_top").forGetter((trunkPlacer) -> {
//                return trunkPlacer.branchStartOffsetFromTop;
//            }), IntProvider.createValidatingCodec(-16, 16).fieldOf("branch_end_offset_from_top").forGetter((trunkPlacer) -> {
//                return trunkPlacer.branchEndOffsetFromTop;
//            }))).apply(instance, MapleTrunkPlacer::new);
//        });
    }
}
