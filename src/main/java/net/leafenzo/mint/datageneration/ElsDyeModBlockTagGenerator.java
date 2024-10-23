package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.registry.tag.ElsDyeModTags;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ElsDyeModBlockTagGenerator extends FabricTagProvider<Block> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public ElsDyeModBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, Registries.BLOCK.getKey(), registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
    // common tags or Tags containing vanilla blocks
//        getOrCreateTagBuilder(ModTags.Blocks.MUSHROOM_BLOCKS)
//                .add(Blocks.RED_MUSHROOM_BLOCK)
//                .add(Blocks.BROWN_MUSHROOM_BLOCK)

    // Special
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ElsDyeModBlocks.MINT_SPRIG_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(ElsDyeModBlocks.PERIWINKLE_PETALS)
        ;

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ElsDyeModBlocks.MINT_BRICKS)
                .add(ElsDyeModBlocks.MINT_BRICK_SLAB)
                .add(ElsDyeModBlocks.MINT_BRICK_STAIRS)
                .add(ElsDyeModBlocks.MINT_BRICK_WALL)

                .add(ElsDyeModBlocks.LAVENDER_OIL_LANTERN)
                .add(ElsDyeModBlocks.LAVENDER_BRICKS)
                .add(ElsDyeModBlocks.LAVENDER_BRICK_SLAB)
                .add(ElsDyeModBlocks.LAVENDER_BRICK_STAIRS)
                .add(ElsDyeModBlocks.LAVENDER_BRICK_WALL)
                .add(ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS)
                .add(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_SLAB)
                .add(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_STAIRS)
                .add(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_WALL)

                .add(ElsDyeModBlocks.CRACKED_CORALSOIL_BRICKS)
                .add(ElsDyeModBlocks.CORALSOIL_BRICKS)
                .add(ElsDyeModBlocks.CORALSOIL_BRICK_SLAB)
                .add(ElsDyeModBlocks.CORALSOIL_BRICK_STAIRS)
                .add(ElsDyeModBlocks.CORALSOIL_BRICK_WALL)

                .add(ElsDyeModBlocks.CINNABAR_BLOCK)
                .add(ElsDyeModBlocks.BUDDING_CINNABAR)
                .add(ElsDyeModBlocks.SMALL_CINNABAR_BUD)
                .add(ElsDyeModBlocks.MEDIUM_CINNABAR_BUD)
                .add(ElsDyeModBlocks.LARGE_CINNABAR_BUD)
                .add(ElsDyeModBlocks.CINNABAR_CLUSTER)
                .add(ElsDyeModBlocks.CINNABAR_PILLAR)
                .add(ElsDyeModBlocks.CINNAMON_BRICKS)
                .add(ElsDyeModBlocks.CINNAMON_BRICK_STAIRS)
                .add(ElsDyeModBlocks.CINNAMON_BRICK_SLAB)
                .add(ElsDyeModBlocks.CINNAMON_BRICK_WALL)
                .add(ElsDyeModBlocks.CRACKED_CINNAMON_BRICKS)

                .add(ElsDyeModBlocks.AMBER_BLOCK)
                .add(ElsDyeModBlocks.AMBER_BRICKS)
                .add(ElsDyeModBlocks.AMBER_BRICK_SLAB)
                .add(ElsDyeModBlocks.AMBER_BRICK_STAIRS)
                .add(ElsDyeModBlocks.AMBER_BRICK_WALL)
                .add(ElsDyeModBlocks.CHISELED_AMBER_BRICKS)
        ;

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ElsDyeModBlocks.LAVENDER_CLAY)
                .add(ElsDyeModBlocks.CORALSOIL)
                .add(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK)
                .add(ElsDyeModBlocks.MADDER_ROOTED_DIRT)
                .add(ElsDyeModBlocks.WAXCAP_WAX_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ElsDyeModBlocks.WAXCAP_STEM_BLOCK)
                .add(ElsDyeModBlocks.WAXCAP_CAP_BLOCK)
                .add(ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BLOCK)
                .add(ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BARK)
                .add(ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BLOCK)
                .add(ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BARK)
        ;

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ElsDyeModBlocks.LAVENDER_BUSHEL)
                .add(ElsDyeModBlocks.CACTUS_FEED)
                .add(ElsDyeModBlocks.WAXCAP_GILL_SLAB)
                .add(ElsDyeModBlocks.WAXCAP_GILLS)
        ;
        getOrCreateTagBuilder(BlockTags.DIRT)
                .add(ElsDyeModBlocks.MADDER_ROOTED_DIRT)
                .add(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK)
        ;


    // Main
