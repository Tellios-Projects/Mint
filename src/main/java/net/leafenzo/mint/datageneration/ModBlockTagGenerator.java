package net.leafenzo.mint.datageneration;

import dev.architectury.platform.Mod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.registry.tag.ModTags;
import net.minecraft.block.*;
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
    // common tags or Tags containing vanilla blocks
//        getOrCreateTagBuilder(ModTags.Blocks.MUSHROOM_BLOCKS)
//                .add(Blocks.RED_MUSHROOM_BLOCK)
//                .add(Blocks.BROWN_MUSHROOM_BLOCK)

    // Special
        //TODO make sure this all checks out
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.MINT_SPRIG_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.FLOWERS)
                .add(ModBlocks.PERIWINKLE_PETALS)
        ;

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.MINT_BRICKS)
                .add(ModBlocks.MINT_BRICK_SLAB)
                .add(ModBlocks.MINT_BRICK_STAIRS)
                .add(ModBlocks.MINT_BRICK_WALL)

                .add(ModBlocks.LAVENDER_OIL_LANTERN)
                .add(ModBlocks.LAVENDER_BRICKS)
                .add(ModBlocks.LAVENDER_BRICK_SLAB)
                .add(ModBlocks.LAVENDER_BRICK_STAIRS)
                .add(ModBlocks.LAVENDER_BRICK_WALL)
                .add(ModBlocks.MOSSY_LAVENDER_BRICKS)
                .add(ModBlocks.MOSSY_LAVENDER_BRICK_SLAB)
                .add(ModBlocks.MOSSY_LAVENDER_BRICK_STAIRS)
                .add(ModBlocks.MOSSY_LAVENDER_BRICK_WALL)

                .add(ModBlocks.CRACKED_CORALSOIL_BRICKS)
                .add(ModBlocks.CORALSOIL_BRICKS)
                .add(ModBlocks.CORALSOIL_BRICK_SLAB)
                .add(ModBlocks.CORALSOIL_BRICK_STAIRS)
                .add(ModBlocks.CORALSOIL_BRICK_WALL)
        ;

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.LAVENDER_CLAY)
                .add(ModBlocks.CORALSOIL)

                .add(ModBlocks.WAXCAP_WAX_BLOCK)
        ;

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(ModBlocks.WAXCAP_STEM_BLOCK)
                .add(ModBlocks.WAXCAP_CAP_BLOCK)
                .add(ModBlocks.WINTERGREEN_CANDY_CANE_BLOCK)
                .add(ModBlocks.WINTERGREEN_CANDY_CANE_BARK)
                .add(ModBlocks.PEPPERMINT_CANDY_CANE_BLOCK)
                .add(ModBlocks.PEPPERMINT_CANDY_CANE_BARK)
        ;

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.LAVENDER_BUSHEL)

                .add(ModBlocks.WAXCAP_GILL_SLAB)
                .add(ModBlocks.WAXCAP_GILLS)
        ;


    // Main
// SLABS & STAIRS & WALLS
        for (Block block : ModBlocks.SLABS) {
            getOrCreateTagBuilder(BlockTags.SLABS).add(block);
        }
        for (Block block : ModBlocks.STAIRS) {
            getOrCreateTagBuilder(BlockTags.STAIRS).add(block);
        }
        for (Block block : ModBlocks.WALLS) {
            getOrCreateTagBuilder(BlockTags.WALLS).add(block);
        }
        for (Block block : ModBlocks.WOODEN_FENCES) {
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(block);
        }
        for (Block block : ModBlocks.FENCE_GATES) {
            getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(block);
        }
        for (Block block : ModBlocks.SIGNS) {
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
        for (Block block : ModBlocks.WOODEN_DOORS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(block);
        }
        for (Block block : ModBlocks.WOODEN_TRAPDOORS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(block);
        }
        for (Block block : ModBlocks.WOODEN_STAIRS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(block);
        }
        for (Block block : ModBlocks.WOODEN_SLABS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(block);
        }
        for (Block block : ModBlocks.WOODEN_PRESSURE_PLATES) {
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(block);
        }
        for (Block block : ModBlocks.WOODEN_BUTTONS) {
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(block);
        }
        for (Block block : ModBlocks.LOGS_THAT_BURN) {
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add(block);
        }
        for (Block block : ModBlocks.PLANKS) {
            getOrCreateTagBuilder(BlockTags.PLANKS).add(block);
        }
        for (Block block : ModBlocks.LEAVES) {
            getOrCreateTagBuilder(BlockTags.LEAVES).add(block);
        }


//  WOOL_BLOCKS
        for (Block block : ModBlocks.WOOL_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.WOOL).add(block);
        }

//  CARPET_BLOCKS
        for (Block block : ModBlocks.WOOL_CARPET_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.WOOL_CARPETS).add(block);
        }

////  TERRACOTTA_BLOCKS
        for (Block block : ModBlocks.DYED_TERRACOTTA_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.DEAD_BUSH_MAY_PLACE_ON).add(block);
        }

////  CONCRETE_BLOCKS
        for (Block block : ModBlocks.CONCRETE_BLOCKS) {
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
        for (Block block : ModBlocks.STAINED_GLASS_BLOCKS) {
            getOrCreateTagBuilder(ModTags.Blocks.GLASS).add(block);
            getOrCreateTagBuilder(ModTags.Blocks.GLASS_BLOCKS).add(block); //common tags just wanna be silly ig
        }

////  STAINED_GLASS_PANE_BLOCKS
        // Not needed, I think?

////  SHULKER_BOX_BLOCKS
        for (Block block : ModBlocks.SHULKER_BOX_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.SHULKER_BOXES).add(block);
        }

////  BED_BLOCKS
        for (Block block : ModBlocks.BED_BLOCKS) {
//            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add(block); // Nope! Not supposed to use that
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

////  SMALL FLOWERS
        for (Block block : ModBlocks.SMALL_FLOWERS) {
            getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(block);
        }

////  FLOWER_POTS
        for (Block block : ModBlocks.FLOWER_POTS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(block);
        }


        //Decor Additions
        for (Block block : ModBlocks.ALL_MUCKTUFF_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
            getOrCreateTagBuilder(BlockTags.MOSS_REPLACEABLE).add(block);
        }

        for (Block block : ModBlocks.ALL_CORRUGATED_IRON_BLOCKS) {
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);
        }

    }
}

