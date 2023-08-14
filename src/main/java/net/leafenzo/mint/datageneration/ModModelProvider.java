package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.MintCropBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.recipe.ModRecipeSerializer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
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
        // MINT - Special
        blockStateModelGenerator.registerCrop(ModBlocks.MINT_CROP, MintCropBlock.AGE, IntStream.rangeClosed(0, MintCropBlock.MAX_AGE).toArray());
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_SPRIG_BLOCK);
        blockStateModelGenerator.registerTintableCross(ModBlocks.WILD_MINT, BlockStateModelGenerator.TintType.NOT_TINTED);
        BlockStateModelGenerator.BlockTexturePool mintBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_BRICKS);
        mintBricksTexturePool.slab(ModBlocks.MINT_BRICK_SLAB);
        mintBricksTexturePool.stairs(ModBlocks.MINT_BRICK_STAIRS);
        //mintBricksTexturePool.wall(ModBlocks.MINT_BRICK_WALL);


        //Main
//  WOOL_BLOCKS     //  CARPET_BLOCKS
        for (int i = 0; i < ModBlocks.CARPET_BLOCKS.length; i++) {
            blockStateModelGenerator.registerWoolAndCarpet(ModBlocks.WOOL_BLOCKS[i], ModBlocks.CARPET_BLOCKS[i]);
        }

//  TERRACOTTA_BLOCKS
        for (Block block : ModBlocks.TERRACOTTA_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(block);
        }

//  CONCRETE_BLOCKS
        for (Block block : ModBlocks.CONCRETE_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(block);
        }

//  CONCRETE_POWDER_BLOCKS
        for (Block block : ModBlocks.CONCRETE_POWDER_BLOCKS) {
            blockStateModelGenerator.registerRandomHorizontalRotations(TexturedModel.CUBE_ALL, block);
        }

//  GLAZED_TERRACOTTA_BLOCKS
        for (Block block : ModBlocks.GLAZED_TERRACOTTA_BLOCKS) {
            registerUpDefaultOrientable(blockStateModelGenerator, block, TexturedModel.CUBE_ALL);
        }

//  STAINED_GLASS_BLOCKS  //  STAINED_GLASS_PANE_BLOCKS
        for (int i = 0; i < ModBlocks.STAINED_GLASS_PANE_BLOCKS.length; i++) {
            blockStateModelGenerator.registerGlassPane(ModBlocks.STAINED_GLASS_BLOCKS[i], ModBlocks.STAINED_GLASS_PANE_BLOCKS[i]);
        }

//  SHULKER_BOX_BLOCKS
        for (Block block : ModBlocks.SHULKER_BOX_BLOCKS) {
            blockStateModelGenerator.registerShulkerBox(block);
        }

//  BED_BLOCKS
        for (Block block : ModBlocks.BED_BLOCKS) {
            blockStateModelGenerator.registerBuiltin(Registries.BLOCK.getId(block), Blocks.OAK_PLANKS)
                    .includeWithItem(Models.TEMPLATE_BED, block);
        }

//  CANDLE_BLOCKS     //  CANDLE_CAKE_BLOCKS
        for (int i = 0; i < ModBlocks.CANDLE_CAKE_BLOCKS.length; i++) {
            blockStateModelGenerator.registerCandle(ModBlocks.CANDLE_BLOCKS[i], ModBlocks.CANDLE_CAKE_BLOCKS[i]);
        }

//  BANNER_BLOCKS     // WALL_BANNER_BLOCKS
        for (int i = 0; i < ModBlocks.WALL_BANNER_BLOCKS.length; i++) {
            blockStateModelGenerator.registerBuiltin(Registries.BLOCK.getId(ModBlocks.BANNER_BLOCKS[i]), Blocks.OAK_PLANKS)
                    .includeWithItem(ModBlocks.BANNER_BLOCKS[i])
                    .includeWithoutItem(ModBlocks.WALL_BANNER_BLOCKS[i]);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //itemModelGenerator.register(ModItems.MINT_SPRIG, Models.GENERATED); // this is a duplicate... somehow???????
        itemModelGenerator.register(ModItems.MINT_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINT_TEA, Models.GENERATED);

//  DYES
        for(Item item : ModItems.DYE_ITEMS) {
            itemModelGenerator.register(item, Models.GENERATED);
        }

//  BANNERS
        for(Block block : ModBlocks.BANNER_BLOCKS) {
            itemModelGenerator.register(block.asItem(), Models.TEMPLATE_BANNER);
        }

        //itemModelGenerator.register(ModItems.MINT_BED, Models.TEMPLATE_BED);
    }
}