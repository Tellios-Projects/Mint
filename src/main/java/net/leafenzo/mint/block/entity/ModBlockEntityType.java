/*
 * Decompiled with CFR 0.2.0 (FabricMC d28b102d).
 */
package net.leafenzo.mint.block.entity;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.types.Type;
import com.mojang.logging.LogUtils;
import java.util.Set;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.BannerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.*;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

/**
 * Represents a type of {@linkplain BlockEntity block entities}.
 * There is one instance of block entity for each placed block entity; this class
 * represents the type of the placed block entities, like chests or furnaces.
 *
 * <p>Block entity types are pre-defined and registered in {@link
 * net.minecraft.registry.Registries#BLOCK_ENTITY_TYPE}. To create a block
 * entity type, the {@linkplain BlockEntityType.Builder#create builder} should be used.
 *
 * <p>Blocks that have corresponding block entities must implement {@link
 * net.minecraft.block.BlockEntityProvider} and list it in the builder of the block
 * entity type. Multiple blocks or block states can be associated with a single block
 * entity type.
 *
 * @see BlockEntity
 * @see net.minecraft.block.BlockEntityProvider
 */
public class ModBlockEntityType<T extends BlockEntity> {
    private static final Logger LOGGER = LogUtils.getLogger();
//    public static final BlockEntityType<ShulkerBoxBlockEntity> MOD_SHULKER_BOX = Registry.register(
//            Registries.BLOCK_ENTITY_TYPE,
//            new Identifier(Super.MOD_ID, "mod_shulker_box"),
//            FabricBlockEntityTypeBuilder.create(ShulkerBoxBlockEntity::new,
//                    ModBlocks.MINT_SHULKER_BOX
//            ).build()
//    );
//    public static final BlockEntityType<BannerBlockEntity> MOD_BANNER = Registry.register(
//            Registries.BLOCK_ENTITY_TYPE,
//            new Identifier(Super.MOD_ID, "mod_banner"),
//            FabricBlockEntityTypeBuilder.create(BannerBlockEntity::new,
//                    ModBlocks.MINT_BANNER
//            ).build()
//    );
//    public static final BlockEntityType<BedBlockEntity> MOD_BED = Registry.register(
//            Registries.BLOCK_ENTITY_TYPE,
//            new Identifier(Super.MOD_ID, "mod_bed"),
//            FabricBlockEntityTypeBuilder.create(BedBlockEntity::new,
//                    ModBlocks.MINT_BED
//            ).build()
//    );
    public static void RegisterModBlockEntityTypes() { ModInit.LOGGER.debug("Registering mod blockEntityTypes for " + Super.MOD_ID);}
}

