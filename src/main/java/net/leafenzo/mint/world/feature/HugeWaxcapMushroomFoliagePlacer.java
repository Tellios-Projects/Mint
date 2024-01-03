package net.leafenzo.mint.world.feature;

//public class HugeWaxcapMushroomFoliagePlacer extends FoliagePlacer {
//    public static final Codec<HugeWaxcapMushroomFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
//        return fillFoliagePlacerFields(instance).and(instance.group(IntProvider.createValidatingCodec(4, 16).fieldOf("height").forGetter((foliagePlacer) -> {
//            return foliagePlacer.height;
//        }), Codec.floatRange(0.0F, 1.0F).fieldOf("hanging_leaves_chance").forGetter((foliagePlacer) -> {
//            return foliagePlacer.decorationChance;
//        })
//        )).apply(instance, HugeWaxcapMushroomFoliagePlacer::new);
//    });
//
//    private final IntProvider height;
//    private final float decorationChance;
//    private final BlockStateProvider decorationBlock;
//
//    public HugeWaxcapMushroomFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height, BlockStateProvider decorationBlock, float decorationChance) {
//        super(radius, offset);
//        this.height = height;
//        this.decorationChance = decorationChance;
//        this.decorationBlock = decorationBlock;
//    }
//
//    protected FoliagePlacerType<?> getType() {
//        return ModWorldGen.HUGE_WAXCAP_MUSHROOM_FOLIAGE_PLACER_TYPE;
//    }
//
//    protected void generate(TestableWorld world, FoliagePlacer.BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, FoliagePlacer.TreeNode treeNode, int foliageHeight, int radius, int offset) {
//        boolean bl = treeNode.isGiantTrunk();
//        BlockPos blockPos = treeNode.getCenter().up(offset);
//        int i = radius + treeNode.getFoliageRadius() - 1;
//        this.generateSquare(world, placer, random, config, blockPos, i - 2, foliageHeight - 3, bl);
//        this.generateSquare(world, placer, random, config, blockPos, i - 1, foliageHeight - 4, bl);
//
//        for(int j = foliageHeight - 5; j >= 0; --j) {
//            this.generateSquare(world, placer, random, config, blockPos, i, j, bl);
//        }
//
//        this.generateSquareWithHangingLeaves(world, placer, random, config, blockPos, i, -1, bl, this.decorationChance, 0);
//        //this.generateSquareWithHangingLeaves(world, placer, random, config, blockPos, i - 1, -2, bl, this.decorationChance, this.hangingLeavesExtensionChance);
//    }
//
//    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
//        return this.height.get(random);
//    }
//
//    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
//        if(y >= -1 && y <= 0 && (dx == radius || dz == radius || (dx == radius - y || dz == radius - y))) {
//            return true;
//        }
//        boolean bl = dx == radius && dz == radius;
//        boolean bl2 = radius > 2;
//        if(bl2) {
//            return bl || dx + dz > radius * 2 - 2;
//        }
//        else {
//            return bl;
//        }
//    }
//}
