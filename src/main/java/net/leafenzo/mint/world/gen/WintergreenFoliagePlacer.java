package net.leafenzo.mint.world.gen;

import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.leafenzo.mint.util.ModWorldGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;

public class WintergreenFoliagePlacer
        extends FoliagePlacer {

    public static final Codec <WintergreenFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> fillFoliagePlacerFields(instance).and(IntProvider.createValidatingCodec(0, 24).fieldOf("trunk_height").forGetter((placer) -> placer.trunkHeight)).apply(instance, WintergreenFoliagePlacer::new));
    private final IntProvider trunkHeight;
    public WintergreenFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider trunkHeight) {
        super(radius, offset);
        this.trunkHeight = trunkHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModWorldGen.WINTERGREEN_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos blockPos = treeNode.getCenter();
        Direction d = Direction.NORTH;

        int i = random.nextInt(2);
        int j = 1;
        int k = 0;
        for (int l = offset; l >= -foliageHeight; --l) {
            this.generateSquare(world, placer, random, config, blockPos, i, l, treeNode.isGiantTrunk());
            if (i >= j) {
                i = k;
                k = 1;
                j = Math.min(j + 1, radius + treeNode.getFoliageRadius());
                continue;
            }
            ++i;
        }


        //            if(l >= (-foliageHeight-2)) {
//                this.generateSquare();
//                this.generateSquare(world, placer, random, config, blockPos, i, l, treeNode.isGiantTrunk());
//            }

//            if(l >= -(foliageHeight+4)) {
//                this.generateSquare(world, placer, random, config, blockPos.offset(d) , i, l, treeNode.isGiantTrunk());
//                if (i >= j) {
//                    i = k;
//                    k = 1;
//                    j = Math.min(j + 1, radius + treeNode.getFoliageRadius());
//                    continue;
//                }
//            }
//            else if(l >= -(foliageHeight+1)) {
//                this.generateSquare(world, placer, random, config, blockPos.offset(d, 1) , i, l, treeNode.isGiantTrunk());
//                if (i >= j) {
//                    i = k;
//                    k = 1;
//                    j = Math.min(j + 1, radius + treeNode.getFoliageRadius());
//                    continue;
//                }
//            }
//            else {
//                this.generateSquare(world, placer, random, config, blockPos.offset(d, 2), i, l, treeNode.isGiantTrunk());
//                if (i >= j) {
//                    i = k;
//                    k = 1;
//                    j = Math.min(j + 1, radius + treeNode.getFoliageRadius());
//                    continue;
//                }
//            }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 0;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx == radius && dz == radius && radius > 0;
    }

}
