package net.leafenzo.mint.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import kotlin.reflect.jvm.internal.impl.util.ModuleVisibilityHelper;
import net.leafenzo.mint.registry.tag.ModTags;
import net.leafenzo.mint.util.ModWorldGen;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.ArrayList;

public class HugeWaxcapMushroomDecorator extends TreeDecorator {
    public static final Codec<HugeWaxcapMushroomDecorator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((treeDecorator) -> treeDecorator.probability),
                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability2").forGetter((treeDecorator) -> treeDecorator.probability2),
                    Codec.floatRange(0.0F, 1.0F).fieldOf("probability3").forGetter((treeDecorator) -> treeDecorator.probability3),
                    BlockStateProvider.TYPE_CODEC.fieldOf("block_provider").forGetter((treeDecorator) -> treeDecorator.blockProvider),
                    BlockStateProvider.TYPE_CODEC.fieldOf("block_provider2").forGetter((treeDecorator) -> treeDecorator.blockProvider2)
            ).apply(instance, HugeWaxcapMushroomDecorator::new)
    );

    private final float probability;
    private final float probability2;
    private final float probability3;
    private final BlockStateProvider blockProvider;
    private final BlockStateProvider blockProvider2;

    public HugeWaxcapMushroomDecorator(float probability, float probability2, float probability3, BlockStateProvider blockProvider, BlockStateProvider blockProvider2) {
        this.probability = probability;
        this.probability2 = probability2;
        this.probability3 = probability3;
        this.blockProvider = blockProvider;
        this.blockProvider2 = blockProvider2;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return ModWorldGen.HUGE_WAXCAP_MUSHROOM_DECORATOR;
    }

    public void generate(Generator context) {
        Random randomSource = context.getRandom();

        //Just within the gills
        context.getLeavesPositions().forEach((blockPos) -> {
            BlockPos blockPos2 = blockPos.down();
            if (context.getWorld().testBlockState(blockPos2, state -> state.getBlock() instanceof MushroomBlock) && !context.getLogPositions().contains(blockPos2)) { //Only replaces mushroom blocks, not including the stem
                //Decide whether to put gunk up in here
                if (randomSource.nextFloat() < probability2) {
                    BlockStateProvider replacement = randomSource.nextFloat() < probability3 ? blockProvider2 : blockProvider;
                    context.replace(blockPos2, replacement.get(randomSource, blockPos2));
                }
                //Or just air
                else {
                    context.replace(blockPos2, Blocks.AIR.getDefaultState());
                }
            }
        });

        //Including the bottom rim of the cap
        context.getLeavesPositions().forEach((blockPos) -> {
            BlockPos blockPos3 = blockPos.down();
            if(context.getWorld().testBlockState(blockPos, state -> state.getBlock() instanceof MushroomBlock) && context.isAir(blockPos3)) { //If there's air there and mushroom above it
                if(randomSource.nextFloat() < probability) {
                    context.replace(blockPos3, blockProvider.get(randomSource, blockPos3));
                }
            }
        });
    }

    private boolean adjacentToStem(BlockPos blockPos, ObjectArrayList<BlockPos> stem) {
        return stem.contains(blockPos.add(-1,0,-1))
          || stem.contains(blockPos.add(-1,0,0))
          || stem.contains(blockPos.add(-1,0,1))
          || stem.contains(blockPos.add(0,0,-1))
          || stem.contains(blockPos.add(0,0,1))
          || stem.contains(blockPos.add(1,0,-1))
          || stem.contains(blockPos.add(1,0,0))
          || stem.contains(blockPos.add(1,0,1));
    }

//    private static void placeDecorations(BlockPos pos, BlockStateProvider block, Generator generator, int number) {
//        Random random = generator.getRandom();
//        generator.replace(pos, block.get(random, pos));
//        for(pos = pos.down(); number > 0; --number) {
//            if(generator.isAir(pos)) {
//                if(number == 1 || !generator.isAir(pos.down()) || random.nextBoolean()) {
//                    generator.replace(pos, block2.get(random, pos));
//                    break;
//                }
//                generator.replace(pos, block.get(random, pos));
//            }
//            pos = pos.down();
//        }
//    }
}
