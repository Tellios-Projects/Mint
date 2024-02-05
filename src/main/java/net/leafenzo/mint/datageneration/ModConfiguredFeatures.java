// SOURCE:
// Eko-byte - Nature's Spirit - https://github.com/Team-Hibiscus/NatureSpirit/blob/dev/src/main/java/net/hibiscus/naturespirit/datagen/HibiscusConfiguredFeatures.java
//

package net.leafenzo.mint.datageneration;

import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.PeachTreeBlock;
import net.leafenzo.mint.world.feature.ModSimpleBlockStateProvider;
import net.leafenzo.mint.world.gen.HugeWaxcapMushroomDecorator;
import net.leafenzo.mint.world.gen.MushroomBlockDirectionDecorator;
import net.minecraft.block.*;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.rule.BlockStateMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseThresholdBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;

public class ModConfiguredFeatures {

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_WILD_MINT = registerKey("patch_wild_mint");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_HYPERICUM = registerKey("patch_hypericum");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_PEACH_TREE = registerKey("patch_peach_tree");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_CORAL_ANEMONE = registerKey("patch_coral_anemone");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_HIDCOTE_LAVENDER = registerKey("patch_hidcote_lavender");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_PERIWINKLE = registerKey("patch_periwinkle");

    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_THISTLE_FLOWER = registerKey("patch_thistle_flower");
    public static final RegistryKey <ConfiguredFeature <?, ?>> HUGE_WAXCAP_MUSHROOM = registerKey("huge_waxcap_mushroom");
    public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_WAXCAP_MUSHROOM = registerKey("patch_waxcap_mushroom");

    public static final RegistryKey <ConfiguredFeature <?, ?>> ORE_MUCKTUFF = registerKey("ore_mucktuff");

    public static void bootstrap(Registerable <ConfiguredFeature <?, ?>> context) {
//        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
//        RegistryEntryLookup<Block> holderGetter = context.getRegistryLookup(RegistryKeys.BLOCK);

        register(context, PATCH_WILD_MINT, Feature.FLOWER,
                new RandomPatchFeatureConfig(16, 8, 5, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WILD_MINT))))
        );

        register(context, PATCH_HYPERICUM, Feature.FLOWER,
                new RandomPatchFeatureConfig(8, 3, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.HYPERICUM))))
        );
        DataPool.Builder<BlockState> peachTreeBuilder = DataPool.builder();
            peachTreeBuilder.add((BlockState)((BlockState) ModBlocks.PEACH_TREE.getDefaultState().with(PeachTreeBlock.AGE, 2)), 1);
            peachTreeBuilder.add((BlockState)((BlockState) ModBlocks.PEACH_TREE.getDefaultState().with(PeachTreeBlock.AGE, 1)), 1);
            peachTreeBuilder.add((BlockState)((BlockState) ModBlocks.PEACH_TREE.getDefaultState().with(PeachTreeBlock.AGE, 0)), 1);
        register(context, PATCH_PEACH_TREE, Feature.FLOWER,
                new RandomPatchFeatureConfig(16, 8, 5, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(peachTreeBuilder))))
        );
        register(context, PATCH_CORAL_ANEMONE, Feature.SIMPLE_BLOCK,
               new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.CORAL_ANEMONE.getDefaultState().with(Properties.WATERLOGGED, true)))
        );

        register(context, PATCH_HIDCOTE_LAVENDER, Feature.FLOWER,
                new RandomPatchFeatureConfig(32, 1, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.HIDCOTE_LAVENDER))))
        );
        DataPool.Builder<BlockState> periwinkleBuilder = DataPool.builder();
        for (Direction direction : Direction.Type.HORIZONTAL) {
                periwinkleBuilder.add((BlockState)((BlockState) ModBlocks.PERIWINKLE_PETALS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, 4)).with(FlowerbedBlock.FACING, direction), 5);
                periwinkleBuilder.add((BlockState)((BlockState) ModBlocks.PERIWINKLE_PETALS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, 3)).with(FlowerbedBlock.FACING, direction), 2);
//                periwinkleBuilder.add((BlockState)((BlockState) ModBlocks.PERIWINKLE_PETALS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, 2)).with(FlowerbedBlock.FACING, direction), 1);
        }
        register(context, PATCH_PERIWINKLE, Feature.FLOWER,
                new RandomPatchFeatureConfig(32, 1, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(periwinkleBuilder))))
        );
        register(context, PATCH_THISTLE_FLOWER, Feature.FLOWER,
                new RandomPatchFeatureConfig(8, 3, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.THISTLE_FLOWER))))
        );
        register(context, HUGE_WAXCAP_MUSHROOM, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.WAXCAP_STEM_BLOCK.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)), new StraightTrunkPlacer(4, 1, 2), BlockStateProvider.of(ModBlocks.WAXCAP_CAP_BLOCK), new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))).ignoreVines().decorators(List.of(
                new MushroomBlockDirectionDecorator(BlockStateProvider.of(ModBlocks.WAXCAP_CAP_BLOCK)),
                new HugeWaxcapMushroomDecorator(
                        0.55f,
                        1.0f,
                        1.0f,
                        BlockStateProvider.of(ModBlocks.HANGING_WAXCAP_WAX.getDefaultState()),
                        BlockStateProvider.of(ModBlocks.WAXCAP_GILL_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP)))
                )
        ).build());
        register(context, PATCH_WAXCAP_MUSHROOM, Feature.RANDOM_PATCH,
                new RandomPatchFeatureConfig(16, 16, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.WAXCAP_MUSHROOM))))
        );
//        ConfiguredFeatures.register(featureRegisterable, PATCH_BROWN_MUSHROOM, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.BROWN_MUSHROOM))));

//        DataPool.Builder<BlockState> builder2 = DataPool.builder();
//            builder2.add((BlockState)((BlockState) ModBlocks.MUCKTUFF.getDefaultState()), 1);
//            builder2.add((BlockState)((BlockState) ModBlocks.ALL_MUCKTUFF_BLOCKS.get(2).getDefaultState()), 1);
        register(context, ORE_MUCKTUFF, Feature.ORE,
                new OreFeatureConfig(
                        (RuleTest) new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD),
                        ModBlocks.MUCKTUFF.getDefaultState(),
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
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Super.MOD_ID, name));
    }

//    public static void registerConfiguredFeatures() {
//        System.out.println("Registering Configured Features For:" + Super.MOD_ID);
//    }

}