// SLABS & STAIRS & WALLS
        for (Block block : ElsDyeModBlocks.SLABS) {
            getOrCreateTagBuilder(BlockTags.SLABS).add(block);
        }
        for (Block block : ElsDyeModBlocks.STAIRS) {
            getOrCreateTagBuilder(BlockTags.STAIRS).add(block);
        }
        for (Block block : ElsDyeModBlocks.WALLS) {
            getOrCreateTagBuilder(BlockTags.WALLS).add(block);
        }
        for (Block block : ElsDyeModBlocks.WOODEN_FENCES) {
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(block);
        }
        for (Block block : ElsDyeModBlocks.FENCE_GATES) {
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(block);
        }
        for (Block block : ElsDyeModBlocks.SIGNS) {
            if(block instanceof SignBlock) {
                getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(block);
            }
            else if(block instanceof WallSignBlock) {
                getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(block);
            }
            else if(block instanceof HangingSignBlock) {
                getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(block);
            }
            else if(block instanceof WallHangingSignBlock) {
                getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(block);
            }
        }
        for (Block block : ElsDyeModBlocks.WOODEN_DOORS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(block);
        }
        for (Block block : ElsDyeModBlocks.WOODEN_TRAPDOORS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(block);
        }
        for (Block block : ElsDyeModBlocks.WOODEN_STAIRS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(block);
        }
        for (Block block : ElsDyeModBlocks.WOODEN_SLABS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(block);
        }
        for (Block block : ElsDyeModBlocks.WOODEN_PRESSURE_PLATES) {
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(block);
        }
        for (Block block : ElsDyeModBlocks.WOODEN_BUTTONS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(block);
        }
        for (Block block : ElsDyeModBlocks.LOGS_THAT_BURN) {
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add(block);
        }
        for (Block block : ElsDyeModBlocks.LOGS) {
            getOrCreateTagBuilder(BlockTags.LOGS).add(block);
        }
        for (Block block : ElsDyeModBlocks.PLANKS) {
            getOrCreateTagBuilder(BlockTags.PLANKS).add(block);
        }
        for (Block block : ElsDyeModBlocks.LEAVES) {
            getOrCreateTagBuilder(BlockTags.LEAVES).add(block);
        }
        for (Block block : ElsDyeModBlocks.SAPLINGS) {
            getOrCreateTagBuilder(BlockTags.SAPLINGS).add(block);
        }


//  WOOL_BLOCKS
        for (Block block : ElsDyeModBlocks.WOOL_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.WOOL).add(block);
        }

//  CARPET_BLOCKS
        for (Block block : ElsDyeModBlocks.WOOL_CARPET_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.WOOL_CARPETS).add(block);
        }

////  TERRACOTTA_BLOCKS
        for (Block block : ElsDyeModBlocks.DYED_TERRACOTTA_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.DEAD_BUSH_MAY_PLACE_ON).add(block);
        }

////  CONCRETE_BLOCKS
        for (Block block : ElsDyeModBlocks.CONCRETE_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
        }

////  CONCRETE_POWDER_BLOCKS
        for (Block block : ElsDyeModBlocks.CONCRETE_POWDER_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).add(block);
        }

////  GLAZED_TERRACOTTA_BLOCKS
        for (Block block : ElsDyeModBlocks.GLAZED_TERRACOTTA_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
        }

////  STAINED_GLASS_BLOCKS
        for (Block block : ElsDyeModBlocks.STAINED_GLASS_BLOCKS) {
            getOrCreateTagBuilder(ElsDyeModTags.Blocks.GLASS).add(block);
            getOrCreateTagBuilder(ElsDyeModTags.Blocks.GLASS_BLOCKS).add(block); //common tags just wanna be silly ig
        }

////  STAINED_GLASS_PANE_BLOCKS
        // Not needed, I think?

////  SHULKER_BOX_BLOCKS
        for (Block block : ElsDyeModBlocks.SHULKER_BOX_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.SHULKER_BOXES).add(block);
        }

////  BED_BLOCKS
        for (Block block : ElsDyeModBlocks.BED_BLOCKS) {
//            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block); // Nope! Not supposed to use that
            getOrCreateTagBuilder(BlockTags.BEDS).add(block);
        }

////  CANDLE_BLOCKS
        for (Block block : ElsDyeModBlocks.CANDLE_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.CANDLES).add(block);
        }

////  CANDLE_CAKE_BLOCKS
        for (Block block : ElsDyeModBlocks.CANDLE_CAKE_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.CANDLE_CAKES).add(block);
        }

////  BANNER_BLOCKS
        for (Block block : ElsDyeModBlocks.BANNER_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.BANNERS).add(block);
        }

////  WALL_BANNER_BLOCKS
        for (Block block : ElsDyeModBlocks.WALL_BANNER_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.BANNERS).add(block);
        }

////  SMALL FLOWERS
        for (Block block : ElsDyeModBlocks.SMALL_FLOWERS) {
            getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
        }

////  FLOWER_POTS
        for (Block block : ElsDyeModBlocks.FLOWER_POTS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(block);
        }


        //Decor Additions
        for (Block block : ElsDyeModBlocks.ALL_MUCKTUFF_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.MOSS_REPLACEABLE).add(block);
        }

        for (Block block : ElsDyeModBlocks.ALL_CORRUGATED_IRON_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
        }

    }
}

