package net.leafenzo.mint.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class CochinealBeetleCactusFeatureConfig implements FeatureConfig {
    protected final int beetle_chance;

    public static final Codec<CochinealBeetleCactusFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(Codec.intRange(0, 10)
                            .fieldOf("beetle_chance")
                            .forGetter((placer) -> placer.beetle_chance))
                    .apply(instance, CochinealBeetleCactusFeatureConfig::new)

    );

    public CochinealBeetleCactusFeatureConfig(int height) {
        this.beetle_chance = height;
    }
}
