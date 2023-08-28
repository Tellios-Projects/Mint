package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends FabricTagProvider<Block> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public ModBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, Registries.BLOCK.getKey(), registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
    // Special - MINT
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.MINT_SPRIG_BLOCK)
        ;

    // Main
//  WOOL_BLOCKS
        for (Block block : ModBlocks.WOOL_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.WOOL).add(block);
        }

//  CARPET_BLOCKS
        for (Block block : ModBlocks.WOOL_CARPET_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.WOOL_CARPETS).add(block);
        }

////  TERRACOTTA_BLOCKS
        for (Block block : ModBlocks.TERRACOTTA_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
        }

////  CONCRETE_POWDER_BLOCKS
        for (Block block : ModBlocks.CONCRETE_POWDER_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block);
        }

////  GLAZED_TERRACOTTA_BLOCKS
        for (Block block : ModBlocks.GLAZED_TERRACOTTA_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
        }

////  STAINED_GLASS_BLOCKS
//
////  STAINED_GLASS_PANE_BLOCKS
//
////  SHULKER_BOX_BLOCKS
        for (Block block : ModBlocks.SHULKER_BOX_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.SHULKER_BOXES).add(block);
        }

////  BED_BLOCKS
        for (Block block : ModBlocks.BED_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.BEDS).add(block);
        }

////  CANDLE_BLOCKS
        for (Block block : ModBlocks.CANDLE_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.CANDLES).add(block);
        }

////  CANDLE_CAKE_BLOCKS
        for (Block block : ModBlocks.CANDLE_CAKE_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.CANDLE_CAKES).add(block);
        }

////  BANNER_BLOCKS
        for (Block block : ModBlocks.BANNER_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.BANNERS).add(block);
        }

////  WALL_BANNER_BLOCKS
        for (Block block : ModBlocks.WALL_BANNER_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.BANNERS).add(block);
        }

////  FLOWERS
        for (Block block : ModBlocks.SMALL_FLOWERS) {
            getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
        }

////  FLOWER_POTS
        for (Block block : ModBlocks.FLOWER_POTS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(block);
        }
    }
}

