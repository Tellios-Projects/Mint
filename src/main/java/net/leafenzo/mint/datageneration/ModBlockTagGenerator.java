package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

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
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.BED_BLOCKS)
                .add(ModBlocks.BANNER_BLOCKS)
                .add(ModBlocks.WALL_BANNER_BLOCKS)
        ;

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.TERRACOTTA_BLOCKS)
                .add(ModBlocks.CONCRETE_BLOCKS)
                .add(ModBlocks.GLAZED_TERRACOTTA_BLOCKS)
                .add(ModBlocks.SHULKER_BOX_BLOCKS)
        ;

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
              .add(ModBlocks.CONCRETE_POWDER_BLOCKS)
        ;

        getOrCreateTagBuilder(BlockTags.BANNERS)
                .add(ModBlocks.BANNER_BLOCKS)
                .add(ModBlocks.WALL_BANNER_BLOCKS)
        ;

        getOrCreateTagBuilder(BlockTags.CANDLES)
                .add(ModBlocks.CANDLE_BLOCKS);
        ;

        getOrCreateTagBuilder(BlockTags.CANDLE_CAKES)
                .add(ModBlocks.CANDLE_CAKE_BLOCKS);
        ;

        getOrCreateTagBuilder(BlockTags.SHULKER_BOXES)
                .add(ModBlocks.SHULKER_BOX_BLOCKS)
        ;

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.MINT_SPRIG_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.WOOL)
                .add(ModBlocks.WOOL_BLOCKS)
        ;

        getOrCreateTagBuilder(BlockTags.WOOL_CARPETS)
                .add(ModBlocks.CARPET_BLOCKS)
        ;
    }
}

