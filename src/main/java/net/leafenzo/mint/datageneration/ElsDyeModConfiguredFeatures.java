// SOURCE:
// Eko-byte - Nature's Spirit - https://github.com/Team-Hibiscus/NatureSpirit/blob/dev/src/main/java/net/hibiscus/naturespirit/datagen/HibiscusConfiguredFeatures.java
//

package net.leafenzo.mint.datageneration;

import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.block.custom.PeachTreeBlock;
import net.leafenzo.mint.block.custom.StrawberryPlantBlock;
import net.leafenzo.mint.world.gen.HugeWaxcapMushroomDecorator;
import net.leafenzo.mint.world.gen.MushroomBlockDirectionDecorator;
import net.leafenzo.mint.world.gen.WintergreenFoliagePlacer;
import net.leafenzo.mint.world.gen.WintergreenTrunkPlacer;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class ElsDyeModConfiguredFeatures {

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_WILD_MINT = registerKey("patch_wild_mint");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_HYPERICUM = registerKey("patch_hypericum");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_PEACH_TREE = registerKey("patch_peach_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_CORAL_ANEMONE = registerKey("patch_coral_anemone");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_BONEMEAL_CORAL_ANEMONE = registerKey("patch_bonemeal_coral_anemone");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_HIDCOTE_LAVENDER = registerKey("patch_hidcote_lavender");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_PERIWINKLE = registerKey("patch_periwinkle");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_THISTLE_FLOWER = registerKey("patch_thistle_flower");
    public static final RegistryKey <ConfiguredFeature <?, ?>> HUGE_WAXCAP_MUSHROOM = registerKey("huge_waxcap_mushroom");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_WAXCAP_MUSHROOM = registerKey("patch_waxcap_mushroom");

    public static final RegistryKey <ConfiguredFeature <?, ?>> WINTERGREEN = registerKey("wintergreen");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PEACH = registerKey("peach");
    public static final RegistryKey <ConfiguredFeature <?, ?>> COCHINEAL_CACTUS = registerKey("cochineal_cactus");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_BONEMEAL_MADDER = registerKey("patch_bonemeal_madder");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_PINEAPPLE = registerKey("patch_pineapple");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_STRAWBERRY = registerKey("patch_strawberry");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_CORDYLINE = registerKey("patch_cordyline");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_DRY_CORDYLINE = registerKey("patch_dry_cordyline");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_POKEWEED = registerKey("patch_pokeweed");

    public static final RegistryKey <ConfiguredFeature <?, ?>> ORE_MUCKTUFF = registerKey("ore_mucktuff");

    public static void bootstrap(Registerable <ConfiguredFeature <?, ?>> context) {
//        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
//        RegistryEntryLookup<Block> holderGetter = context.getRegistryLookup(RegistryKeys.BLOCK);

        register(context, PATCH_WILD_MINT, Feature.FLOWER,
                new RandomPatchFeatureConfig(16, 8, 5, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.WILD_MINT))))
        );

        register(context, PATCH_HYPERICUM, Feature.FLOWER,
                new RandomPatchFeatureConfig(8, 3, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.HYPERICUM))))
        );
        DataPool.Builder<BlockState> peachTreeBuilder = DataPool.builder();
            peachTreeBuilder.add(ElsDyeModBlocks.PEACH_TREE.getDefaultState().with(PeachTreeBlock.AGE, 2), 1);
            peachTreeBuilder.add(ElsDyeModBlocks.PEACH_TREE.getDefaultState().with(PeachTreeBlock.AGE, 1), 1);
            peachTreeBuilder.add(ElsDyeModBlocks.PEACH_TREE.getDefaultState().with(PeachTreeBlock.AGE, 0), 1);
        register(context, PATCH_PEACH_TREE, Feature.FLOWER,
                new RandomPatchFeatureConfig(16, 8, 5, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(peachTreeBuilder))))
        );
        register(context, PATCH_CORAL_ANEMONE, Feature.SIMPLE_BLOCK,
               new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.CORAL_ANEMONE.getDefaultState().with(Properties.WATERLOGGED, true)))
        );
        register(context, PATCH_BONEMEAL_CORAL_ANEMONE, Feature.RANDOM_PATCH, //TODO FIXME
                new RandomPatchFeatureConfig(16, 5, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.CORAL_ANEMONE.getDefaultState().with(Properties.WATERLOGGED, true)))))
        );

        register(context, PATCH_HIDCOTE_LAVENDER, Feature.FLOWER,
                new RandomPatchFeatureConfig(32, 1, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.HIDCOTE_LAVENDER))))
        );
        DataPool.Builder<BlockState> periwinkleBuilder = DataPool.builder();
        for (Direction direction : Direction.Type.HORIZONTAL) {
                periwinkleBuilder.add(ElsDyeModBlocks.PERIWINKLE_PETALS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, 4).with(FlowerbedBlock.FACING, direction), 5);
                periwinkleBuilder.add(ElsDyeModBlocks.PERIWINKLE_PETALS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, 3).with(FlowerbedBlock.FACING, direction), 2);
