package net.leafenzo.mint.datageneration;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.leafenzo.mint.Super;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> PATCH_WILD_MINT_PLACED = registerKey("patch_wild_mint_placed");

    public static final RegistryKey<PlacedFeature> PATCH_HYPERICUM_PLACED = registerKey("patch_hypericum_placed");
    public static final RegistryKey<PlacedFeature> PATCH_CORAL_ANEMONE_PLACED = registerKey("patch_coral_anemone_placed");

    public static final RegistryKey<PlacedFeature> PATCH_HIDCOTE_LAVENDER_PLACED = registerKey("patch_hidcote_lavender_placed");
    public static final RegistryKey<PlacedFeature> PATCH_PERIWINKLE_PLACED = registerKey("patch_periwinkle_placed");

    public static final RegistryKey<PlacedFeature> PATCH_THISTLE_FLOWER_PLACED = registerKey("patch_thistle_flower_placed");
//    public static final RegistryKey<PlacedFeature> HUGE_WAXCAP_MUSHROOM_PLACED = registerKey("huge_waxcap_mushroom_placed"); // Don't add me unless needed
    public static final RegistryKey<PlacedFeature> PATCH_WAXCAP_MUSHROOM_OLD_GROWTH_PLACED = registerKey("patch_waxcap_mushroom_old_growth_placed");

    public static void bootstrap(Registerable <PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        registerKey(context,
                PATCH_WILD_MINT_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_WILD_MINT),
                RarityFilterPlacementModifier.of(35),
                CountPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );

        registerKey(context,
                PATCH_HYPERICUM_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_HYPERICUM),
                RarityFilterPlacementModifier.of(10),
                CountPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        registerKey(context,
                PATCH_CORAL_ANEMONE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_CORAL_ANEMONE),
                RarityFilterPlacementModifier.of(7),
                SquarePlacementModifier.of(),
                PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
//        PlacedFeatures.register(featureRegisterable, SEA_PICKLE, reference6, RarityFilterPlacementModifier.of(16), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

        registerKey(context,
                PATCH_HIDCOTE_LAVENDER_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_HIDCOTE_LAVENDER),
                RarityFilterPlacementModifier.of(100),
                NoiseThresholdCountPlacementModifier.of(-0.8, 3, 7),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        registerKey(context,
                PATCH_PERIWINKLE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_PERIWINKLE),
                RarityFilterPlacementModifier.of(60),
                NoiseThresholdCountPlacementModifier.of(-0.8, 0, 60),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
//        PlacedFeatures.register(featureRegisterable, FLOWER_CHERRY, registryEntry25, NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        registerKey(context,
                PATCH_THISTLE_FLOWER_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_THISTLE_FLOWER),
                RarityFilterPlacementModifier.of(10),
                CountPlacementModifier.of(1),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        registerKey(context,
                PATCH_WAXCAP_MUSHROOM_OLD_GROWTH_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_WAXCAP_MUSHROOM),
                RarityFilterPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
//        PlacedFeatures.register(featureRegisterable, BROWN_MUSHROOM_OLD_GROWTH, registryEntry18, VegetationPlacedFeatures.mushroomModifiers(4, CountPlacementModifier.of(3)));
    }

    //    private static List<PlacementModifier> mushroomModifiers(int chance, @Nullable PlacementModifier modifier) {
//        ImmutableList.Builder builder = ImmutableList.builder();
//        if (modifier != null) {
//            builder.add(modifier);
//        }
//        if (chance != 0) {
//            builder.add(RarityFilterPlacementModifier.of(chance));
//        }
//        builder.add(SquarePlacementModifier.of());
//        builder.add(PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP);
//        builder.add(BiomePlacementModifier.of());
//        return builder.build();
//    }
    public static void registerModifications() {
        //TODO Hypericum will spawn alone on top of grass in (#c:climate_dry || #c:climate_temperate) && #c:climate_hot
        //TODO Coral Anemone will rarely spawn alone in #c:ocean && !#c:aquatic_icy
        //TODO Hidcote Lavender will rarely spawn in large patches in fields or meadows
        //TODO Periwinkle will spawn in large patches in dark forests. Has random rotations little holes (random but small chance of 3/4 or 2/4)
        //TODO Thistle Flowers will rarely spawn alone in jungles or sparse jungles
        //TODO small waxcap mushrooms will spawn sparsely when shallow underground, or in Old Growth spruce forests, and more commonly in swamps (but in practice less commonly, given the less chances without podzol and shadows)

        BiomeModifications.create(new Identifier(Super.MOD_ID, "overworld_vegetation"))
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(BiomeKeys.MEADOW).or(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST)),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_WILD_MINT_PLACED); }
                )

//                .add(ModificationPhase.ADDITIONS,
//                        BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_DRY).and(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_HOT))
//                                .or(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_TEMPERATE)).and(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_HOT)),
//                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_HYPERICUM_PLACED); }
//                )
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(BiomeTags.IS_SAVANNA).or(BiomeSelectors.tag(BiomeTags.IS_BADLANDS)),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_HYPERICUM_PLACED); }
                )
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag((ConventionalBiomeTags.AQUATIC_ICY)).negate().and(BiomeSelectors.tag(ConventionalBiomeTags.SHALLOW_OCEAN)), //Not Aquatic Icy, and is Shallow Ocean
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_CORAL_ANEMONE_PLACED); }
                )

                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_PERIWINKLE_PLACED); }
                )
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(BiomeKeys.PLAINS).or(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST)),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_HIDCOTE_LAVENDER_PLACED); }
                )

                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(BiomeKeys.SPARSE_JUNGLE), //TODO Nature's Spirit compat for the proper Mediterranean climates
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_THISTLE_FLOWER_PLACED); }
                )
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA).or(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_PINE_TAIGA)), //TODO Nature's Spirit compat for Redwood Forest
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_WAXCAP_MUSHROOM_OLD_GROWTH_PLACED); }
                )
        ;

//        BiomeModifications.create(new Identifier(Super.MOD_ID, "plains_vegetation"))
//                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(BiomeKeys.PLAINS), context -> {
//                    context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_HIDCOTE_LAVENDER_PLACED);
//                });
    }

//    private static List<PlacementModifier> mushroomModifiers(int chance, @Nullable PlacementModifier modifier) {
//        ImmutableList.Builder builder = ImmutableList.builder();
//        if (modifier != null) {
//            builder.add(modifier);
//        }
//        if (chance != 0) {
//            builder.add(RarityFilterPlacementModifier.of(chance));
//        }
//        builder.add(SquarePlacementModifier.of());
//        builder.add(PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP);
//        builder.add(BiomePlacementModifier.of());
//        return builder.build();
//    }

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
