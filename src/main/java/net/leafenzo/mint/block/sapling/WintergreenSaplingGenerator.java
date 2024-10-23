package net.leafenzo.mint.block.sapling;

import net.leafenzo.mint.datageneration.ElsDyeModConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WintergreenSaplingGenerator extends SaplingGenerator {
    public WintergreenSaplingGenerator() { }

    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ElsDyeModConfiguredFeatures.WINTERGREEN;
    }
}