//                periwinkleBuilder.add((BlockState)((BlockState) ModBlocks.PERIWINKLE_PETALS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, 2)).with(FlowerbedBlock.FACING, direction), 1);
        }
        register(context, PATCH_PERIWINKLE, Feature.FLOWER,
                new RandomPatchFeatureConfig(32, 1, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(periwinkleBuilder))))
        );
        register(context, PATCH_THISTLE_FLOWER, Feature.FLOWER,
                new RandomPatchFeatureConfig(8, 3, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.THISTLE_FLOWER))))
        );
        register(context, HUGE_WAXCAP_MUSHROOM, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ElsDyeModBlocks.WAXCAP_STEM_BLOCK.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), new StraightTrunkPlacer(4, 1, 2), BlockStateProvider.of(ElsDyeModBlocks.WAXCAP_CAP_BLOCK), new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))).ignoreVines().decorators(List.of(
                new MushroomBlockDirectionDecorator(BlockStateProvider.of(ElsDyeModBlocks.WAXCAP_CAP_BLOCK)),
                new HugeWaxcapMushroomDecorator(
                        0.55f,
                        1.0f,
                        1.0f,
                        BlockStateProvider.of(ElsDyeModBlocks.HANGING_WAXCAP_WAX.getDefaultState()),
                        BlockStateProvider.of(ElsDyeModBlocks.WAXCAP_GILL_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP)))
                )
        ).build());
        register(context, PATCH_WAXCAP_MUSHROOM, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(16, 16, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.WAXCAP_MUSHROOM))))
        );

        register(context, PATCH_BONEMEAL_MADDER, Feature.FLOWER,
                new RandomPatchFeatureConfig(2, 3, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.MADDER))))
        );

        register(context, PATCH_PINEAPPLE, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.PINEAPPLE)), List.of(Blocks.GRASS_BLOCK, Blocks.SAND), 30));

        DataPool.Builder<BlockState> strawberryBuilder = DataPool.builder();
        for (int i = 0; i < 5; i++) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                strawberryBuilder.add(( ElsDyeModBlocks.STRAWBERRY_PLANT.getDefaultState().with(StrawberryPlantBlock.FLOWER_AMOUNT, 4)).with(StrawberryPlantBlock.FACING, direction).with(StrawberryPlantBlock.AGE, i), 5);
                strawberryBuilder.add(( ElsDyeModBlocks.STRAWBERRY_PLANT.getDefaultState().with(StrawberryPlantBlock.FLOWER_AMOUNT, 3)).with(StrawberryPlantBlock.FACING, direction).with(StrawberryPlantBlock.AGE, i), 2);
            }
        }
        BlockPredicate strawberryPredicate = BlockPredicate.allOf(BlockPredicate.wouldSurvive(Blocks.BAMBOO.getDefaultState(), Vec3i.ZERO), BlockPredicate.matchingBlocks(Blocks.AIR));
        register(context, PATCH_STRAWBERRY, Feature.FLOWER,
                new RandomPatchFeatureConfig(3, 3, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(strawberryBuilder)), strawberryPredicate))
        );

        DataPool.Builder<BlockState> cordylineBuilder = DataPool.builder();
            cordylineBuilder.add(ElsDyeModBlocks.CORDYLINE.getDefaultState(), 8);
            cordylineBuilder.add(ElsDyeModBlocks.PLUM_CORDYLINE.getDefaultState(), 6);
            cordylineBuilder.add(ElsDyeModBlocks.TALL_CORDYLINE.getDefaultState(), 4);
            cordylineBuilder.add(ElsDyeModBlocks.TALL_PLUM_CORDYLINE.getDefaultState(), 3);
        register(context, PATCH_CORDYLINE, Feature.FLOWER,
                new RandomPatchFeatureConfig(32, 5, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(cordylineBuilder))))
        );
        DataPool.Builder<BlockState> dryCordylineBuilder = DataPool.builder();
            dryCordylineBuilder.add(Blocks.DEAD_BUSH.getDefaultState(), 8);
            dryCordylineBuilder.add(ElsDyeModBlocks.CORDYLINE.getDefaultState(), 4);
            dryCordylineBuilder.add(ElsDyeModBlocks.PLUM_CORDYLINE.getDefaultState(), 8);
            dryCordylineBuilder.add(ElsDyeModBlocks.TALL_CORDYLINE.getDefaultState(), 2);
            dryCordylineBuilder.add(ElsDyeModBlocks.TALL_PLUM_CORDYLINE.getDefaultState(), 3);
        register(context, PATCH_DRY_CORDYLINE, Feature.FLOWER,
                new RandomPatchFeatureConfig(24, 4, 1, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(dryCordylineBuilder))))
        );

        register(context, PATCH_POKEWEED, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ElsDyeModBlocks.POKEWEED)), List.of(Blocks.GRASS_BLOCK), 30));

        register(context, WINTERGREEN, Feature.TREE,
                new TreeFeatureConfig.Builder(
                        BlockStateProvider.of(ElsDyeModBlocks.WINTERGREEN_WOODSET.getLog()),
                        new WintergreenTrunkPlacer(11, 0, 0),
                        BlockStateProvider.of(ElsDyeModBlocks.WINTERGREEN_WOODSET.getLeaves()),
                        new WintergreenFoliagePlacer(UniformIntProvider.create(2,2),UniformIntProvider.create(0,0),UniformIntProvider.create(1,1)),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().build()
        );

//        ConfiguredFeatures.register(featureRegisterable, PATCH_BROWN_MUSHROOM, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.BROWN_MUSHROOM))));

//        DataPool.Builder<BlockState> builder2 = DataPool.builder();
//            builder2.add((BlockState)((BlockState) ModBlocks.MUCKTUFF.getDefaultState()), 1);
//            builder2.add((BlockState)((BlockState) ModBlocks.ALL_MUCKTUFF_BLOCKS.get(2).getDefaultState()), 1);
        register(context, ORE_MUCKTUFF, Feature.ORE,
                new OreFeatureConfig(
                        new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD),
                        ElsDyeModBlocks.MUCKTUFF.getDefaultState(),
//                        new ModSimpleBlockStateProvider(new WeightedBlockStateProvider(builder))
//                        BlockStateProvider.of(new WeightedBlockStateProvider(builder)),
//                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(builder))),
//                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(builder))),
                        64
                )
        );
    }
    private static <FC extends FeatureConfig, F extends Feature <FC>> void register(Registerable <ConfiguredFeature <?, ?>> context, RegistryKey <ConfiguredFeature <?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature <>(feature, configuration));
    }

    public static RegistryKey <ConfiguredFeature <?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ElsDyeMod.MOD_ID, name));
    }

//    public static void registerConfiguredFeatures() {
//        System.out.println("Registering Configured Features For:" + Super.MOD_ID);
//    }

}
