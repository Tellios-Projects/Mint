package net.leafenzo.mint.datageneration;

import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.registry.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends FabricTagProvider<Item> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public ModItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, Registries.ITEM.getKey(), registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
    // Vanilla
        getOrCreateTagBuilder(ModTags.Items.DYES)
                .add(Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE, Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE, Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE, Items.PURPLE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE)
        ;
        getOrCreateTagBuilder(ModTags.Items.GOLD_INGOTS)
                .add(Items.GOLD_INGOT);
        ;

    // Special - MINT
        getOrCreateTagBuilder(ItemTags.VILLAGER_PLANTABLE_SEEDS)
                .add(ModItems.MINT_SPRIG)
        ;

    // Main
        for (Block block : ModBlocks.WOOL_CARPET_BLOCKS) {
            getOrCreateTagBuilder(ItemTags.WOOL_CARPETS).add(block.asItem());  // used for adding a carpet to llamas in LlamaEntity.isHorseArmor()
        }

        for (Block block : ModBlocks.SMALL_FLOWERS) {
            getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(block.asItem());
        }

        for (Block block : ModBlocks.CANDLE_BLOCKS) {
            getOrCreateTagBuilder(ItemTags.CANDLES).add(block.asItem());
        }

        for (Item item : ModItems.DYE_ITEMS) {
            getOrCreateTagBuilder(ModTags.Items.DYES).add(item);
        }
    }
}







