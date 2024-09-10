package net.leafenzo.mint.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class MadderFeatureConfig implements FeatureConfig {
    protected final int root_depth;

    public static final Codec<MadderFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(Codec.intRange(2, 10)
                            .fieldOf("root_depth")
                            .forGetter((placer) -> placer.root_depth))
                    .apply(instance, MadderFeatureConfig::new)

    );

    public MadderFeatureConfig(int height) {
        this.root_depth = height;
    }
}
