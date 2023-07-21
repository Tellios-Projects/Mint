package net.leafenzo.mint.block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.item.ModItemGroups;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final Block MINT = registerBlockWithoutBlockItem("mint", new MintCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.LICHEN_GREEN)));
    public static final Block MINT_WOOL = registerBlock("mint_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE = registerBlock("mint_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_TERRACOTTA = registerBlock("mint_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_GLAZED_TERRACOTTA = registerBlock("mint_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS = registerBlock("mint_stained_glass", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    //public static final Block MINT_STAINED_GLASS_PANE = registerBlock("mint_stained_glass_pane", new StainedGlassPaneBlock(DyeColor.MINT, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    
    public static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name,block,group);
        return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
    }

    public static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
    }

    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }

    public static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    private static ToIntFunction<BlockState> createLightLevelFromProperty(int litLevel, BooleanProperty property) {
        return state -> state.get(property) != false ? litLevel : 0;
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
        //ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(blockItem));
        return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), blockItem);
    }

    public static void registerModBlocks() {
        ModInit.LOGGER.debug("Registering mod blocks for " + Super.MOD_ID);
    }
}
