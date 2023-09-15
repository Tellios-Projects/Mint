package net.leafenzo.mint.datageneration;

import com.ibm.icu.text.Normalizer2;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leafenzo.mint.block.MintCropBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
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

    public final void registerFlowerPot(BlockStateModelGenerator blockStateModelGenerator, Block plantBlock, Block flowerPotBlock, BlockStateModelGenerator.TintType tintType) {
        TextureMap textureMap = TextureMap.plant(plantBlock);
        Identifier identifier = tintType.getFlowerPotCrossModel().upload(flowerPotBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(flowerPotBlock, identifier));
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // MINT - Special
        blockStateModelGenerator.registerCrop(ModBlocks.MINT_CROP, MintCropBlock.AGE, IntStream.rangeClosed(0, MintCropBlock.MAX_AGE).toArray());
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_SPRIG_BLOCK);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.WILD_MINT, ModBlocks.POTTED_WILD_MINT, BlockStateModelGenerator.TintType.NOT_TINTED);

        BlockStateModelGenerator.BlockTexturePool mintBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_BRICKS);
        mintBricksTexturePool.slab(ModBlocks.MINT_BRICK_SLAB);
        mintBricksTexturePool.stairs(ModBlocks.MINT_BRICK_STAIRS);
        //mintBricksTexturePool.wall(ModBlocks.MINT_BRICK_WALL);

        // PEACH - Special
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.PEACH_LOG);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.HYPERICUM, ModBlocks.POTTED_HYPERICUM, BlockStateModelGenerator.TintType.NOT_TINTED);
        //coral anemone

        // PERIWINKLE - Special
        BlockStateModelGenerator.BlockTexturePool lavenderBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LAVENDER_BRICKS);
        lavenderBricksTexturePool.slab(ModBlocks.LAVENDER_BRICK_SLAB);
        lavenderBricksTexturePool.stairs(ModBlocks.LAVENDER_BRICK_STAIRS);
        lavenderBricksTexturePool.wall(ModBlocks.LAVENDER_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool mossyLavenderBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MOSSY_LAVENDER_BRICKS);
        mossyLavenderBricksTexturePool.slab(ModBlocks.MOSSY_LAVENDER_BRICK_SLAB);
        mossyLavenderBricksTexturePool.stairs(ModBlocks.MOSSY_LAVENDER_BRICK_STAIRS);
        mossyLavenderBricksTexturePool.wall(ModBlocks.MOSSY_LAVENDER_BRICK_WALL);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.LAVENDER_CLAY);
        blockStateModelGenerator.registerAxisRotated(ModBlocks.LAVENDER_BUSHEL, TexturedModel.CUBE_BOTTOM_TOP);
        blockStateModelGenerator.registerFlowerbed(ModBlocks.PERIWINKLE_PETALS);
        blockStateModelGenerator.registerLantern(ModBlocks.LAVENDER_OIL_LANTERN);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.HIDCOTE_LAVENDER, ModBlocks.POTTED_HIDCOTE_LAVENDER, BlockStateModelGenerator.TintType.NOT_TINTED);

        // ARTICHOKE - Special
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.WAXCAP_MUSHROOM, ModBlocks.POTTED_WAXCAP_MUSHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerItemModel(ModBlocks.WAXCAP_WAX.asItem()); // So it uses the right item texture
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.WAXCAP_WAX, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerItemModel(ModBlocks.THISTLE_FLOWER.asItem()); // for some reason this needs to be split up like this to make it's blockItem grab from an item texture without duplicate model errors
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.THISTLE_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ModBlocks.THISTLE_FLOWER, ModBlocks.POTTED_THISTLE_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.WAXCAP_WAX_BLOCK);
        blockStateModelGenerator.registerAxisRotated(ModBlocks.WAXCAP_STEM_BLOCK, TexturedModel.CUBE_ALL);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.WAXCAP_CAP_BLOCK);

        //Main
//  SLABS & STAIRS & WALLS
        //not standardized yet

//  FLOWER_POT_BLOCKS & FLOWERS
//        for (Block block : ModBlocks.FLOWER_POT_FROM_BLOCK.keySet()) {
//            registerFlowerPotPlant(blockStateModelGenerator, block, ModBlocks.FLOWER_POT_FROM_BLOCK.get(block), BlockStateModelGenerator.TintType.NOT_TINTED);
////            blockStateModelGenerator.registerFlowerPotPlant(block, ModBlocks.FLOWER_POT_FROM_BLOCK.get(block), BlockStateModelGenerator.TintType.NOT_TINTED);
//        }

//  WOOL_BLOCKS     //  CARPET_BLOCKS
        for(Block wool : ModBlocks.WOOL_BLOCKS) {
            Block carpet = ModBlocks.WOOL_CARPET_FROM_WOOL.get(wool);
            if(carpet != null) { blockStateModelGenerator.registerWoolAndCarpet(wool, carpet);  }
            else { blockStateModelGenerator.registerCubeAllModelTexturePool(wool); }
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
            blockStateModelGenerator.registerSouthDefaultHorizontalFacing(TexturedModel.TEMPLATE_GLAZED_TERRACOTTA, block);
            //registerUpDefaultOrientable(blockStateModelGenerator, block, TexturedModel.CUBE_ALL);
        }

//  STAINED_GLASS_BLOCKS  //  STAINED_GLASS_PANE_BLOCKS
        for(Block glass : ModBlocks.STAINED_GLASS_BLOCKS) {
            Block pane = ModBlocks.STAINED_GLASS_PANE_FROM_STAINED_GLASS.get(glass);
            if(pane != null) { blockStateModelGenerator.registerGlassPane(glass, pane);  }
            else { blockStateModelGenerator.registerCubeAllModelTexturePool(glass); }
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
        for(Block candle : ModBlocks.CANDLE_BLOCKS) {
            Block cake = ModBlocks.CANDLE_CAKE_FROM_CANDLE.get(candle);
            if(cake != null) { blockStateModelGenerator.registerCandle(candle, cake); }
            else { throw new RuntimeException(Registries.BLOCK.getId(candle).toString() + "does not have a candle cake"); }
        }

//  BANNER_BLOCKS     // WALL_BANNER_BLOCKS
        for(Block banner : ModBlocks.BANNER_BLOCKS) {
            Block wallBanner = ModBlocks.WALL_BANNER_FROM_BANNER.get(banner);
            if(wallBanner != null) {
                blockStateModelGenerator.registerBuiltin(Registries.BLOCK.getId(banner), Blocks.OAK_PLANKS)
                        .includeWithItem(banner)
                        .includeWithoutItem(wallBanner);
            }
            else { throw new RuntimeException(Registries.BLOCK.getId(banner).toString() + "does not have a wall banner"); }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.MINT_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINT_TEA, Models.GENERATED);

        itemModelGenerator.register(ModItems.PEACH_PIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_PEACH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_COBBLER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRUIT_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMBROSIA, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_ANEMONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORAL_ANEMONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_BRANCH, Models.GENERATED);

        itemModelGenerator.register(ModItems.FLOWERING_MELON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_LAVENDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVENDER_BREAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVENDER_SOAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVENDER_OIL, Models.GENERATED);

        //itemModelGenerator.register(ModItems.THISTLE_FLOWER, Models.GENERATED);

//        itemModelGenerator.register(ModBlocks.THISTLE_FLOWER.asItem(), Models.GENERATED);
//        itemModelGenerator.register(ModBlocks.WAXCAP_WAX.asItem(), Models.GENERATED);

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