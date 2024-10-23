package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.block.custom.*;
import net.leafenzo.mint.item.ElsDyeModItems;
import net.leafenzo.mint.registration.ElsDyeModRegistryHelper;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.state.property.ElsDyeModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Arrays;
import java.util.stream.IntStream;

import static net.leafenzo.mint.registration.ElsDyeModRegistryHelper.ItemRegistry;
public class ElsDyeModModelProvider extends FabricModelProvider {
    public ElsDyeModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void registerUpDefaultOrientable(BlockStateModelGenerator blockStateModelGenerator, Block block, TexturedModel.Factory modelFactory) {
        Identifier identifier = modelFactory.upload(block, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).coordinate(createUpDefaultRotationStates()));
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

    public final void registerCoralAnemoneBlock(BlockStateModelGenerator blockStateModelGenerator) {
        Block block = ElsDyeModBlocks.CORAL_ANEMONE;
        Identifier identifier1 = new Identifier(ElsDyeMod.MOD_ID, "block/coral_anemone");
        Identifier identifier2 = new Identifier(ElsDyeMod.MOD_ID, "block/coral_anemone_waterlogged");
        blockStateModelGenerator.registerItemModel(block.asItem()); // So it uses the right item texture
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.WATERLOGGED, identifier2, identifier1)));
    }

    public final void registerFlowerPot(BlockStateModelGenerator blockStateModelGenerator, Block plantBlock, Block flowerPotBlock, BlockStateModelGenerator.TintType tintType) {
        TextureMap textureMap = TextureMap.plant(plantBlock);
        Identifier identifier = tintType.getFlowerPotCrossModel().upload(flowerPotBlock, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(flowerPotBlock, identifier));
    }

    /**
     * @param blockStateModelGenerator
     * @param block Block class must have Properties.FACING and ModProperties.DIAGONAL
     */
    public final void registerDiagonalBlock(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier straightId = Models.CUBE_ALL.upload(block, TextureMap.all(block), blockStateModelGenerator.modelCollector);
        Identifier diagonalId = Models.CUBE_ALL.upload(block, "_diagonal", TextureMap.all(TextureMap.getSubId(block, "_diagonal")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(ElsDyeModProperties.DIAGONAL, diagonalId, straightId))
                .coordinate(BlockStateModelGenerator.createNorthDefaultRotationStates()));
    }

    /**
     * @param blockStateModelGenerator
     * @param block Block class must have Properties.FACING and ModProperties.DIAGONAL
     */
    public final void registerDiagonalSlab(BlockStateModelGenerator blockStateModelGenerator, Block block, Block fullblock) {
        Identifier straightId = Models.CUBE_ALL.upload(block, "_double", TextureMap.all(fullblock), blockStateModelGenerator.modelCollector);
        Identifier diagonalId = Models.CUBE_ALL.upload(block, "diagonal_double", TextureMap.all(TextureMap.getSubId(fullblock, "_diagonal")), blockStateModelGenerator.modelCollector);

        Identifier slabId = Models.SLAB.upload(block, TextureMap.all(fullblock), blockStateModelGenerator.modelCollector);
        Identifier slabTopId = Models.SLAB_TOP.upload(block, "_top", TextureMap.all(fullblock), blockStateModelGenerator.modelCollector);

        Identifier diagonalSlabId = Models.SLAB.upload(block, "_diagonal", TextureMap.all(TextureMap.getSubId(fullblock, "_diagonal")), blockStateModelGenerator.modelCollector);
        Identifier diagonalSlabTopId = Models.SLAB_TOP.upload(block, "_diagonal_top", TextureMap.all(TextureMap.getSubId(fullblock, "_diagonal")), blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.SLAB_TYPE, ElsDyeModProperties.DIAGONAL)
                        .register(SlabType.BOTTOM, false, BlockStateVariant.create().put(VariantSettings.MODEL, slabId))
                        .register(SlabType.TOP, false, BlockStateVariant.create().put(VariantSettings.MODEL, slabTopId))
                        .register(SlabType.DOUBLE, false, BlockStateVariant.create().put(VariantSettings.MODEL, straightId))

                        .register(SlabType.BOTTOM, true, BlockStateVariant.create().put(VariantSettings.MODEL, diagonalSlabId))
                        .register(SlabType.TOP, true, BlockStateVariant.create().put(VariantSettings.MODEL, diagonalSlabTopId))
                        .register(SlabType.DOUBLE, true, BlockStateVariant.create().put(VariantSettings.MODEL, diagonalId))
                )
                .coordinate(BlockStateModelGenerator.createNorthDefaultRotationStates())
        );
    }

    /**
     * See also:
     * BlockStateModelGenerator.registerTripwireHook
     * BlockStateModelGenerator.registerDoubleBlock
     * BlockStateModelGenerator.registerCrop
     */
    public final void registerTwoTallCrop(BlockStateModelGenerator blockStateModelGenerator, Block crop, Property<Integer> ageProperty/*, int[] upperAgeTextureIndices, int[] lowerAgeTextureIndices*/) {
        //This creates some extra unused models. This causes no issues whatsoever, so I've decided not to fix it.
        for(int i = 0; i < ageProperty.getValues().size(); i++) {
            blockStateModelGenerator.createSubModel(crop, "_stage" + i + "_top", Models.CROSS, TextureMap::cross);
            blockStateModelGenerator.createSubModel(crop, "_stage" + i + "_bottom", Models.CROSS, TextureMap::cross);
        }
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop)
                .coordinate(BlockStateVariantMap.create(Properties.DOUBLE_BLOCK_HALF, ageProperty).register(
                        (a, b) -> BlockStateVariant.create().put(VariantSettings.MODEL, TextureMap.getSubId(crop, "_stage" + b + (a == DoubleBlockHalf.LOWER ? "_bottom" : "_top")))))
        );
    }
    public final void registerCrossCrop(BlockStateModelGenerator blockStateModelGenerator, Block crop, Property<Integer> ageProperty) {
        for(int i = 0; i < ageProperty.getValues().size(); i++) {
            blockStateModelGenerator.createSubModel(crop, "_stage" + i, Models.CROSS, TextureMap::cross);
        }
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(crop)
                .coordinate(BlockStateVariantMap.create(ageProperty).register(
                        (a) -> BlockStateVariant.create().put(VariantSettings.MODEL, TextureMap.getSubId(crop, "_stage" + a)))));
    }
    public final void createWoodSign(BlockStateModelGenerator blockStateModelGenerator, Block planks, Block signBlock, Block wallSignBlock) {
        TextureMap textureMapping = TextureMap.texture(planks);
        Identifier resourceLocation = Models.PARTICLE.upload(signBlock, textureMapping, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(signBlock, resourceLocation));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallSignBlock, resourceLocation));
        blockStateModelGenerator.registerItemModel(signBlock.asItem());
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallSignBlock);
    }
    public final void createHangingSign(BlockStateModelGenerator blockStateModelGenerator, Block strippedLog, Block hangingSign, Block wallHangingSign) {
        TextureMap textureMap = TextureMap.particle(strippedLog);
        Identifier identifier = Models.PARTICLE.upload(hangingSign, textureMap, blockStateModelGenerator.modelCollector);
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(hangingSign, identifier));
        blockStateModelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(wallHangingSign, identifier));
        blockStateModelGenerator.registerItemModel(hangingSign.asItem());
        blockStateModelGenerator.excludeFromSimpleItemModelGeneration(wallHangingSign);
    }
    public final void registerBlocksInWoodSet(BlockStateModelGenerator blockStateModelGenerator, WoodSet woodSet) {
        Block log = woodSet.getLog();
        Block wood = woodSet.getWood();
        
        if(log != null && wood != null) { blockStateModelGenerator.registerLog(log).log(log).wood(wood);  }
        else if(log != null) { blockStateModelGenerator.registerLog(log).log(log); }
        
        Block strippedLog = woodSet.getStrippedLog();
        Block strippedWood = woodSet.getStrippedWood();
        if(strippedLog != null && strippedWood != null) { blockStateModelGenerator.registerLog(strippedLog).log(strippedLog).wood(strippedWood);  }
        else if(strippedLog != null) { blockStateModelGenerator.registerLog(strippedLog).log(strippedLog); }
        
        Block leaves = woodSet.getLeaves();
        if(leaves != null) { blockStateModelGenerator.registerSingleton(leaves, TexturedModel.LEAVES); }

        Block sapling = woodSet.getSapling();
        Block pottedSapling = woodSet.getPottedSapling();
        if(sapling != null && pottedSapling != null) { blockStateModelGenerator.registerFlowerPotPlant(sapling, pottedSapling, BlockStateModelGenerator.TintType.NOT_TINTED); }
        else if(sapling != null) { blockStateModelGenerator.registerTintableCross(sapling, BlockStateModelGenerator.TintType.NOT_TINTED); }

        Block planks = woodSet.getPlanks();
        Block stairs = woodSet.getStairs();
        Block slab = woodSet.getSlab();
        Block fence = woodSet.getFence();
        Block fenceGate = woodSet.getFenceGate();
        Block pressurePlate = woodSet.getPressurePlate();
        Block button = woodSet.getButton();
        if(planks != null) {
            BlockStateModelGenerator.BlockTexturePool planksTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(planks);
            if(stairs != null) { planksTexturePool.stairs(stairs); }
            if(slab != null) { planksTexturePool.slab(slab); }
            if(fence != null) { planksTexturePool.fence(fence); }
            if(fenceGate != null) { planksTexturePool.fenceGate(fenceGate); }
            if(pressurePlate != null) { planksTexturePool.pressurePlate(pressurePlate); }
            if(button != null) { planksTexturePool.button(button); }
        }

        Block mosaic = woodSet.getMosaic();
        Block mosaicStairs = woodSet.getMosaicStairs();
        Block mosaicSlab = woodSet.getMosaicSlab();
        if(mosaic != null) {
            BlockStateModelGenerator.BlockTexturePool mosaicTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(mosaic);
            if(mosaicStairs != null) { mosaicTexturePool.stairs(mosaicStairs); }
            if(mosaicSlab != null) { mosaicTexturePool.slab(mosaicSlab); }
        }

        Block door = woodSet.getDoor();
        if(door != null) { blockStateModelGenerator.registerDoor(door); }

        Block trapDoor = woodSet.getTrapDoor();
        if(trapDoor != null) { blockStateModelGenerator.registerTrapdoor(trapDoor); }

        Block sign = woodSet.getSign();
        Block wallSign = woodSet.getWallSign();
        if(sign != null && wallSign != null && planks != null) { createWoodSign(blockStateModelGenerator, planks, sign, wallSign); }

        Block hangingSign = woodSet.getHangingSign();
        Block hangingWallSign = woodSet.getHangingWallSign();
        if(hangingSign != null && hangingWallSign != null && strippedLog != null) { createHangingSign(blockStateModelGenerator, strippedLog, hangingSign, hangingWallSign); }

        Item boatItem = woodSet.getBoatItem();
        if(boatItem != null) { blockStateModelGenerator.registerItemModel(boatItem);  }

        Item chestBoatItem = woodSet.getChestBoatItem();
        if(chestBoatItem != null) { blockStateModelGenerator.registerItemModel(chestBoatItem);  }
    }


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // MINT - Special
        blockStateModelGenerator.registerCrop(ElsDyeModBlocks.MINT_CROP, MintCropBlock.AGE, IntStream.rangeClosed(0, MintCropBlock.MAX_AGE).toArray());
        blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.MINT_SPRIG_BLOCK);
        blockStateModelGenerator.registerFlowerPotPlant(ElsDyeModBlocks.WILD_MINT, ElsDyeModBlocks.POTTED_WILD_MINT, BlockStateModelGenerator.TintType.NOT_TINTED);

        BlockStateModelGenerator.BlockTexturePool mintBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.MINT_BRICKS);
        mintBricksTexturePool.slab(ElsDyeModBlocks.MINT_BRICK_SLAB);
        mintBricksTexturePool.stairs(ElsDyeModBlocks.MINT_BRICK_STAIRS);
        mintBricksTexturePool.wall(ElsDyeModBlocks.MINT_BRICK_WALL);
        
        blockStateModelGenerator.registerLog(ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BLOCK).log(ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BLOCK).wood(ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BARK);
        blockStateModelGenerator.registerLog(ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BLOCK).log(ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BLOCK).wood(ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BARK);
