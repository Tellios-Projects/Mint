package net.leafenzo.mint.world.gen;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModFeatures {
    public static final Feature<CochinealBeetleCactusFeatureConfig> COCHINEAL_BEETLE_CACTUS = registerFeatures("cochineal_beetle_cactus", new CochinealBeetleCactusFeature(CochinealBeetleCactusFeatureConfig.CODEC));
    public static final Feature<MadderFeatureConfig> MADDER = registerFeatures("madder", new MadderFeature(MadderFeatureConfig.CODEC));
    private static <C extends FeatureConfig, F extends Feature<C>> F registerFeatures(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }
    public static void registerFeatures() {
    }
}
