// SOURCE:
// Eko-byte - Nature's Spirit - https://github.com/Team-Hibiscus/NatureSpirit/blob/dev/src/main/java/net/hibiscus/naturespirit/datagen/HibiscusConfiguredFeatures.java
//

package net.leafenzo.mint.datageneration;

import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {
    public static final RegistryKey <ConfiguredFeature <?, ?>> HUGE_WAXCAP_MUSHROOM = registerKey("huge_waxcap_mushroom");

    public static void bootstrap(Registerable <ConfiguredFeature <?, ?>> context) {
//        var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
//        RegistryEntryLookup<Block> holderGetter = context.getRegistryLookup(RegistryKeys.BLOCK);


//        register(context, HUGE_WAXCAP_MUSHROOM, Feature.TREE, TreeConfiguredFeatures.oak.decorators(List.of(beehiveTreeDecorator)).build());
//        register(context, )
//
//        register(context, HUGE_WAXCAP_MUSHROOM, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(ModBlocks.WAXCAP_STEM_BLOCK),
//                new BeehiveTreeDecorator(0.001f),
//                new StraightTrunkPlacer(4, 1, 2),
//                //new OliveTrunkPlacer(4, 1, 2, UniformIntProvider.create(4, 6), .85F, UniformIntProvider.create(4, 5), Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
//                BlockStateProvider.of(ModBlocks.WAXCAP_CAP_BLOCK),
//                //new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 60),
//
//                new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))
//        ).ignoreVines().build());
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