//        if(log != null && wood != null) { blockStateModelGenerator.registerLog(log).log(log).wood(wood);  }
//        else if(log != null) { blockStateModelGenerator.registerLog(log).log(log); }

        // PEACH - Special
        this.registerCoralAnemoneBlock(blockStateModelGenerator);
        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.PEACH_TREE.asItem());
        this.registerTwoTallCrop(blockStateModelGenerator, ElsDyeModBlocks.PEACH_TREE, TwoTallCropBlock.AGE);
        blockStateModelGenerator.registerFlowerPotPlant(ElsDyeModBlocks.PEACH_SAPLING, ElsDyeModBlocks.POTTED_PEACH_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.PEACH_LEAVES);
        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.FLOWERING_PEACH_LEAVES);
        blockStateModelGenerator.registerFlowerPotPlant(ElsDyeModBlocks.HYPERICUM, ElsDyeModBlocks.POTTED_HYPERICUM, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.CORALSOIL);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.CRACKED_CORALSOIL_BRICKS);
        BlockStateModelGenerator.BlockTexturePool coralsoilBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.CORALSOIL_BRICKS);
        coralsoilBricksTexturePool.slab(ElsDyeModBlocks.CORALSOIL_BRICK_SLAB);
        coralsoilBricksTexturePool.stairs(ElsDyeModBlocks.CORALSOIL_BRICK_STAIRS);
        coralsoilBricksTexturePool.wall(ElsDyeModBlocks.CORALSOIL_BRICK_WALL);

        // PERIWINKLE - Special
        BlockStateModelGenerator.BlockTexturePool lavenderBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.LAVENDER_BRICKS);
        lavenderBricksTexturePool.slab(ElsDyeModBlocks.LAVENDER_BRICK_SLAB);
        lavenderBricksTexturePool.stairs(ElsDyeModBlocks.LAVENDER_BRICK_STAIRS);
        lavenderBricksTexturePool.wall(ElsDyeModBlocks.LAVENDER_BRICK_WALL);

        BlockStateModelGenerator.BlockTexturePool mossyLavenderBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS);
        mossyLavenderBricksTexturePool.slab(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_SLAB);
        mossyLavenderBricksTexturePool.stairs(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_STAIRS);
        mossyLavenderBricksTexturePool.wall(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_WALL);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.LAVENDER_CLAY);
