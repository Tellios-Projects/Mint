package net.leafenzo.mint.datageneration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.*;
import net.leafenzo.mint.block.custom.*;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.state.property.ModProperties;
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

import java.util.stream.IntStream;

import static net.leafenzo.mint.registration.ModRegistryHelper.*;
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

    public final void registerCoralAnemoneBlock(BlockStateModelGenerator blockStateModelGenerator) {
        Block block = ModBlocks.CORAL_ANEMONE;
        Identifier identifier1 = new Identifier(Super.MOD_ID, "block/coral_anemone");
        Identifier identifier2 = new Identifier(Super.MOD_ID, "block/coral_anemone_waterlogged");
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
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(ModProperties.DIAGONAL, diagonalId, straightId))
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
                .coordinate(BlockStateVariantMap.create(Properties.SLAB_TYPE, ModProperties.DIAGONAL)
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
        blockStateModelGenerator.registerCrop(ModBlocks.MINT_CROP, MintCropBlock.AGE, IntStream.rangeClosed(0, MintCropBlock.MAX_AGE).toArray());
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_SPRIG_BLOCK);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.WILD_MINT, ModBlocks.POTTED_WILD_MINT, BlockStateModelGenerator.TintType.NOT_TINTED);

        BlockStateModelGenerator.BlockTexturePool mintBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.MINT_BRICKS);
        mintBricksTexturePool.slab(ModBlocks.MINT_BRICK_SLAB);
        mintBricksTexturePool.stairs(ModBlocks.MINT_BRICK_STAIRS);
        mintBricksTexturePool.wall(ModBlocks.MINT_BRICK_WALL);
        
        blockStateModelGenerator.registerLog(ModBlocks.WINTERGREEN_CANDY_CANE_BLOCK).log(ModBlocks.WINTERGREEN_CANDY_CANE_BLOCK).wood(ModBlocks.WINTERGREEN_CANDY_CANE_BARK);
        blockStateModelGenerator.registerLog(ModBlocks.PEPPERMINT_CANDY_CANE_BLOCK).log(ModBlocks.PEPPERMINT_CANDY_CANE_BLOCK).wood(ModBlocks.PEPPERMINT_CANDY_CANE_BARK);
