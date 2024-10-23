package net.leafenzo.mint.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.leafenzo.mint.util.ElsDyeModWorldGen;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;


/**
 * This decorator exists to make mushroom blocks in the foliage of a "tree" that uses mushroom blocks have correct blockstates for interior faces.
 */
public class MushroomBlockDirectionDecorator extends TreeDecorator {
    public static final Codec<MushroomBlockDirectionDecorator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BlockStateProvider.TYPE_CODEC.fieldOf("block_provider").forGetter((treeDecorator) -> treeDecorator.blockProvider))
                    .apply(instance, MushroomBlockDirectionDecorator::new)
    );

    private final BlockStateProvider blockProvider;

    public MushroomBlockDirectionDecorator(BlockStateProvider blockProvider) {
        this.blockProvider = blockProvider;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return ElsDyeModWorldGen.MUSHROOM_BLOCK_DIRECTION_DECORATOR;
    }

    public void generate(Generator context) {
        Random randomSource = context.getRandom();
        context.getLeavesPositions().forEach((blockPos) -> {
            boolean west = !context.getWorld().testBlockState(blockPos.west(), state -> state.getBlock() instanceof MushroomBlock);
            boolean east = !context.getWorld().testBlockState(blockPos.east(), state -> state.getBlock() instanceof MushroomBlock);
            boolean north = !context.getWorld().testBlockState(blockPos.north(), state -> state.getBlock() instanceof MushroomBlock);
            boolean south = !context.getWorld().testBlockState(blockPos.south(), state -> state.getBlock() instanceof MushroomBlock);
            boolean up = !context.getWorld().testBlockState(blockPos.up(), state -> state.getBlock() instanceof MushroomBlock);
            boolean down = !context.getWorld().testBlockState(blockPos.down(), state -> state.getBlock() instanceof MushroomBlock);

            context.replace(blockPos, blockProvider.get(randomSource, blockPos)
                    .withIfExists(MushroomBlock.WEST, west)
                    .withIfExists(MushroomBlock.EAST, east)
                    .withIfExists(MushroomBlock.NORTH, north)
                    .withIfExists(MushroomBlock.SOUTH, south)
                    .withIfExists(MushroomBlock.UP, up)
                    .withIfExists(MushroomBlock.DOWN, down)
            );
        });
    }
}
