package net.leafenzo.mint.world.feature;
import com.mojang.serialization.Codec;
import net.leafenzo.mint.util.ModWorldGen;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.BlockStateProviderType;

public class ModSimpleBlockStateProvider extends BlockStateProvider {
    public static final Codec <ModSimpleBlockStateProvider> CODEC;

    static {
        CODEC = BlockState.CODEC.fieldOf("state").xmap(ModSimpleBlockStateProvider::new, (modSimpleBlockStateProvider) -> {
            return modSimpleBlockStateProvider.state;
        }).codec();
    }

    private final BlockState state;

    public ModSimpleBlockStateProvider(BlockState state) {
        this.state = state;
    }

    @Override protected BlockStateProviderType <?> getType() {
        return ModWorldGen.MOD_SIMPLE_BLOCK_STATE_PROVIDER;
    }

    @Override public BlockState get(Random randomSource, BlockPos blockPos) {
        return this.state;
    }
}
