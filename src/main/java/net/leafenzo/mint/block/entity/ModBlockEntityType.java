///*
// * Decompiled with CFR 0.2.0 (FabricMC d28b102d).
// */
//package net.leafenzo.mint.block.entity;
//
//import com.google.common.collect.ImmutableSet;
//import com.mojang.datafixers.types.Type;
//import com.mojang.logging.LogUtils;
//import java.util.Set;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.entity.*;
//import net.minecraft.datafixer.TypeReferences;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.Registry;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.Util;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.BlockView;
//import org.jetbrains.annotations.Nullable;
//import org.slf4j.Logger;
//
///**
// * Represents a type of {@linkplain BlockEntity block entities}.
// * There is one instance of block entity for each placed block entity; this class
// * represents the type of the placed block entities, like chests or furnaces.
// *
// * <p>Block entity types are pre-defined and registered in {@link
// * net.minecraft.registry.Registries#BLOCK_ENTITY_TYPE}. To create a block
// * entity type, the {@linkplain BlockEntityType.Builder#create builder} should be used.
// *
// * <p>Blocks that have corresponding block entities must implement {@link
// * net.minecraft.block.BlockEntityProvider} and list it in the builder of the block
// * entity type. Multiple blocks or block states can be associated with a single block
// * entity type.
// *
// * @see BlockEntity
// * @see net.minecraft.block.BlockEntityProvider
// */
//public class ModBlockEntityType<T extends BlockEntity> {
//    private static final Logger LOGGER = LogUtils.getLogger();
//
//    public static final ModBlockEntityType<BannerBlockEntity> BANNER = BlockEntityType.create("banner", Builder.create(BannerBlockEntity::new, Blocks.WHITE_BANNER, Blocks.ORANGE_BANNER, Blocks.MAGENTA_BANNER, Blocks.LIGHT_BLUE_BANNER, Blocks.YELLOW_BANNER, Blocks.LIME_BANNER, Blocks.PINK_BANNER, Blocks.GRAY_BANNER, Blocks.LIGHT_GRAY_BANNER, Blocks.CYAN_BANNER, Blocks.PURPLE_BANNER, Blocks.BLUE_BANNER, Blocks.BROWN_BANNER, Blocks.GREEN_BANNER, Blocks.RED_BANNER, Blocks.BLACK_BANNER, Blocks.WHITE_WALL_BANNER, Blocks.ORANGE_WALL_BANNER, Blocks.MAGENTA_WALL_BANNER, Blocks.LIGHT_BLUE_WALL_BANNER, Blocks.YELLOW_WALL_BANNER, Blocks.LIME_WALL_BANNER, Blocks.PINK_WALL_BANNER, Blocks.GRAY_WALL_BANNER, Blocks.LIGHT_GRAY_WALL_BANNER, Blocks.CYAN_WALL_BANNER, Blocks.PURPLE_WALL_BANNER, Blocks.BLUE_WALL_BANNER, Blocks.BROWN_WALL_BANNER, Blocks.GREEN_WALL_BANNER, Blocks.RED_WALL_BANNER, Blocks.BLACK_WALL_BANNER));
//    public static final ModBlockEntityType<ShulkerBoxBlockEntity> SHULKER_BOX = BlockEntityType.create("shulker_box", Builder.create(ShulkerBoxBlockEntity::new, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX));
//    public static final ModBlockEntityType<BedBlockEntity> BED = BlockEntityType.create("bed", Builder.create(BedBlockEntity::new, Blocks.RED_BED, Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, Blocks.PINK_BED, Blocks.PURPLE_BED, Blocks.WHITE_BED, Blocks.YELLOW_BED));
//    private final BlockEntityFactory<? extends T> factory;
//    private final Set<Block> blocks;
//    private final Type<?> type;
//
//    /**
//     * {@return the block entity type's ID, or {@code null} if it is unregistered}
//     *
//     * <p>This should never return {@code null} under normal circumstances.
//     */
//    @Nullable
//    public static Identifier getId(BlockEntityType<?> type) {
//        return Registries.BLOCK_ENTITY_TYPE.getId(type);
//    }
//
//    private static <T extends BlockEntity> BlockEntityType<T> create(String id, Builder<T> builder) {
//        if (builder.blocks.isEmpty()) {
//            LOGGER.warn("Block entity type {} requires at least one valid block to be defined!", (Object)id);
//        }
//        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
//        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build(type));
//    }
//
//    public ModBlockEntityType(BlockEntityFactory<? extends T> factory, Set<Block> blocks, Type<?> type) {
//        this.factory = factory;
//        this.blocks = blocks;
//        this.type = type;
//    }
//
//    /**
//     * {@return a new instance of the block entity}
//     *
//     * @see BlockEntityType.BlockEntityFactory
//     */
//    @Nullable
//    public T instantiate(BlockPos pos, BlockState state) {
//        return this.factory.create(pos, state);
//    }
//
//    /**
//     * {@return whether the block entity type supports {@code state}}
//     *
//     * <p>The block, not the block state, determines the corresponding block entity type;
//     * therefore, for states of the same block, the return value is the same.
//     */
//    public boolean supports(BlockState state) {
//        return this.blocks.contains(state.getBlock());
//    }
//
//    /**
//     * {@return the block entity instance of this type at {@code pos}, or {@code null} if
//     * no such block entity exists}
//     *
//     * @see BlockView#getBlockEntity
//     */
//    @Nullable
//    public T get(BlockView world, BlockPos pos) {
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//        if (blockEntity == null || blockEntity.getType() != this) {
//            return null;
//        }
//        return (T)blockEntity;
//    }
//
//    public static final class Builder<T extends BlockEntity> {
//        private final BlockEntityFactory<? extends T> factory;
//        final Set<Block> blocks;
//
//        private Builder(BlockEntityFactory<? extends T> factory, Set<Block> blocks) {
//            this.factory = factory;
//            this.blocks = blocks;
//        }
//
//        public static <T extends BlockEntity> Builder<T> create(BlockEntityFactory<? extends T> factory, Block ... blocks) {
//            return new Builder<T>(factory, ImmutableSet.copyOf(blocks));
//        }
//
//        public BlockEntityType<T> build(Type<?> type) {
//            return new BlockEntityType<T>(this.factory, this.blocks, type);
//        }
//    }
//
//    @FunctionalInterface
//    public static interface BlockEntityFactory<T extends BlockEntity> {
//        public T create(BlockPos var1, BlockState var2);
//    }
//}
//