//        blockStateModelGenerator.registerAxisRotated(ModBlocks.LAVENDER_BUSHEL, TexturedModel.CUBE_BOTTOM_TOP);
        this.registerUpDefaultOrientable(blockStateModelGenerator, ElsDyeModBlocks.LAVENDER_BUSHEL, TexturedModel.CUBE_BOTTOM_TOP);

        blockStateModelGenerator.registerFlowerbed(ElsDyeModBlocks.PERIWINKLE_PETALS);
        blockStateModelGenerator.registerLantern(ElsDyeModBlocks.LAVENDER_OIL_LANTERN);

        blockStateModelGenerator.registerFlowerPotPlant(ElsDyeModBlocks.HIDCOTE_LAVENDER, ElsDyeModBlocks.POTTED_HIDCOTE_LAVENDER, BlockStateModelGenerator.TintType.NOT_TINTED);

        // ARTICHOKE - Special
        blockStateModelGenerator.registerFlowerPotPlant(ElsDyeModBlocks.WAXCAP_MUSHROOM, ElsDyeModBlocks.POTTED_WAXCAP_MUSHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.HANGING_WAXCAP_WAX.asItem()); // So it uses the right item texture
        blockStateModelGenerator.registerTintableCrossBlockState(ElsDyeModBlocks.HANGING_WAXCAP_WAX, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.THISTLE_FLOWER.asItem()); // for some reason this needs to be split up like this to make it's blockItem grab from an item texture without duplicate model errors
        blockStateModelGenerator.registerTintableCrossBlockState(ElsDyeModBlocks.THISTLE_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ElsDyeModBlocks.THISTLE_FLOWER, ElsDyeModBlocks.POTTED_THISTLE_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.WAXCAP_WAX_BLOCK);

        registerDiagonalBlock(blockStateModelGenerator, ElsDyeModBlocks.WAXCAP_GILLS);
        registerDiagonalSlab(blockStateModelGenerator, ElsDyeModBlocks.WAXCAP_GILL_SLAB, ElsDyeModBlocks.WAXCAP_GILLS);

        blockStateModelGenerator.registerMushroomBlock(ElsDyeModBlocks.WAXCAP_CAP_BLOCK);
        blockStateModelGenerator.registerMushroomBlock(ElsDyeModBlocks.WAXCAP_STEM_BLOCK);
        blockStateModelGenerator.registerCrop(ElsDyeModBlocks.ARTICHOKE_CROP, ArtichokeCropBlock.AGE, IntStream.rangeClosed(0, ArtichokeCropBlock.MAX_AGE).toArray());


        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.CACTUS_FEED);
        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.MADDER_ROOTED_DIRT);
        blockStateModelGenerator.registerTintableCrossBlockState(ElsDyeModBlocks.MADDER, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.MADDER);
        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.CINNABAR_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.BUDDING_CINNABAR);
        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.SMALL_CINNABAR_BUD);
        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.MEDIUM_CINNABAR_BUD);
        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.LARGE_CINNABAR_BUD);
        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.CINNABAR_CLUSTER);
        BlockStateModelGenerator.BlockTexturePool cinnamonBricksTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.CINNAMON_BRICKS);
        cinnamonBricksTexturePool.stairs(ElsDyeModBlocks.CINNAMON_BRICK_STAIRS);
        cinnamonBricksTexturePool.slab(ElsDyeModBlocks.CINNAMON_BRICK_SLAB);
        cinnamonBricksTexturePool.wall(ElsDyeModBlocks.CINNAMON_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.CRACKED_CINNAMON_BRICKS);

        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.AMBER_BLOCK);
        BlockStateModelGenerator.BlockTexturePool amberBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ElsDyeModBlocks.AMBER_BRICKS);
        amberBricksTexturePool.slab(ElsDyeModBlocks.AMBER_BRICK_SLAB);
        amberBricksTexturePool.stairs(ElsDyeModBlocks.AMBER_BRICK_STAIRS);
        amberBricksTexturePool.wall(ElsDyeModBlocks.AMBER_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ElsDyeModBlocks.CHISELED_AMBER_BRICKS);

        registerCrossCrop(blockStateModelGenerator, ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP, SavannabudsCropBlock.AGE);
        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS);
        blockStateModelGenerator.registerTintableCrossBlockState(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS, BlockStateModelGenerator.TintType.NOT_TINTED);

        registerCrossCrop(blockStateModelGenerator, ElsDyeModBlocks.PINEAPPLE_STEM, PineappleStemBlock.AGE);
        blockStateModelGenerator.registerTintableCrossBlockState(ElsDyeModBlocks.PINEAPPLE_CROWN, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTintableCross(ElsDyeModBlocks.ALOE, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ElsDyeModBlocks.ALOE, ElsDyeModBlocks.POTTED_ALOE, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerItemModel(ElsDyeModBlocks.STRAWBERRY_PLANT.asItem());
        blockStateModelGenerator.registerTintableCross(ElsDyeModBlocks.CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ElsDyeModBlocks.CORDYLINE, ElsDyeModBlocks.POTTED_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ElsDyeModBlocks.PLUM_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ElsDyeModBlocks.PLUM_CORDYLINE, ElsDyeModBlocks.POTTED_PLUM_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(ElsDyeModBlocks.TALL_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(ElsDyeModBlocks.TALL_PLUM_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTintableCrossBlockState(ElsDyeModBlocks.POKEWEED, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ElsDyeModBlocks.POKEWEED, ElsDyeModBlocks.POTTED_POKEWEED, BlockStateModelGenerator.TintType.NOT_TINTED);

        //Main
// WOODSETS
        for (WoodSet woodSet : ElsDyeModBlocks.WOODSETS) {
            registerBlocksInWoodSet(blockStateModelGenerator, woodSet);
        }

//  SLABS & STAIRS & WALLS
        //not standardized yet

//  FLOWER_POT_BLOCKS & FLOWERS
//        for (Block block : ModBlocks.FLOWER_POT_FROM_BLOCK.keySet()) {
//            registerFlowerPotPlant(blockStateModelGenerator, block, ModBlocks.FLOWER_POT_FROM_BLOCK.get(block), BlockStateModelGenerator.TintType.NOT_TINTED);
////            blockStateModelGenerator.registerFlowerPotPlant(block, ModBlocks.FLOWER_POT_FROM_BLOCK.get(block), BlockStateModelGenerator.TintType.NOT_TINTED);
//        }

//  Spawn Eggs
        Arrays.stream(ElsDyeModRegistryHelper.ItemRegistry.SPAWN_EGG_ITEMS.toArray()).toList().forEach(item -> blockStateModelGenerator.registerParentedItemModel((Item)item, ModelIds.getMinecraftNamespacedItem("template_spawn_egg")));

//  WOOL_BLOCKS     //  CARPET_BLOCKS
        for(Block wool : ElsDyeModBlocks.WOOL_BLOCKS) {
            Block carpet = ElsDyeModBlocks.WOOL_CARPET_FROM_WOOL.get(wool);
            if(carpet != null) { blockStateModelGenerator.registerWoolAndCarpet(wool, carpet);  }
            else { blockStateModelGenerator.registerCubeAllModelTexturePool(wool); }
        }

//  TERRACOTTA_BLOCKS
        for (Block block : ElsDyeModBlocks.DYED_TERRACOTTA_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(block);
        }

//  CONCRETE_BLOCKS
        for (Block block : ElsDyeModBlocks.CONCRETE_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(block);
        }

//  CONCRETE_POWDER_BLOCKS
        for (Block block : ElsDyeModBlocks.CONCRETE_POWDER_BLOCKS) {
            blockStateModelGenerator.registerRandomHorizontalRotations(TexturedModel.CUBE_ALL, block);
        }

//  GLAZED_TERRACOTTA_BLOCKS
        for (Block block : ElsDyeModBlocks.GLAZED_TERRACOTTA_BLOCKS) {
            blockStateModelGenerator.registerSouthDefaultHorizontalFacing(TexturedModel.TEMPLATE_GLAZED_TERRACOTTA, block);
            //registerUpDefaultOrientable(blockStateModelGenerator, block, TexturedModel.CUBE_ALL);
        }

//  STAINED_GLASS_BLOCKS  //  STAINED_GLASS_PANE_BLOCKS
        for(Block glass : ElsDyeModBlocks.STAINED_GLASS_BLOCKS) {
            Block pane = ElsDyeModBlocks.STAINED_GLASS_PANE_FROM_STAINED_GLASS.get(glass);
            if(pane != null) { blockStateModelGenerator.registerGlassPane(glass, pane);  }
            else { blockStateModelGenerator.registerCubeAllModelTexturePool(glass); }
        }

//  SHULKER_BOX_BLOCKS
        for (Block block : ElsDyeModBlocks.SHULKER_BOX_BLOCKS) {
            blockStateModelGenerator.registerShulkerBox(block);
        }

//  BED_BLOCKS
        for (Block block : ElsDyeModBlocks.BED_BLOCKS) {
            blockStateModelGenerator.registerBuiltin(Registries.BLOCK.getId(block), Blocks.OAK_PLANKS)
                    .includeWithItem(Models.TEMPLATE_BED, block);
        }

//  CANDLE_BLOCKS     //  CANDLE_CAKE_BLOCKS
        for(Block candle : ElsDyeModBlocks.CANDLE_BLOCKS) {
            Block cake = ElsDyeModBlocks.CANDLE_CAKE_FROM_CANDLE.get(candle);
            if(cake != null) { blockStateModelGenerator.registerCandle(candle, cake); }
            else { throw new RuntimeException(Registries.BLOCK.getId(candle) + "does not have a candle cake"); }
        }

//  BANNER_BLOCKS     // WALL_BANNER_BLOCKS
        for(Block banner : ElsDyeModBlocks.BANNER_BLOCKS) {
            Block wallBanner = ElsDyeModBlocks.WALL_BANNER_FROM_BANNER.get(banner);
            if(wallBanner != null) {
                blockStateModelGenerator.registerBuiltin(Registries.BLOCK.getId(banner), Blocks.OAK_PLANKS)
                        .includeWithItem(banner)
                        .includeWithoutItem(wallBanner);
            }
            else { throw new RuntimeException(Registries.BLOCK.getId(banner) + "does not have a wall banner"); }
        }

        //Decor Additions
        for(Block block : ElsDyeModBlocks.ALL_CORRUGATED_IRON_BLOCKS) {
            blockStateModelGenerator.registerAxisRotated(block, TexturedModel.CUBE_ALL);
        }

        for(Block block : ElsDyeModBlocks.ALL_MUCKTUFF_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(block);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ElsDyeModItems.MINT_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.MINT_TEA, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.MINT_SPRIG, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.WINTERGREEN_SAP, Models.GENERATED);
//        itemModelGenerator.register(ModItems.WINTERGREEN_BERRIES, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.WINTER_MEDLEY, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.WINTERGREEN_CANDY_CANE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PEPPERMINT_CANDY_CANE, Models.GENERATED);

        itemModelGenerator.register(ElsDyeModItems.PEACH_PIT, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PEACH, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PEACH_SLICE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.GOLDEN_PEACH, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PEACH_COBBLER, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.FRUIT_SALAD, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.AMBROSIA, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.COOKED_ANEMONE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PEACH_BRANCH, Models.GENERATED);

        itemModelGenerator.register(ElsDyeModItems.FLOWERING_MELON, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.SMOKED_LAVENDER, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.LAVENDER_BREAD, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.LAVENDER_SOAP, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.LAVENDER_OIL, Models.GENERATED);

        itemModelGenerator.register(ElsDyeModItems.ARTICHOKE_HEART, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.ARTICHOKE_LAMB, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.BREAKFAST_PORKCHOP, Models.GENERATED);

        itemModelGenerator.register(ElsDyeModItems.COCHINEAL_BEETLE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.CARMINIC_COCHINEAL_BEETLE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.CACTUS_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.MADDER_ROOT, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.CINNABAR, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.POWDERED_CINNABAR, Models.GENERATED);
//        itemModelGenerator.register(ModItems.GAS_BOMB, Models.GENERATED);

        itemModelGenerator.register(ElsDyeModItems.AMBER, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.EMBER, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.EMBER_ARROW, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.SAVANNABUD_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PINEAPPLE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PINEAPPLE_CROWN, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PINEAPPLE_SLICES, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PINEAPPLE_KEBAB, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.PINEAPPLE_TART, Models.GENERATED);

        itemModelGenerator.register(ElsDyeModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.CHOCOLATE_STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.GOLDEN_STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.STRAWBERRY_MILK, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.STRAWBERRY_SHORTCAKE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.STRAWBERRY_CHEESECAKE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.ANGEL_FOOD_CAKE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.CHERRIES, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.CHERRY_PIE, Models.GENERATED);
        itemModelGenerator.register(ElsDyeModBlocks.VELVET_CAKE.asItem(), Models.GENERATED);
        itemModelGenerator.register(ElsDyeModItems.POKEBERRIES, Models.GENERATED);
// Decor Additions
//        for(Item item : DYED_PAPER_ITEMS) {
//            itemModelGenerator.register(item, Models.GENERATED);
//        }

//  DYES
        for(Item item : ItemRegistry.DYE_ITEMS) {
            itemModelGenerator.register(item, Models.GENERATED);
        }

//  BANNERS
        for(Block block : ElsDyeModBlocks.BANNER_BLOCKS) {
            itemModelGenerator.register(block.asItem(), Models.TEMPLATE_BANNER);
        }
        //itemModelGenerator.register(ModItems.MINT_BED, Models.TEMPLATE_BED);
    }
}