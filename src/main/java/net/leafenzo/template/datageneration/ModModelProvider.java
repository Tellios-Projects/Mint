package net.leafenzo.template.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.leafenzo.template.Super;
import net.leafenzo.template.block.ModBlocks;
import net.leafenzo.template.data.client.ModTexturedModel;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

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

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    // Block Models
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

     // BlockItem Models, every block model needs one of these
        //Copy Parent
        //blockStateModelGenerator.registerParentedItemModel(ModBlocks.BLAZE_ROD_BLOCK, Super.asResource("block/blaze_rod_block"));
    }
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}