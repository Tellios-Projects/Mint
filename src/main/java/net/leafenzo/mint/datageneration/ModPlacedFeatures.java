package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> PATCH_WILD_MINT_PLACED = registerKey("patch_wild_mint_placed");

    public static final RegistryKey<PlacedFeature> PATCH_HYPERICUM_PLACED = registerKey("patch_hypericum_placed");
    public static final RegistryKey<PlacedFeature> PATCH_PEACH_TREE_PLACED = registerKey("patch_peach_tree_placed");
    public static final RegistryKey<PlacedFeature> PEACH_PLACED = registerKey("peach");
    public static final RegistryKey<PlacedFeature> PATCH_CORAL_ANEMONE_PLACED = registerKey("patch_coral_anemone_placed");

    public static final RegistryKey<PlacedFeature> PATCH_HIDCOTE_LAVENDER_PLACED = registerKey("patch_hidcote_lavender_placed");
    public static final RegistryKey<PlacedFeature> PATCH_PERIWINKLE_PLACED = registerKey("patch_periwinkle_placed");

    public static final RegistryKey<PlacedFeature> PATCH_THISTLE_FLOWER_SPARSE_JUNGLE_PLACED = registerKey("patch_thistle_flower_sparse_jungle_placed");
    public static final RegistryKey<PlacedFeature> PATCH_THISTLE_FLOWER_PLACED = registerKey("patch_thistle_flower_placed");
//    public static final RegistryKey<PlacedFeature> HUGE_WAXCAP_MUSHROOM_PLACED = registerKey("huge_waxcap_mushroom_placed"); // Don't add me unless needed
    public static final RegistryKey<PlacedFeature> PATCH_WAXCAP_MUSHROOM_OLD_GROWTH_PLACED = registerKey("patch_waxcap_mushroom_old_growth_placed");

    public static final RegistryKey<PlacedFeature> WINTERGREEN_SNOWY_PLAINS_PLACED = registerKey("wintergreen_snowy_plains_placed");
    public static final RegistryKey<PlacedFeature> WINTERGREEN_PLACED = registerKey("wintergreen_placed");

    public static final RegistryKey<PlacedFeature> ORE_MUCKTUFF_PLACED = registerKey("ore_mucktuff_placed");

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
                PATCH_PEACH_TREE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_PEACH_TREE),
                RarityFilterPlacementModifier.of(1),
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
                PATCH_THISTLE_FLOWER_SPARSE_JUNGLE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_THISTLE_FLOWER),
                RarityFilterPlacementModifier.of(10),
                CountPlacementModifier.of(3),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
        registerKey(context,
                PATCH_THISTLE_FLOWER_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_THISTLE_FLOWER),
                RarityFilterPlacementModifier.of(50),
                CountPlacementModifier.of(3),
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

        registerKey(context,
                WINTERGREEN_SNOWY_PLAINS_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WINTERGREEN),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.02f, 1), ModBlocks.WINTERGREEN_WOODSET.getSapling())
        );
        registerKey(context,
                WINTERGREEN_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.WINTERGREEN),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0, 0.05f, 1), ModBlocks.WINTERGREEN_WOODSET.getSapling())
        );

        registerKey(context,
                ORE_MUCKTUFF_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ORE_MUCKTUFF),
//                RarityFilterPlacementModifier.of(35),
                CountPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                //PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0)),
                BiomePlacementModifier.of()
        );

//                PlacedFeatures.register(featureRegisterable, ORE_DIRT, registryEntry7,
//                OrePlacedFeatures.modifiersWithCount(7, HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(160))));
    }

    public static void registerModifications() {
        BiomeModifications.create(new Identifier(Super.MOD_ID, "overworld_vegetation"))
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(BiomeKeys.MEADOW).or(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST).or(BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST))),
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
                        BiomeSelectors.includeByKey(BiomeKeys.SPARSE_JUNGLE),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PEACH_PLACED); }
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
                        BiomeSelectors.includeByKey(BiomeKeys.PLAINS).or(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST).or(BiomeSelectors.includeByKey(BiomeKeys.SUNFLOWER_PLAINS))),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_HIDCOTE_LAVENDER_PLACED); }
                )

                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(BiomeKeys.SPARSE_JUNGLE),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_THISTLE_FLOWER_SPARSE_JUNGLE_PLACED); }
                )
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(BiomeTags.IS_SAVANNA), //TODO Nature's Spirit compat for the proper Mediterranean climates
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_THISTLE_FLOWER_PLACED); }
                )
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA).or(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_PINE_TAIGA)), //TODO Nature's Spirit compat for Redwood Forest
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, PATCH_WAXCAP_MUSHROOM_OLD_GROWTH_PLACED); }
                )

                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag((ConventionalBiomeTags.SNOWY_PLAINS)),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, WINTERGREEN_SNOWY_PLAINS_PLACED); }
                )
                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag((ConventionalBiomeTags.SNOWY_PLAINS)).negate().and(BiomeSelectors.tag(ConventionalBiomeTags.CLIMATE_COLD)),
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, WINTERGREEN_PLACED); }
                )

                .add(ModificationPhase.ADDITIONS,
                        BiomeSelectors.tag(ConventionalBiomeTags.OCEAN).or(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES)).or(BiomeSelectors.includeByKey(BiomeKeys.DEEP_DARK)).or(BiomeSelectors.tag(ConventionalBiomeTags.SWAMP).or(BiomeSelectors.tag(ConventionalBiomeTags.JUNGLE))), //TODO Nature's Spirit compat for other swamps
                        context -> { context.getGenerationSettings().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, ORE_MUCKTUFF_PLACED); }
                )
        ;
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
