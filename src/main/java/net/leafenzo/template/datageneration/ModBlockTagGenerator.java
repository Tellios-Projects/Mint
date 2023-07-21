package net.leafenzo.template.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.leafenzo.template.block.ModBlocks;
import net.leafenzo.template.registry.tag.ModBlockTags;
import net.minecraft.block.Block;
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


   //BlockTags & ItemTags are vanilla

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                //.add(ModBlocks.)
             ;

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.LEAVES)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.WOOL)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.CRYSTAL_SOUND_BLOCKS)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.INFINIBURN_OVERWORLD)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.SOUL_FIRE_BASE_BLOCKS)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.SOUL_SPEED_BLOCKS)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.WITHER_IMMUNE)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.GUARDED_BY_PIGLINS)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.CLIMBABLE)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.SAND)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.DIRT)
        //.add(ModBlocks.)
        ;

        getOrCreateTagBuilder(BlockTags.DEAD_BUSH_MAY_PLACE_ON)
        //.add(ModBlocks.)
        ;
    }
}

