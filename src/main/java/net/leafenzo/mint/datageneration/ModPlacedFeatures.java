package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.leafenzo.mint.Super;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> HUGE_WAXCAP_MUSHROOM_PLACED = registerKey("huge_waxcap_mushroom_placed");
//    public static final RegistryKey<PlacedFeature> WAXCAP_MUSHROOM_PLACED = registerKey("waxcap_mushroom_placed");

//    public static final RegistryKey<PlacedFeature> PATCH_WILD_MINT_PLACED = registerKey("patch_wild_mint_placed");
    public static final RegistryKey<PlacedFeature> PATCH_HIDCOTE_LAVENDER_PLACED = registerKey("patch_hidcote_lavender_placed");
//    public static final RegistryKey<PlacedFeature> PATCH_THISTLE_FLOWER_PLACED = registerKey("patch_thistle_flower_placed");

    public static void bootstrap(Registerable <PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

//        registerKey(context,
//                PATCH_WILD_MINT_PLACED,
//                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_WILD_MINT),
//                CountPlacementModifier.of(3),
//                RarityFilterPlacementModifier.of(6),
//                SquarePlacementModifier.of(),
//                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
//                BiomePlacementModifier.of()
//        );
        registerKey(context,
                PATCH_HIDCOTE_LAVENDER_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_HIDCOTE_LAVENDER),
                CountPlacementModifier.of(1), //TODO Makeme
                RarityFilterPlacementModifier.of(1),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        //        registerKey(context,
//                PATCH_THISTLE_FLOWER_PLACED,
//                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_WILD_MINT),
//                CountPlacementModifier.of(10),
//                RarityFilterPlacementModifier.of(6),
//                SquarePlacementModifier.of(),
//                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
//                BiomePlacementModifier.of()
//        );
    }

    public static void registerModifications() {
        //TODO Wild Mint will spawn sparsely in cold places next to rivers
        //TODO Hidcote Lavender will rarely spawn in large patches in fields or meadows
        //TODO Periwinkle will spawn in large patches in dark forests
        //TODO Thistle Flowers will rarely spawn alone in jungles or sparse jungles
        //TODO Hypericum will spawn alone in savannah, savannah plateaus, anywhere grassy where it is very warm
        //TODO small waxcap mushrooms will spawn sparsely when shallow underground, or in Old Growth spruce forests, and more commonly in swamps (but in practice less commonly, given the less chances without podzol and shadows)

        BiomeModifications.create(new Identifier(Super.MOD_ID, "vegetation")).add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(), context -> {
//            context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_WILD_MINT_PLACED);
//            context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_THISTLE_FLOWER_PLACED);
//            context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_HIDCOTE_LAVENDER_PLACED);
        });
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
