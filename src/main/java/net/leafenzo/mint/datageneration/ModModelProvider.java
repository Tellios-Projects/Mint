package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leafenzo.mint.block.MintCropBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.stream.IntStream;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void registerUpDefaultOrientable(BlockStateModelGenerator blockStateModelGenerator, Block block, TexturedModel.Factory modelFactory) {
        Identifier identifier = modelFactory.upload(block, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).coordinate(this.createUpDefaultRotationStates()));
        //blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateModelGenerator.createNorthDefaultRotationStates(), identifier));
    }
    private static BlockStateVariantMap createUpDefaultRotationStates() {
        return BlockStateVariantMap.create(Properties.FACING).register(Direction.DOWN, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180)).register(Direction.UP, BlockStateVariant.create()).register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90)).register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R180)).register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R270)).register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.Y, VariantSettings.Rotation.R90));
    }
    private void registerWithModelId(BlockStateModelGenerator blockStateModelGenerator, Block block, Identifier ModelId) {
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, ModelId));
    }

//    public final void registerBed(BlockStateModelGenerator blockStateModelGenerator, Block bed, Block particleSource) {
//        blockStateModelGenerator.blockStateCollector.accept(Models.TEMPLATE_BED.upload(ModelIds.getItemModelId(bed.asItem()), TextureMap.particle(particleSource), blockStateModelGenerator.modelCollector));
//    }


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    // Block Models
        blockStateModelGenerator.registerCrop(ModBlocks.MINT_CROP, MintCropBlock.AGE, IntStream.rangeClosed(0, MintCropBlock.MAX_AGE).toArray());
        blockStateModelGenerator.registerWoolAndCarpet(ModBlocks.MINT_WOOL, ModBlocks.MINT_CARPET);

        blockStateModelGenerator.registerGlassPane(ModBlocks.MINT_STAINED_GLASS, ModBlocks.MINT_STAINED_GLASS_PANE);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_CONCRETE);
        blockStateModelGenerator.registerRandomHorizontalRotations(TexturedModel.CUBE_ALL, ModBlocks.MINT_CONCRETE_POWDER);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_TERRACOTTA);
        registerUpDefaultOrientable(blockStateModelGenerator, ModBlocks.MINT_GLAZED_TERRACOTTA, TexturedModel.CUBE_ALL);
        blockStateModelGenerator.registerCandle(ModBlocks.MINT_CANDLE, ModBlocks.MINT_CANDLE_CAKE);

        blockStateModelGenerator.registerBuiltin(ModelIds.getMinecraftNamespacedBlock("banner"), Blocks.OAK_PLANKS)
                .includeWithItem(ModBlocks.MINT_BANNER)
                .includeWithoutItem(ModBlocks.MINT_WALL_BANNER);

        blockStateModelGenerator.registerBuiltin(ModelIds.getMinecraftNamespacedBlock("bed"), Blocks.OAK_PLANKS)
                .includeWithItem(Models.TEMPLATE_BED, ModBlocks.MINT_BED);

        blockStateModelGenerator.registerShulkerBox(ModBlocks.MINT_SHULKER_BOX);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_SPRIG_BLOCK);
        blockStateModelGenerator.registerTintableCross(ModBlocks.WILD_MINT, BlockStateModelGenerator.TintType.NOT_TINTED);

        BlockStateModelGenerator.BlockTexturePool mintBricksTexturePool =
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_BRICKS);
        mintBricksTexturePool.slab(ModBlocks.MINT_BRICK_SLAB);
        mintBricksTexturePool.stairs(ModBlocks.MINT_BRICK_STAIRS);
        //mintBricksTexturePool.wall(ModBlocks.MINT_BRICK_WALL);





        // Cube with all sides the same texture, IE Dirt
        // blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.COMPRESSED_STONE);

        // Non-rotatable Cube with faces of different textures
        //blockStateModelGenerator.registerSingleton(ModBlocks.BOOK_BLOCK, TexturedModel.CUBE_COLUMN);

        // Pillar Block, IE Oak Logs
        //blockStateModelGenerator.registerAxisRotated(ModBlocks.SUPER_COMPRESSED_DEEPSLATE, TexturedModel.CUBE_COLUMN);

        // Reversible Pillar Block
        //registerUpDefaultOrientable(blockStateModelGenerator, ModBlocks.BLAZE_ROD_BLOCK, TexturedModel.CUBE_COLUMN);

        // Leaves Like Blocks, includes things that sample from Biome Color maps.
        //blockStateModelGenerator.registerSingleton(ModBlocks.GRASS_CLIPPINGS_BLOCK, TexturedModel.LEAVES);


        // Block Item Models
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_CROP, Super.asResource("block/mint_crop"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.WILD_MINT, Super.asResource("block/wild_mint"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_WOOL, Super.asResource("block/mint_wool"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_CARPET, Super.asResource("block/mint_carpet"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_CONCRETE, Super.asResource("block/mint_concrete"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_CONCRETE_POWDER, Super.asResource("block/mint_concrete_powder"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_TERRACOTTA, Super.asResource("block/mint_terracotta"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_GLAZED_TERRACOTTA, Super.asResource("block/mint_glazed_terracotta"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_STAINED_GLASS, Super.asResource("block/mint_stained_glass"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_CANDLE, Super.asResource("block/mint_candle"));
//        blockStateModelGenerator.registerParentedItemModel(ModBlocks.MINT_CANDLE_CAKE, Super.asResource("block/mint_candle_cake"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //itemModelGenerator.register(ModItems.MINT_SPRIG, Models.GENERATED); // this is a duplicate... somehow???????
        itemModelGenerator.register(ModItems.MINT_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINT_DYE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINT_TEA, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINT_BANNER, Models.TEMPLATE_BANNER);
        //itemModelGenerator.register(ModItems.MINT_BED, Models.TEMPLATE_BED);
    }
}