//        if(log != null && wood != null) { blockStateModelGenerator.registerLog(log).log(log).wood(wood);  }
//        else if(log != null) { blockStateModelGenerator.registerLog(log).log(log); }

        // PEACH - Special
        this.registerCoralAnemoneBlock(blockStateModelGenerator);
        blockStateModelGenerator.registerItemModel(ModBlocks.PEACH_TREE.asItem());
        this.registerTwoTallCrop(blockStateModelGenerator, ModBlocks.PEACH_TREE, TwoTallCropBlock.AGE);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.PEACH_SAPLING, ModBlocks.POTTED_PEACH_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PEACH_LEAVES);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FLOWERING_PEACH_LEAVES);
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.HYPERICUM, ModBlocks.POTTED_HYPERICUM, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CORALSOIL);
        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CRACKED_CORALSOIL_BRICKS);
        BlockStateModelGenerator.BlockTexturePool coralsoilBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.CORALSOIL_BRICKS);
        coralsoilBricksTexturePool.slab(ModBlocks.CORALSOIL_BRICK_SLAB);
        coralsoilBricksTexturePool.stairs(ModBlocks.CORALSOIL_BRICK_STAIRS);
        coralsoilBricksTexturePool.wall(ModBlocks.CORALSOIL_BRICK_WALL);

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
//        blockStateModelGenerator.registerAxisRotated(ModBlocks.LAVENDER_BUSHEL, TexturedModel.CUBE_BOTTOM_TOP);
        this.registerUpDefaultOrientable(blockStateModelGenerator, ModBlocks.LAVENDER_BUSHEL, TexturedModel.CUBE_BOTTOM_TOP);

        blockStateModelGenerator.registerFlowerbed(ModBlocks.PERIWINKLE_PETALS);
        blockStateModelGenerator.registerLantern(ModBlocks.LAVENDER_OIL_LANTERN);

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.HIDCOTE_LAVENDER, ModBlocks.POTTED_HIDCOTE_LAVENDER, BlockStateModelGenerator.TintType.NOT_TINTED);

        // ARTICHOKE - Special
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.WAXCAP_MUSHROOM, ModBlocks.POTTED_WAXCAP_MUSHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerItemModel(ModBlocks.HANGING_WAXCAP_WAX.asItem()); // So it uses the right item texture
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.HANGING_WAXCAP_WAX, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerItemModel(ModBlocks.THISTLE_FLOWER.asItem()); // for some reason this needs to be split up like this to make it's blockItem grab from an item texture without duplicate model errors
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.THISTLE_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ModBlocks.THISTLE_FLOWER, ModBlocks.POTTED_THISTLE_FLOWER, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.WAXCAP_WAX_BLOCK);

        registerDiagonalBlock(blockStateModelGenerator, ModBlocks.WAXCAP_GILLS);
        registerDiagonalSlab(blockStateModelGenerator, ModBlocks.WAXCAP_GILL_SLAB, ModBlocks.WAXCAP_GILLS);

        blockStateModelGenerator.registerMushroomBlock(ModBlocks.WAXCAP_CAP_BLOCK);
        blockStateModelGenerator.registerMushroomBlock(ModBlocks.WAXCAP_STEM_BLOCK);
        blockStateModelGenerator.registerCrop(ModBlocks.ARTICHOKE_CROP, ArtichokeCropBlock.AGE, IntStream.rangeClosed(0, ArtichokeCropBlock.MAX_AGE).toArray());


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CACTUS_FEED);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MADDER_ROOTED_DIRT);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.MADDER, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerItemModel(ModBlocks.MADDER);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AMBER_BLOCK);
        BlockStateModelGenerator.BlockTexturePool amberBricksTexturePool =
                blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.AMBER_BRICKS);
        amberBricksTexturePool.slab(ModBlocks.AMBER_BRICK_SLAB);
        amberBricksTexturePool.stairs(ModBlocks.AMBER_BRICK_STAIRS);
        amberBricksTexturePool.wall(ModBlocks.AMBER_BRICK_WALL);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_AMBER_BRICKS);

        registerCrossCrop(blockStateModelGenerator, ModBlocks.SHIMMERING_SAVANNABUDS_CROP, SavannabudsCropBlock.AGE);
        blockStateModelGenerator.registerItemModel(ModBlocks.SHIMMERING_SAVANNABUDS);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.SHIMMERING_SAVANNABUDS, BlockStateModelGenerator.TintType.NOT_TINTED);

        registerCrossCrop(blockStateModelGenerator, ModBlocks.PINEAPPLE_STEM, PineappleStemBlock.AGE);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.PINEAPPLE_CROWN, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTintableCross(ModBlocks.ALOE, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ModBlocks.ALOE, ModBlocks.POTTED_ALOE, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerItemModel(ModBlocks.STRAWBERRY_PLANT.asItem());
        blockStateModelGenerator.registerTintableCross(ModBlocks.CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ModBlocks.CORDYLINE, ModBlocks.POTTED_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerTintableCross(ModBlocks.PLUM_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ModBlocks.PLUM_CORDYLINE, ModBlocks.POTTED_PLUM_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(ModBlocks.TALL_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(ModBlocks.TALL_PLUM_CORDYLINE, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.POKEWEED, BlockStateModelGenerator.TintType.NOT_TINTED);
        registerFlowerPot(blockStateModelGenerator, ModBlocks.POKEWEED, ModBlocks.POTTED_POKEWEED, BlockStateModelGenerator.TintType.NOT_TINTED);

        //Main
// WOODSETS
        for (WoodSet woodSet : ModBlocks.WOODSETS) {
            registerBlocksInWoodSet(blockStateModelGenerator, woodSet);
        }

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
        for (Block block : ModBlocks.DYED_TERRACOTTA_BLOCKS) {
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

        //Decor Additions
        for(Block block : ModBlocks.ALL_CORRUGATED_IRON_BLOCKS) {
            blockStateModelGenerator.registerAxisRotated(block, TexturedModel.CUBE_ALL);
        }

        for(Block block : ModBlocks.ALL_MUCKTUFF_BLOCKS) {
            blockStateModelGenerator.registerCubeAllModelTexturePool(block);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.MINT_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINT_TEA, Models.GENERATED);
        itemModelGenerator.register(ModItems.MINT_SPRIG, Models.GENERATED);
        itemModelGenerator.register(ModItems.WINTERGREEN_SAP, Models.GENERATED);
//        itemModelGenerator.register(ModItems.WINTERGREEN_BERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.WINTER_MEDLEY, Models.GENERATED);
        itemModelGenerator.register(ModItems.WINTERGREEN_CANDY_CANE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEPPERMINT_CANDY_CANE, Models.GENERATED);

        itemModelGenerator.register(ModItems.PEACH_PIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_PEACH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_COBBLER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FRUIT_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMBROSIA, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_ANEMONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PEACH_BRANCH, Models.GENERATED);

        itemModelGenerator.register(ModItems.FLOWERING_MELON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_LAVENDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVENDER_BREAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVENDER_SOAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.LAVENDER_OIL, Models.GENERATED);

        itemModelGenerator.register(ModItems.ARTICHOKE_HEART, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARTICHOKE_LAMB, Models.GENERATED);
        itemModelGenerator.register(ModItems.BREAKFAST_PORKCHOP, Models.GENERATED);

        itemModelGenerator.register(ModItems.COCHINEAL_BEETLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARMINIC_COCHINEAL_BEETLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CACTUS_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ModItems.MADDER_ROOT, Models.GENERATED);

        itemModelGenerator.register(ModItems.AMBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.EMBER_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAVANNABUD_SEEDS, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINEAPPLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINEAPPLE_CROWN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINEAPPLE_SLICES, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINEAPPLE_KEBAB, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINEAPPLE_TART, Models.GENERATED);

        itemModelGenerator.register(ModItems.STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOCOLATE_STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_STRAWBERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAWBERRY_MILK, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAWBERRY_SHORTCAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STRAWBERRY_CHEESECAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANGEL_FOOD_CAKE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRIES, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_PIE, Models.GENERATED);
        itemModelGenerator.register(ModBlocks.VELVET_CAKE.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModItems.POKEBERRIES, Models.GENERATED);
// Decor Additions
//        for(Item item : DYED_PAPER_ITEMS) {
//            itemModelGenerator.register(item, Models.GENERATED);
//        }

//  DYES
        for(Item item : ItemRegistry.DYE_ITEMS) {
            itemModelGenerator.register(item, Models.GENERATED);
        }

//  BANNERS
        for(Block block : ModBlocks.BANNER_BLOCKS) {
            itemModelGenerator.register(block.asItem(), Models.TEMPLATE_BANNER);
        }
        //itemModelGenerator.register(ModItems.MINT_BED, Models.TEMPLATE_BED);
    }
}