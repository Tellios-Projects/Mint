// SOURCE:
// Eko-byte - Nature's Spirit - https://github.com/Team-Hibiscus/NatureSpirit/blob/dev/src/main/java/net/hibiscus/naturespirit/datagen/HibiscusConfiguredFeatures.java
//

package net.leafenzo.mint.datageneration;

import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.world.gen.HugeWaxcapMushroomDecorator;
import net.leafenzo.mint.world.gen.MushroomBlockDirectionDecorator;
import net.minecraft.block.MushroomBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final RegistryKey <ConfiguredFeature <?, ?>> HUGE_WAXCAP_MUSHROOM = registerKey("huge_waxcap_mushroom");

//    public static final Feature <HugeMushroomFeatureConfig> HUGE_WAXCAP_MUSHROOM_FEATURE = Registry.register(Registries.FEATURE,
//            new Identifier(Super.MOD_ID, "huge_waxcap_mushroom_feature"),
//            new HugeWaxcapMushroomFeature(HugeMushroomFeatureConfig.CODEC)
//    );


    //TODO make huge waxcaps spawn in old growth spruce forests

    public static void bootstrap(Registerable <ConfiguredFeature <?, ?>> context) {
//        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
//        RegistryEntryLookup<Block> holderGetter = context.getRegistryLookup(RegistryKeys.BLOCK);

//        register(context, HUGE_WAXCAP_MUSHROOM, Feature.HUGE_BROWN_MUSHROOM, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.WAXCAP_STEM_BLOCK.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)),
//                new StraightTrunkPlacer(3, 1, 2),
//                BlockStateProvider.of(ModBlocks.WAXCAP_CAP_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false)),
//                new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
//                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))
//        ).ignoreVines()
//                .decorators(List.of(new HugeWaxcapMushroomDecorator(0.66f, new ModSimpleBlockStateProvider(ModBlocks.WAXCAP_WAX.getDefaultState())))).build());

        //      ConfiguredFeatures.register(featureRegisterable, ACACIA, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.ACACIA_LOG), new ForkingTrunkPlacer(5, 2, 2), BlockStateProvider.of(Blocks.ACACIA_LEAVES), new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());


                register(context, HUGE_WAXCAP_MUSHROOM, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.WAXCAP_STEM_BLOCK.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)),
                new StraightTrunkPlacer(5, 2, 3),
//                new StraightTrunkPlacer(9, 1, 2),
//                new ForkingTrunkPlacer(5, 5, 7),
                BlockStateProvider.of(ModBlocks.WAXCAP_CAP_BLOCK),
//                new AcaciaFoliagePlacer(ConstantIntProvider.create(6), ConstantIntProvider.create(3)),
                new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
//                new JungleFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5)))
                .ignoreVines()
                .decorators(List.of(
                        new MushroomBlockDirectionDecorator(BlockStateProvider.of(ModBlocks.WAXCAP_CAP_BLOCK)),
                        new HugeWaxcapMushroomDecorator(
                                0.55f,
                                1.0f,
                                1.0f,
                                BlockStateProvider.of(ModBlocks.HANGING_WAXCAP_WAX.getDefaultState()),
                                BlockStateProvider.of(ModBlocks.WAXCAP_GILL_SLAB.getDefaultState().with(SlabBlock.TYPE, SlabType.TOP)))
                )).build());
    }

    private static <FC extends FeatureConfig, F extends Feature <FC>> void register(Registerable <ConfiguredFeature <?, ?>> context, RegistryKey <ConfiguredFeature <?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature <>(feature, configuration));
    }

    public static RegistryKey <ConfiguredFeature <?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Super.MOD_ID, name));
    }

    public static void registerConfiguredFeatures() {
        System.out.println("Registering Configured Features For:" + Super.MOD_ID);
    }

}
