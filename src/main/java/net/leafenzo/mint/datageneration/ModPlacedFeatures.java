package net.leafenzo.mint.datageneration;

import net.leafenzo.mint.Super;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> HUGE_WAXCAP_MUSHROOM_PLACED = registerKey("huge_waxcap_mushroom_placed");

    public static final RegistryKey<PlacedFeature> WILD_MINT_PLACED = registerKey("wild_mint_placed");
    public static final RegistryKey<PlacedFeature> THISTLE_FLOWER_PLACED = registerKey("thistle_flower_placed");
    public static final RegistryKey<PlacedFeature> WAXCAP_MUSHROOM_PLACED = registerKey("waxcap_mushroom_placed");


    public static void bootstrap(Registerable <PlacedFeature> context) {
//        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
//
//        registerKey(context,
//                WILD_MINT_PLACED,
//                configuredFeatureRegistryEntryLookup.getOrThrow(HibiscusConfiguredFeatures.FLOWER_GOLDEN_WILDS),
//                CountPlacementModifier.of(5),
//                RarityFilterPlacementModifier.of(1),
//                SquarePlacementModifier.of(),
//                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
//                BiomePlacementModifier.of()
//        );
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Super.MOD_ID, name));
    }

    private static void registerKey(Registerable<PlacedFeature> context, RegistryKey <PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void registerKey(Registerable <PlacedFeature> context, RegistryKey <PlacedFeature> key, RegistryEntry <ConfiguredFeature <?, ?>> configuration, PlacementModifier... modifiers) {
        registerKey(context, key, configuration, List.of(modifiers));
    }
}
