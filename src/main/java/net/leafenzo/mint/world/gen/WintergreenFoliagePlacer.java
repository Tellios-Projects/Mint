package net.leafenzo.mint.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.leafenzo.mint.util.ElsDyeModUtil;
import net.leafenzo.mint.util.ElsDyeModWorldGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

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
        return ElsDyeModWorldGen.WINTERGREEN_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, FoliagePlacer.BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos blockPos = treeNode.getCenter();
        Direction d = ElsDyeModUtil.randomHorizontalDirectionWithCoordinateSeed(blockPos);

        int i = 0;
        int j = 1;
        int k = 0;

        for (int l = offset+2; l >= -foliageHeight; --l) {
            // lil swoosh at the top, and the reason why l = offset+2 to start with
            if(l >= offset+2) {
                this.generateSquare(world, placer, random, config, blockPos.offset(d, 4), 0, l, treeNode.isGiantTrunk());
                this.generateSquare(world, placer, random, config, blockPos.offset(d, 4), 0, l-1, treeNode.isGiantTrunk());
            }
            else if(l >= offset+1) {
                this.generateSquare(world, placer, random, config, blockPos.offset(d, 3), 0, l, treeNode.isGiantTrunk());
                this.generateSquare(world, placer, random, config, blockPos.offset(d, 3), 0, l-1, treeNode.isGiantTrunk());
            }
            else if(l >= offset-1) {
                this.generateSquare(world, placer, random, config, blockPos.offset(d, 2), 1, l, treeNode.isGiantTrunk());
            }
            else if(l >= offset-4) {
                this.generateSquare(world, placer, random, config, blockPos.offset(d, 1), 1, l, treeNode.isGiantTrunk());
            }
            else {
                this.generateSquare(world, placer, random, config, blockPos, i, l, treeNode.isGiantTrunk());
            }

            // Alternating leaves radius
            if (i >= j) {
                i = k;
                k = 1;
                j = Math.min(j + 1, radius + treeNode.getFoliageRadius());
                continue;
            }
            ++i;
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return Math.max(4, trunkHeight - this.trunkHeight.get(random));
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx == radius && dz == radius && radius > 0;
    }

}
